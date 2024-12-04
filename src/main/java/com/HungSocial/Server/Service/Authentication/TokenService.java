package com.HungSocial.Server.Service.Authentication;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HungSocial.Server.DTO.Request.Auth.TokenRequest;
import com.HungSocial.Server.Entity.Token.TokenEntity;
import com.HungSocial.Server.Repository.Token.TokenRepository;
import com.HungSocial.Server.exception.AppException;
import com.HungSocial.Server.exception.ErrorCode;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
@Service
public class TokenService {

    protected static final String SIGNER_KEY = "ZD6omglDgYd6qxO8gTLZZoxOOfBHapKjf754nV8L9o1wxBH0jl0i1AW1oi/Lbygv";

     @Autowired
    private TokenRepository tokenRepository;

    // public TokenEntity saveToken(String token) throws Exception {
    //     // Phân tích token và trích xuất thông tin
    //     JWSObject jwsObject = JWSObject.parse(token);

    //     if (jwsObject.verify(new MACVerifier(SIGNER_KEY.getBytes()))) {
    //         // Trích xuất claims
    //         JWTClaimsSet claims = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
    //         String tokenId = claims.getJWTID();
    //         Date expiryTime = claims.getExpirationTime();
            
            

    //         // Lưu token vào cơ sở dữ liệu
    //         TokenEntity tokenEntity = new TokenEntity();
    //         tokenEntity.setTokenid(tokenId);
    //         tokenEntity.setExpirytime((java.sql.Date) expiryTime);
    //         return tokenRepository.save(tokenEntity);
    //     } else {
    //         throw new RuntimeException("Xác minh token thất bại");
    //     }
    // }


   public TokenEntity logout(TokenRequest tokenRequest) throws JOSEException, ParseException{
       var signToken = verifyToken(tokenRequest.getToken());

       String jit = signToken.getJWTClaimsSet().getJWTID();
       Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

       TokenEntity tokenEntity = new TokenEntity();
       
       tokenEntity.setTokenid(jit);
       tokenEntity.setExpirytime(expiryTime);
       tokenEntity.setEmail(tokenRequest.getEmail());

       // Lưu vào repository
    try {
        TokenEntity savedToken = tokenRepository.save(tokenEntity);
        return savedToken; // Trả về token đã lưu
    } catch (Exception e) {
        // Xử lý lỗi khi lưu token
        throw new AppException(ErrorCode.TOKEN_EXISTED);
    }

   }

    private SignedJWT verifyToken(String token) throws JOSEException,ParseException{
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if(!(verified && expiryTime.after(new Date()))){
            throw new AppException(ErrorCode.TOKEN_FALSE);
        }

        
        return signedJWT;
    }



     public boolean validateToken(String email, String token) {
        try {
            // Phân tích token thành JWSObject
            JWSObject jwsObject = JWSObject.parse(token);

            // Xác minh chữ ký của token
            if (!jwsObject.verify(new MACVerifier(SIGNER_KEY.getBytes()))) {
                return false;
            }

            // Lấy các claims từ token
            JWTClaimsSet claims = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());

            // Kiểm tra email và thời hạn của token
            String tokenEmail = claims.getSubject();
            Date expirationTime = claims.getExpirationTime();
          

            return (tokenEmail.equals(email) && expirationTime.after(new Date()));

        } catch (ParseException | JOSEException e) {
            return false;
        }
    }
}

