package pl.mariuszk.productmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.mariuszk.productmanager.enums.FieldType;
import pl.mariuszk.productmanager.model.ProductTemplate;
import pl.mariuszk.productmanager.model.Dictionary;
import pl.mariuszk.productmanager.repository.DictionaryRepository;
import pl.mariuszk.productmanager.repository.ProductTemplateRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ProductManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductManagerApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ProductTemplateRepository templateRepository, DictionaryRepository dictionaryRepository) {
        return args -> {
            tryAddTemplate(templateRepository, ProductTemplate.builder()
                    .name("Meble")
                    .fields(Map.of(
                            "Dlugosc", FieldType.LONG,
                            "Wysokosc", FieldType.LONG,
                            "Szerokosc", FieldType.DOUBLE,
                            "Kolor", FieldType.STRING_D))
                    .dictionaries(Collections.singletonMap("Kolor", "KOLORY"))
                    .build());
            tryAddTemplate(templateRepository, ProductTemplate.builder()
                    .name("Konsole")
                    .fields(Map.of(
                            "Pojemnosc dysku twardego", FieldType.STRING,
                            "Typ konsoli", FieldType.STRING,
                            "Kolor", FieldType.STRING_D,
                            "Kształt", FieldType.STRING_D))
                    .dictionaries(Map.of("Kolor", "KOLORY", "Kształt", "KSZTAŁTY"))
                    .build());

            tryAddDictionary(dictionaryRepository, Dictionary.builder()
                    .name("KOLORY")
                    .values(List.of("Czerwony", "Niebieski", "Zielony", "Czarny"))
                    .build());
            tryAddDictionary(dictionaryRepository, Dictionary.builder()
                    .name("ROZMIARY")
                    .values(List.of("XS", "S", "M", "L", "XL"))
                    .build());
            tryAddDictionary(dictionaryRepository, Dictionary.builder()
                    .name("KSZTAŁTY")
                    .values(List.of("KWADRAT", "KOŁO", "TRÓJKĄT"))
                    .build());
        };
    }

    private void tryAddTemplate(ProductTemplateRepository repository, ProductTemplate template) {
        repository.findProductTemplateByName(template.getName()).ifPresentOrElse(g -> {
            System.out.println("Podany szablon został już zapisany.");
        }, () -> {
            repository.save(template);
            System.out.println("Zapisano szablon " + template.getName());
        });
    }

    private void tryAddDictionary(DictionaryRepository repository, Dictionary dictionary) {
        repository.findDictionaryByName(dictionary.getName()).ifPresentOrElse(g -> {
            System.out.println("Podany słownik został już zapisany.");
        }, () -> {
            repository.save(dictionary);
            System.out.println("Zapisano słownik " + dictionary.getName());
        });
    }
}
