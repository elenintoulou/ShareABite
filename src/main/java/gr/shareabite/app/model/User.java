package gr.shareabite.app.model;

import gr.shareabite.app.core.enums.Region;
import gr.shareabite.app.core.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//in order to make my constructors with the properties i want every time, without a specific order
@Builder
@Table(name = "users")
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    //Relationships
    @Enumerated(EnumType.STRING)
    //should i make it nullable=false??
    private Region region;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
//    Maybe add relationship with food request and requested items

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodRequest> foodRequests = new ArrayList<>();

    //method to create the uuid
    @PrePersist
    public void initializeUUID() {
        if (uuid == null) uuid = UUID.randomUUID().toString();
    }
}
