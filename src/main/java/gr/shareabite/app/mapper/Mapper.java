package gr.shareabite.app.mapper;

import gr.shareabite.app.dto.UserEditDTO;
import gr.shareabite.app.dto.UserInsertDTO;
import gr.shareabite.app.dto.UserReadOnlyDTO;
import gr.shareabite.app.dto.UserRegisterDTO;
import gr.shareabite.app.model.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public User mapToEntity(UserInsertDTO userInsertDTO) {
        return User.builder()
                .username(userInsertDTO.getUsername())
                .password(userInsertDTO.getPassword())
                .email(userInsertDTO.getEmail())
                .phoneNumber(userInsertDTO.getPhoneNumber())
                .region(userInsertDTO.getRegion())
                .role(userInsertDTO.getRole())
                .build();
    }

    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getId(), user.getUuid(), user.getUsername(), user.getEmail(),
                user.getPhoneNumber(),  user.getRole(), user.getRegion(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public UserEditDTO mapToUserEditDTO(User user) {
        return new UserEditDTO(user.getUsername(), user.getPassword(), user.getRole(), user.getRegion(), user.getEmail()
                , user.getPhoneNumber());
    }

    public void applyEdits(User user, UserEditDTO userEditDTO) {
        user.setEmail(userEditDTO.getEmail());
        user.setPhoneNumber(userEditDTO.getPhoneNumber());
        user.setRegion(userEditDTO.getRegion());
        user.setRole(userEditDTO.getRole());
        if (userEditDTO.getPassword() != null && !userEditDTO.getPassword().isBlank()) {
            user.setPassword(userEditDTO.getPassword()); // TODO: encode όταν βάλεις security
        }
    }

    public UserRegisterDTO mapToUserRegisterDTO(User user) {
        return new UserRegisterDTO(user.getUsername(), user.getPassword(), user.getEmail(), user.getRegion());
    }

    public void applyRegistration(User user, UserRegisterDTO userRegisterDTO) {
        user.setEmail(userRegisterDTO.getEmail());
        user.setRegion(userRegisterDTO.getRegion());
        if (userRegisterDTO.getPassword() != null && !userRegisterDTO.getPassword().isBlank()) {
            user.setPassword(userRegisterDTO.getPassword()); // TODO: encode όταν βάλεις security
        }
    }
    public User mapToEntity(UserRegisterDTO userRegisterDTO) {
        return User.builder()
                .username(userRegisterDTO.getUsername())
                .password(userRegisterDTO.getPassword())
                .email(userRegisterDTO.getEmail())
                .region(userRegisterDTO.getRegion())
                .build();
    }
}
