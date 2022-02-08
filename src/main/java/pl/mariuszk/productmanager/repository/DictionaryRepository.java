package pl.mariuszk.productmanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pl.mariuszk.productmanager.model.frontend.DictionaryDto;
import pl.mariuszk.productmanager.model.rest.Dictionary;

import java.util.List;
import java.util.Optional;

public interface DictionaryRepository extends MongoRepository<Dictionary, String> {

    @Query("{}, { _id: 1, name: 1}")
    List<DictionaryDto> findAllDictionariesDto();

    Optional<Dictionary> findDictionaryByName(String name);
}
