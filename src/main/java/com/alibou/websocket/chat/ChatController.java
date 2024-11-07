package com.alibou.websocket.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage") // what is the URL that I want to use to invoke method send message
    @SendTo("/topic/public") // where to send
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage // automatically send /topic/public
    ) {
        return chatMessage;
    }

    /* /topic is from > WebSocketConfig.java */

    @MessageMapping("/chat.addUser") // when a new user connects to our chat application
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
