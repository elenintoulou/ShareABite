package gr.shareabite.app.dto;

import gr.shareabite.app.core.enums.Region;
import gr.shareabite.app.core.enums.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO {

    @NotNull(message = "The username cannot not be null.")
    @Size(min = 2, max = 30, message = "The username must be between 2 - 30 characters.")
    private String username;

    @NotNull(message = "The password cannot be null.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])^.{8,}$",
            message = "The password must have at least 1 small character, 1 capital character, " +
                    "1 number and 1 symbol without spaces. ")
    private String password;

    @NotNull(message = "The email cannot be null.")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Invalid email address.")
    private String email;

    @Pattern(
            regexp = "^(\\+\\d{1,3}[- ]?)?\\d{7,15}$",
            message = "Invalid phone number.")
    private String phoneNumber;

    @NotNull(message = "The role cannot be null.")
    private Role role;

    @NotNull(message = "The region cannot be null.")
    private Region region;
}
