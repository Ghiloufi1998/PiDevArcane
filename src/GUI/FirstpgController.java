/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bensa
 */
public class FirstpgController implements Initializable {
 @FXML
    private Button btn2;

    @FXML
    private Button btn;
   private Scene scene;
    private Parent root;
    private Stage stage;
    @FXML
    void pai(ActionEvent event) {
       
     try {
         root = FXMLLoader.load(getClass().getResource("paiCRUD.fxml"));
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
     } catch (IOException ex) {
         Logger.getLogger(FirstpgController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
   
    @FXML
    void rev(ActionEvent event) {
 try {
         root = FXMLLoader.load(getClass().getResource("reservationfxml.fxml"));
         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
     } catch (IOException ex) {
         Logger.getLogger(FirstpgController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

}
    

