package view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author duy
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailAuthentication {
	
    public static String generateRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000000); // Tạo số nguyên ngẫu nhiên từ 0 đến 999999
        return String.format("%06d", randomNumber); // Định dạng chuỗi để đảm bảo chuỗi có độ dài bằng 6
    }

    public static String sendEmail(String email) {
    	String Mcode = generateRandomNumber();
    	String senderEmail = "bankloverboy@gmail.com";
        String senderPassword = "xgdjkhjlmxdrnvqb";

        String recipientEmail = email;

        String smtpHost = "smtp.gmail.com";
        String smtpPort = "587";

        String subject = "Verify Email Adress for Loverboy Bank";
        String body ="Subject: Email Verification Code\r\n"
        		+ "\r\n"
        		+ "You have requested to receive a verification code for your account. Below is your verification code: " + Mcode + ".\r\n"
        		+ "\r\n"
        		+ "Please enter this verification code in the application to verify your account. This code will expire after 60 minutes.\r\n"
        		+ "\r\n"
        		+ "If you did not make this request, please disregard this email.\r\n"
        		+ "\r\n"
        		+ "Sincerely,\r\n"
        		+ "\r\n"
        		+ "LoverBoy Bank";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
        	session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }
        return Mcode;
    }
    public static String OTP(String email) {
    	String Mcode = generateRandomNumber();
    	String senderEmail = "bankloverboy@gmail.com";
        String senderPassword = "xgdjkhjlmxdrnvqb";

        String recipientEmail = email;

        String smtpHost = "smtp.gmail.com";
        String smtpPort = "587";

        String subject = "Verify Email Adress for Loverboy Bank";
        String body ="Your OTP code is: " + Mcode + ". This code will expire after 60 minutes. "
                + "Please enter this code in the application to verify your account. "
                + "If you did not make this request, please ignore this message.";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
        	session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }
        return Mcode;
    }

}