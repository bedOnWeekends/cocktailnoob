package com.drink.cocktailnoob.service;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ChatService {

    private final ChatClient chatClient;

    private final ChatHistoryService chatHistoryService;

    @Value("classpath:templates/bartender-role-prompt.st")
    private Resource getBartenderRole;

    @Value("classpath:templates/sihoon.st")
    private Resource getSihoon;

    @Value("${chat.password}")
    private String REAL_PASSWORD;

    @Autowired
    public ChatService(ChatClient.Builder builder, ChatHistoryService chatHistoryService) {
        this.chatClient = builder.build();
        this.chatHistoryService = chatHistoryService;
    }

    public ResponseEntity<String> chatGpt(String request, String password, HttpSession session) {
        try {
            if (!password.equals(REAL_PASSWORD)) {
                log.info("password is wrong: {}", session.getId());
                return ResponseEntity.badRequest().build();
            }
            String sessionId = session.getId();
            PromptTemplate promptTemplate = new PromptTemplate(getBartenderRole);
            List<String> historyList = chatHistoryService.getChatHistory(sessionId);

            if (historyList == null){
                historyList = new ArrayList<>();
            }

            Prompt prompt = promptTemplate.create(Map.of("request", request, "historyList", historyList));

            ChatResponse chatResponse = chatClient
                    .prompt(prompt)
                    .call()
                    .chatResponse();

            String answer = chatResponse
                    .getResult()
                    .getOutput()
                    .getContent();

            historyList.add(String.format("question: %s | answer: %s", request, answer));
            chatHistoryService.saveChatHistory(sessionId, historyList);

            return ResponseEntity.ok(answer);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.getStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 대화 요청을 처리하는 메소드
     * @param request 대화 내용
     * @param password 비밀번호
     * @param session 세션
     * @return 대화 결과
     */
    public ResponseEntity<String> chatGpt2(String request, String password, HttpSession session) {
        try {
            request = request.split("@")[1]; // @뒤에 있는 내용만 가져오기
            if (!password.equals(REAL_PASSWORD)) {
                log.info("password is wrong: {}", session.getId());
                return ResponseEntity.badRequest().build();
            }
            if (request.equals("삭제")){
                chatHistoryService.removeChatHistory("sihoon"); // sihoon 세션의 대화 내용 삭제
                return ResponseEntity.ok("삭제되었습니다.");
            }
            PromptTemplate promptTemplate = new PromptTemplate(getSihoon); // sihoon.st 템플릿 파일
            List<String> historyList = chatHistoryService.getChatHistory("sihoon"); // sihoon 세션의 대화 내용 가져오기

            if (historyList == null){
                historyList = new ArrayList<>();
            }

            Prompt prompt = promptTemplate.create(Map.of("request", request, "historyList", historyList)); // 템플릿 파일에 대화 내용과 대화 기록을 넣어서 prompt 생성

            ChatResponse chatResponse = chatClient
                    .prompt(prompt)
                    .call()
                    .chatResponse();

            String answer = chatResponse
                    .getResult()
                    .getOutput()
                    .getContent();
            log.info("answer: {}", answer);

            historyList.add(String.format("question: %s | answer: %s", request, answer)); // 대화 기록에 대화 내용과 대화 결과 추가
            chatHistoryService.saveChatHistory("sihoon", historyList); // sihoon 세션에 대화 기록 저장

            return ResponseEntity.ok(answer); // 대화 결과 반환
        } catch (Exception e) {
            log.error(e.getMessage());
            e.getStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}