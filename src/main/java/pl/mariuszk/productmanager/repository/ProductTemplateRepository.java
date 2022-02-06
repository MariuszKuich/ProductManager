package pl.mariuszk.productmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pl.mariuszk.productmanager.model.ProductTemplate;
import pl.mariuszk.productmanager.model.rest.ProductTemplateBriefDto;

import java.util.List;
import java.util.Optional;

public interface ProductTemplateRepository extends MongoRepository<ProductTemplate, String> {
    Optional<ProductTemplate> findProductTemplateByName(String name);

    boolean existsById(String id);

    @Query("{}, { _id: 1, name: 1}")
    List<ProductTemplateBriefDto> findAllBrief();
}
