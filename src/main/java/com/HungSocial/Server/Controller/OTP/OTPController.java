package com.HungSocial.Server.Controller.OTP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HungSocial.Server.DTO.OTP.OTPRequest;
import com.HungSocial.Server.Service.OTP.OTPService;

@RestController
@RequestMapping("/otp")
public class OTPController {

    @Autowired
    private OTPService otpService;

    // API để gửi OTP qua email
    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestBody OTPRequest otpRequest) {
        otpService.createAndSendOTP(otpRequest.getUserId(), otpRequest.getEmail());
        return ResponseEntity.ok("OTP has been sent to " + otpRequest.getEmail());
    }

    // API để xác minh OTP
    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody OTPRequest otpRequest) {
        boolean isValid = otpService.verifyOTP(otpRequest.getUserId(), otpRequest.getOtp());
        if (isValid) {
            return ResponseEntity.ok("OTP verified successfully!");
        } else {
            return ResponseEntity.status(400).body("Invalid OTP or OTP expired.");
        }
    }
}
