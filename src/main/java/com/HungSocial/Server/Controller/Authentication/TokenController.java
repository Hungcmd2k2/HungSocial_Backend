package com.HungSocial.Server.Controller.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HungSocial.Server.DTO.Request.Auth.TokenRequest;
import com.HungSocial.Server.DTO.Response.ApiResponse;
import com.HungSocial.Server.Service.Authentication.TokenService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/logout")
    @SuppressWarnings("UseSpecificCatch")
    public ResponseEntity<ApiResponse<Object>> logout(@RequestBody TokenRequest tokenRequest) {
        try {
            tokenService.logout(tokenRequest); // Truyền `tokenRequest` thay vì `tokenRequest.getEmail()`

            ApiResponse<Object> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Token đã được lưu vào cơ sở dữ liệu thành công!",
                null
            );
            return ResponseEntity.ok(response);

        } catch (Exception e) { 
            ApiResponse<Object> errorResponse = new ApiResponse<>(
                "error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Đã xảy ra lỗi khi xử lý yêu cầu!",
                e.getMessage()
            );
            return ResponseEntity.ok(errorResponse);
        }
    }


    @PostMapping("/token")
    public ResponseEntity<ApiResponse<Object>> checkToken(@RequestBody TokenRequest tokenRequest) {
        
        boolean valid = tokenService.validateToken(tokenRequest.getEmail(), tokenRequest.getToken());

        if (valid) {
            ApiResponse<Object> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Token hợp lệ",
                null
            );
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(
                "success",
                HttpStatus.UNAUTHORIZED.value(),
                "Token không hợp lệ",
                null
            );
            return ResponseEntity.ok(response);
        }
    }
}
