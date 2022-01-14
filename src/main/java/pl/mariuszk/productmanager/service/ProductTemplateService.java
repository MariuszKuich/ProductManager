package pl.mariuszk.productmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mariuszk.productmanager.enums.FieldType;
import pl.mariuszk.productmanager.mapper.ProductTemplateMapper;
import pl.mariuszk.productmanager.model.ProductTemplate;
import pl.mariuszk.productmanager.model.frontend.ProductTemplateDto;
import pl.mariuszk.productmanager.repository.ProductTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTemplateService {

    private final ProductTemplateRepository productTemplateRepository;
    private final ProductTemplateMapper productTemplateMapper;

    public List<ProductTemplate> getAvailableProductsTemplates() {
        return productTemplateRepository.findAll();
    }

    public ProductTemplate getProductTemplateById(String templateId) {
        return productTemplateRepository.findById(templateId).orElseThrow();
    }

    public ProductTemplate saveProductTemplate(ProductTemplateDto productTemplateDto) {
        return productTemplateRepository.save(productTemplateMapper.map(productTemplateDto));
    }

    public boolean productTemplateExists(String templateId) {
        return productTemplateRepository.existsById(templateId);
    }

    public void deleteProductTemplate(String templateId) {
        productTemplateRepository.deleteById(templateId);
    }

    public FieldType[] getAvailableFieldTypesList() {
        return FieldType.values();
    }
}
