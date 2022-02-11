package pl.mariuszk.productmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mariuszk.productmanager.enums.FieldType;
import pl.mariuszk.productmanager.exception.DictionaryNotFoundException;
import pl.mariuszk.productmanager.exception.ProductTemplateNotFoundException;
import pl.mariuszk.productmanager.mapper.ProductTemplateMapper;
import pl.mariuszk.productmanager.model.ProductTemplate;
import pl.mariuszk.productmanager.model.frontend.FieldTypeDto;
import pl.mariuszk.productmanager.model.frontend.ProductTemplateDto;
import pl.mariuszk.productmanager.model.rest.Dictionary;
import pl.mariuszk.productmanager.model.rest.ProductTemplateBriefDto;
import pl.mariuszk.productmanager.repository.ProductTemplateRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static pl.mariuszk.productmanager.config.ProductManagerConfig.DICTIONARY_LABEL_PREFIX;

@Service
@RequiredArgsConstructor
public class ProductTemplateService {

    private final ProductTemplateRepository productTemplateRepository;
    private final ProductTemplateMapper productTemplateMapper;
    private final DictionaryService dictionaryService;

    public List<ProductTemplate> getAvailableProductsTemplates() {
        return productTemplateRepository.findAll();
    }

    public ProductTemplate getProductTemplateById(String templateId) throws ProductTemplateNotFoundException {
        return productTemplateRepository.findById(templateId).orElseThrow(ProductTemplateNotFoundException::new);
    }

    public ProductTemplate saveProductTemplate(ProductTemplateDto productTemplateDto) {
        return productTemplateRepository.save(productTemplateMapper.map(productTemplateDto));
    }

    public boolean productTemplateExists(String templateId) {
        return productTemplateRepository.existsById(templateId);
    }

    public void deleteProductTemplate(String templateId) {
        productTemplateRepository.deleteById(templateId);
    }

    public FieldTypeDto[] getAvailableFieldTypes() {
        List<Dictionary> dictionaries =  dictionaryService.getAllDictionaries();

        List<FieldTypeDto> fieldTypeDtos = new ArrayList<>();
        fieldTypeDtos.add(FieldTypeDto.builder().value(FieldType.STRING.name()).label(FieldType.STRING.getLabel()).build());
        fieldTypeDtos.add(FieldTypeDto.builder().value(FieldType.LONG.name()).label(FieldType.LONG.getLabel()).build());
        fieldTypeDtos.add(FieldTypeDto.builder().value(FieldType.DOUBLE.name()).label(FieldType.DOUBLE.getLabel()).build());
        fieldTypeDtos.addAll(
                dictionaries.stream().map(d ->
                        FieldTypeDto.builder()
                                .value(DICTIONARY_LABEL_PREFIX + d.getName())
                                .label(DICTIONARY_LABEL_PREFIX + d.getName())
                                .build())
                .collect(Collectors.toList())
        );

        return fieldTypeDtos.toArray(FieldTypeDto[]::new);
    }

    public List<ProductTemplateBriefDto> getAvailableProductTemplatesSummary() {
        return productTemplateRepository.findAllBrief();
    }

    public Map<String, List<String>> getDictionariesValuesForFields(String templateId) throws ProductTemplateNotFoundException, DictionaryNotFoundException {
        ProductTemplate productTemplate = productTemplateRepository.findById(templateId).orElseThrow(ProductTemplateNotFoundException::new);

        Map<String, List<String>> dictionariesValuesForFields = new HashMap<>();
        for (String templateDictionaryKey : productTemplate.getDictionaries().keySet()) {
            dictionariesValuesForFields.put(templateDictionaryKey,
                    dictionaryService.getDictionaryByName(productTemplate.getDictionaries().get(templateDictionaryKey)).getValues());
        }

        return dictionariesValuesForFields;
    }

    public List<String> getExistingProductTemplatesNames() {
        return productTemplateRepository.findAll().stream().map(ProductTemplate::getName).collect(Collectors.toList());
    }
}
