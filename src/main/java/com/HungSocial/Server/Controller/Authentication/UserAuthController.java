package com.HungSocial.Server.Controller.Authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HungSocial.Server.DTO.Login.LoginResponse;
import com.HungSocial.Server.DTO.Login.UserLoginRequest;
import com.HungSocial.Server.DTO.Request.Auth.CheckPassword;
import com.HungSocial.Server.DTO.Response.ApiResponse;
import com.HungSocial.Server.Entity.User.User;
import com.HungSocial.Server.Service.Authentication.UserAuthService;
import com.HungSocial.Server.Service.User.UserService;


@RestController
@RequestMapping("/auth")
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(@RequestBody UserLoginRequest userLoginRequest) {
        String isAuthenticated = userAuthService.login(userLoginRequest.getEmail(), userLoginRequest.getPassword());
       Optional<User> userRequest = userService.getUserByEmail(userLoginRequest.getEmail());
       User user = userRequest.get();
        Integer  userId= user.getId();
        String username=user.getUsername();

        if (isAuthenticated != null) {
            LoginResponse loginResponse = new LoginResponse(userId,username,userLoginRequest.getEmail(),isAuthenticated);
            ApiResponse<Object> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Mật khẩu đúng",
                loginResponse
            );
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(
                "success",
                HttpStatus.UNAUTHORIZED.value(),
                "Mật khẩu sai",
                null
            );
            return ResponseEntity.ok(response);
        }
    }
    
    @PostMapping("/checkpassword")
    public ResponseEntity<ApiResponse<Object>> checkPassword(@RequestBody CheckPassword checkPassword) {
        boolean result = userAuthService.checkPassWord(checkPassword.getUserid(), checkPassword.getPassword());
        if(result){
            ApiResponse<Object> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Mật khẩu đúng",
                null
            );
            return ResponseEntity.ok(response);
        }
        else{
            ApiResponse<Object> response = new ApiResponse<>(
                "success",
                HttpStatus.UNAUTHORIZED.value(),
                "Mật khẩu sai",
                null
            );
            return ResponseEntity.ok(response); 
        }
    }
    
    

    
    
}
