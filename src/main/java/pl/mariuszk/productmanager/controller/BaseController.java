package pl.mariuszk.productmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
@Controller
public class BaseController {

    @GetMapping
    public ModelAndView getLoginPage() {
        return new ModelAndView("redirect:/product-group");
    }
}
