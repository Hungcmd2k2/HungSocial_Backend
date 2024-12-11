package com.HungSocial.Server.Config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;

@Configuration
public class WebConfig implements WebMvcConfigurer {

   
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") //Địa chỉ của client
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(false);// Không dùng cookie, chỉ xác thực qua token
    }

    // Cấu hình CORS trong security (nếu cần)
    @Bean
    public HttpSecurity httpSecurity(HttpSecurity http) throws Exception {
        http.cors(); // Kích hoạt CORS
        return http;
    }

    @Value("${server.port}")
    private String serverPort;

    @Value("${server.url:}")
    private String serverUrl;

    @PostConstruct
    public void init() {
        try {
            // Lấy địa chỉ IP của máy
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            // Thiết lập lại server.url
            if (serverUrl.isEmpty()) {
                System.setProperty("server.url", "http://" + hostAddress + ":" + serverPort);
                System.setProperty("client.url", "http://" + hostAddress + ":4200");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
