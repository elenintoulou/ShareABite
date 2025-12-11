package gr.shareabite.app.service.implementation;

import gr.shareabite.app.core.enums.Status;
import gr.shareabite.app.core.exception.NotExistingEntityException;
import gr.shareabite.app.dto.FoodRequestCreateDTO;
import gr.shareabite.app.dto.FoodRequestReadOnlyDTO;
import gr.shareabite.app.mapper.FoodRequestMapper;
import gr.shareabite.app.model.FoodRequest;
import gr.shareabite.app.model.User;
import gr.shareabite.app.repository.FoodRequestRepository;
import gr.shareabite.app.repository.UserRepository;
import gr.shareabite.app.service.interfaces.IFoodRequestService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
@Slf4j
@RequiredArgsConstructor
public class FoodRequestServiceImpl implements IFoodRequestService {

    private final FoodRequestRepository foodRequestRepository;
    private final FoodRequestMapper foodRequestMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void saveFoodRequest(FoodRequestCreateDTO foodRequestCreateDTO) throws NotExistingEntityException {

        //i am taking the username from the logged in user from spring security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getName())) {
            throw new IllegalStateException("No authenticated user found.");
        }
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotExistingEntityException("User", username));

        FoodRequest foodRequest = foodRequestMapper.mapToEntity(foodRequestCreateDTO, user);

        foodRequestRepository.save(foodRequest);
        log.info("Food request created for user: {}", username);
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public Page<FoodRequestReadOnlyDTO> getPaginatedFoodRequests(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<FoodRequest> requestsPage = foodRequestRepository.findByStatus(Status.OPEN, pageable);

        return requestsPage.map(foodRequestMapper::mapToFoodRequest);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public FoodRequestReadOnlyDTO getFoodRequestById(Long id) throws EntityNotFoundException {
        FoodRequest foodRequest = foodRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Request not found"));

        return foodRequestMapper.mapToFoodRequest(foodRequest);
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

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Page<FoodRequestReadOnlyDTO> getPaginatedFoodRequestsForCurrentUser(int page, int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getName())) {
            throw new IllegalStateException("No authenticated user found.");
        }

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found: " + username));

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<FoodRequest> requestsPage = foodRequestRepository.findByUser(user, pageable);

        return requestsPage.map(foodRequestMapper::mapToFoodRequest);
    }
}
