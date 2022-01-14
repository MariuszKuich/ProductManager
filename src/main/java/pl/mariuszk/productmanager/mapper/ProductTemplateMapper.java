package pl.mariuszk.productmanager.mapper;

import org.springframework.stereotype.Service;
import pl.mariuszk.productmanager.config.ProductManagerConfig;
import pl.mariuszk.productmanager.enums.FieldType;
import pl.mariuszk.productmanager.model.ProductTemplate;
import pl.mariuszk.productmanager.model.frontend.ProductTemplateDto;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductTemplateMapper {

    public ProductTemplate map(ProductTemplateDto productTemplateDto) {
        ProductTemplate productTemplate = new ProductTemplate();
        productTemplate.setName(productTemplateDto.getName());
        productTemplate.setFields(getFieldsMap(productTemplateDto.getFieldsNames(), productTemplateDto.getFieldTypes()));
        productTemplate.setDictionaries(new HashMap<>());

        return  productTemplate;
    }

    private Map<String, FieldType> getFieldsMap(String[] fieldsNames, FieldType[] fieldTypes) {
        Map<String, FieldType> fieldsMap = new HashMap<>();

        for (int i = 0; i < ProductManagerConfig.MAX_ATTRIBUTES_SIZE ; i++) {
            if (fieldsNames[i] == null ) {
                break;
            }
            fieldsMap.put(fieldsNames[i], fieldTypes[i]);
        }

        return fieldsMap;
    }
}
