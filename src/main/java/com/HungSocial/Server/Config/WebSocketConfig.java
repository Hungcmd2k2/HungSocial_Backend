package com.HungSocial.Server.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-socket") // Định nghĩa endpoint WebSocket
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS(); // Sử dụng SockJS để fallback khi WebSocket không khả dụng
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // Broker để gửi tin nhắn
        registry.setApplicationDestinationPrefixes("/app"); // Tiền tố cho các tin nhắn từ client đến server
    }
}


