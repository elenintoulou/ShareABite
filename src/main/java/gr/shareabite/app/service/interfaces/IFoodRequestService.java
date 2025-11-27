package gr.shareabite.app.service.interfaces;

import gr.shareabite.app.core.enums.Status;
import gr.shareabite.app.core.exception.NotExistingEntityException;
import gr.shareabite.app.dto.FoodRequestCreateDTO;
import gr.shareabite.app.dto.FoodRequestReadOnlyDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;

public interface IFoodRequestService {
    void saveFoodRequest(FoodRequestCreateDTO foodRequestCreateDTO) throws NotExistingEntityException;
    Page<FoodRequestReadOnlyDTO> getPaginatedFoodRequests(int page, int size);
    FoodRequestReadOnlyDTO getFoodRequestById(Long id) throws EntityNotFoundException;
    void updateFoodRequest(Long id, FoodRequestCreateDTO foodRequestCreateDTO);
    void deleteFoodRequest(Long id);
    void updateFoodRequestStatus(Long id, Status status);
    Page<FoodRequestReadOnlyDTO> getPaginatedFoodRequestsForCurrentUser(int page, int size);
}
