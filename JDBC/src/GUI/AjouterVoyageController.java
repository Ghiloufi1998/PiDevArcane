/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.ModifierVoyageController.getListTitle;
import entities.Hebergement;
import entities.Personne;
import entities.Transport;
import entities.Vol;
import entities.Voyageorganise;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.management.Notification;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import services.ServicePersonne;
import services.ServiceVol;
import services.ServiceVoyageorganise;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
//import tray.

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class AjouterVoyageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     
    @FXML
    private TextField Descriptions;
    @FXML
    private TextField Prix;
    @FXML
    private TextField Nbre_Places ;
    @FXML
    private TextField Vol ;
    @FXML
    private TextField Transport ;
    @FXML
    private Button ajouter;
    ServiceVoyageorganise sv = new ServiceVoyageorganise();
    ServiceVol v = new ServiceVol();
        ValidationSupport validationSupport = new ValidationSupport();    
    
    
    
    public static ObservableList<String> getListTitle() {
        ServiceVoyageorganise cs = new ServiceVoyageorganise();
        ObservableList<String> listtitle = cs.GetAllTitle();
        return listtitle;}
    
    public static ObservableList<Integer> getVols() {
        ServiceVol cs = new ServiceVol();
        ObservableList<Integer> listtitle = cs.GetAllvol();
        return listtitle;}
    
    
    @FXML
    private ComboBox<String> t;
    @FXML
    private ComboBox<Integer> cv;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        t.getItems().addAll(getListTitle());
        cv.getItems().addAll(getVols());
        validationSupport.registerValidator(Descriptions,Validator.createEmptyValidator("Veuillez saisir "));
        validationSupport.registerValidator(t,Validator.createEmptyValidator("Veuillez saisir "));
        validationSupport.registerValidator(cv,Validator.createEmptyValidator("Veuillez saisir "));
        validationSupport.registerValidator(Prix,Validator.createEmptyValidator("Veuillez saisir "));
        validationSupport.registerValidator(Nbre_Places,Validator.createEmptyValidator("Veuillez saisir "));
        
    }


     
    
    
    @FXML
    private void Insert(ActionEvent event) {
        
//       String s=cv.getSelectionModel().getSelectedItem().toString();
//        String s1=t.getSelectionModel().getSelectedItem().toString();
//        System.out.println(s1+""+s);
        //if (!Descriptions.getText().equals("") && !Prix.getText().equals("") && !Nbre_Places.getText().equals("") && !Vol.getText().equals("") && !Transport.getText().equals("") ){
        //if (!Descriptions.getText().equals("") && !Prix.getText().equals("") && !Nbre_Places.getText().equals("") && !cv.getValue().equals("") && !t.getValue().equals("") ){
        //if (!Descriptions.getText().equals("") && !Prix.getText().equals("") && !Nbre_Places.getText().equals("") && !( (cv.getItems()).isEmpty() ) && !( (t.getItems()).isEmpty()) ){
        //if (!Descriptions.getText().equals("") && !Prix.getText().equals("") && !Nbre_Places.getText().equals("") && !s.equals("") && !s1.equals("") ){
        if (!Descriptions.getText().equals("") && !Prix.getText().equals("") && !Nbre_Places.getText().equals("")  ){

        Voyageorganise vo = new Voyageorganise();
        vo.setDescription(Descriptions.getText());
        int x = Integer.parseInt(Prix.getText());
        vo.setPrix(x);
        int y = Integer.parseInt(Nbre_Places.getText());
        vo.setNbre_Places(y);
        //int z = Integer.parseInt(Vol.getText());
        //vo.setVol_id(z);
        vo.setVol_id(cv.getValue());
        vo.setVol(v.get(cv.getValue()));
        //vo.setVol(v.get(z));
        //vo.setTransport(sv.get(Transport.getText()));
        vo.setTransport(sv.get(t.getValue()));

        //Vol v1 = new Vol(3,"iheb", "Chiheb", "moncef");
        //Hebergement h = new Hebergement(2,"un","deux",0,"trois","pdf");
        //Transport t= new Transport(3,"uno","dos",0,5,"x",h);
        //Voyageorganise vo = new Voyageorganise(13,"ihebmodi", "yahmdoi", 22, 33, v1,t);
        if (sv.nbrv(vo)==0)
        {   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment Ajouter un voyage");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            
            sv.ajout(vo);
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Voyage ajouté");
            tr.setMessage("inserted succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));}
    }
        else {TrayNotification t = new TrayNotification();
            AnimationType tp = AnimationType.POPUP;
            t.setAnimationType(tp);
            t.setTitle("Voyage existant");
            t.setMessage("Impossible d'ajouter le voyage ");
            t.setNotificationType(NotificationType.ERROR);
            t.showAndDismiss(Duration.millis(5000));}
    
    }}

    @FXML
    private void modifier(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierVoyage.fxml"));
            Parent root = loader.load();
            ModifierVoyageController controller = loader.getController();
            Scene s = new Scene(root);
            Stage sg = new Stage();
            sg.setScene(s);
            sg.show();
            //nom.getScene().setRoot(root);
        } catch (IOException ex) { 
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
