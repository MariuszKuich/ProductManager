package pl.mariuszk.productmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mariuszk.productmanager.exception.DictionaryNotFoundException;
import pl.mariuszk.productmanager.service.DictionaryService;

@RequestMapping("/dictionary")
@Controller
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping("/list")
    public String getDictionaryListPage(Model model) {
        model.addAttribute("dictionaries", dictionaryService.getAllDictionariesDto());

        return "dictionary-list";
    }

    @GetMapping("/{dictionaryId}/describe")
    public String getDictionaryDetailsPage(@PathVariable("dictionaryId") String dictionaryId, Model model) throws DictionaryNotFoundException {
        model.addAttribute("dictionary", dictionaryService.getDictionaryById(dictionaryId));

        return "dictionary-details";
    }
}
