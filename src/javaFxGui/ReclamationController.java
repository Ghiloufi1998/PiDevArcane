/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxGui;

import entities.Reclamation;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import service.ReclamationService;
import utils.Notification;

/**
 * FXML Controller class
 *
 * @author Souha saffar
 */
public class ReclamationController implements Initializable {

    @FXML
    private TextArea free;
    @FXML
    private Button env;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoie(ActionEvent event) {
       
        Date dates = (Date.valueOf(date.getValue()));

        Reclamation r = new Reclamation(description.getText(), descrp_txt1.getText(), dates, LoginController.usr);
        ReclamationService rc = new ReclamationService();
        rc.insert(r);
        rea();
        Notification notif = new Notification();
        notif.notification("Réclamation","Réclamation ajouté avec succée",NotificationType.SUCCESS);
    }

    
    
}
