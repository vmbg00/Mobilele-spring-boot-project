package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.services.RegisterServiceModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class RegisterController {

    @GetMapping("/register")
    public String getRegister(){
        return "auth-register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute RegisterServiceModel registerServiceModel){

        return "redirect:login";
    }

}
