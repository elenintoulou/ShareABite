package gr.shareabite.app.model;

import gr.shareabite.app.core.enums.FoodItems;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "requested_items")

//Represents the entity of a specific item
public class RequestedItem extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String unit;

    @Enumerated(EnumType.STRING)
    private FoodItems foodItems;

    @ManyToOne
    @JoinColumn(name = "food_request_id")
    private FoodRequest foodRequest;

}
