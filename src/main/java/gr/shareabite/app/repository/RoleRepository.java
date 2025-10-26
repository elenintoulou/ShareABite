package gr.shareabite.app.repository;

import gr.shareabite.app.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
