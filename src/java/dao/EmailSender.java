/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author MSI GTX
 */
public class EmailSender {

    private final String from = "kiashop2024@gmail.com"; // Thay thế bằng địa chỉ email của bạn
    private final String appPassword = "thuj cbpy naox unlh"; // Thay thế bằng mật khẩu ứng dụng bạn đã tạo

    public void sendRegistrationEmail(String recipientEmail, int pin) {
        String host = "smtp.gmail.com";
        String subject = "Registration Successful";
        String message = "Hello, your registration was successful. Your PIN is: " + pin;
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, appPassword);
            }
        });

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);
            System.out.println("Email sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Email sending failed");
        }
    }

    public int generateRandomPin() {
        Random random = new Random();
        return 10000 + random.nextInt(90000);
    }

    public void sendForgotEmail(String recipientEmail, int pin) {
        String host = "smtp.gmail.com";
        String subject = "Registration Successful";
        String message = " Your PIN is: " + pin;
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, appPassword);
            }
        });

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);
            System.out.println("Email sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Email sending failed");
        }
    }

}
