package com.HungSocial.Server.Entity.OTP;

import java.time.LocalDateTime;

public class OTPData {
    private String otp;           // Mã OTP
    private LocalDateTime expiryTime;  // Thời gian hết hạn của OTP

    // Constructor
    public OTPData(String otp, LocalDateTime expiryTime) {
        this.otp = otp;
        this.expiryTime = expiryTime;
    }

    // Getters và Setters
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
}
