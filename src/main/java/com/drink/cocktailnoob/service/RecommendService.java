package com.drink.cocktailnoob.service;

import com.drink.cocktailnoob.entity.Cocktail;
import com.drink.cocktailnoob.repository.CocktailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RecommendService {

    private final CocktailRepository CocktailRepository;

    @Autowired
    public RecommendService(com.drink.cocktailnoob.repository.CocktailRepository cocktailRepository) {
        CocktailRepository = cocktailRepository;
    }


    public ResponseEntity<Cocktail> recommend(String taste, Integer alcohol, String glass) {
        log.info("recommend()");
        log.info(taste);
        log.info(alcohol.toString());
        log.info(glass);

        try {
            Integer alcoholStart = alcohol - 1;
            Integer alcoholEnd = alcohol + 10;
            List<Cocktail> cocktailList = CocktailRepository.findByTasteAndAlcoholBetweenAndGlass(taste, alcoholStart, alcoholEnd, glass);
            log.info(cocktailList.toString());
            int size = cocktailList.size();
            if (size == 0) {
                return ResponseEntity.notFound().build();
            }else{

                int random = (int) (Math.random() * size);
                return ResponseEntity.ok(cocktailList.get(random));
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
