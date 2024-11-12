package services;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailSenderService {
    public static void sendEmail(String code, String to) {
        String from = "b.f.g@internet.ru";
        String host = "smtp.mail.ru";
        String smtpPort = "465"; //465 587

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(
                props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, "FjttryWvMzVpg3pUYXHj");
                    }
                }
        );

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Clever");
            message.setText("Кайнар-салам! Вот твой код: " + code); // сверстать html и сделать красиво, а пока так сойдет
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
