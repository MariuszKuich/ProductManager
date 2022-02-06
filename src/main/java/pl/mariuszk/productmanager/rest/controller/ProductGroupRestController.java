package pl.mariuszk.productmanager.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mariuszk.productmanager.exception.ProductTemplateNotFoundException;
import pl.mariuszk.productmanager.model.ProductTemplate;
import pl.mariuszk.productmanager.model.rest.ProductTemplateBriefDto;
import pl.mariuszk.productmanager.service.ProductTemplateService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-group")
public class ProductGroupRestController {

    private final ProductTemplateService productTemplateService;

    @GetMapping("/available-groups")
    public ResponseEntity<List<ProductTemplateBriefDto>> getAvailableProductGroups() {
        return ResponseEntity.ok(productTemplateService.getAvailableProductTemplatesSummary());
    }

    @GetMapping("/describe")
    public ResponseEntity<ProductTemplate> getProductTemplateFullInfo(@RequestParam("id") String templateId) {
        try {
            return ResponseEntity.ok(productTemplateService.getProductTemplateById(templateId));
        }
        catch (ProductTemplateNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}