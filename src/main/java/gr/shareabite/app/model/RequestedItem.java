package gr.shareabite.app.model;

import gr.shareabite.app.core.enums.FoodItems;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    //connecting with enums
    @Enumerated(EnumType.STRING)
    private FoodItems foodItems;

    @ManyToOne
    @JoinColumn(name = "food_request_id")
    private FoodRequest foodRequest;
}
