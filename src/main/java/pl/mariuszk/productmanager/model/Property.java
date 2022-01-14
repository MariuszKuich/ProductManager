package pl.mariuszk.productmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mariuszk.productmanager.enums.FieldType;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Property {
    private int id;
    private FieldType fieldType;
    private String label;
    private Object value;
}
