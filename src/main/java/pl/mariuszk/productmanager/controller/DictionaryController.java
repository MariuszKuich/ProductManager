package pl.mariuszk.productmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mariuszk.productmanager.config.ProductManagerConfig;
import pl.mariuszk.productmanager.exception.DictionaryNotFoundException;
import pl.mariuszk.productmanager.model.frontend.DictionaryAddDto;
import pl.mariuszk.productmanager.service.DictionaryService;

import javax.validation.Valid;

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

    @GetMapping("/add")
    public String getDictionaryAddPage(Model model) {
        model.addAttribute("dictionaryDto", new DictionaryAddDto());
        model.addAttribute("maxDictionaryElementsNumber", ProductManagerConfig.MAX_DICTIONARY_ELEMENTS_SIZE);

        return "dictionary-add";
    }

    @PostMapping("/add")
    public String saveDictionary(@ModelAttribute("dictionaryDto") @Valid DictionaryAddDto dictionaryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "dictionary-add";
        }
        dictionaryService.saveDictionary(dictionaryDto);

        return "redirect:/dictionary/list";
    }
}
