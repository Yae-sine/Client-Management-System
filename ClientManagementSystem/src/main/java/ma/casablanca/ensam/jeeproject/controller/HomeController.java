package ma.casablanca.ensam.jeeproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/clients";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}

