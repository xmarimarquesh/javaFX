package com.desktopapp;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleRole;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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
        controller.btMain.setText(id.toString());
        
        loader.setController(controller);

        return scene;
    }

    private Integer id;
    public void setId(Integer id) { this.id = id; }
    
    @FXML
    protected Button btMain;

    @FXML
    protected PasswordField senha;

    @FXML
    protected void logar(MouseEvent e) throws Exception{}

    @FXML
    protected void verSenha() throws Exception{
        this.senha.setAccessibleRole(AccessibleRole.TEXT_FIELD);
    }

    @FXML
    protected void esqueceuSenha() throws Exception
    {
        // Stage crrStage = (Stage)btMain.getScene().getWindow();

        // crrStage.close();
        Stage newStage = new Stage();

        Scene newScene = RecuperarController.CreateScene(id);
        newStage.setScene(newScene);

        newStage.show();
    }

}
