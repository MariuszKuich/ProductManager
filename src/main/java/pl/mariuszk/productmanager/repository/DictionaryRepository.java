package pl.mariuszk.productmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.mariuszk.productmanager.model.rest.Dictionary;

import java.util.Optional;

public interface DictionaryRepository extends MongoRepository<Dictionary, String> {

    Optional<Dictionary> findDictionaryByName(String name);
}
