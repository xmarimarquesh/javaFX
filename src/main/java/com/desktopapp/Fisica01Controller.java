package com.desktopapp;

import java.net.URL;
import java.util.ArrayList;
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
        URL sceneUrl = Fisica01Controller.class.getResource("fisica01-scene.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        return scene;
    }
    
    final double Loss = 0.2;

    @FXML
    private VBox box;

    @FXML
    private Canvas canvas;

    @FXML
    private AnchorPane anchor;

    ArrayList<Spring> molas = new ArrayList<Spring>();
    ArrayList<Body> bolas = new ArrayList<Body>();
    Color[] colors = new Color[6];

    final double T = 0.03;
    final double G = 2;
    
    Timer timer = new Timer();
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colors[0] = Color.GREEN;
        colors[1] = Color.BLUE;
        colors[2] = Color.PINK;
        colors[3] = Color.ORANGE;
        colors[4] = Color.YELLOW;
        colors[5] = Color.RED;


        int count = 20;
        for(int i = 0; i < 50; i++){
            bolas.add(new Body(15,0,count,count,-1000, 30,0,0));
            count+=200;
        }

        for(int i = 0; i < bolas.size()-1; i++){
            for(int j = i + 1; j < bolas.size(); j++){
                molas.add(new Spring(0, 500, 34, bolas.get(i), bolas.get(j)));
            }
        }

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

        if(body.getPosY() <= 0){
            body.setvY(body.getvY() * - Loss);
            body.setPosY(0);
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
    
    
    private void draw() {

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
       
        g.setLineWidth(3);
        for(int i = 0; i < bolas.size()-1; i++){
            for(int j = i + 1; j < bolas.size(); j++){
                Body b1 = bolas.get(i);
                Body b2 = bolas.get(j);

                g.strokeLine(
                    b1.getPosX() + b1.getD()/2, 
                    b1.getPosY() + b1.getD()/2, 
                    b2.getPosX() + b2.getD()/2, 
                    b2.getPosY() + b2.getD()/2);
                g.setStroke(colors[i % 6]);
            }
        }

        for(int i = 0; i < bolas.size(); i++){
            g.setFill(colors[i % 6]);
            g.fillArc(bolas.get(i).getPosX(), bolas.get(i).getPosY(), bolas.get(i).getD(), bolas.get(i).getD(), 0f, 360, ArcType.ROUND);
        }
        
    }
}
