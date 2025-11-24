package gr.shareabite.app.core.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public String handleIfExists(EntityAlreadyExistsException e, Model model) {
        model.addAttribute("message" , e.getMessage());
        return "error";
    }

    @ExceptionHandler(NotExistingEntityException.class)
    public String handleIfNotExists(NotExistingEntityException e, Model model) {
        model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

