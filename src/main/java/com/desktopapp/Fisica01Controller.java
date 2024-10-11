package com.desktopapp;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Fisica01Controller implements Initializable {
    public static Scene CreateScene() throws Exception
    {
        URL sceneUrl = Fisica01Controller.class.getResource("fisica01-scene.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    private VBox box;

    @FXML
    private Canvas canvas;

    @FXML
    private AnchorPane anchor;

    @FXML
    private void interact(MouseEvent e){

    }

    @FXML
    private void pressed(MouseEvent e){
        
    }

    @FXML
    private void released(MouseEvent e){
        
    }

    double velocidade = 0 + 0.2;
    int py = 0;

    Timer timer = new Timer();
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
            
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run(){
                    draw();
                    box.requestLayout();
                }
            }, 30, 30);
    }

    private void draw() {
        var g = canvas.getGraphicsContext2D();

        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double v0 = velocidade;

        // double width = canvas.getHeight();
        // double height = canvas.getHeight();

        g.setFill(Color.BLUE);

        g.fillArc(280, py, 70, 70, 0f, 360, ArcType.ROUND);
        py += 1;
        velocidade = v0 + 0.2;
            
    }
}
