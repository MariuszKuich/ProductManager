package pl.mariuszk.productmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.mariuszk.productmanager.enums.FieldType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
@Document
@NoArgsConstructor
public class ProductTemplate {
    @Id
    private String id;
    @NotNull
    @Size(min=2, max=30)
    private String name;
    //TODO
    //@Size(min=1)
    private Map<String, FieldType> fields;
    private Map<String, String> dictionaries;
}
