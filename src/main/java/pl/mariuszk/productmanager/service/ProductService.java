package pl.mariuszk.productmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mariuszk.productmanager.enums.FieldType;
import pl.mariuszk.productmanager.exception.ProductTemplateNotFoundException;
import pl.mariuszk.productmanager.model.Product;
import pl.mariuszk.productmanager.model.ProductTemplate;
import pl.mariuszk.productmanager.model.Property;
import pl.mariuszk.productmanager.repository.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductTemplateService productTemplateService;

    public Optional<Product> getProductById(String productId) {
        return productRepository.findById(productId);
    }

    public List<Product> getProductsByTemplateId(String templateId) throws ProductTemplateNotFoundException {
        return productRepository.findByTemplateId(templateId).orElseThrow(ProductTemplateNotFoundException::new);
    }

    public String saveProduct(Product product) {
        return productRepository.save(product).getId();
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    public void deleteProductsAssignedToTemplate(String templateId) {
        productRepository.deleteByTemplateId(templateId);
    }

    public boolean productExists(String productId) {
        return productRepository.existsById(productId);
    }

    public Product prepareProductBasedOnTemplate(String templateId) throws ProductTemplateNotFoundException {
        ProductTemplate productTemplate = productTemplateService.getProductTemplateById(templateId);
        Product product = new Product();
        product.setProperties(fillPropertiesFromTemplate(productTemplate.getFields()));
        product.setTemplateId(templateId);
        return product;
    }

    private List<Property> fillPropertiesFromTemplate(Map<String, FieldType> templateFields)  {
        return templateFields.keySet().stream().map(key -> Property.builder()
                .id(key.hashCode())
                .fieldType(templateFields.get(key))
                .label(key)
                .value(null)
                .build())
                .collect(Collectors.toList());
    }
}
