package pl.mariuszk.productmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mariuszk.productmanager.service.ProductTemplateService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product-group")
public class ProductGroupController {

    private final ProductTemplateService productTemplateService;

    @GetMapping
    public String getProductGroupMainPage(Model model) {
        model.addAttribute("templates", productTemplateService.getAvailableProductsTemplates());
        return "product-group";
    }

    @GetMapping("/add")
    public String getProductGroupAddPage() {
        return "product-group-add";
    }

    @GetMapping("/{templateId}/describe")
    public String getTemplateInfoPage(@PathVariable(name = "templateId") String templateId, Model model) {
        model.addAttribute("template", productTemplateService.getProductTemplateById(templateId));
        return "product-group-description";
    }
}
