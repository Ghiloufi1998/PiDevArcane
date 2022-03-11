/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 * FXML Controller class
 *
 * @author Ghiloufi
 */

import Service.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ghiloufi
 */

public class DashboardGestFXMLController implements Initializable {



    @FXML
    private BorderPane bp;
    @FXML
    private Button heb_btn;
    @FXML
    private Button transport_btn;
    @FXML
    private Button reserv_btn;
    @FXML
    private Button sondage_btn;
    @FXML
    private Button reclam_btn;
    @FXML
    private AnchorPane Transports;
    @FXML
    private Text Hellomsg;
    @FXML
    private ImageView user_image;
    @FXML
    private Button profile_btn;
    @FXML
    private Button mdp_btn;
    @FXML
    private Button logout;
    @FXML
    private Button pay_btn;
    @FXML
    private Button stat_btn;
    @FXML
    private Button guide_btn;
    @FXML
    private Button cours_btn;
    @FXML
    private Button exer_btn;
    @FXML
    private Button vol_btn;
    UserService us = new UserService(); 

    /**
     * Initializes the controller class.
     */
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Hellomsg.setText("Bonjour : " + us.getUserlogged().getName());
        } catch (SQLException ex) {
            Logger.getLogger(DashboardGestFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     

    @FXML
    private void Hebergement(ActionEvent event) {
    }

    @FXML
    private void Transport(ActionEvent event) {
    }

    @FXML
    private void STAT(ActionEvent event) {
    }

    @FXML
    private void pay(ActionEvent event) {
    }

    @FXML
    private void res(ActionEvent event) {
    }

    @FXML
    private void sondage(ActionEvent event) {
    }

    @FXML
    private void guide(ActionEvent event) {
    }

    @FXML
    private void cours(ActionEvent event) {
    }

    @FXML
    private void exercice(ActionEvent event) {
    }

    @FXML
    private void rec(ActionEvent event) {
    }

    @FXML
    private void vol(ActionEvent event) {
    }
    
}
 
    

