package com.desktopapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.crypto.spec.DESKeySpec;

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
        URL sceneUrl = Fisica01Controller.class.getResource("fisica01-scene.fxml");
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

    Body body = new Body(30,0,300,0,-200, 30,0,50);
    Body body2 = new Body(30,0,200,0,500, 60,0,50000);
    Body body3 = new Body(3000,0,200,0,500, 90,0,50000);

    Spring spring2 = new Spring(0, 120, 0, body2, body3);
    Spring spring3 = new Spring(0, 120, 0, body, body3);
    
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
        double v = (body.getV0Y() + G * T);
        body.setvY(v + body.getAy());
    }
    
    public void calcVelX(Body body){
        body.setvX(body.getV0X() + body.getAx());
    }
    
    public void calcPosY(Body body){
        body.setPosY(body.getPosY() + (body.getvY() * T));
        
        if (body.getPosY() > (canvas.getHeight() - body.getD())) {
            body.setvY(body.getvY() * - Loss);
            body.setPosY(canvas.getHeight() - body.getD());
        }
    }
    
    public void calcPosX(Body body){
        body.setPosX(body.getPosX() + (body.getvX() * T));
        
        if (body.getPosX() > (canvas.getWidth() - body.getD())) {
            body.setvX(body.getvX() * - Loss);
            body.setPosX(canvas.getWidth() - body.getD());
        }
        
        if(body.getPosX() <= 0){
            body.setvX(body.getvX() * - Loss);
            body.setPosX(0);
        }
        
    }
    
    public void calcSpringX(Spring spring){
        spring.setX(Math.sqrt((Math.pow(spring.getB1().getPosX() - spring.getB2().getPosX(), 2)) + (Math.pow((spring.getB1().getPosY() - spring.getB2().getPosY()), 2))) - spring.getLenght());
    }
    
    public void calcSpringForce(Spring spring){
        spring.setForce(spring.getK() * spring.getX());
    }
    
    public void calcAcceleration(Spring spring) {
        double k = spring.getK(); 
        double x = spring.getX(); 
        
        double force = -k * x; 
        
        double angle = Math.atan2((spring.getB1().getPosY() - spring.getB2().getPosY()), (spring.getB1().getPosX() - spring.getB2().getPosX()));
        
        double Fx = force * Math.cos(angle);
        double Fy = force * Math.sin(angle);
    
        double a1x = Fx / spring.getB1().getMass();
        double a1y = Fy / spring.getB1().getMass();
        
        double a2x = -Fx / spring.getB2().getMass();
        double a2y = -Fy / spring.getB2().getMass();
        
        spring.getB1().setAx(a1x);
        spring.getB1().setAy(a1y);
        
        spring.getB2().setAx(a2x);
        spring.getB2().setAy(a2y);
    }
    
    
    Spring spring = new Spring(0, 120, 0, body, body2);
    private void draw() {
        ArrayList<Spring> molas = new ArrayList<>();
        ArrayList<Body> bolas = new ArrayList<>();
        
        for ( int i = 0; i < bolas.size(); i += 2){
            molas.add(new Spring(i, i, i, bolas.get(i), bolas.get(i + 1)));
        }

        molas.add(spring);
        molas.add(spring2);
        molas.add(spring3);
        
        bolas.add(body);
        bolas.add(body2);
        bolas.add(body3);

        for(int i = 0; i < molas.size(); i++){
            calcSpringX(molas.get(i));
            calcSpringForce(molas.get(i));
            calcAcceleration(molas.get(i));
        }

        for(int i = 0; i < bolas.size(); i++){
            bolas.get(i).setV0Y(bolas.get(i).getvY());
            bolas.get(i).setV0X(bolas.get(i).getvX());
            calcVelY(bolas.get(i));
            calcPosY(bolas.get(i));
            calcVelX(bolas.get(i));
            calcPosX(bolas.get(i));
        }

        var g = canvas.getGraphicsContext2D();

        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        g.strokeLine(body.getPosX() + body.getD()/2, body.getPosY()+ body.getD()/2, body2.getPosX()+ body2.getD()/2, body2.getPosY()+ body2.getD()/2);
        g.setStroke(Color.GREEN);
        g.setLineWidth(10);

        g.strokeLine(body3.getPosX() + body3.getD()/2, body3.getPosY()+ body3.getD()/2, body2.getPosX()+ body2.getD()/2, body2.getPosY()+ body2.getD()/2);
        g.setStroke(Color.ORANGE);
        g.setLineWidth(10);

        g.strokeLine(body.getPosX() + body.getD()/2, body.getPosY()+ body.getD()/2, body3.getPosX()+ body3.getD()/2, body3.getPosY()+ body3.getD()/2);
        g.setStroke(Color.GRAY);
        g.setLineWidth(10);

        g.setFill(Color.BLUE);
        g.fillArc(body.getPosX(), body.getPosY(), body.getD(), body.getD(), 0f, 360, ArcType.ROUND);
        
        g.setFill(Color.RED);
        g.fillArc(body2.getPosX(), body2.getPosY(), body2.getD(), body2.getD(), 0f, 360, ArcType.ROUND);

        g.setFill(Color.PINK);
        g.fillArc(body3.getPosX(), body3.getPosY(), body3.getD(), body3.getD(), 0f, 360, ArcType.ROUND);
        
    }
}
