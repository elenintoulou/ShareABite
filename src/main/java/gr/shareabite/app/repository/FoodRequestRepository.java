package gr.shareabite.app.repository;

import gr.shareabite.app.model.FoodRequest;
import gr.shareabite.app.enums.Region;
import gr.shareabite.app.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRequestRepository extends JpaRepository<FoodRequest, Long> {

    List<FoodRequest> findByStatus(Status status);
    List<FoodRequest> findByRegion(Region region);
    List<FoodRequest> findByRegionAndStatus(Region region, Status status);

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //Optional<FoodRequest> findByUserId(Long id);
    // i have to connect the user with the foodrequest
}
