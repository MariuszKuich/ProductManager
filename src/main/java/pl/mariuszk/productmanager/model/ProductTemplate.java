package pl.mariuszk.productmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.mariuszk.productmanager.enums.FieldType;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
@Document
public class ProductTemplate {
    @Id
    private String id;
    private String name;
    private Map<String, FieldType> fields;
    private Map<String, String> dictionaries;
}
