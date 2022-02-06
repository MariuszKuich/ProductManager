package pl.mariuszk.productmanager.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Document
@NoArgsConstructor
public class Dictionary {

    @Id
    private String id;
    @NotBlank
    private String name;
    @NotNull
    @Size(min=1)
    private List<String> values;
}
