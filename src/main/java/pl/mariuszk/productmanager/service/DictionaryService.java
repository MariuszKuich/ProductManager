package pl.mariuszk.productmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mariuszk.productmanager.exception.DictionaryNotFoundException;
import pl.mariuszk.productmanager.mapper.DictionaryDisplayMapper;
import pl.mariuszk.productmanager.mapper.DictionaryMapper;
import pl.mariuszk.productmanager.model.ProductTemplate;
import pl.mariuszk.productmanager.model.frontend.DictionaryAddDto;
import pl.mariuszk.productmanager.model.frontend.DictionaryDisplayDto;
import pl.mariuszk.productmanager.model.frontend.DictionaryDto;
import pl.mariuszk.productmanager.model.frontend.DictionaryEditDto;
import pl.mariuszk.productmanager.model.Dictionary;
import pl.mariuszk.productmanager.repository.DictionaryRepository;
import pl.mariuszk.productmanager.repository.ProductTemplateRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final DictionaryRepository dictionaryRepository;
    private final ProductTemplateRepository productTemplateRepository;
    private final DictionaryMapper dictionaryMapper;
    private final DictionaryDisplayMapper dictionaryDisplayMapper;

    public List<Dictionary> getAllDictionaries() {
        return dictionaryRepository.findAll();
    }

    public List<DictionaryDto> getAllDictionariesDto() {
        return dictionaryRepository.findAllDictionariesDto();
    }

    public List<DictionaryDisplayDto> getAllDictionariesDisplayDto() {
        List<String> dictionariesInUse = getNamesOfDictionariesInUse();

        return getAllDictionariesDto().stream()
                .map(dict -> dictionaryDisplayMapper.map(dict, dictionariesInUse))
                .collect(Collectors.toList());
    }

    public List<String> getExistingDictionariesNames() {
        return dictionaryRepository.findAll().stream().map(Dictionary::getName).collect(Collectors.toList());
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

    public boolean dictionaryExists(String dictionaryId) {
        return dictionaryRepository.existsById(dictionaryId);
    }

    public void deleteDictionary(String dictionaryId) {
        dictionaryRepository.deleteById(dictionaryId);
    }

    private List<String> getNamesOfDictionariesInUse() {
        return productTemplateRepository.findAll().stream()
                .map(ProductTemplate::getDictionaries)
                .map(Map::values)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
