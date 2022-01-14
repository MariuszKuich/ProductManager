package pl.mariuszk.productmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbstractProduct {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String manufacturerCode;
    @NotBlank
    private String storeCode;
}
