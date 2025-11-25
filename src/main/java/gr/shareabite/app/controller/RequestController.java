package gr.shareabite.app.controller;

import gr.shareabite.app.dto.FoodRequestCreateDTO;
import gr.shareabite.app.dto.RequestedItemsCreateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor

public class RequestController {
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

    }
}
