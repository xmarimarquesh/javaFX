package com.desktopapp;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RecuperarController {

    public static Scene CreateScene(Integer id) throws Exception {
        URL urlScene = RecuperarController.class.getResource("recuperar-scene.fxml");
        FXMLLoader loader = new FXMLLoader(urlScene);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        RecuperarController controller = loader.getController();
        controller.setId(id);
        
        loader.setController(controller);

        return scene;
    }

    private Integer id;
    public void setId(Integer id) { this.id = id; }

    @FXML
    private TextField email_recuperar;
    public TextField getEmail_recuperar() { return email_recuperar; }

    @FXML
    protected void recuperarSenha() throws Exception {
        Stage stage = (Stage)email_recuperar.getScene().getWindow();
        stage.close();

        Stage newStage = new Stage();
        Scene scene = RecuperarController2.CreateScene(id, email_recuperar.getText());
        newStage.setScene(scene);
        newStage.show();
    }


}
