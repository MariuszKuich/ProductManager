package pl.mariuszk.productmanager.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mariuszk.productmanager.exception.ProductTemplateNotFoundException;
import pl.mariuszk.productmanager.model.Product;
import pl.mariuszk.productmanager.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/{templateId}")
    public ResponseEntity<List<Product>> getAllProductsFromGivenGroup(@PathVariable("templateId") String templateId) {
        try {
            return ResponseEntity.ok(productService.getProductsByTemplateId(templateId));
        }
        catch (ProductTemplateNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
