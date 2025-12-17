package gr.shareabite.app.controller;

import gr.shareabite.app.core.enums.FoodItems;
import gr.shareabite.app.core.enums.Status;
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

import java.security.Principal;


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
        // dropdown for the FoodItems
        model.addAttribute("foodItems", FoodItems.values());
        return "foodrequest";
    }

    @PostMapping("/foodrequest/create")
    public String foodRequestPost(@Valid @ModelAttribute("foodRequestCreateDTO") FoodRequestCreateDTO foodRequestCreateDTO,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                  @RequestParam(name = "action", required = false) String action,
                                  Model model) {

        //this is for when the user wants to add more items and clicks the add item button
        if ("addItem".equals(action)) {
            // Add one more empty item row
            foodRequestCreateDTO.getRequestedItemList().add(new RequestedItemsCreateDTO());
            // for the dropdown
            model.addAttribute("foodItems", FoodItems.values());

            return "foodrequest";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("foodItems", FoodItems.values());
            return "foodrequest";
        }
        try {
            iFoodRequestService.saveFoodRequest(foodRequestCreateDTO);
        } catch (NotExistingEntityException e) {
            // User not found (should not happen normally)
            bindingResult.reject(null, "Something went wrong. Please try again.");
            model.addAttribute("foodItems", FoodItems.values());
            return "foodrequest";
        } catch (Exception e) {
            // Any unexpected error
            bindingResult.reject(null, "An unexpected error occurred.");
            model.addAttribute("foodItems", FoodItems.values());
            return "foodrequest";
        }
        // Success message
        redirectAttributes.addFlashAttribute("success", "Your food request was created successfully!");
        return "redirect:/user/success";
    }

    @GetMapping("/success")
    public String showSuccessPage(Model model) {
        //i put that instead of the simple way which just returned the success page because i was taking an 500 error
        if (!model.containsAttribute("success")) {
            model.addAttribute("success", "Your request has been submitted.");
        }
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

    @GetMapping("/foodrequest/{id}")
    public String showFoodRequestDetails(@PathVariable("id") Long id, Model model) {
        FoodRequestReadOnlyDTO foodRequestReadOnlyDTO = iFoodRequestService.getFoodRequestById(id);
        model.addAttribute("foodRequest", foodRequestReadOnlyDTO);
        return "foodrequestdetails";
    }

    @GetMapping("/requests/open")
    public String openRequestsForVolunteer(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size, Principal principal, Model model){

        Page<FoodRequestReadOnlyDTO> requestsPage = iFoodRequestService.getRequestsByRegion(principal.getName(), page, size);
        model.addAttribute("requestsPage", requestsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        return "openrequests";
    }

    @PostMapping("/foodrequest/{id}/fulfill")
    public String fulfillRequest(@PathVariable Long id, Principal principal, RedirectAttributes redirectAttributes) {

        try {
            iFoodRequestService.fulfillRequest(id, principal.getName());
            redirectAttributes.addFlashAttribute("success","Thank you! The request has been completed.");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/user/requests/open";
    }

    @PostMapping("/foodrequest/{id}/cancel")
    public String cancelRequest(@PathVariable Long id, Principal principal, RedirectAttributes redirectAttributes) {
        try {
            iFoodRequestService.updateFoodRequestStatus(id, Status.CANCELLED, principal.getName());
            redirectAttributes.addFlashAttribute("success", "Request cancelled");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/user/foodrequest/myrequests";
    }
}
