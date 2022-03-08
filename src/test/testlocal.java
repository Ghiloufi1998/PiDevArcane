/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bensa
 */
public class testlocal extends  Application {

  public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Views/paiCRUD.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    public static void main(String[] args) {
        launch(args);
    }

    
}

    

