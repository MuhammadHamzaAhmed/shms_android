package com.shms.shms_android.model;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class MailSender{
    private final static String USER_NAME = "noreply.shms@gmail.com";
    private static String PASSWORD = "SHMS1575";
    private final static String HOST = "smtp.gmail.com";
    private final static String subject = "Verify OTP SHMS.";
    private final static String RESET = "Hello %s\nIt seems like you are trying to change password.\n"+
                                  "Enter the code to reset your password.\n\n %d \n\nDon't share " +
                                  "this code with anyone!\n\n if you have not request to reset your" +
                                  " password, change your password. If this code does not work you can" +
                                  " request a new one.";

    private final static String CREATE = "Hello %s\n Welcome you have created a new account.\n"+
                                         "Enter the code to activate your account.\n\n %d \n\nDon't share " +
                                         "this code with anyone!\n\n if you have not created an account" +
                                         ", reply Not subscribed to this mail.";

    public static void resetPassword(String name, int resetNumber, String email) throws Exception{
        send(name,  resetNumber, email, RESET);
    }

    public static void activateAccount(String name, int resetNumber, String email) throws Exception{
        send(name,  resetNumber, email, CREATE);
    }

    public static void send(String name, int resetNumber, String email, String mail) throws Exception{
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(USER_NAME));
        InternetAddress recipent = new InternetAddress(email);
        msg.addRecipient(Message.RecipientType.TO, recipent);
        msg.setSubject(subject);
        msg.setText(String.format(mail, name, resetNumber));
        Transport.send(msg);
    }

    public static void setPassword(String pass){
        PASSWORD = pass;
    }
}
