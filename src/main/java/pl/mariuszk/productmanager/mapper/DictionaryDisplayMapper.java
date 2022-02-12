package pl.mariuszk.productmanager.mapper;

import org.springframework.stereotype.Service;
import pl.mariuszk.productmanager.model.frontend.DictionaryDisplayDto;
import pl.mariuszk.productmanager.model.frontend.DictionaryDto;

import java.util.List;

@Service
public class DictionaryDisplayMapper {

    public DictionaryDisplayDto map(DictionaryDto dictionaryDto, List<String> dictionariesInUse) {
        return DictionaryDisplayDto.builder()
                .id(dictionaryDto.getId())
                .name(dictionaryDto.getName())
                .isUsed(dictionariesInUse.contains(dictionaryDto.getName()))
                .build();
    }
}
