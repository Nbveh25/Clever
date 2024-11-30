package services;

public interface EmailService {
    void sendVerificationCode(String code, String email);
}