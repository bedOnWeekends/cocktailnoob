package com.drink.cocktailnoob.service;

import com.drink.cocktailnoob.entity.Cocktail;
import com.drink.cocktailnoob.repository.CocktailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@Slf4j
public class InsertService {

    private final CocktailRepository cocktailRepository;

    @Autowired
    public InsertService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }


    public void insertCocktail(String name, String glass, String image, String ingredients, String steps, String taste, Integer alcohol) {
        log.info("insertCocktail()");

        StringTokenizer st1 = new StringTokenizer(ingredients, ",");

        List<Cocktail.Ingredient> ingredientList = new ArrayList<>();
        while (st1.hasMoreTokens()){
            String ingredientString = st1.nextToken();
            StringTokenizer stIng = new StringTokenizer(ingredientString,":");
            ingredientList.add(new Cocktail.Ingredient(stIng.nextToken(), stIng.nextToken()));
        }
        Cocktail cocktail = new Cocktail(null, name, glass, image, ingredientList, steps, taste, alcohol);

        cocktailRepository.save(cocktail);
    }
}
