package gr.shareabite.app.repository;

import gr.shareabite.app.model.RequestedItem;
import gr.shareabite.app.core.enums.FoodItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestedItemsRepository extends JpaRepository<RequestedItem, Long> {

    List<RequestedItem> findByFoodRequestId(Long foodRequestId);
    List<RequestedItem> findByFoodItems(FoodItems foodItems);
    void deleteByFoodRequestId(Long foodRequestId);
}
