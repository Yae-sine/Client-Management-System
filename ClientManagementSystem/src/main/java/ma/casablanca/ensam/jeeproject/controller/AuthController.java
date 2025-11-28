package ma.casablanca.ensam.jeeproject.controller;

import ma.casablanca.ensam.jeeproject.dto.UserRegistrationDto;
import ma.casablanca.ensam.jeeproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error" , required = false) String error,
            @RequestParam(value = "logout" , required = false) String logout,
            Model model
            ){
        if(error != null){
            model.addAttribute("error", "Invalid username and password");
        }
        if(logout !=null){
            model.addAttribute("message" , "You have been logged out successfully");
        }
        return "auth/login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model){
        model.addAttribute("userDto" , new UserRegistrationDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute("userDto") @Validated UserRegistrationDto dto ,
            BindingResult result,
            Model model) {
        if(result.hasErrors()) {
            return "auth/register";
        }
        try{
            userService.register(dto);
            return "redirect:/auth/login?success";
        }
        catch(Exception ex){
            model.addAttribute("error", ex.getMessage());
            return "auth/register";
        }
    }

}
