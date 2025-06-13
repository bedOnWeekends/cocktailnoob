package com.drink.cocktailnoob.config;

import com.drink.cocktailnoob.service.ChatHistoryService;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CustomSessionListener implements HttpSessionListener {

    private final ChatHistoryService chatHistoryService;

    @Autowired
    public CustomSessionListener(ChatHistoryService chatHistoryService) {
        this.chatHistoryService = chatHistoryService;
    }

    @Override
    public void sessionCreated(HttpSessionEvent event){
        HttpSession session = event.getSession();

        log.info("new session created");
        log.info(session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event){
        HttpSession session = event.getSession();
        String sessionId = session.getId();

        log.info("session destroyed");
        log.info(sessionId);

        chatHistoryService.removeChatHistory(sessionId);
    }


}
