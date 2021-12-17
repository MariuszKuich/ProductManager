package pl.mariuszk.productmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/product")
@Controller
@RequiredArgsConstructor
public class ProductController {

    @GetMapping("/add")
    public String getAddProductPage(@RequestParam String productTemplateId, Model model) {
        model.addAttribute("productTemplateId", productTemplateId);

        return "product-add";
    }
}
