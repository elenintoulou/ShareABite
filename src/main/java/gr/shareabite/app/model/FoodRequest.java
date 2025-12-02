package gr.shareabite.app.model;

import gr.shareabite.app.core.enums.Region;
import gr.shareabite.app.core.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "food_request")

public class FoodRequest extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "foodRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequestedItem> requestedItemList = new ArrayList<>();
}
