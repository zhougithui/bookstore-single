package org.bear.bookstore.web.ws;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    
	@Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Map attributes) throws Exception {
        log.info("============Before Handshake===========");
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Exception ex) {
        log.info("============After Handshake==============");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}