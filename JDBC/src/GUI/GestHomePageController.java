/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class GestHomePageController implements Initializable {

    @FXML
    private Button btnSondage;
    @FXML
    private Button btnStat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void NavigSondage(ActionEvent event) {
            try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/Sondage.fxml"));
            Parent root = loader.load();
  
            Scene scene = new Scene(root);
            Stage stage =(Stage)btnSondage.getScene().getWindow();
            stage.setScene(scene);
            //stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
    }

    @FXML
    private void NavigStat(ActionEvent event) {
            try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/Resultat.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage =(Stage)btnStat.getScene().getWindow();
            stage.setScene(scene);
            //stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
    }
    
}
