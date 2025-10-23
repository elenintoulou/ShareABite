package gr.shareabite.app.model;

import gr.shareabite.app.model.static_data.Region;
import gr.shareabite.app.model.static_data.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User extends AbstractEntity {

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

    //add methods in my entity?
//
//    public void addUser(User user) {
//        if(users == null) users = new HashSet<>();
//        users.add(user);
//        user.get
//    }

    //method to create the uuid
    @PrePersist
    public void initializeUUID() {
        if (uuid == null) uuid = UUID.randomUUID().toString();
    }
}
