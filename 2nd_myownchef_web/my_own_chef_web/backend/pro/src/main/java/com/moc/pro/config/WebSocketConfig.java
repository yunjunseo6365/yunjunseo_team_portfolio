package com.moc.pro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket 설정
 * STOMP over SockJS 사용
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 메시지 브로커 설정
     * - /topic: 브로드캐스트 (구독)
     * - /app: 서버로 메시지 전송
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 클라이언트가 구독할 prefix
        config.enableSimpleBroker("/topic");
        
        // 클라이언트가 메시지를 보낼 prefix
        config.setApplicationDestinationPrefixes("/app");
    }

    /**
     * WebSocket 엔드포인트 설정
     * - /ws/chat: WebSocket 연결 엔드포인트
     * - SockJS fallback 지원
     * - CORS 허용
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat")
                .setAllowedOrigins("http://localhost:3010")
                .withSockJS();
    }
}
