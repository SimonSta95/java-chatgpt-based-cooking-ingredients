package com.example.chatgptbasedcookingingredients;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public String categorizeIngredient(@RequestBody String ingredient) {

        String prompt = "Is " + ingredient + " Vegan, Vegetarian or Regular?";

        return ingredientService.getAnswerFromOpenAi(prompt);
    }

}
