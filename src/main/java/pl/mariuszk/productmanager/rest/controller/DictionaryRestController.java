package pl.mariuszk.productmanager.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mariuszk.productmanager.exception.DictionaryNotFoundException;
import pl.mariuszk.productmanager.model.frontend.DictionaryDto;
import pl.mariuszk.productmanager.model.rest.Dictionary;
import pl.mariuszk.productmanager.service.DictionaryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dictionary")
public class DictionaryRestController {

    private final DictionaryService dictionaryService;

    @GetMapping("/available-dictionaries")
    public ResponseEntity<List<DictionaryDto>> getAvailableDictionaries() {
        return ResponseEntity.ok(dictionaryService.getAllDictionariesDto());
    }

    @GetMapping("/describe")
    public ResponseEntity<Dictionary> getDictionaryFullInfo(@RequestParam("id") String dictionaryId) {
        try {
            return ResponseEntity.ok(dictionaryService.getDictionaryById(dictionaryId));
        }
        catch (DictionaryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
