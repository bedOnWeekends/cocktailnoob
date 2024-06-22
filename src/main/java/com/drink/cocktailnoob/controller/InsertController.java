package com.drink.cocktailnoob.controller;

import com.drink.cocktailnoob.service.InsertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class InsertController {

    @Autowired
    private InsertService insertService;

    @GetMapping("/insertCocktail")
    public String insertCocktail(){
        log.info("insertCocktail()");

        return "insertCocktail";
    }

    @PostMapping("/insertCocktail")
    public String insertCocktailPost(String name, String glass,
                                     String image, String ingredients,
                                     String steps, String taste,
                                     Integer alcohol){
        log.info("insertCocktailPost()");

        insertService.insertCocktail(name, glass, image, ingredients, steps, taste, alcohol);

        return "insertCocktail";
    }
}
