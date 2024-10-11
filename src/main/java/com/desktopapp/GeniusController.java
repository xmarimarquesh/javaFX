package com.desktopapp;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.text.Text;

import java.util.ArrayList;

public class GeniusController {
    public static Scene CreateScene(Integer id, String email) throws Exception {
        URL sceneUrl = GeniusController.class.getResource("genius-scene.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        GeniusController controller = loader.getController();
        controller.setId(id);
        
        loader.setController(controller);
        
        return scene;
    }

    private Integer[] collors = new Integer[6];
    private ArrayList<Integer> sequencia = new ArrayList<Integer>();
    
    private Integer id;
    public void setId(Integer id) { this.id = id; }

    Timer timer = new Timer();

    @FXML
    private Text email;

    @FXML
    private Text pontos;

    @FXML
    private Circle blue;

    @FXML
    private Circle pink;

    @FXML
    private Circle green;

    @FXML
    private Circle yellow;

    @FXML
    private Circle red;

    @FXML
    private Circle purple;

    @FXML
    private void rodada() throws Exception {
        Random r = new Random();
        Integer value = r.nextInt(6);

        sequencia.add(value);

        System.out.println("\nArray: ");
        for (int i = 0; i < sequencia.size(); i++) {
            System.out.print(sequencia.get(i));
        }

        System.out.println("\nSIZE: "+sequencia.size());

        for (int i = 0; i < sequencia.size(); i++) {

            int valor = sequencia.get(i);
            System.out.println("valor atual: " + valor);
            
            switch (valor) {
                case 0:
                    blue.setFill(Color.web("#ffffff", 1));
                    System.out.println(blue.getFill().toString());
                    break;
                case 1:
                    pink.setFill(Color.web("#ffffff", 1));
                    System.out.println(pink.getFill().toString());
                        break;
                case 2:
                    green.setFill(Color.web("#ffffff"));
                    System.out.println(green.getFill().toString());
                    break;
                case 3:
                    yellow.setFill(Color.web("#ffffff"));
                    break;
                case 4:
                    red.setFill(Color.web("#ffffff"));
                    break;
                case 5:
                    purple.setFill(Color.web("#ffffff"));
                    break;
            }

            timer.scheduleAtFixedRate(new TimerTask() {
                
                public void run() {

                    blue.setFill(Color.web("#1f93ffad"));
                    pink.setFill(Color.web("#ff1fd68a"));
                    green.setFill(Color.web("#6bff2199"));
                    yellow.setFill(Color.web("#fff81f8c"));
                    red.setFill(Color.web("#ff1f1f87"));
                    purple.setFill(Color.web("#ad1fff87"));
                }
            }, 2000, 2000);

        }
    }
}
    
