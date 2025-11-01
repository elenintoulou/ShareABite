package gr.shareabite.app.dto;

import gr.shareabite.app.enums.Region;
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
public class UserEditDTO {

    @Size(min = 2, max = 30, message = "The username must be between 2 - 30 characters.")
    private String username;

    @NotNull(message = "The region cannot be null.")
    private Region region;

    @NotNull(message = "The email cannot be null.")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Invalid email address.")
    private String email;

    @Pattern(
            regexp = "^(\\+\\d{1,3}[- ]?)?\\d{7,15}$",
            message = "Invalid phone number.")
    private String phoneNumber;

}
