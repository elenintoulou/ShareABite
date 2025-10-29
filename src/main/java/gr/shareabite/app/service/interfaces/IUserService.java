package gr.shareabite.app.service.interfaces;

import gr.shareabite.app.dto.UserEditDTO;
import gr.shareabite.app.dto.UserInsertDTO;
import gr.shareabite.app.dto.UserRegisterDTO;
import gr.shareabite.app.exception.EntityAlreadyExistsException;
import gr.shareabite.app.exception.NotExistingEntityException;

public interface IUserService {

    void saveUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException;
    void registerUser(UserRegisterDTO userRegisterDTO) throws EntityAlreadyExistsException;
    void editUser(UserEditDTO userEditDTO) throws NotExistingEntityException, EntityAlreadyExistsException;
}
