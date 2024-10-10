package com.desktopapp;

import java.net.URL;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RecuperarController {

    public static Scene CreateScene(Integer id, String email) throws Exception {
        URL urlScene = RecuperarController.class.getResource("recuperar-scene.fxml");
        FXMLLoader loader = new FXMLLoader(urlScene);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        RecuperarController controller = loader.getController();
        controller.setId(id);
        controller.email_recuperar.setText(email);
        
        loader.setController(controller);

        return scene;
    }

    private Integer id;
    public void setId(Integer id) { this.id = id; }

    @FXML
    private TextField email_recuperar;
    // public TextField setEmail_recuperar() { return email_recuperar; }

    @FXML
    protected void recuperarSenha() throws Exception {
        Stage stage = (Stage)email_recuperar.getScene().getWindow();
        stage.close();

        Stage newStage = new Stage();
        Scene scene = RecuperarController2.CreateScene(id, email_recuperar.getText());
        newStage.setScene(scene);
        newStage.show();


        // ENVIAR EMAIL
        final String username = "testestudos01@gmail.com";
        final String password = "estudoteste01";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.user", email_recuperar.getText());

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email_recuperar.getText()));
            mimeMessage.setSubject("Recuperar senha - APP");
            mimeMessage.setText("Código de recuperação: 98345");

            Transport.send(mimeMessage);
            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
