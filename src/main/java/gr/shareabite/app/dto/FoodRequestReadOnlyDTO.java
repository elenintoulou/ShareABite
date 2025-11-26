package gr.shareabite.app.dto;

import gr.shareabite.app.core.enums.Region;
import gr.shareabite.app.core.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FoodRequestReadOnlyDTO {

    private Long id;
    private Status status;
    private Region region;
    private String username;
    private LocalDateTime createdAt;
    private List<RequestedItemsReadOnlyDTO> requestedItems;
}
