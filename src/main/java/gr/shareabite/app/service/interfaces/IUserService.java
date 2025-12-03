package gr.shareabite.app.service.interfaces;

import gr.shareabite.app.dto.*;
import gr.shareabite.app.core.exception.EntityAlreadyExistsException;
import gr.shareabite.app.core.exception.NotExistingEntityException;
import org.springframework.data.domain.Page;

public interface IUserService {

    void saveUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException;
    void registerUser(UserRegisterDTO userRegisterDTO) throws EntityAlreadyExistsException;
    void changePassword(Long userId, PasswordChangeDTO passwordChangeDTO) throws NotExistingEntityException;
    Page<UserReadOnlyDTO> getPaginatedUsers(int page, int size);
    UserEditDTO getCurrentUserForEdit();
    void updateCurrentUser(UserEditDTO userEditDTO) throws EntityAlreadyExistsException;
    UserReadOnlyDTO getUserDetails();
}
