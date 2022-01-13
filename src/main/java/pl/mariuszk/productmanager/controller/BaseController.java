package pl.mariuszk.productmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class BaseController {

    @GetMapping
    public String getLoginPage() {
        return "redirect:/product-group";
    }
}
