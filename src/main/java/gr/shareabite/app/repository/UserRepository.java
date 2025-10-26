package gr.shareabite.app.repository;

import gr.shareabite.app.model.User;
import gr.shareabite.app.enums.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // This interface already provides basic CRUD methods like save(), delete(), findById(), etc., through JpaRepository.

    Optional<User> findByEmail(String email);
    Optional<User> findByUuid(String uuid);
    boolean existsByEmail(String email);
    Page<User> findByRegion(Region region, Pageable pageable);
    Optional<User> findByUsername(String username);
}
