package com.drink.cocktailnoob.repository;


import com.drink.cocktailnoob.entity.Cocktail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CocktailRepository extends MongoRepository<Cocktail, String> {
    List<Cocktail> findByTasteAndAlcoholBetweenAndGlass(String taste, Integer alcoholStart, Integer alcoholEnd, String glass);
}
