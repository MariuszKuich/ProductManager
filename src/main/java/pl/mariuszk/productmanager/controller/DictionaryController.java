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
import org.springframework.web.bind.annotation.RequestParam;
import pl.mariuszk.productmanager.config.ProductManagerConfig;
import pl.mariuszk.productmanager.exception.DictionaryNotFoundException;
import pl.mariuszk.productmanager.model.frontend.DictionaryAddDto;
import pl.mariuszk.productmanager.model.frontend.DictionaryEditDto;
import pl.mariuszk.productmanager.model.Dictionary;
import pl.mariuszk.productmanager.service.DictionaryService;

import javax.validation.Valid;

@RequestMapping("/dictionary")
@Controller
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping("/list")
    public String getDictionaryListPage(Model model) {
        model.addAttribute("dictionaries", dictionaryService.getAllDictionariesDisplayDto());

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
        model.addAttribute("existingDictionaries", dictionaryService.getExistingDictionariesNames());

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

    @GetMapping("/edit")
    public String getDictionaryEditPage(@RequestParam("dictionaryId") String dictionaryId, Model model) throws DictionaryNotFoundException {
        Dictionary dictionary = dictionaryService.getDictionaryById(dictionaryId);

        model.addAttribute("dictionary", dictionary);
        model.addAttribute("dictionaryEditDto", new DictionaryEditDto());
        model.addAttribute("maxDictionaryElementsNumber", ProductManagerConfig.MAX_DICTIONARY_ELEMENTS_SIZE);
        model.addAttribute("currentElementIndex", dictionary.getValues().size());

        return "dictionary-edit";
    }

    @PostMapping("/edit")
    public String editDictionary(@RequestParam("dictionaryId") String dictionaryId, DictionaryEditDto dictionaryEditDto) throws DictionaryNotFoundException {
        dictionaryService.saveNewElementsInDictionary(dictionaryId, dictionaryEditDto);

        return "redirect:/dictionary/list";
    }

    @PostMapping("/{dictionaryId}/delete")
    public String deleteDictionary(@PathVariable(name = "dictionaryId") String dictionaryId) {
        if (dictionaryService.dictionaryExists(dictionaryId)) {
            dictionaryService.deleteDictionary(dictionaryId);
        }

        return "redirect:/dictionary/list";
    }
}
