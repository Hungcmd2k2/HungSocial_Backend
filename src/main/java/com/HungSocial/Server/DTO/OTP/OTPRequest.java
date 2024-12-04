package com.HungSocial.Server.DTO.OTP;

public class OTPRequest {
    private String userId;
    private String email;
    private String otp;

    // Constructor
    public OTPRequest(String userId, String email , String otp) {
        this.userId = userId;
        this.email = email;
        this.otp = otp;
    }

    // Getters và Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}