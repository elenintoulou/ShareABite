package gr.shareabite.app.service.interfaces;

import gr.shareabite.app.dto.*;
import gr.shareabite.app.core.exception.EntityAlreadyExistsException;

public interface IUserService {

    void registerUser(UserRegisterDTO userRegisterDTO) throws EntityAlreadyExistsException;
    UserEditDTO getCurrentUserForEdit();
    void updateCurrentUser(UserEditDTO userEditDTO) throws EntityAlreadyExistsException;
    UserReadOnlyDTO getUserDetails();
}
