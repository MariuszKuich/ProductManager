package pl.mariuszk.productmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mariuszk.productmanager.exception.DictionaryNotFoundException;
import pl.mariuszk.productmanager.model.rest.Dictionary;
import pl.mariuszk.productmanager.repository.DictionaryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final DictionaryRepository dictionaryRepository;

    public List<Dictionary> getAllDictionaries() {
        return dictionaryRepository.findAll();
    }

    public Dictionary getDictionaryByName(String name) throws DictionaryNotFoundException {
        return dictionaryRepository.findDictionaryByName(name).orElseThrow(DictionaryNotFoundException::new);
    }
}
