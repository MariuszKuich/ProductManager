package pl.mariuszk.productmanager.mapper;

import org.springframework.stereotype.Service;
import pl.mariuszk.productmanager.model.frontend.DictionaryAddDto;
import pl.mariuszk.productmanager.model.rest.Dictionary;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DictionaryMapper {

    public Dictionary map(DictionaryAddDto dictionaryDto) {
        return Dictionary.builder()
                .name(dictionaryDto.getName())
                .values(Arrays.stream(dictionaryDto.getFieldsNames()).filter(Objects::nonNull).collect(Collectors.toList()))
                .build();
    }
}
