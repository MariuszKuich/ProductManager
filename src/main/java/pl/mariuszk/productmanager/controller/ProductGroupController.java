package pl.mariuszk.productmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mariuszk.productmanager.config.ProductManagerConfig;
import pl.mariuszk.productmanager.exception.ProductTemplateNotFoundException;
import pl.mariuszk.productmanager.model.frontend.ProductTemplateDto;
import pl.mariuszk.productmanager.service.ProductService;
import pl.mariuszk.productmanager.service.ProductTemplateService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product-group")
public class ProductGroupController {

    private final ProductTemplateService productTemplateService;
    private final ProductService productService;

    @GetMapping
    public String getProductGroupMainPage(Model model) {
        model.addAttribute("templates", productTemplateService.getAvailableProductsTemplates());
        return "product-group";
    }

    @GetMapping("/add")
    public String getProductGroupAddPage(Model model) {
        model.addAttribute("templateDto", new ProductTemplateDto());
        model.addAttribute("fieldTypes", productTemplateService.getAvailableFieldTypes());
        model.addAttribute("maxAttributesNumber", ProductManagerConfig.MAX_ATTRIBUTES_SIZE);
        model.addAttribute("existingProductTemplatesNames", productTemplateService.getExistingProductTemplatesNames());
        return "product-group-add";
    }

    @PostMapping("/add")
    public String addProductGroup(@ModelAttribute("template") @Valid ProductTemplateDto productTemplateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product-group-add";
        }
        productTemplateService.saveProductTemplate(productTemplateDto);

        return "redirect:/product-group";
    }

    @GetMapping("/{templateId}/describe")
    public String getTemplateInfoPage(@PathVariable(name = "templateId") String templateId, Model model) throws ProductTemplateNotFoundException {
        model.addAttribute("template", productTemplateService.getProductTemplateById(templateId));
        return "product-group-description";
    }

    @PostMapping("/{templateId}/delete")
    public String deleteProductGroup(@PathVariable(name = "templateId") String templateId) {
        if (productTemplateService.productTemplateExists(templateId)) {
            productTemplateService.deleteProductTemplate(templateId);
            productService.deleteProductsAssignedToTemplate(templateId);
        }

        return "redirect:/product-group";
    }
}
