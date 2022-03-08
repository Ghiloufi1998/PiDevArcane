/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.Réservation;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.RéservationCRUD;

/**
 * FXML Controller class
 *
 * @author bensa
 */
public class InterfaceController implements Initializable {

    @FXML
    private DatePicker date_deb ;
    @FXML 
    private DatePicker date_fin ;
    @FXML 
    private TextField Type ;
    @FXML
    private Button btn;
    RéservationCRUD r =new RéservationCRUD();
    DatePicker checkInDatePicker = new DatePicker(LocalDate.now());
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   @FXML
    private void Insert(ActionEvent event) {
        Réservation p = new Réservation();
        p.setDate_deb(java.sql.Date.valueOf(date_deb.getValue()));
        p.setDate_fin(java.sql.Date.valueOf(date_fin.getValue()));
        p.setType(Type.getText());
        p.getUser().getID_user();
        r.insert(p);
        
       
    }
    
    }    

   
  
