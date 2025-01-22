package ru.Garsone_Perro.Backend.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.Garsone_Perro.Backend.Entities.ChatMessage;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.register/{conversationId}/{userId}")
    public ResponseEntity<String> register(@DestinationVariable("conversationId") Long conversationId, @DestinationVariable("userId") Long userId, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("Регистрация");
        try {
            headerAccessor.getSessionAttributes().put("userId", userId);
            headerAccessor.getSessionAttributes().put("conversationId", conversationId);
            System.out.println("Пользователь " + userId + " зарегистрирован в чате " + conversationId);
            return ResponseEntity.ok("Регистрация успешна");
        } catch (Exception e) {
            System.err.println("Ошибка регистрации: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Регистрация не удалась");
        }
    }

    @MessageMapping("/chat.send/{conversationId}")
    @SendTo("/topic/conversation/{conversationId}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor, @DestinationVariable("conversationId") Long conversationId) {
        Long senderId = (Long) headerAccessor.getSessionAttributes().get("userId");
        Long sessionConverstionId = (Long) headerAccessor.getSessionAttributes().get("conversationId");

        System.out.println("Чат: " + headerAccessor.getSessionAttributes().get("conversationId"));
        
        if (senderId == null || sessionConverstionId == null || !sessionConverstionId.equals(conversationId)) {
            throw new IllegalStateException("Пользователь не зарегистрирован или ID беседы не соответствует");
        }

        chatMessage.setSenderId(senderId);
        chatMessage.setConversationId(conversationId);
        System.out.println("Сообщение от пользователя с ID: " + senderId + ", Содержание: " + chatMessage.getContent() + ", Чат: " + conversationId);
        return chatMessage;
    }
}
