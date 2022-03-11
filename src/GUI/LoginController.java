/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Service.UserService;
import com.jfoenix.controls.JFXButton;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.management.Notification;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.GuiNavigate;
import static utils.PatternEmail.validate;

   

/**
 * FXML Controller class
 *
 * @author Souha saffar
 */
public class LoginController implements Initializable {

    @FXML
    private TextField ai;
    @FXML
    private Button sc;
    @FXML
    private PasswordField mp;
    private boolean user;
       public static int userid;
       public static User usr;
    UserService as = new UserService();
    @FXML
    private ImageView key_pic_Login_Btn;
    @FXML
    private JFXButton mdpoub;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }
        @FXML
     private void click(ActionEvent event) throws IOException,SQLException { 

                UserService us = new UserService();
                String email = ai.getText();
                String mdp = mp.getText();
             
             if ( (mdp.length()>8) && ( validate(email) ) ){
                  
                  if ( us.login(email, mdp)  ) {
                   TrayNotification tr = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tr.setAnimationType(type);
                tr.setTitle("User connecté");
                tr.setMessage("Binevenue");
                tr.setNotificationType(NotificationType.SUCCESS);
                tr.showAndDismiss(Duration.millis(5000));
                 if( us.getUserByEmail(email).getRole().equals("Client")){
                          GuiNavigate nav = new GuiNavigate();
        nav.navigate(event, "PIDEV", "/GUI/DashboardUserFXML.fxml");
                    }
                 else if (us.getUserByEmail(email).getRole().equals("Gestionnaire")){
                          GuiNavigate nav = new GuiNavigate();
        nav.navigate(event, "PIDEV", "/GUI/DashboardGestFXML.fxml");
                    }
                 else if (us.getUserByEmail(email).getRole().equals("Admin")){
                          GuiNavigate nav = new GuiNavigate();
        nav.navigate(event, "PIDEV", "/GUI/DashboardAdminFXML.fxml");
                    }
                   
                }
                  else {
                      TrayNotification tr = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tr.setAnimationType(type);
                tr.setTitle("Erreur");
                tr.setMessage("Non Conncecté");
                tr.setNotificationType(NotificationType.ERROR);
                tr.showAndDismiss(Duration.millis(5000));
                  } 
                    
                }else { TrayNotification tr = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tr.setAnimationType(type);
                tr.setTitle("Erreur");
                tr.setMessage("Verifier les champs");
                tr.setNotificationType(NotificationType.ERROR);
                tr.showAndDismiss(Duration.millis(5000));
                 }
     }

    @FXML
    private void recupmdp(ActionEvent event) throws IOException {
        
        GuiNavigate nav = new GuiNavigate();
        nav.navigate(event, "PIDEV", "/GUI/RecupMDP.fxml");
    }
            }
       
        