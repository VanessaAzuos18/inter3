package lafiesta.controller;


//import org.apache.commons.mail.*;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.net.MalformedURLException;
import java.util.Properties;


public class RecuperarSenhaController {
    @FXML
    private TextField campoRecuperarEmail;
    @FXML
    private TextField campoRecuperarDoc;


//    @FXML
//    public void Recuperar(ActionEvent event) throws EmailException, MalformedURLException, MessagingException {
//        String to = campoRecuperarEmail.getText();
//        String doc = campoRecuperarDoc.getText();
//
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");
//
//        Session session = Session.getDefaultInstance(props, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("equipelafiesta2018@gmail.com", "CP3000346");
//            }
//        });
//
//        MimeMessage message = new MimeMessage(session);
//        message.setFrom(new InternetAddress("erickgvieira@gmail.com"));
//        Address[] toUser = InternetAddress
//                .parse(to);
//        message.setRecipients(Message.RecipientType.TO, toUser);
//        message.setSubject("Recuperar Senha: ");
//        message.setText("A sua senha é: ", "utf-8", "html");
//
//        Transport.send(message);
//
//        JOptionPane.showMessageDialog(null, "E-mail enviado com sucesso!");
//
//    }
}
