package gr.shareabite.app.service.implementation;

import gr.shareabite.app.dto.PasswordChangeDTO;
import gr.shareabite.app.dto.UserEditDTO;
import gr.shareabite.app.dto.UserInsertDTO;
import gr.shareabite.app.dto.UserRegisterDTO;
import gr.shareabite.app.enums.Role;
import gr.shareabite.app.exception.EntityAlreadyExistsException;
import gr.shareabite.app.exception.NotExistingEntityException;
import gr.shareabite.app.mapper.Mapper;
import gr.shareabite.app.model.User;
import gr.shareabite.app.repository.UserRepository;
import gr.shareabite.app.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

//FIX MY SERVICEEEEEEEEEEEE!!!!!!!!
public class UserServiceImpl implements IUserService {

    //Dependency injection
    private final UserRepository userRepository;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;
    //private final RoleRepository roleRepository;

    //override my interfaces!!!!!
    @Override
    //if any exception happens, the transaction is canceled
    @Transactional(rollbackOn = Exception.class)
    public void registerUser(UserRegisterDTO userRegisterDTO) throws EntityAlreadyExistsException{

        String username = userRegisterDTO.getUsername().trim();
        String email = userRegisterDTO.getEmail().trim().toLowerCase();

            if (userRepository.existsByUsername(username)) {
                throw new EntityAlreadyExistsException("User", "User with username: " + username +
                         " already exists.");
            }
            if (userRepository.existsByEmail(email)) {
                 throw new EntityAlreadyExistsException("User", "User with email: " + email + " already exists.");
            }

            User user = mapper.mapToEntity(userRegisterDTO);
            user.setUsername(username);
            user.setEmail(email);
            user.setRole(Role.USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Αποθήκευση στη βάση
            userRepository.save(user);
            log.info("User with username={} and email={} has registered successfully.", username, email);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void editUser(UserEditDTO userEditDTO)
            throws NotExistingEntityException, EntityAlreadyExistsException {

        User user = userRepository.findByUsername(userEditDTO.getUsername())
                .orElseThrow(() -> new NotExistingEntityException(
                        "User", "User with username: " + userEditDTO.getUsername() + " does not exist."));

        if (userEditDTO.getEmail() != null && !userEditDTO.getEmail().isBlank()) {
            var owner = userRepository.findByEmail(userEditDTO.getEmail().trim().toLowerCase());
            if (owner.isPresent() && !owner.get().getUsername().equals(userEditDTO.getUsername())) {
                throw new EntityAlreadyExistsException("User", "Email already in use by another account.");
            }
        }

        mapper.applyEdits(user, userEditDTO);
        userRepository.save(user);
        log.info("User with username={} updated successfully.", userEditDTO.getUsername());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void saveUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException {
            if (userRepository.existsByEmail(userInsertDTO.getEmail()) ||
                    userRepository.existsByUsername(userInsertDTO.getUsername())) {
                throw new EntityAlreadyExistsException("User", "User with username: " +userInsertDTO.getUsername()
                        + " and email: " + userInsertDTO.getEmail() + " Already exists.");
            }
            User user = mapper.mapToEntity(userInsertDTO);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.save(user);
            log.info("User with username={} and email={} has been saved successfully.", userInsertDTO.getUsername(), userInsertDTO.getEmail());
    }

    @Override
    @Transactional
    public void changePassword(Long userId, PasswordChangeDTO passwordChangeDTO) throws NotExistingEntityException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotExistingEntityException("User", "User not found"));

        // 1) Check old password matches stored password
        if (!passwordEncoder.matches(passwordChangeDTO.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect.");
        }

        // 2) Check new == confirm
        if (!passwordChangeDTO.getNewPassword().equals(passwordChangeDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("New password and confirmation do not match.");
        }

        // 3) Encode new password and set it on the SAME field
        String encodedNewPassword = passwordEncoder.encode(passwordChangeDTO.getNewPassword());
        user.setPassword(encodedNewPassword);

        // 4) Save updates
        userRepository.save(user);
    }
}
