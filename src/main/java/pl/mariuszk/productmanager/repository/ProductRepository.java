package pl.mariuszk.productmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.mariuszk.productmanager.model.Product;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByTemplateId(String templateId);

    void deleteByTemplateId(String templateId);
}
