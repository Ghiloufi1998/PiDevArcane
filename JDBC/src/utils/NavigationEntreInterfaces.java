/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Dhia
 */
public class NavigationEntreInterfaces {

    public void navigate(ActionEvent event,String title,String url) throws IOException{
     
     ((Node)event.getSource()).getScene().getWindow().hide();
     Parent root = FXMLLoader.load(getClass().getResource(url));
     Scene scene = new Scene(root);
     Stage stage = new Stage();
     stage.setScene(scene);
     stage.setTitle(title);
     stage.show();
 }
    
}