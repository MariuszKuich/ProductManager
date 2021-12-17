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
            Map<String, FieldType> fields = Map.of(
                    "Dlugosc", FieldType.LONG,
                    "Wysokosc", FieldType.LONG,
                    "Szerokosc", FieldType.LONG,
                    "Kolor", FieldType.STRING);
            ProductTemplate template = ProductTemplate.builder()
                    .name("Meble")
                    .fields(fields)
                    .dictionaries(Collections.singletonMap("Kolor", "KOLORY"))
                    .build();
            repository.findProductTemplateByName(template.getName()).ifPresentOrElse(g -> {
                System.out.println("Podany szablon został już zapisany.");
            }, () -> {
                ProductTemplate savedTemplate = repository.save(template);
                ProductTemplate retrievedTemplate = repository.findById(savedTemplate.getId()).get();
                System.out.println(retrievedTemplate);
            });
        };
    }
}
