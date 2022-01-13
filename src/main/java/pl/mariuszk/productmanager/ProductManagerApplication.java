package pl.mariuszk.productmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.mariuszk.productmanager.enums.FieldType;
import pl.mariuszk.productmanager.model.ProductTemplate;
import pl.mariuszk.productmanager.repository.ProductTemplateRepository;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class ProductManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductManagerApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ProductTemplateRepository repository) {
        return args -> {
            tryAddTemplate(repository, ProductTemplate.builder()
                    .name("Meble")
                    .fields(Map.of(
                            "Dlugosc", FieldType.LONG,
                            "Wysokosc", FieldType.LONG,
                            "Szerokosc", FieldType.DOUBLE,
                            "Kolor", FieldType.STRING))
                    .dictionaries(Collections.singletonMap("Kolor", "KOLORY"))
                    .build());
            tryAddTemplate(repository, ProductTemplate.builder()
                    .name("Konsole")
                    .fields(Map.of(
                            "Pojemnosc dysku twardego", FieldType.STRING,
                            "Typ konsoli", FieldType.STRING,
                            "Kolor", FieldType.STRING))
                    .dictionaries(Collections.singletonMap("Kolor", "KOLORY"))
                    .build());
        };
    }

    private void tryAddTemplate(ProductTemplateRepository repository, ProductTemplate template) {
        repository.findProductTemplateByName(template.getName()).ifPresentOrElse(g -> {
            System.out.println("Podany szablon został już zapisany.");
        }, () -> {
            ProductTemplate savedTemplate = repository.save(template);
            ProductTemplate retrievedTemplate = repository.findById(savedTemplate.getId()).get();
            System.out.println(retrievedTemplate);
        });
    }
}
