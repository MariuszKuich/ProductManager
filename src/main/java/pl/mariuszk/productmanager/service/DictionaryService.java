package pl.mariuszk.productmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mariuszk.productmanager.exception.DictionaryNotFoundException;
import pl.mariuszk.productmanager.mapper.DictionaryMapper;
import pl.mariuszk.productmanager.model.frontend.DictionaryAddDto;
import pl.mariuszk.productmanager.model.frontend.DictionaryDto;
import pl.mariuszk.productmanager.model.frontend.DictionaryEditDto;
import pl.mariuszk.productmanager.model.rest.Dictionary;
import pl.mariuszk.productmanager.repository.DictionaryRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public Dictionary saveNewElementsInDictionary(String dictionaryId, DictionaryEditDto dictionaryEditDto) throws DictionaryNotFoundException {
        Dictionary dictionary = getDictionaryById(dictionaryId);
        dictionary.getValues().addAll(Arrays.stream(dictionaryEditDto.getNewFieldsNames()).filter(Objects::nonNull).collect(Collectors.toList()));

        return dictionaryRepository.save(dictionary);
    }
}
