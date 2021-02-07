package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.services.RegisterServiceModel;
import bg.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping()
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerModel")
    public RegisterServiceModel registerServiceModel(){
        return new RegisterServiceModel();
    }

    @GetMapping("/users/register")
    public String getRegister(Model model){
        if (!model.containsAttribute("registerServiceModel")){
            model.addAttribute("registerServiceModel", new RegisterServiceModel());
        }
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String postRegister(@Valid @ModelAttribute RegisterServiceModel registerServiceModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("registerServiceModel", registerServiceModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerServiceModel", bindingResult);

            return "redirect:/users/register";
        }

        userService.registerUser(registerServiceModel);

        return "redirect:/users/login";
    }
}
