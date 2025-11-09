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
        return new UserEditDTO(user.getRegion(), user.getEmail());
    }

    //!!!!!!!!!!!!μηπωσ να το σβησω απο εδω γτ εχει λιγη λογικη μεσα? και να το παω στο service?!!!!!!
    public void applyEdits(User user, UserEditDTO userEditDTO) {
        if (userEditDTO.getEmail() != null && !userEditDTO.getEmail().isBlank()) {
            user.setEmail(userEditDTO.getEmail());
        }

        if (userEditDTO.getRegion() != null) {
            user.setRegion(userEditDTO.getRegion());
        }
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
    //to "update" my existing user with the dto data!!!!
    public void applyRegistration(User user, UserRegisterDTO userRegisterDTO) {
        user.setEmail(userRegisterDTO.getEmail());
        user.setRegion(userRegisterDTO.getRegion());
    }
}
