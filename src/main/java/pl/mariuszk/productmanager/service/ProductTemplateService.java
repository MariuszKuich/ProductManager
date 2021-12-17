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
}
