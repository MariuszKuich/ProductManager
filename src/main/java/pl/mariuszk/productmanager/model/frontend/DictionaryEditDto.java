package pl.mariuszk.productmanager.model.frontend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pl.mariuszk.productmanager.config.ProductManagerConfig;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder
public class DictionaryEditDto {
    @NotNull
    @Size(min=1)
    private String[] newFieldsNames;

    public DictionaryEditDto() {
        this.newFieldsNames = new String[ProductManagerConfig.MAX_DICTIONARY_ELEMENTS_SIZE];
    }
}
