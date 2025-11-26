package gr.shareabite.app.mapper;

import gr.shareabite.app.core.enums.Status;
import gr.shareabite.app.dto.FoodRequestCreateDTO;
import gr.shareabite.app.dto.FoodRequestReadOnlyDTO;
import gr.shareabite.app.dto.RequestedItemsReadOnlyDTO;
import gr.shareabite.app.model.FoodRequest;
import gr.shareabite.app.model.RequestedItem;
import gr.shareabite.app.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodRequestMapper {

    public FoodRequest mapToEntity(FoodRequestCreateDTO foodRequestCreateDTO, User user) {
        FoodRequest foodRequest = FoodRequest.builder()
                .region(foodRequestCreateDTO.getRegion())
                .status(Status.OPEN)
                .user(user)
                .build();

        List<RequestedItem> items = foodRequestCreateDTO.getRequestedItemList()
                .stream()
                .map(itemDto -> RequestedItem.builder()
                        .foodItems(itemDto.getFoodItems())
                        .quantity(itemDto.getQuantity())
                        .unit(itemDto.getUnit())
                        .foodRequest(foodRequest)
                        .build()
                )
                .toList();

        foodRequest.setRequestedItemList(items);

        return foodRequest;
    }

    public FoodRequestReadOnlyDTO mapToFoodRequest(FoodRequest foodRequest) {

        //mapping the list
        List<RequestedItemsReadOnlyDTO> requestedItemDTOs =
                foodRequest.getRequestedItemList()
                        .stream()
                        .map(item -> new RequestedItemsReadOnlyDTO(
                                item.getId(),
                                item.getQuantity(),
                                item.getUnit(),
                                item.getFoodItems()
                        ))
                        .toList();

        return new FoodRequestReadOnlyDTO(foodRequest.getId(), foodRequest.getStatus(), foodRequest.getRegion(),
                foodRequest.getUser().getUsername(), foodRequest.getCreatedAt(), requestedItemDTOs);
    }
}
