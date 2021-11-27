package com.pihotel.controller.modelview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import com.pihotel.websocket.ChatMessage;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WebSocketEventListener {
	
	@Autowired
	private SimpMessageSendingOperations sendingOperations;
	
	@EventListener
	public void handleWebSocketConnectListener(final SessionConnectedEvent event) {
		log.info("Received a new web socket connection");
	}
	
	@EventListener
	public void handleWebSocketDisconnectListener(final SessionConnectedEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null) {
            log.info("User Disconnected : {}", username);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.setSender(username);

            sendingOperations.convertAndSend("/topic/public", chatMessage);
        }
	}
	
}
