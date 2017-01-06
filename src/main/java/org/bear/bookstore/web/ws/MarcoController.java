package org.bear.bookstore.web.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller  
public class MarcoController {  
  
    @MessageMapping("/marco")  
    public Shout handleShout(Shout incoming) {  
        log.info("Received message: " + incoming.getMessage());  
  
        try { Thread.sleep(2000); } catch (InterruptedException e) {}  
  
        Shout outgoing = new Shout();  
        outgoing.setMessage("Polo!");  
  
        return outgoing;  
    }  
}  