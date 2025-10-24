package gr.shareabite.app.dto;

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
public class UserSignInDTO {

    @NotNull(message = "The username cannot not be null.")
    @Size(min = 2, max = 30, message = "The username must be between 2 - 30 characters.")
    private String username;

    @NotNull(message = "The password cannot be null.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])^.{8,}$",
            message = "The password must have at least 1 small character, 1 capital character, " +
                    "1 number and 1 symbol without spaces. ")
    private String password;
}
