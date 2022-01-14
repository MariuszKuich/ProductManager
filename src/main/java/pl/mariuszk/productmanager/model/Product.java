package pl.mariuszk.productmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Document
@NoArgsConstructor
public class Product extends AbstractProduct {
    @Id
    private String id;
    @NotNull
    @Size(min=1)
    private List<Property> properties;
}
