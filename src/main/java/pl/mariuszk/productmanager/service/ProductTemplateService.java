package pl.mariuszk.productmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mariuszk.productmanager.model.ProductTemplate;
import pl.mariuszk.productmanager.repository.ProductTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTemplateService {

    private final ProductTemplateRepository productTemplateRepository;

    public List<ProductTemplate> getAvailableProductsTemplates() {
        return productTemplateRepository.findAll();
    }

    public ProductTemplate getProductTemplateById(String templateId) {
        return productTemplateRepository.findById(templateId).orElseThrow();
    }

    public ProductTemplate saveProductTemplate(ProductTemplate productTemplate) {
        return productTemplateRepository.save(productTemplate);
    }

    public boolean productTemplateExists(String templateId) {
        return productTemplateRepository.existsById(templateId);
    }

    public void deleteProductTemplate(String templateId) {
        productTemplateRepository.deleteById(templateId);
    }
}
