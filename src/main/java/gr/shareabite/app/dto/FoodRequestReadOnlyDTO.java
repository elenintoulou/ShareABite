package gr.shareabite.app.dto;

import gr.shareabite.app.core.enums.Region;
import gr.shareabite.app.core.enums.Status;
import gr.shareabite.app.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FoodRequestReadOnlyDTO {

    private Long id;
    private Status status;
    private Region region;
    private User user;
    private CreatedDate createdDate;
}
