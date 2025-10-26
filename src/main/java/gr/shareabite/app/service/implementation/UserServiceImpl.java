package gr.shareabite.app.service.implementation;

import gr.shareabite.app.dto.UserEditDTO;
import gr.shareabite.app.dto.UserInsertDTO;
import gr.shareabite.app.dto.UserRegisterDTO;
import gr.shareabite.app.exceptions.EntityAlreadyExistsException;
import gr.shareabite.app.exceptions.NotExistingEntityException;
import gr.shareabite.app.model.User;
import gr.shareabite.app.repository.RoleRepository;
import gr.shareabite.app.repository.UserRepository;
import gr.shareabite.app.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    //Dependency injection
    private final UserRepository userRepository;
    private final Mapper mapper;
    //i should add a class for authentication
    private final RoleRepository roleRepository;

    //override my interfaces

    @Override
    //if any exception happens, the transaction is canceled
    @Transactional(rollbackOn = Exception.class)
    public void registerUser(UserRegisterDTO userRegisterDTO) throws EntityAlreadyExistsException {
        try {
//            if (userRepository.findByUsername(userRegisterDTO.getUsername()).isPresent() ||
//                    userRepository.findByEmail(userRegisterDTO.getEmail()).isPresent())
//                throw EntityAlreadyExistsException("User", "User with username: " + userRegisterDTO.getUsername() +
//                        " and email: " + userRegisterDTO.getEmail() + " already exists.");
//
//            User user = mapper.mapToUserEntity



        } catch (){

        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void editUser(UserEditDTO userEditDTO) throws NotExistingEntityException {

    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void saveUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException {

    }
}
