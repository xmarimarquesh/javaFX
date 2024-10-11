package com.desktopapp;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.Timer;

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

public class StarController implements Initializable {
    public static Scene CreateScene() throws Exception
    {
        URL sceneUrl = StarController.class.getResource("canvastar-scene.fxml");
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

    Timer timer = new Timer();
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run(){
                rotation += Math.PI / 20;
                draw();
                box.requestLayout();
            }
        }, 40, 40);
    }

    double rotation = 0;
    private void draw() {
        var g = canvas.getGraphicsContext2D();
        
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double[] xs = new double[10];
        double[] ys = new double[10];

        double theta = 0f;

        for(int i = 0; i < 10; i++){
            double rho = i % 2 == 0 ? 200 : 80;
            
            xs[i] = rho * Math.cos(theta);
            ys[i] = rho * Math.sin(theta);

            theta += 2 * Math.PI / 10;
        }

        for(int i = 0; i < 10; i++){
            var x = xs[i];
            var y = ys[i];

            xs[i] = x * Math.cos(rotation) + y * Math.sin(rotation);
            ys[i] = x * Math.sin(rotation) - y * Math.cos(rotation);
        }

        for(int i = 0; i < 10; i++){
            xs[i] += canvas.getWidth() / 2;
            ys[i] += canvas.getHeight() / 2;
        }

        g.setFill(Color.web("ff0085"));
        g.fillPolygon(xs, ys, 10);

        

    }
}
