package gr.shareabite.app.mapper;

import gr.shareabite.app.dto.UserEditDTO;
import gr.shareabite.app.dto.UserInsertDTO;
import gr.shareabite.app.dto.UserReadOnlyDTO;
import gr.shareabite.app.dto.UserRegisterDTO;
import gr.shareabite.app.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getId(), user.getUuid(), user.getUsername(), user.getEmail(),
                user.getPhoneNumber(), user.getRole(), user.getRegion(), user.getCreatedAt(), user.getUpdatedAt());
    }

    //to create a new User from the dto
    public User mapToEntity(UserRegisterDTO userRegisterDTO) {
        return User.builder()
                .username(userRegisterDTO.getUsername())
                .password(userRegisterDTO.getPassword())
                .email(userRegisterDTO.getEmail())
                .region(userRegisterDTO.getRegion())
                .build();
    }
}
