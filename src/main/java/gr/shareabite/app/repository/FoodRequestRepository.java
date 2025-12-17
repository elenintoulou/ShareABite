package gr.shareabite.app.repository;

import gr.shareabite.app.model.FoodRequest;
import gr.shareabite.app.core.enums.Region;
import gr.shareabite.app.core.enums.Status;
import gr.shareabite.app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FoodRequestRepository extends JpaRepository<FoodRequest, Long> {

    List<FoodRequest> findByStatus(Status status);
    List<FoodRequest> findByRegion(Region region);
    List<FoodRequest> findByRegionAndStatus(Region region, Status status);
    Page<FoodRequest> findByUser(User user, Pageable pageable);
    Page<FoodRequest> findByStatus(Status status, Pageable pageable);
    Page<FoodRequest> findByStatusAndRegion(Status status, Region region, Pageable pageable);
    Page<FoodRequest> findByRegionAndStatusIn(Region region, Collection<Status> statuses, Pageable pageable);

}
