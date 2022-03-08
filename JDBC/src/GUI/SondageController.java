/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Sondage;
import java.io.IOException;
import javafx.collections.ObservableList;
import java.net.URL;
import java.sql.Connection;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import services.ServiceSondage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SondageController implements Initializable {

    @FXML
    private TextField Textsujet;
    @FXML
    private TextField Textcategorie;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private TableColumn<Sondage, Integer> colid;
    @FXML
    private TableColumn<Sondage, String> colsujet;
    @FXML
    private TableColumn<Sondage, String> colcategorie;
    @FXML
    private TableView<Sondage> table;
    ServiceSondage ss = new ServiceSondage();
    @FXML
    private Button btnsupprimer;
    String erreur;
    
    ValidationSupport validationSupport= new ValidationSupport();
    @FXML
    private Button btnajoutq;
    private TextField recherche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addListenerForTable();
        ShowTable();
        validationSupport.registerValidator(Textsujet, Validator.createEmptyValidator("Text obligatoire"));
        validationSupport.registerValidator(Textcategorie, Validator.createEmptyValidator("Text obligatoire"));
        
    }  
    
     private ObservableList<Sondage> getTableList() {
       
        ObservableList<Sondage> List = ss.afficher();
        return List ;
        
    }
    
    public void ShowTable() {
        ObservableList<Sondage> list = getTableList();
        table.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<>("sondage_id"));
        colsujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        colcategorie.setCellValueFactory(new PropertyValueFactory<>("catégorie"));
        
        
       
    }
  
    
    private void Insert() {
        String sujet = Textsujet.getText();
        String cat = Textcategorie.getText();
        Sondage s = new Sondage(sujet,cat);
       if (ss.nbrs(sujet)==0){
            ss.ajout(s);
             System.out.println("Sondage ajoutééé");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Sondage");
            tr.setMessage("Ajoutée avec succés");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            ShowTable();
            Textsujet.setText("");
            Textcategorie.setText("");
            
       }else {
          TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Sondage");
            tr.setMessage("ERROR: Sondage déja existe  ");
            tr.setNotificationType(NotificationType.ERROR);
            tr.showAndDismiss(Duration.millis(5000));
 
       }
    } 
    
    @FXML
    private void AjouterT(ActionEvent event) {
        erreur="";
        if (!testSujet()|| !testCategorie()) {
             erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
             System.out.println("Sondage non ajoutéé");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Sondage");
            tr.setMessage("Ajoutée failed ");
            tr.setNotificationType(NotificationType.ERROR);
            tr.showAndDismiss(Duration.millis(5000));
        }else{ 
            
         Insert();
        }
       
    }
    
    private void addListenerForTable(){
        table.getSelectionModel().selectedItemProperty().addListener((obs,oldselection,newselection)->{
        if (newselection != null){
            btnModifier.setDisable(false);
            btnsupprimer.setDisable(false);
            Textsujet.setText(newselection.getSujet());
            Textcategorie.setText(newselection.getCatégorie());
        }else{
               Textsujet.setText("");
               Textcategorie.setText("");
               btnModifier.setDisable(true);
               btnsupprimer.setDisable(true);
        }
        }); 
    
    }

    @FXML
    private void ModifierT(ActionEvent event) {
        Sondage sondage = table.getSelectionModel().getSelectedItem();
        String sujet = Textsujet.getText();
        String cat = Textcategorie.getText();
        Sondage s = new Sondage(sondage.getSondage_id(),sujet,cat);
        
        // BOX CONFIRMATION 
//        Stage stage = (Stage)MyAnchorPane.getScene().getWindow();
        Stage stage = new Stage();
        
        Alert.AlertType type =Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");
        alert.initModality(Modality.APPLICATION_MODAL);
       // alert.initOwner(stage);
        alert.getDialogPane().setContentText("Voulez-vous modifier le sondage?");
        alert.getDialogPane().setHeaderText("Confirmation");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get()== ButtonType.OK){
            ss.modifier(s);
            ShowTable();
            
        //NOTIFICATION
        
         System.out.println("Sondage Modifiée");
            TrayNotification tr = new TrayNotification();
            AnimationType typeE = AnimationType.POPUP;
            tr.setAnimationType(typeE);
            tr.setTitle("Sondage");
            tr.setMessage("Modifiée avec succés");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
           
        }else if (result.get()== ButtonType.CANCEL){
            System.out.println("jj");
        }
       
          
            
        
    }

    @FXML
    private void SupprimerT(ActionEvent event) {
         Sondage sondage = table.getSelectionModel().getSelectedItem();
        String sujet = Textsujet.getText();
        String cat = Textcategorie.getText();
        int id = sondage.getSondage_id();
        
         // BOX CONFIRMATION 
//        Stage stage = (Stage)MyAnchorPane.getScene().getWindow();
        Stage stage = new Stage();
        
        Alert.AlertType type =Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");
        alert.initModality(Modality.APPLICATION_MODAL);
       // alert.initOwner(stage);
        alert.getDialogPane().setContentText("Voulez-vous supprimer le sondage?");
        alert.getDialogPane().setHeaderText("Confirmation");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get()== ButtonType.OK){
            ss.supprimer(id);
            ShowTable();
            
        //NOTIFICATION
        
         System.out.println("Sondage Supprimée");
            TrayNotification tr = new TrayNotification();
            AnimationType typeE = AnimationType.POPUP;
            tr.setAnimationType(typeE);
            tr.setTitle("Sondage");
            tr.setMessage("Supprimé avec succés");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
           
        }else if (result.get()== ButtonType.CANCEL){
            System.out.println("jj");
        }
       
          
    }

    @FXML
    private void AjouterQuestion(ActionEvent event) {
        
          try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/Questions.fxml"));
            Parent root = loader.load();
           
            QuestionsController questionController = loader.getController();
            questionController.showInformation(Textsujet.getText());
            
            Scene scene = new Scene(root);
            Stage stage =(Stage)btnAjouter.getScene().getWindow();
            stage.setScene(scene);
            //stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
    }

   //----------------------Control saisie----------------------
    
    @FXML
    private boolean testSujet() {
         int nbNonChar = 0;
        for (int i = 1; i < Textsujet.getText().trim().length(); i++) {
            char ch = Textsujet.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && Textsujet.getText().trim().length() >= 3) {
          //  nomCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
           // nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }

    @FXML
    private boolean testCategorie() {
           int nbNonChar = 0;
        for (int i = 1; i < Textcategorie.getText().trim().length(); i++) {
            char ch = Textcategorie.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && Textcategorie.getText().trim().length() >= 3) {
          //  nomCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
           // nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }

}





    

    
    

    


