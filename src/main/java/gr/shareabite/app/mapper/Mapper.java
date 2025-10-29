package gr.shareabite.app.mapper;

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
}
