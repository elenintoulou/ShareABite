package gr.shareabite.app.service.implementation;

import gr.shareabite.app.dto.*;
import gr.shareabite.app.core.enums.Role;
import gr.shareabite.app.core.exception.EntityAlreadyExistsException;
import gr.shareabite.app.core.exception.NotExistingEntityException;
import gr.shareabite.app.mapper.UserMapper;
import gr.shareabite.app.model.User;
import gr.shareabite.app.repository.UserRepository;
import gr.shareabite.app.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

//FIX MY SERVICEEEEEEEEEEEE!!!!!!!!
public class UserServiceImpl implements IUserService {

    //Dependency injection
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
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
    public UserEditDTO getCurrentUserForEdit() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found: " + username));

        UserEditDTO userEditDTO = new UserEditDTO();
        userEditDTO.setUsername(user.getUsername());
        userEditDTO.setEmail(user.getEmail());
        userEditDTO.setRegion(user.getRegion());
        return userEditDTO;
    }

    @Override
    @Transactional
    public void updateCurrentUser(UserEditDTO userEditDTO) throws EntityAlreadyExistsException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new IllegalStateException("User not found: " + currentUsername));

        // Optional: checks for unique username/email
        if (!user.getUsername().equals(userEditDTO.getUsername())
                && userRepository.existsByUsername(userEditDTO.getUsername())) {
            throw new EntityAlreadyExistsException("User", "username");
        }

        if (!user.getEmail().equals(userEditDTO.getEmail())
                && userRepository.existsByEmail(userEditDTO.getEmail())) {
            throw new EntityAlreadyExistsException("User", "email");
        }

        //user.setUsername(userEditDTO.getUsername());
        user.setEmail(userEditDTO.getEmail());
        user.setRegion(userEditDTO.getRegion());
        user.setPhoneNumber(userEditDTO.getPhoneNumber());

        userRepository.save(user);
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
        Pageable pageable = PageRequest.of(page, size);
        Page<User> pageResult = userRepository.findAll(pageable);
        List<UserReadOnlyDTO> dtoList = pageResult.getContent()
                .stream()
                .map(mapper::mapToUserReadOnlyDTO)
                .toList();
        return new PageImpl<>(dtoList, pageable, pageResult.getTotalElements());
    }
}


