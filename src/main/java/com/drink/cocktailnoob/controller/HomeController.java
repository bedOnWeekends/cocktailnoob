package com.drink.cocktailnoob.controller;

import com.drink.cocktailnoob.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }


    @GetMapping("/")
    public String index(){
        log.info("index()");

        return "index";
    }

    @GetMapping("/recommend")
    public String recommend(){
        log.info("recommend()");

        return "recommend";
    }

    @GetMapping("/search")
    public String search(){
        log.info("search()");

        return "search";
    }

    @GetMapping("/chat")
    public String chat(){
        log.info("chat()");

        return "chat";
    }

    @GetMapping("/map")
    public String map(){
        log.info("map()");

        return "map";
    }

    @GetMapping("/cocktail-info/{id}")
    public String cocktailInfo(@PathVariable String id, Model model){
        log.info("cocktailInfo()");

        return homeService.cocktailInfo(id, model);
    }

}
