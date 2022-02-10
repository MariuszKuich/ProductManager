package pl.mariuszk.productmanager.model.frontend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pl.mariuszk.productmanager.config.ProductManagerConfig;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder
public class DictionaryAddDto {
    @NotBlank
    private String name;
    @NotNull
    @Size(min=1)
    private String[] fieldsNames;

    public DictionaryAddDto() {
        this.fieldsNames = new String[ProductManagerConfig.MAX_DICTIONARY_ELEMENTS_SIZE];
    }
}
