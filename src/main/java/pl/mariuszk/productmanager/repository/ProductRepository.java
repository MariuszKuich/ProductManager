package pl.mariuszk.productmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.mariuszk.productmanager.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
