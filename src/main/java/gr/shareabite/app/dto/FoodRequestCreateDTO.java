package gr.shareabite.app.dto;

import gr.shareabite.app.core.enums.Region;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodRequestCreateDTO {

    @NotNull(message = "You have to choose the region")
    private Region region;

    @Valid
    @NotNull(message = "Choose at least one item.")
    @Size(min = 1, message = "You must add at least one requested item.")
    private List<RequestedItemsCreateDTO> requestedItemList = new ArrayList<>();
}
