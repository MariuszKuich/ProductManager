package pl.mariuszk.productmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.mariuszk.productmanager.enums.FieldType;

import javax.validation.constraints.NotBlank;

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
    @NotBlank
    private String name;
    @NotNull
    @Size(min=1)
    private Map<String, FieldType> fields;
    @NotNull
    private Map<String, String> dictionaries;
}
