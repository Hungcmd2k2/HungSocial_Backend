package com.HungSocial.Server.Service.Authentication;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.HungSocial.Server.Entity.User.User;
import com.HungSocial.Server.Repository.User.UserRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;

@Service
public class UserAuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    protected static final String SIGNER_KEY = "ZD6omglDgYd6qxO8gTLZZoxOOfBHapKjf754nV8L9o1wxBH0jl0i1AW1oi/Lbygv";
    public String login(String email, String rawPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return createToken(email);
            }
        }
        return null;
    }
    
    //Kiểm tra mật khẩu đúng hay sai 
    public boolean checkPassWord(Integer userid,String password){
        Optional<User> userOptional = userRepository.findById(userid);
        // Kiểm tra nếu user tồn tại
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return  passwordEncoder.matches(password, user.getPassword());      
        }
        else{
            return false;
        }
    }
   

    private String createToken(String email) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jWTClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("HungService")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("customClaim", "custom")
                .build();
        Payload payload = new Payload(jWTClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
