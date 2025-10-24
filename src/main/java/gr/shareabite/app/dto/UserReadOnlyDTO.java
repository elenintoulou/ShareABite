package gr.shareabite.app.dto;

import gr.shareabite.app.model.static_data.Region;
import gr.shareabite.app.model.static_data.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReadOnlyDTO {

    private Long id;
    private Long uuid;
    private String username;
    private String email;
    private String phoneNumber;
    private Role role;
    private Region region;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
