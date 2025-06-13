package com.drink.cocktailnoob.service;

import com.drink.cocktailnoob.entity.Cocktail;
import com.drink.cocktailnoob.repository.CocktailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SearchService {
    private final CocktailRepository cocktailRepository;

    @Autowired
    public SearchService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }


    public ResponseEntity<List<Cocktail>> getAllCocktail() {
        log.info("getAllCocktail()");

        try {
            List<Cocktail> cocktailList = cocktailRepository.findAll();
            return ResponseEntity.ok(cocktailList);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
