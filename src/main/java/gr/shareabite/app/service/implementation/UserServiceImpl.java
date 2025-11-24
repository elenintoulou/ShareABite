package gr.shareabite.app.service.implementation;

import gr.shareabite.app.dto.*;
import gr.shareabite.app.core.enums.Role;
import gr.shareabite.app.core.exception.EntityAlreadyExistsException;
import gr.shareabite.app.core.exception.NotExistingEntityException;
import gr.shareabite.app.mapper.Mapper;
import gr.shareabite.app.model.User;
import gr.shareabite.app.repository.UserRepository;
import gr.shareabite.app.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    private final
    //private final RoleRepository roleRepository;
    //override my interfaces!!!!!
    @Override
    //if any exception happens, the transaction is canceled

    //!!!!!ADD TRY CATCHES!!!!!AND NOT FORGET TO ADD IN THE CATCH THROW e!!!!!!!!
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
    public void editUser(String userUuid, UserEditDTO userEditDTO)
            throws NotExistingEntityException, EntityAlreadyExistsException {


        User user = userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new NotExistingEntityException("User", "Not found"));

        String newEmail = (userEditDTO.getEmail() == null) ? null
                : userEditDTO.getEmail().trim().toLowerCase();

        if (newEmail != null && !newEmail.equals(user.getEmail())) {
            boolean taken = userRepository.existsByEmailIgnoreCaseAndIdNot(newEmail, user.getId());
            if (taken) throw new EntityAlreadyExistsException("User", "Email already in use");
            user.setEmail(newEmail);
        }

        // username uniqueness (if editable)
        if (userEditDTO.getUsername() != null && !userEditDTO.getUsername().equals(user.getUsername())) {
            boolean taken = userRepository.existsByUsernameAndIdNot(userEditDTO.getUsername(), user.getId());
            if (taken) throw new EntityAlreadyExistsException("User", "Username already in use");
            user.setUsername(userEditDTO.getUsername());
        }


        mapper.applyEdits(user, userEditDTO);
        userRepository.save(user);
        log.info("User with email={} updated successfully.", userEditDTO.getEmail());
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

    @Override
    public Page<UserReadOnlyDTO> getPaginatedUsers(int page, int size) {
        return null;
    }
}
