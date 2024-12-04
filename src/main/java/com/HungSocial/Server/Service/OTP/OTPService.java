package com.HungSocial.Server.Service.OTP;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.HungSocial.Server.Entity.OTP.OTPData;

@Service
public class OTPService {

    // Bộ nhớ tạm để lưu OTP cho mỗi người dùng
    private final Map<String, OTPData> otpStore = new HashMap<>();

    // Inject JavaMailSender để gửi email
    @Autowired
    private JavaMailSender mailSender;

    // Tạo mã OTP ngẫu nhiên gồm 6 chữ số
    private String generateOTP() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    // Gửi OTP qua email và lưu vào bộ nhớ với thời gian hết hạn
    public void createAndSendOTP(String userId, String email) {
        String otp = generateOTP();
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5); // OTP hết hạn sau 5 phút
        otpStore.put(userId, new OTPData(otp, expiryTime));

        // Gửi OTP qua email
        sendEmail(email, otp);
    }

    // Gửi email chứa OTP
    private void sendEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);
    }

    // Xác minh mã OTP
    public boolean verifyOTP(String userId, String userInputOtp) {
        OTPData otpData = otpStore.get(userId);

        // Kiểm tra OTP có hợp lệ và chưa hết hạn
        if (otpData == null || otpData.getExpiryTime().isBefore(LocalDateTime.now())) {
            return false;
        }

        // Kiểm tra mã OTP có đúng không
        return otpData.getOtp().equals(userInputOtp);
    }
}

