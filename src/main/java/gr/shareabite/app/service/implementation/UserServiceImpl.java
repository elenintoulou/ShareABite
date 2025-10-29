package gr.shareabite.app.service.implementation;

import gr.shareabite.app.dto.UserEditDTO;
import gr.shareabite.app.dto.UserInsertDTO;
import gr.shareabite.app.dto.UserRegisterDTO;
import gr.shareabite.app.exception.EntityAlreadyExistsException;
import gr.shareabite.app.exception.NotExistingEntityException;
import gr.shareabite.app.model.User;
import gr.shareabite.app.repository.UserRepository;
import gr.shareabite.app.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

//FIX MY SERVICEEEEEEEEEEEE!!!!!!!!
public class UserServiceImpl implements IUserService {

    //Dependency injection
    private final UserRepository userRepository;
  //add a mapper
    //i should add a class for authentication
    //private final RoleRepository roleRepository;

    //override my interfaces

    @Override
    //if any exception happens, the transaction is canceled
    @Transactional(rollbackOn = Exception.class)
    public void registerUser(UserRegisterDTO userRegisterDTO) throws EntityAlreadyExistsException{
            if (userRepository.existsByUsername(userRegisterDTO.getUsername())||
                    userRepository.existsByEmail(userRegisterDTO.getEmail())) {
                throw new EntityAlreadyExistsException("User", "User with username: " + userRegisterDTO.getUsername() +
                        " and email: " + userRegisterDTO.getEmail() + " already exists.");
            }
            userRepository.save(new User());
            log.info("User with username={} and email={} has registered successfully.", userRegisterDTO.getUsername(), userRegisterDTO.getEmail());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void editUser(UserEditDTO userEditDTO) throws NotExistingEntityException, EntityAlreadyExistsException {
            if (!userRepository.existsByUsername(userEditDTO.getUsername())) {
                throw new NotExistingEntityException("User", "User with username: " + userEditDTO.getUsername()
                               + " does not exist.");
            }
            //check if email doesn't belong to someone else
            var owner = userRepository.findByEmail(userEditDTO.getEmail());
            if (owner.isPresent() && !owner.get().getUsername().equals(userEditDTO.getUsername())) {
                throw new EntityAlreadyExistsException("User", "User with username: " +userEditDTO.getUsername() + " and email: " +userEditDTO.getEmail() + " already exists.");
            }

//            userRepository.save(userRepository.findByUsername(userEditDTO.getUsername()).);
            log.info("User with username={} has updated successfully.", userEditDTO.getUsername());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void saveUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException {
            if (userRepository.existsByEmail(userInsertDTO.getEmail()) ||
                    userRepository.existsByUsername(userInsertDTO.getUsername())) {
                throw new EntityAlreadyExistsException("User", "User with username: " +userInsertDTO.getUsername()
                        + " and email: " + userInsertDTO.getEmail() + " Already exists.");
            }
            userRepository.save(new User());
            log.info("User with username={} and email={} has been saved successfully.", userInsertDTO.getUsername(), userInsertDTO.getEmail());
    }
}
