package com.drink.cocktailnoob.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "cocktail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cocktail {
    private String id;
    private String name;
    private String glass;
    private String image;
    private List<Ingredient> ingredients;
    private String steps;
    private String taste;
    private Integer alcohol;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Ingredient {
        private String name;
        private String amount;
    }
}
