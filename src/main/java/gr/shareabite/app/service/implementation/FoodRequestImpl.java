package gr.shareabite.app.service.implementation;

import gr.shareabite.app.core.enums.Status;
import gr.shareabite.app.dto.FoodRequestCreateDTO;
import gr.shareabite.app.dto.FoodRequestReadOnlyDTO;
import gr.shareabite.app.service.interfaces.IFoodRequestService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FoodRequestImpl implements IFoodRequestService {

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void saveFoodRequest(FoodRequestCreateDTO foodRequestCreateDTO) {

    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Page<FoodRequestReadOnlyDTO> getPaginatedFoodRequests(int page, int size) {
        return null;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public FoodRequestReadOnlyDTO getFoodRequestById(Long id) {
        return null;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateFoodRequest(Long id, FoodRequestCreateDTO foodRequestCreateDTO) {

    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteFoodRequest(Long id) {

    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateFoodRequestStatus(Long id, Status status) {

    }
}
