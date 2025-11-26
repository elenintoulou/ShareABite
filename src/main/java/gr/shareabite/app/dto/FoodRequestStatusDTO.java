package gr.shareabite.app.dto;

import gr.shareabite.app.core.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FoodRequestStatusDTO {
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;
}
