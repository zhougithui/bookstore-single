package org.bear.bookstore.web.ws;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("myHandler")
public class MyHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("websocket " + message.getPayload());
        try {
        	Thread.sleep(2000);  
			session.sendMessage(new TextMessage("give me five " + Math.random()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    
  
    @Override  
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {  
       log.info("session closed!");  
    }  
  
    @Override  
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {  
        log.info("session established!");  
    } 

}