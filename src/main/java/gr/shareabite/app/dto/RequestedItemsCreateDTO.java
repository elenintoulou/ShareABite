package gr.shareabite.app.dto;

import gr.shareabite.app.core.enums.FoodItems;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RequestedItemsCreateDTO {

    @NotNull(message="The field must not be null")
    private FoodItems foodItems;

    @Positive(message="The quantity must be a positive number")
    private int quantity;

    @NotNull(message="The unit must not be null")
    @NotBlank(message="The unit must not be blank")
    private String unit;
}
