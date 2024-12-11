package com.HungSocial.Server.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableWebSocketMessageBroker
@PropertySource("classpath:application.properties")
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Value("${allowed.origins}")
    private String allowedOrigins;
    @Value("${client.url:}")
    private String clientUrl;
 @PostConstruct
    public void init() {
        System.out.println("Allowed Origins: " + allowedOrigins);
    }
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-socket","/ws-notifications") // Định nghĩa endpoint WebSocket
                .setAllowedOrigins("http://localhost:4200",clientUrl)
                .withSockJS(); // Sử dụng SockJS để fallback khi WebSocket không khả dụng
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // Broker để gửi tin nhắn
        registry.setApplicationDestinationPrefixes("/app"); // Tiền tố cho các tin nhắn từ client đến server
    }
    
}


