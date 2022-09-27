package com.example.thymeleafTestApplication.thymeleafTestApplication.web.controller;

import com.example.thymeleafTestApplication.thymeleafTestApplication.model.Character;
import com.example.thymeleafTestApplication.thymeleafTestApplication.model.CharacterForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Controller
public class CharacterController {

    private static int tabPV[] = new int[11];

    private static String tabType[] = new String[]{"Magicien", "Guerrier"};
    private static List<Character> characterList = new ArrayList<Character>();
    private RestTemplate restTemplate = new RestTemplate();
    static final String URL_CHARACTERS = "http://localhost:8081/characters";
    static final String URL_CREATE_CHARACTER = "http://localhost:8081/characters";

    static final String URL_EDIT_CHARACTER = "http://localhost:8081/characters/edit";

    static final String URL_CHARACTERS_XML = "http://localhost:8081/characters.xml";
    static final String URL_CHARACTERS_JSON = "http://localhost:8081/characters.json";

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


    @GetMapping
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @GetMapping("/characters")
    public String listCharacters(Model model) {
        Character[] characterList = restTemplate.getForObject(URL_CHARACTERS, Character[].class);
        model.addAttribute("characterList", characterList);
        return "characterList";
    }

    @GetMapping("/characters/addcharacter")
    public String addCharacterForm(Model model) {
        CharacterForm characterForm = new CharacterForm();
        model.addAttribute("characterForm", characterForm);
        model.addAttribute("tabPV", tabPV);
        model.addAttribute("tabType", tabType);
        return "addCharacter";
    }

    @PostMapping("/characters/addcharacter")
    public String saveCharacter(Model model, //
                                @ModelAttribute("characterForm") CharacterForm characterForm) {
        String name = characterForm.getName();
        String type = characterForm.getType();
        String image = characterForm.getName() + "_0.jpg";
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
        } else if (type == null || type.isEmpty()) {
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

            restTemplate.postForLocation(URL_CREATE_CHARACTER, newCharacter);


            return "redirect:/characters";

        }
    }

    @GetMapping("/characters/{id}")
    public String displayCharacter(Model model, @PathVariable int id) {
        Character character = restTemplate.getForObject(URL_CHARACTERS + "/" + id, Character.class);
        model.addAttribute("character", character);
        return "myCharacter";
    }

    @GetMapping("/characters/edit/{id}")
    public String editCharacterForm(Model model, @PathVariable int id) {
        Character character = restTemplate.getForObject(URL_CHARACTERS + '/' + id, Character.class);
        System.out.println("passé ici");
        model.addAttribute("character", character);
        model.addAttribute("tabPV", tabPV);
        model.addAttribute("tabType", tabType);
        return "editCharacter";
    }
  @PutMapping("/characters/edit/{id}")
    public String editCharacter(@PathVariable int id, @ModelAttribute Character character) {
        character.setId(id);
        character.setImage(character.getName()+"_0.jpg");
      System.out.println("hey hoooo");
      restTemplate.put(URL_CHARACTERS, character);

      return "redirect:/characters";
  }

  @DeleteMapping("/characters/{id}")
    public String deleteCharacter(@PathVariable int id) {
      restTemplate.delete(URL_CHARACTERS + '/' + id);
        return  "redirect:/characters";
  }
}
