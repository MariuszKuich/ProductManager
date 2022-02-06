package pl.mariuszk.productmanager.mapper;

import org.springframework.stereotype.Service;
import pl.mariuszk.productmanager.config.ProductManagerConfig;
import pl.mariuszk.productmanager.enums.FieldType;
import pl.mariuszk.productmanager.model.ProductTemplate;
import pl.mariuszk.productmanager.model.frontend.ProductTemplateDto;

import java.util.HashMap;
import java.util.Map;

import static pl.mariuszk.productmanager.config.ProductManagerConfig.DICTIONARY_LABEL_PREFIX;

@Service
public class ProductTemplateMapper {

    public ProductTemplate map(ProductTemplateDto productTemplateDto) {
        ProductTemplate productTemplate = new ProductTemplate();
        productTemplate.setName(productTemplateDto.getName());

        ProductTemplateFieldsDictionaries productTemplateFieldsDictionaries =
                getFieldsAndDictionariesFromDto(productTemplateDto.getFieldsNames(), productTemplateDto.getFieldTypes());

        productTemplate.setFields(productTemplateFieldsDictionaries.fieldsMap);
        productTemplate.setDictionaries(productTemplateFieldsDictionaries.dictionariesMap);

        return productTemplate;
    }

    private ProductTemplateFieldsDictionaries getFieldsAndDictionariesFromDto(String[] fieldsNames, String[] fieldTypes) {
        Map<String, FieldType> fieldsMap = new HashMap<>();
        Map<String, String> dictionariesMap = new HashMap<>();

        for (int i = 0; i < ProductManagerConfig.MAX_ATTRIBUTES_SIZE ; i++) {
            if (fieldsNames[i] == null ) {
                break;
            }

            if (fieldTypes[i].contains(DICTIONARY_LABEL_PREFIX)) {
                fieldsMap.put(fieldsNames[i], FieldType.STRING_D);
                dictionariesMap.put(fieldsNames[i], fieldTypes[i].replace(DICTIONARY_LABEL_PREFIX, ""));
            }
            else {
                fieldsMap.put(fieldsNames[i], FieldType.valueOf(fieldTypes[i]));
            }
        }

        return new ProductTemplateFieldsDictionaries(fieldsMap, dictionariesMap);
    }

    private static class ProductTemplateFieldsDictionaries {
        private final Map<String, FieldType> fieldsMap;
        private final Map<String, String> dictionariesMap;

        ProductTemplateFieldsDictionaries(Map<String, FieldType> fieldsMap, Map<String, String> dictionariesMap) {
            this.fieldsMap = fieldsMap;
            this.dictionariesMap = dictionariesMap;
        }
    }
}
