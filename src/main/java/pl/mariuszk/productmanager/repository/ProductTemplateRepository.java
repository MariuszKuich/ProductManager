package pl.mariuszk.productmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.mariuszk.productmanager.model.ProductTemplate;

import java.util.Optional;

public interface ProductTemplateRepository extends MongoRepository<ProductTemplate, String> {
    Optional<ProductTemplate> findProductTemplateByName(String name);

    boolean existsById(String id);
}
