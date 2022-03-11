/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Vol;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import Service.ServiceVol;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.GuiNavigate;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class VolController implements Initializable {

    @FXML
    private TextField dp;
    @FXML
    private TextField ds;
    @FXML
    private TableView<Vol> table;
    @FXML
    private Button Ajouter;
    @FXML
    private Button Modifier;
    @FXML
    private Button Supprimer;
    @FXML
    private TableColumn<Vol, Integer> cvid;
    @FXML
    private TableColumn<Vol, String> cd;
    @FXML
    private TableColumn<Vol, String> cdp;
    ServiceVol sv= new ServiceVol();
    FilteredList<Vol> filter = new FilteredList<>(getTableList(), e -> true);
    SortedList<Vol> sort = new SortedList<>(filter);
    @FXML
    private TextField search;
                    ValidationSupport validationSupport = new ValidationSupport();    
    @FXML
    private BorderPane bp;
    @FXML
    private Button voyage_btn;
    @FXML
    private Button heb_btn;
    @FXML
    private Button transport_btn;
    @FXML
    private Button prom_btn;
    @FXML
    private Button reserv_btn;
    @FXML
    private Button sondage_btn;
    @FXML
    private Button prod_btn;
    @FXML
    private Button cmd_btn;
    @FXML
    private Button forum_btn;
    @FXML
    private Button reclam_btn;
    @FXML
    private Button reclam_btn1;
    @FXML
    private AnchorPane Transports;
    @FXML
    private ImageView user_image;
    @FXML
    private Button profile_btn;
    @FXML
    private Button mdp_btn;
    @FXML
    private Button logout;
    @FXML
    private Button retour;
                    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addListenerForTable();
        ShowTable();
        validationSupport.registerValidator(ds,Validator.createEmptyValidator("Veuillez saisir Destination "));
        validationSupport.registerValidator(dp,Validator.createEmptyValidator("Veuillez saisir Départ "));

    }    

    
     private ObservableList<Vol> getTableList() {
       
        ObservableList<Vol> List = sv.afficher();
        return List ;
        
    }
    
    @FXML
    public void ShowTable() {
        ObservableList<Vol> list = getTableList();
        table.setItems(list);
        cvid.setCellValueFactory(new PropertyValueFactory<>("vol_id"));
        cd.setCellValueFactory(new PropertyValueFactory<>("Destination"));
        cdp.setCellValueFactory(new PropertyValueFactory<>("Départ"));
        
        
       
    }
  
    
    private void Insert() {
        String dest = ds.getText();
        String dep = dp.getText();
        Vol v = new Vol(dest, dep, "");
       if (!dest.equals("") && !dep.equals("") ){
            sv.ajout(v);
            ShowTable();
            ds.setText("");
            dp.setText("");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Vol ajouté");
            tr.setMessage("inserted succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            
       }
    } 
    
    @FXML
    private void Ajouter(ActionEvent event) {
        Insert();
    }
    
    
    
    private void addListenerForTable(){
        table.getSelectionModel().selectedItemProperty().addListener((obs,oldselection,newselection)->{
        if (newselection != null){
            Modifier.setDisable(false);
            Supprimer.setDisable(false);
            ds.setText(newselection.getDestination());
            dp.setText(newselection.getDépart());
        }else{
               ds.setText("");
               dp.setText("");
               Modifier.setDisable(true);
               Supprimer.setDisable(true);
        }
        }); 
    
    }


    private void SupprimerT(ActionEvent event) {
         
    }

    @FXML
    private void modifier(ActionEvent event) {
        Vol v = table.getSelectionModel().getSelectedItem();
        String des = ds.getText();
        String dep = dp.getText();
        Vol vv = new Vol(v.getVol_id(), des, dep, "");
        if (!des.equals("") && !dep.equals("") ){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment Modifier");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            sv.modifier(vv);
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Vol modifié");
            tr.setMessage("Updated succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            ShowTable();}}

    }

    @FXML
    private void Supprimer(ActionEvent event) {
        
        Vol v = table.getSelectionModel().getSelectedItem();
        
        int id = v.getVol_id();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment Supprimer");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
       
            sv.supprimer(id);
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Vol Supprimé");
            tr.setMessage("Deleted succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            ShowTable();}
    }

    @FXML
    private void sort(KeyEvent event) {
       
         search.setOnKeyReleased(e -> {
                  

            search.textProperty().addListener((observable, oldValue, newValue) -> {
               filter.setPredicate(t -> {
                   if (newValue == null || newValue.isEmpty()) {
                       return true;
                   }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (t.getDestination().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                   }
                });
            });
               
            sort.comparatorProperty().bind(table.comparatorProperty());
          table.setItems(sort);
      });  
        
        
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
    private void Retour(ActionEvent event) throws IOException {
        GuiNavigate nav = new GuiNavigate();
      nav.navigate(event, "Gestion", "/gui/Gest.fxml");
        
    }
    
}
