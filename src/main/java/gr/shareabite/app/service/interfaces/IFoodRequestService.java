package gr.shareabite.app.service.interfaces;

import gr.shareabite.app.core.enums.Status;
import gr.shareabite.app.dto.FoodRequestCreateDTO;
import gr.shareabite.app.dto.FoodRequestReadOnlyDTO;
import org.springframework.data.domain.Page;

public interface IFoodRequestService {
    void saveFoodRequest(FoodRequestCreateDTO foodRequestCreateDTO);
    Page<FoodRequestReadOnlyDTO> getPaginatedFoodRequests(int page, int size);
    FoodRequestReadOnlyDTO getFoodRequestById(Long id);
    void updateFoodRequest(Long id, FoodRequestCreateDTO foodRequestCreateDTO);
    void deleteFoodRequest(Long id);
    void updateFoodRequestStatus(Long id, Status status);



}
