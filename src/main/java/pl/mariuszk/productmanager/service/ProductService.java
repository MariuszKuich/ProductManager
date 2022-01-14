package pl.mariuszk.productmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mariuszk.productmanager.enums.FieldType;
import pl.mariuszk.productmanager.exception.ProductTemplateNotFoundException;
import pl.mariuszk.productmanager.factory.PropertyTypeFactory;
import pl.mariuszk.productmanager.model.Product;
import pl.mariuszk.productmanager.model.ProductTemplate;
import pl.mariuszk.productmanager.model.Property;
import pl.mariuszk.productmanager.repository.ProductRepository;
import pl.mariuszk.productmanager.repository.ProductTemplateRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductTemplateRepository productTemplateRepository;
    private final PropertyTypeFactory propertyTypeFactory;

    public String saveProduct(Product product) {
        return productRepository.save(product).getId();
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    public boolean productExists(String productId) {
        return productRepository.existsById(productId);
    }

    public Product prepareProductBasedOnTemplate(String templateId) throws ProductTemplateNotFoundException {
        ProductTemplate productTemplate =
                productTemplateRepository.findById(templateId).orElseThrow(ProductTemplateNotFoundException::new);
        Product product = new Product();
        product.setProperties(fillPropertiesFromTemplate(productTemplate.getFields()));
        return  product;
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
