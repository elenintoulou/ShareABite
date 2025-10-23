package gr.shareabite.app.service.interfaces;

import gr.shareabite.app.dto.UserInsertDTO;
import gr.shareabite.app.exceptions.EntityAlreadyExistsException;

public interface IUserService {

    void saveUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException;
}
