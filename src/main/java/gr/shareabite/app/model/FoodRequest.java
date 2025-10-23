package gr.shareabite.app.model;

import gr.shareabite.app.model.static_data.Region;
import gr.shareabite.app.model.static_data.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "food_request")

public class FoodRequest extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Region region;

    @OneToMany(mappedBy = "foodRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequestedItem> requestedItems = new ArrayList<>();
}
