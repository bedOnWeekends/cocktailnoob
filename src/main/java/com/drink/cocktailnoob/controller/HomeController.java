package com.drink.cocktailnoob.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class HomeController {

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
}
