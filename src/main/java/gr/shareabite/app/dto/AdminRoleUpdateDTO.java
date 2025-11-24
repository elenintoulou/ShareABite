package gr.shareabite.app.dto;

import gr.shareabite.app.core.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AdminRoleUpdateDTO {

    @NotNull
    private Long id;

    @NotNull(message = "Role cannot be null.")
    private Role role;
}
