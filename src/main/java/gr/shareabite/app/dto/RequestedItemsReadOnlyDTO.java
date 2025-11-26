package gr.shareabite.app.dto;

import gr.shareabite.app.core.enums.FoodItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestedItemsReadOnlyDTO {

    private Long id;
    private int quantity;
    private String unit;
    private FoodItems foodItems;
}
