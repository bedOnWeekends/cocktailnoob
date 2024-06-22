package com.drink.cocktailnoob.repository;


import com.drink.cocktailnoob.entity.Cocktail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CocktailRepository extends MongoRepository<Cocktail, String> {
}
