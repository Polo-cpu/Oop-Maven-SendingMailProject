package com.SendMail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Date;

public class SendMail {

    private int number;

    private String email;
    public SendMail(int number, String email){
        this.number = number;
        this.email = email;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void send(int number, String sendToMail){
        final String userName = "berkatilla46@hotmail.com";
        final String password = "berk1881*";
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        Properties propertiesObj = new Properties();
        propertiesObj.setProperty("mail.transport.protocol", "smtp");
        propertiesObj.put("mail.smtp.auth","true");
        propertiesObj.put("mail.smtp.starttls.enable","true");
        propertiesObj.put("mail.smtp.host", "smtp.gmail.com");
        propertiesObj.put("mail.smtp.port","587");
        propertiesObj.put("mail.debug", "true");
        propertiesObj.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        propertiesObj.put("mail.smtp.socketFactory.fallback", "false");
        Session sessionObj = Session.getInstance(propertiesObj, new javax.mail.Authenticator(){

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName,password);
            }
        });
        try {
            Message messageObj = new MimeMessage(sessionObj);
            messageObj.setFrom(new InternetAddress("berkatilla46@hotmail.com"));
            messageObj.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendToMail));
            messageObj.setSubject("Absenteeism");
            messageObj.setText(number + "Has absent today : " + today );
            Transport.send(messageObj);
            System.out.println("Message sent successfully");
        }catch (MessagingException e){
            System.err.println("Message Error: " +e.getMessage());
        }
    }
}