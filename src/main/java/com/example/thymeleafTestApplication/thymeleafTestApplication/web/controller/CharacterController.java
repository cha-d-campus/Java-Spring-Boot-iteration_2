package com.example.thymeleafTestApplication.thymeleafTestApplication.web.controller;

import com.example.thymeleafTestApplication.thymeleafTestApplication.model.Character;
import com.example.thymeleafTestApplication.thymeleafTestApplication.model.CharacterForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class CharacterController {

    private static int tabPV[] = new int [11];

    private static String tabType[] = new String[] {"Magicien", "Guerrier"};
    private static List<Character> characterList = new ArrayList<Character>();

    static {
        characterList.add(new Character(6, "Lux", "Magicien", 5, "Lux_0.jpg"));
        characterList.add(new Character(2, "Garen", "Guerrier", 9, "Garen_0.jpg"));
        characterList.add(new Character(9, "Veigar", "Magicien", 5, "Veigar_0.jpg"));
        characterList.add(new Character(10, "Morgana", "Magicien", 6, "Morgana_0.jpg"));
        characterList.add(new Character(42, "Sett", "Guerrier", 8, "Sett_0.jpg"));
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
        model.addAttribute("tabPV", tabPV);
        model.addAttribute("tabType", tabType);
        return "addCharacter";
    }

    @RequestMapping(value = {"/characters/addcharacter"}, method = RequestMethod.POST)
    public String saveCharacter(Model model, //
            @ModelAttribute("characterForm") CharacterForm characterForm) {
        String name = characterForm.getName();
        String type = characterForm.getType();
        String image = characterForm.getName()+"_0.jpg";
        int lifepoints = characterForm.getLifepoints();
        Random randId = new Random();
        int upperbound = 500;
        boolean idExist = characterList
                .stream()
                .map(Character::getId)
                .anyMatch(randId::equals);
        if (idExist) {
            return "addCharacter";
        }
        int id = randId.nextInt(upperbound);

        boolean nameIsExist = characterList
                .stream()
                .map(Character::getName)
                .anyMatch(name::equals);
        if (nameIsExist) {
            model.addAttribute("errorMessageSameName", errorMessageSameName);
            model.addAttribute("tabPV", tabPV);
            model.addAttribute("tabType", tabType);
            return "addCharacter";
        } else if (name.isEmpty()) {
            model.addAttribute("errorMessageEmptyName", errorMessageEmptyName);
            model.addAttribute("tabPV", tabPV);
            model.addAttribute("tabType", tabType);
            return "addCharacter";
        }else if (type == null || type.isEmpty()) {
            model.addAttribute("errorMessageEmptyType", errorMessageEmptyType);
            model.addAttribute("tabPV", tabPV);
            model.addAttribute("tabType", tabType);
            return "addCharacter";
        } else if (lifepoints == 0) {
            model.addAttribute("errorMessageEmptyLifepoints", errorMessageEmptyLifepoints);
            model.addAttribute("tabPV", tabPV);
            model.addAttribute("tabType", tabType);
            return "addCharacter";
        } else {
            Character newCharacter = new Character(id, name, type, lifepoints, image);

            characterList.add(newCharacter);

            return "redirect:/characters";
        }
    }

    @RequestMapping("/characters/{id}")
    public String displayACharacter(Model model, @PathVariable int id) {
        Character character = characterList
                .stream()
                .filter(x -> id == x.getId())
                .findFirst()
                .orElse(null);
        if(character == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("character", character);
        return "myCharacter";
    }
}
