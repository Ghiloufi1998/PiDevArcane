/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Service.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author Ghiloufi
 */
public class ProfileFXMLController implements Initializable {

    @FXML
    private Text role;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXComboBox<String> sexe;
    @FXML
    private JFXTextField email;
    UserService us = new UserService(); 
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXButton btnmaj;
    @FXML
    private JFXButton parcourir;
    @FXML
    private ImageView image;
    private javafx.scene.image.Image imagee;
    @FXML
    private Text imglink;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> list = FXCollections.observableArrayList("Femme", "Homme");
         sexe.setItems(list);
          try {
         role.setText("Je Suis Un : " + us.getUserlogged().getRole());
       
            getUserdata();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(ProfileFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void getUserdata() throws SQLException {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        nom.setText(us.getUserlogged().getFname());
        prenom.setText(us.getUserlogged().getName());
        email.setText(us.getUserlogged().getEmail());
        adresse.setText(us.getUserlogged().getAdresse());
        sexe.setValue(us.getUserlogged().getGender());
        
        Image imageee = new Image(String.valueOf(us.getUserlogged().getImage()));
    image.setFitHeight(250); //726
    image.setFitWidth(250); //500
    image.setImage(imageee);
         date.setValue(LocalDate.parse(us.getUserlogged().getBirthday().toString(), formatter));
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        User u = new User();
        u.setId(us.getUserlogged().getId());
        u.setBirthday(Date.valueOf(date.getValue()));
        u.setEmail(email.getText());
        u.setFname(prenom.getText());
        u.setGender(sexe.getValue());
        u.setName(nom.getText());
        u.setImage(imglink.getText());
    
        u.setAdresse(adresse.getText());
        u.setRole(us.getUserlogged().getRole());
        
       
     
        
        us.UpdateUserWithoutOffre(u);
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
                imglink.setText(F);
               
                
                   imagee = new javafx.scene.image.Image(file.toURI().toString(), 150, 100, true, true);
                   image.setImage(imagee);

            } else {
                JOptionPane.showMessageDialog(null, "Impossible d'ajouter");
            }
        });
    }
}
