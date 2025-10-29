package gr.shareabite.app.mapper;

import gr.shareabite.app.dto.UserEditDTO;
import gr.shareabite.app.dto.UserInsertDTO;
import gr.shareabite.app.dto.UserReadOnlyDTO;
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

    public void applyEdits(User u, UserEditDTO userEditDTO) {
        u.setEmail(userEditDTO.getEmail());
        u.setPhoneNumber(userEditDTO.getPhoneNumber());
        u.setRegion(userEditDTO.getRegion());
        u.setRole(userEditDTO.getRole());
        if (userEditDTO.getPassword() != null && !userEditDTO.getPassword().isBlank()) {
            u.setPassword(userEditDTO.getPassword()); // TODO: encode όταν βάλεις security
        }
    }
}
