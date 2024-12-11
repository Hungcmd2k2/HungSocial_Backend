package com.HungSocial.Server.Service.OTP;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
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
    public boolean  createAndSendOTP(String email) {
        String otp = generateOTP();
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5); // OTP hết hạn sau 5 phút
        otpStore.put(email, new OTPData(otp, expiryTime));

        // Gửi OTP qua email
       return sendEmail(email, otp);
    }

    // Gửi email chứa OTP
    private boolean  sendEmail(String toEmail, String otp) {
       try {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);
        return true;
    } catch (MailException e) {
        return false;
    }
    }

    // Xác minh mã OTP
    public boolean verifyOTP(String email, String userInputOtp) {
        OTPData otpData = otpStore.get(email);

        // Kiểm tra OTP có hợp lệ và chưa hết hạn
        if (otpData == null || otpData.getExpiryTime().isBefore(LocalDateTime.now())) {
            return false;
        }

        // Kiểm tra mã OTP có đúng không
        return otpData.getOtp().equals(userInputOtp);
    }
}

