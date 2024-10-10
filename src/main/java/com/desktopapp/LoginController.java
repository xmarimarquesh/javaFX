package com.desktopapp;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {

    public static Scene CreateScene(Integer id) throws Exception
    {
        URL sceneUrl = LoginController.class.getResource("login-scene.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        
        Parent root = loader.load();
        Scene scene = new Scene(root);

        LoginController controller = loader.getController();
        controller.setId(id);
        
        loader.setController(controller);

        return scene;
    }

    private Integer id;
    public void setId(Integer id) { this.id = id; }

    private boolean senhaVisivel = false;
    private TextField campoTexto;

    @FXML
    private TextField email;
    
    @FXML
    protected Button btMain;

    @FXML
    protected PasswordField senha;

    @FXML
    protected void logar(MouseEvent e) throws Exception{
        Stage crrStage = (Stage)btMain.getScene().getWindow();

        crrStage.close();
        Stage newStage = new Stage();

        Scene newScene = GeniusController.CreateScene(id, email.getText());
        newStage.setScene(newScene);

        newStage.show();
    }

    @FXML
    protected void verSenha() throws Exception {
        System.out.println(senhaVisivel);

        AnchorPane pai = (AnchorPane) senha.getParent();

        if (!senhaVisivel) {
            campoTexto = new TextField();
            campoTexto.setText(senha.getText());
            campoTexto.setPromptText("Digite sua senha");

            pai.getChildren().add(campoTexto);
            senha.setVisible(false);

            senhaVisivel = true;
        } else {
            PasswordField novoCampoSenha = new PasswordField();
            novoCampoSenha.setText(campoTexto.getText());
            novoCampoSenha.setPromptText("Digite sua senha");
            
            pai.getChildren().add(novoCampoSenha);
            campoTexto.setVisible(false);
            
            senhaVisivel = false;
        }
    }

    @FXML
    protected void esqueceuSenha() throws Exception
    {
        // Stage crrStage = (Stage)btMain.getScene().getWindow();

        // crrStage.close();
        Stage newStage = new Stage();

        Scene newScene = RecuperarController.CreateScene(id, email.getText());
        newStage.setScene(newScene);

        newStage.show();
    }

}
