package com.drink.cocktailnoob.controller;

import com.drink.cocktailnoob.entity.Cocktail;
import com.drink.cocktailnoob.service.ChatService;
import com.drink.cocktailnoob.service.MapService;
import com.drink.cocktailnoob.service.RecommendService;
import com.drink.cocktailnoob.service.SearchService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RestApiController {

    private final ChatService chatService;
    private final MapService mapService;
    private final SearchService searchService;
    private final RecommendService recommendService;

    @Autowired
    public RestApiController(ChatService chatService, MapService mapService, SearchService searchService, RecommendService recommendService) {
        this.chatService = chatService;
        this.mapService = mapService;
        this.searchService = searchService;
        this.recommendService = recommendService;
    }

    @GetMapping("/cocktails")
    public ResponseEntity<?> recommend(@RequestParam(required = false) String taste,
                                       @RequestParam(required = false) Integer alcohol,
                                       @RequestParam(required = false) String glass){
        log.info("recommend()");

        if (taste != null && alcohol != null && glass != null){
            return recommendService.recommend(taste, alcohol, glass);
        } else if (taste == null && alcohol == null && glass == null) {
            return searchService.getAllCocktail();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/chat-gpt")
    public ResponseEntity<String> chatGpt(@RequestParam String request,
                                          @RequestParam String password, HttpSession session){
        log.info("chatGpt()");

        return chatService.chatGpt(request, password, session);
    }

    @PostMapping("/other-chat")
    public ResponseEntity<String> otherChat(@RequestParam String request,
                                            @RequestParam String password, HttpSession session){
        log.info("otherChat()");

        return chatService.chatGpt2(request, password, session);
    }


}
