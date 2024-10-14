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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Fisica01Controller implements Initializable{
    public static Scene CreateScene() throws Exception
    {
        URL sceneUrl = Fisica01Controller.class.getResource("PhisicsScreen.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        return scene;
    }
    
    final double Loss = 0.7;

    @FXML
    private VBox box;

    @FXML
    private Canvas canvas;

    @FXML
    private AnchorPane anchor;

    final double T = 0.03;
    final double G = 980;

    Body body = new Body(0,0,200,0,50, 70,0,50);
    Body body2 = new Body(0,0,300,0,100, 40,0,50000);

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

    public void calcVelY(Body body){
        double v = body.getV0Y() + G * T;
        body.setvY(v);
    }

    public void calcVelX(Body body){
        double v = body.getV0X();

        if (body.getPosX() > (canvas.getWidth() - body.getD())) {
            body.setvX(body.getvX() * -Loss);
        }else if(body.getPosX() < (0)){
            body.setvX((v * - 1) * Loss);
        }
        else {
            body.setvX(v);
        }
    }

    public void calcPosY(Body body){
        body.setPosY(body.getPosY() + (body.getvY() * T));

        if (body.getPosY() > (canvas.getHeight() - body.getD())) {
            body.setvY(body.getvY() * -Loss);
            body.setPosY(canvas.getHeight() - body.getD());
        }
    }

    public void calcPosX(Body body){
        body.setPosX(body.getPosX()+ (body.getvX() * T));
    }

    public void setSpringLenght(Spring spring, Body body){

    }

    private void draw() {

        body.setV0Y(body.getvY());
        body2.setV0Y(body2.getvY());

        body.setV0X(body.getvX());
        body2.setV0X(body2.getvX());
        

        calcVelY(body);
        calcPosY(body);

        calcVelY(body2);
        calcPosY(body2);

        calcVelX(body);
        calcPosX(body);

        calcVelX(body2);
        calcPosX(body2);   
  
        
        var g = canvas.getGraphicsContext2D();

        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        g.setFill(Color.BLUE);
        g.fillArc(body.getPosX(), body.getPosY(), body.getD(), body.getD(), 0f, 360, ArcType.ROUND);
        
        g.setFill(Color.RED);
        g.fillArc(body2.getPosX(), body2.getPosY(), body2.getD(), body2.getD(), 0f, 360, ArcType.ROUND);
        
        g.strokeLine(body.getPosX() + body.getD()/2, body.getPosY()+ body.getD()/2, body2.getPosX()+ body2.getD()/2, body2.getPosY()+ body2.getD()/2);
    }
}
