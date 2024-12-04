package com.HungSocial.Server.Config;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
     private final String[] PUBLIC_ENDPOINT={"/chat-socket/**","/api/files/{filename}","/api/users","/api/users/email/{email}","/api/users/username/{username}","/auth/login","/auth/token","/auth/logout","/api/users/all","/api/users/search/{keyword}"};
     protected static final String SIGNER_KEY ="ZD6omglDgYd6qxO8gTLZZoxOOfBHapKjf754nV8L9o1wxBH0jl0i1AW1oi/Lbygv";
     
    // .requestMatchers(HttpMethod.POST,PUBLIC_ENDPOINT).permitAll()
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception { 
        httpSecurity.authorizeHttpRequests(request->
        request.requestMatchers(HttpMethod.POST,PUBLIC_ENDPOINT).permitAll()
        .requestMatchers(HttpMethod.GET,PUBLIC_ENDPOINT).permitAll()
        .anyRequest().authenticated());

        httpSecurity.oauth2ResourceServer(oauth2 ->
            oauth2.jwt(jwtCofigurer -> jwtCofigurer.decoder(jwtDecoder())));

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
}
    @Bean
    JwtDecoder jwtDecoder(){
 SecretKeySpec secretKeySpec = new SecretKeySpec( SIGNER_KEY.getBytes(),"HS512");
    
    return NimbusJwtDecoder
                            .withSecretKey(secretKeySpec)
                            .macAlgorithm(MacAlgorithm.HS512)
                            .build();
    }
}
