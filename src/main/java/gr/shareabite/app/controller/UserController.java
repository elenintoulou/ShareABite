package gr.shareabite.app.controller;

import gr.shareabite.app.dto.UserRegisterDTO;
import gr.shareabite.app.exception.EntityAlreadyExistsException;
import gr.shareabite.app.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserController {

    //Dependency injection the Interface and not the implementation!!!!
    private final IUserService iUserService;

    // με το model (ένα έτοιμο interface του Spring που χρησιμοποιεί ένα Map για να ορίζει key, value pairs)
    // μεταφέρουμε data από τον Controller στη σελίδα
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userRegisterDTO") UserRegisterDTO userRegisterDTO,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //!!!!!ADD METHOD TO SAVE THE USER!!!!!!!!!!???
        //User savedUser;

        //ADD VALIDATION AND VALIDATOR
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            iUserService.registerUser(userRegisterDTO);
        } catch (EntityAlreadyExistsException e) {
            // Here you decide *where* to show the error:
            if (e.getMessage().contains("username")) {
                bindingResult.rejectValue("username", null, "This username is already taken.");
            } else if (e.getMessage().contains("email")) {
                bindingResult.rejectValue("email", null, "This email is already registered.");
            } else {
                // fallback → global error
                bindingResult.reject(null, e.getMessage());
            }

            return "register";
        }

        //success flash message!
        redirectAttributes.addFlashAttribute("success", "Your account has been created." +
                " Please log in.");

        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
}