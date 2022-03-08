/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import entities.Réservation;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import services.RéservationCRUD;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author bensa
 */public class RéservationCRUDController implements Initializable {
RéservationCRUD rc= new RéservationCRUD();
    @FXML
    private TableView<Réservation> tR;

    @FXML
    private TableColumn<Réservation, Date> DD;

    @FXML
    private TableColumn<Réservation, Date> Df;

    @FXML
    private TableColumn<Réservation, String> t;

    @FXML
    private TableColumn<Réservation, Integer> na;

    @FXML
    private TableColumn<Réservation, Integer> ne;
    
    @FXML
    private TableColumn<Réservation, Integer> edicol;
 @FXML
    private TableColumn<Réservation,Integer > hb;

    @FXML
    private TableColumn<Réservation, Integer> vol;
    @FXML
    private TextField input1;
 @FXML
    private ComboBox<String> input5;

    @FXML
    private ComboBox<String> input4;

    @FXML
    private ComboBox<String> input6;

    @FXML
    private DatePicker input2;

    @FXML
    private DatePicker input3;
 @FXML
    private Button supp;

    @FXML
    private Button mod;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ShowTable();
           String type[] =
                   {  "Individuelle", "Voyage Organisé" };
 
        // Create a combo box
     ObservableList    x =FXCollections.observableArrayList(type);
      input4.setItems(x);
       ArrayList values =new ArrayList(Arrays.asList("1","2","3","4","5","6","7","8","9")) ;

           
        ObservableList    nb =FXCollections.observableArrayList(values);
        input5.setItems(nb);
       input6.setItems(nb);
        
    }     
    private ObservableList<Réservation> getTableList() {
       
        ObservableList<Réservation> List = rc.Rall();
        return List ;
        
}
     public void ShowTable() {
        ObservableList<Réservation> list = getTableList();
        tR.setItems(list);
        DD.setCellValueFactory(new PropertyValueFactory<>("Date_deb"));
        Df.setCellValueFactory(new PropertyValueFactory<>("Date_fin"));
        t.setCellValueFactory(new PropertyValueFactory<>("Type"));
       na.setCellValueFactory(new PropertyValueFactory<>("Nbr_adultes"));
       ne.setCellValueFactory(new PropertyValueFactory<>("Nbr_enfants"));
    }
      @FXML
    void del(ActionEvent event) {
        
Réservation vo = tR.getSelectionModel().getSelectedItem();
        
       Date d = Date.valueOf(input2.getValue());
       Date f = Date.valueOf(input3.getValue());
        String x = (String) input4.getValue();
       String ty=(String) input5.getValue();
       int fd=Integer.parseInt(ty);
       String z=(String) input6.getValue();
       int  sq=Integer.parseInt(z);
   
       Réservation v = new Réservation(vo.getID_rev(),d,f, x,fd,sq);
            
            rc.update(v);
            ShowTable();
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Voyage modifié ");
            tr.setMessage("updated succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));

    }

    @FXML
    void supe(ActionEvent event) {
  
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment Supprimer");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
         Réservation   vo = tR.getSelectionModel().getSelectedItem();
        rc.delete(vo.getID_rev());
        ShowTable();
        
        TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Voyage supprimé");
            tr.setMessage("deleted succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
        }
    

    }
}
