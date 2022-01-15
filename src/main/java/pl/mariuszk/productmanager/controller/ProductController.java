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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mariuszk.productmanager.exception.ProductTemplateNotFoundException;
import pl.mariuszk.productmanager.model.Product;
import pl.mariuszk.productmanager.service.ProductService;
import pl.mariuszk.productmanager.service.ProductTemplateService;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/product")
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductTemplateService productTemplateService;

    @GetMapping("/add")
    public String getAddProductPage(@RequestParam String productTemplateId, Model model) throws ProductTemplateNotFoundException {
        Product product = productService.prepareProductBasedOnTemplate(productTemplateId);
        model.addAttribute("product", product);
        model.addAttribute("templateName", productTemplateService.getProductTemplateById(productTemplateId).getName());
        model.addAttribute("propertiesCount", product.getProperties().size());

        return "product-add";
    }

    @PostMapping("/add")
    public String saveProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "product-add";
        }

        String productId = productService.saveProduct(product);

        redirectAttributes.addAttribute("productId", productId);

        return "redirect:/product/product-created";
    }

    @GetMapping("/product-created")
    public String getProductCreatedPage(@RequestParam("productId") String productId, Model model) {
        model.addAttribute("productId", productId);

        return "product-created";
    }

    @GetMapping("/products-list")
    public String getProductsListPage(@RequestParam("productTemplateId") String templateId, Model model) {
        model.addAttribute("products", productService.getProductsByTemplateId(templateId));
        model.addAttribute("templateName", productTemplateService.getProductTemplateById(templateId).getName());

        return "products-list";
    }

    @PostMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable("productId") String productId, @RequestParam("productTemplateId") String templateId,
                                RedirectAttributes redirectAttributes) {
        if (productService.productExists(productId)) {
            productService.deleteProduct(productId);
        }

        redirectAttributes.addAttribute("productTemplateId", templateId);
        return "redirect:/product/products-list";
    }

    @GetMapping("/{productId}/details")
    public String getProductDetails(@PathVariable("productId") String productId, @RequestParam("productTemplateId") String templateId,
                                      Model model, RedirectAttributes redirectAttributes) {
        Optional<Product> product = productService.getProductById(productId);

        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("productTemplateId", templateId);

            return "product-details";
        }
        else {
            redirectAttributes.addAttribute("productTemplateId", templateId);
            return "redirect:/product/products-list";
        }
    }
}
