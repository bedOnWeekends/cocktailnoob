package com.drink.cocktailnoob.service;

import com.drink.cocktailnoob.entity.Cocktail;
import com.drink.cocktailnoob.entity.Cocktail.Ingredient;
import com.drink.cocktailnoob.repository.CocktailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class HomeService {

    private final CocktailRepository cocktailRepository;

    @Autowired
    public HomeService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }


    public String cocktailInfo(String id, Model model) {
        log.info("cocktailInfo()");

        Cocktail cocktail = cocktailRepository.findById(id).orElse(null);
        if (cocktail == null){
            cocktail = new Cocktail(null, "None", "None", "/images/cocktail-svgrepo.svg", List.of(new Ingredient("None", "None")), "None", "None", 0);
        }
        log.info(cocktail.getName());
        model.addAttribute("cocktail", cocktail);

        return "cocktailInfo";
    }

}
