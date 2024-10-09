package com.desktopapp;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;

public class RecuperarController2 {
    public static Scene CreateScene(Integer id, String email) throws Exception {
        URL urlScene = RecuperarController2.class.getResource("recuperar2-scene.fxml");
        FXMLLoader loader = new FXMLLoader(urlScene);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        RecuperarController2 controller = loader.getController();
        controller.setId(id);

        controller.email.setText(email);
        
        loader.setController(controller);

        return scene;
    }

    private Integer id;
    public void setId(Integer id) { this.id = id; }

    @FXML
    protected Text email;

    @FXML
    protected void back(){}

    @FXML
    protected void confirmarCodigo(){}
    
}
