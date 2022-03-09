/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxGui;

import entities.Offre;
import entities.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import service.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import static utils.PatternEmail.validate;

/**
 * FXML Controller class
 *
 * @author Souha saffar
 */
public class SignUpController implements Initializable {
  private javafx.scene.image.Image image;

    @FXML
    private Label gendre;
    @FXML
    private Button ajout;
    @FXML
    private PasswordField mdp;
    @FXML
    private TextField no;
    @FXML
    private TextField preno;
    @FXML
    private DatePicker ddn;
    @FXML
    private TextField address;
    @FXML
    private TextField email;
    @FXML
    private ComboBox<String> gend;
    @FXML
    private TextField imag;
    @FXML
    private Button parcourir;
        @FXML
    private ImageView img;
  UserService us = new UserService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> list = FXCollections.observableArrayList("Femme", "Homme");
         gend.setItems(list);
        // TODO
    }  
    
    private boolean validateInputs() {
        if (no.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez saisir votre Prenom");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        } else if ((preno.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre Nom");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if ((email.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
         
        } else if ((mdp.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre mot de passe");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
       
        } else if (mdp.getText().length() < 8) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Mot de passe doit dépasser les 6 caractères");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if (!(validate(email.getText()))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez vérifier votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if (ddn.getValue().isAfter(LocalDate.now())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez vérifier votre date de naissance");
            alert2.setHeaderText(null);
            alert2.show();
            return false; 
        } 
        return true;
} 

    @FXML
    private void addPerson(ActionEvent event) throws IOException {
        
            
            if (!validateInputs()); 
            else{
                Offre f1=new Offre(1,"", "", 0, 0);
                User u1 = new User();
                u1.setName(no.getText());
                u1.setAdresse(address.getText());
                u1.setImage(imag.getText());
                u1.setGender(gend.getValue());
                u1.setBirthday(java.sql.Date.valueOf(ddn.getValue()));
                u1.setEmail(email.getText());
                u1.setFname(preno.getText());
                u1.setPassword(mdp.getText());
                u1.setOffre(f1);
              
//            String nom=no.getText();
//            String prénom=preno.getText();
//            Date datenaissance=java.sql.Date.valueOf(ddn.getValue());
//            String motpasse=mdp.getText();
//            String addresse =address.getText();
//            String e_mail=email.getText();
//            String imagee=imag.getText();
//            String g=(String) gend.getValue();
// 
            
             
           
                  
                     System.out.println(u1);
                    us.SignUp(u1);
                      TrayNotification tr = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;
                    tr.setAnimationType(type);
                    tr.setTitle("add");
                    tr.setMessage("Created succefully");
                    tr.setNotificationType(NotificationType.SUCCESS);
                    tr.showAndDismiss(Duration.millis(5000));
              try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("../javaFxGui/login.fxml"));
            Parent root = loader.load();
           
         
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
                   
            }
                    
//                    AjouterUserController auc =loader.getController();
//                  //  auc.setAffiche(us.afficherUser().toString());
//                    no.getScene().setRoot(root);
                  
       
           // System.out.println(ex.getMessage());
            
    }

    @FXML
    private void seelect(ActionEvent event) {
    }

    @FXML
    private void Pparcourir_User(ActionEvent event) {
     Stage primaryStage = new Stage();
        primaryStage.onShowingProperty();
        primaryStage.setTitle("selectionner une image !!!");
        FileChooser filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files ", "*.png", "*.jpg", "*.gif"),
        new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        parcourir.setOnAction(e -> {
            File file = filechooser.showOpenDialog(primaryStage);
            if (file != null) {
                //String s = file.getAbsolutePath();
                String F = file.toURI().toString();
                imag.setText(F);
                  image = new javafx.scene.image.Image(file.toURI().toString(), 150, 100, true, true);
               Image imagee;
                    img.setImage(image);

            } else {
                JOptionPane.showMessageDialog(null, "Impossible d'ajouter");
            }
        });
    }
}
