package pl.mariuszk.productmanager.model.frontend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DictionaryDisplayDto {

    private String id;
    private String name;
    private Boolean isUsed;
}
