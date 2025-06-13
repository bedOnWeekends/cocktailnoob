package com.drink.cocktailnoob.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ChatHistoryService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public ChatHistoryService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveChatHistory(String sessionId, List<String> history) {
        String string = "saveChatHistory()" + "\n" +
                sessionId + "\n" +
                history.toString();
        log.info(string);

        redisTemplate.opsForValue().set(sessionId, history);
    }

    public List<String> getChatHistory(String sessionId) {
        log.info("getChatHistory()");
        log.info(sessionId);

        return (List<String>) redisTemplate.opsForValue().get(sessionId);
    }

    public void removeChatHistory(String sessionId){
        log.info("removeChatHistory()");
        log.info(sessionId);

        redisTemplate.delete(sessionId);
    }
}