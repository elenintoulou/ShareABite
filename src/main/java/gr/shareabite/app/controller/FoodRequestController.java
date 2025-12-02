package gr.shareabite.app.controller;

import gr.shareabite.app.core.exception.NotExistingEntityException;
import gr.shareabite.app.dto.FoodRequestCreateDTO;
import gr.shareabite.app.dto.FoodRequestReadOnlyDTO;
import gr.shareabite.app.dto.RequestedItemsCreateDTO;
import gr.shareabite.app.service.interfaces.IFoodRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor

public class FoodRequestController {

    private final IFoodRequestService iFoodRequestService;

    @GetMapping("/foodrequest/create")
    public String showFoodRequestForm(Model model) {
        FoodRequestCreateDTO foodRequestCreateDTO = new FoodRequestCreateDTO();
        foodRequestCreateDTO.getRequestedItemList().add(new RequestedItemsCreateDTO());
        model.addAttribute("foodRequestCreateDTO", foodRequestCreateDTO);
        return "foodrequest";
    }

    @PostMapping("/foodrequest/create")
    public String foodRequestPost(@Valid @ModelAttribute("foodRequestCreateDTO") FoodRequestCreateDTO foodRequestCreateDTO,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "foodrequest";
        }
        try {
            iFoodRequestService.saveFoodRequest(foodRequestCreateDTO);
        } catch (NotExistingEntityException e) {
            // User not found (should not happen normally)
            bindingResult.reject(null, "Something went wrong. Please try again.");
            return "foodrequest";
        } catch (Exception e) {
            // Any unexpected error
            bindingResult.reject(null, "An unexpected error occurred.");
            return "foodrequest";
        }

        // Success message
        redirectAttributes.addFlashAttribute("success", "Your food request was created successfully!");
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }

    @GetMapping("/foodrequest/myrequests")
    public String showMyRequests(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 Model model) {

        Page<FoodRequestReadOnlyDTO> requestsPage =
                iFoodRequestService.getPaginatedFoodRequestsForCurrentUser(page, size);

        model.addAttribute("requestsPage", requestsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);

        return "myrequests";
    }

    @GetMapping("/foodrequest/open")
    public String showOpenRequests(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   Model model) {

        Page<FoodRequestReadOnlyDTO> requestsPage = iFoodRequestService.getPaginatedFoodRequests(page, size);

        model.addAttribute("requestsPage", requestsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);

        return "openrequests";
    }
}
