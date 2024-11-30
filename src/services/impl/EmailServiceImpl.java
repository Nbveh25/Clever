package services.impl;

import services.EmailService;
import utils.EmailSenderUtil;

public class EmailServiceImpl implements EmailService {
    @Override
    public void sendVerificationCode(String code, String email) {
        EmailSenderUtil.sendEmail(code, email);
    }
}