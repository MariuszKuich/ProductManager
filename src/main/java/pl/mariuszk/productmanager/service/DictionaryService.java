package pl.mariuszk.productmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mariuszk.productmanager.exception.DictionaryNotFoundException;
import pl.mariuszk.productmanager.mapper.DictionaryMapper;
import pl.mariuszk.productmanager.model.frontend.DictionaryAddDto;
import pl.mariuszk.productmanager.model.frontend.DictionaryDto;
import pl.mariuszk.productmanager.model.rest.Dictionary;
import pl.mariuszk.productmanager.repository.DictionaryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final DictionaryRepository dictionaryRepository;
    private final DictionaryMapper dictionaryMapper;

    public List<Dictionary> getAllDictionaries() {
        return dictionaryRepository.findAll();
    }

    public List<DictionaryDto> getAllDictionariesDto() {
        return dictionaryRepository.findAllDictionariesDto();
    }

    public Dictionary getDictionaryByName(String name) throws DictionaryNotFoundException {
        return dictionaryRepository.findDictionaryByName(name).orElseThrow(DictionaryNotFoundException::new);
    }

    public Dictionary getDictionaryById(String id) throws DictionaryNotFoundException {
        return dictionaryRepository.findById(id).orElseThrow(DictionaryNotFoundException::new);
    }

    public Dictionary saveDictionary(DictionaryAddDto dictionaryDto) {
        return dictionaryRepository.save(dictionaryMapper.map(dictionaryDto));
    }
}
