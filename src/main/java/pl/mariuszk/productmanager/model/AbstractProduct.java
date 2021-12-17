package pl.mariuszk.productmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbstractProduct {
    private String name;
    private String description;
    private String manufacturerCode;
    private String storeCode;
}
