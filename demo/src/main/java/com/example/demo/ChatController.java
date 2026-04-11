package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        // Route the message directly to the recipient's specific queue
        // Example: If recipient is "Alice", it goes to "/queue/Alice"
        messagingTemplate.convertAndSend("/queue/" + chatMessage.getRecipient(), chatMessage);
    }
}