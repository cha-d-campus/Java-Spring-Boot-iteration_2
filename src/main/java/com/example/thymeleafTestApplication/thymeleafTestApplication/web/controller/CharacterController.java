package com.example.thymeleafTestApplication.thymeleafTestApplication.web.controller;

import com.example.thymeleafTestApplication.thymeleafTestApplication.model.Character;
import com.example.thymeleafTestApplication.thymeleafTestApplication.model.CharacterForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CharacterController {
    private static List<Character> characterList = new ArrayList<Character>();

    static {
        characterList.add(new Character(5, "Lux", "Magicien", 4));
        characterList.add(new Character(2, "Garen", "Guerrier", 9));
        characterList.add(new Character(9, "Veigar", "Magicien", 5));
        characterList.add(new Character(9, "Morgana", "Magicien", 6));
        characterList.add(new Character(42, "Sett", "Guerrier", 8));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message.emptyName}")
    private String errorMessageEmptyName;

    @Value("${error.message.sameName}")
    private String errorMessageSameName;

    @Value("${error.message.emptyType}")
    private String errorMessageEmptyType;

    @Value("${error.message.emptyLifepoints}")
    private String errorMessageEmptyLifepoints;

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @RequestMapping("/characters")
    public String listCharacters(Model model) {
        model.addAttribute("characterList", characterList);
        return "characterList";
    }

    @RequestMapping(value = {"/characters/addcharacter"}, method = RequestMethod.GET)
    public String showAddCharacterPage(Model model) {
        CharacterForm characterForm = new CharacterForm();
        model.addAttribute("characterForm", characterForm);
        return "addCharacter";
    }

    @RequestMapping(value = {"/characters/addcharacter"}, method = RequestMethod.POST)
    public String saveCharacter(Model model, //
                                @ModelAttribute("characterForm") CharacterForm characterForm) {
        String name = characterForm.getName();
        String type = characterForm.getType();
        int lifepoints = characterForm.getLifepoints();

        boolean nameIsExist = characterList
                .stream()
                .map(Character::getName)
                .anyMatch(name::equals);
        if (nameIsExist) {
            model.addAttribute("errorMessageSameName", errorMessageSameName);
            return "addCharacter";
        } else if (name == null || name.isEmpty()) {
            model.addAttribute("errorMessageEmptyName", errorMessageEmptyName);
            return "addCharacter";
        }else if (type == null || type.isEmpty()) {
            model.addAttribute("errorMessageEmptyType", errorMessageEmptyType);
            return "addCharacter";
        } else if (lifepoints == 0) {
            model.addAttribute("errorMessageEmptyLifepoints", errorMessageEmptyLifepoints);
            return "addCharacter";
        } else {
            Character newCharacter = new Character(50, name, type, lifepoints);

            characterList.add(newCharacter);

            return "redirect:/characters";
        }
    }
}
