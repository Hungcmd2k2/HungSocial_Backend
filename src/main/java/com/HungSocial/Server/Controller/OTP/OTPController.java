package com.HungSocial.Server.Controller.OTP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HungSocial.Server.DTO.OTP.OTPRequest;
import com.HungSocial.Server.DTO.Response.ApiResponse;
import com.HungSocial.Server.Service.OTP.OTPService;

@RestController
@RequestMapping("/api/otp")
public class OTPController {

    @Autowired
    private OTPService otpService;

    // API để gửi OTP qua email
    @PostMapping("/send")
    public ResponseEntity<ApiResponse<Object>> sendOtp(@RequestBody OTPRequest otpRequest) {
        boolean sendOtp =  otpService.createAndSendOTP(otpRequest.getEmail());
        if(sendOtp){
            ApiResponse<Object> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Gửi mã otp thành công!",
                otpRequest.getEmail()
            );
            return ResponseEntity.ok(response);
        }
        else{
            ApiResponse<Object> response = new ApiResponse<>(
                "error",
                HttpStatus.NOT_FOUND.value(),
                "Gửi mã otp ko thành công!",
                null
            );
            return ResponseEntity.ok(response);
        }       
    }

    // API để xác minh OTP
    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<Object>> verifyOtp(@RequestBody OTPRequest otpRequest) {
        boolean isValid = otpService.verifyOTP(otpRequest.getEmail(), otpRequest.getOtp());
        if (isValid) {
            ApiResponse<Object> response = new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                " mã otp hợp lệ!",
                null
            );
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Object> response = new ApiResponse<>(
                "error",
                HttpStatus.NOT_FOUND.value(),
                "mã otp ko hợp lệ!",
                null
            );
            return ResponseEntity.ok(response);
        }
    }
}
