/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class GestController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private ImageView im;
    @FXML
    private Button qt;
    @FXML
    private ImageView img1;
    @FXML
    private Button heb_btn;
    @FXML
    private Button Hebergement_btn;
    @FXML
    private Button prom_btn;
    @FXML
    private Button reserv_btn;
    @FXML
    private Button sondage_btn;
    @FXML
    private Button prod_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
                
//        File file = new File("273164846_107014608564264_3517549433838451993_n.jpg");
//        Image image = new Image(file.toURI().toString
        Image Image = new Image(getClass().getResourceAsStream("3.png"));
        img = new ImageView(Image);
        Image Image1 = new Image(getClass().getResourceAsStream("images.png"));
        im = new ImageView(Image1);
    }    

    @FXML
    private void Vol(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Vol.fxml"));
            Parent root = loader.load();
            VolController controller = loader.getController();
            Scene s = new Scene(root);
            Stage sg = new Stage();
            sg.setScene(s);
            sg.show();
            //nom.getScene().setRoot(root);
        } catch (IOException ex) { 
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void Voyages(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterVoyage.fxml"));
            Parent root = loader.load();
            AjouterVoyageController controller = loader.getController();
            Scene s = new Scene(root);
            Stage sg = new Stage();
            sg.setScene(s);
            sg.show();
            //nom.getScene().setRoot(root);
        } catch (IOException ex) { 
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void Stats(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("stats.fxml"));
            Parent root = loader.load();
            StatsController controller = loader.getController();
            Scene s = new Scene(root);
            Stage sg = new Stage();
            sg.setScene(s);
            sg.show();
            //nom.getScene().setRoot(root);
        } catch (IOException ex) { 
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void quitter(ActionEvent event) {
       
       // qt.setText("Quit");
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment quitter l'espace Gestionnaire ?");
       qt.setOnAction(( ActionEvent event1) -> {
       
        Optional<ButtonType> action = alert.showAndWait();
        
        
            if (action.get() == ButtonType.OK) 
            Platform.exit();
        });
        
    }

    @FXML
    private void Transport(ActionEvent event) {
    }

    @FXML
    private void Hebergement(ActionEvent event) {
    }
}
    

