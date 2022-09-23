package com.example.thymeleafTestApplication.thymeleafTestApplication;

//import com.example.thymeleafTestApplication.thymeleafTestApplication.model.Character;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class ThymeleafTestApplication {
    static final String URL_CHARACTERS = "http://localhost:8081/characters";

    static final String URL_CHARACTERS_XML = "http://localhost:8081/characters.xml";
    static final String URL_CHARACTERS_JSON = "http://localhost:8081/characters.json";

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafTestApplication.class, args);
        System.out.println("HELLOOOOOOOO !!!");

//        // HttpHeaders
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
//        // Request to return JSON format
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("my_other_key", "my_other_value");
//
//        // HttpEntity<Character[]>: To get result as String.
//        HttpEntity<Character[]> entity = new HttpEntity<Character[]>(headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Send request with GET method and default Headers.
//        ResponseEntity <Character[]> response = restTemplate.exchange(URL_CHARACTERS,//
//                HttpMethod.GET, entity, Character[].class);
//
//        HttpStatus statusCode = response.getStatusCode();
//        System.out.println("Response Satus Code: " + statusCode);
//
//        // Status Code: 200
//        if (statusCode == HttpStatus.OK) {
//            // Response Body Data
//            Character[] list = response.getBody();
//
//            if (list != null) {
//                for (Character e : list) {
//                    System.out.println("Personnages : " + e.getId() + " - " + e.getName());
//                }
//            }
//        }

    }

}

