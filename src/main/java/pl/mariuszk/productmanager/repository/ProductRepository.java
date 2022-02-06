package pl.mariuszk.productmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.mariuszk.productmanager.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<List<Product>> findByTemplateId(String templateId);

    void deleteByTemplateId(String templateId);
}
