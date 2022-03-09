/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxGui;

import entities.User;
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
import service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Duration;
import javax.management.Notification;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.NavigationEntreInterfaces;

   

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
        @FXML
     private void click(ActionEvent event) throws IOException,SQLException { 
     
       
          
                UserService us = new UserService();
                String email = ai.getText();
                String mdp = mp.getText();
             
             
                   us.login(email,mdp);
                   TrayNotification tr = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tr.setAnimationType(type);
                tr.setTitle("User connecté");
                tr.setMessage("Binevenue");
                tr.setNotificationType(NotificationType.SUCCESS);
                tr.showAndDismiss(Duration.millis(5000));
                
                
                    
                }
            }
       
        
//        else if (user){
//             try {
//            if (validateInputs()) {
//                String email = ai.getText();
//                String mdp = ai.getText();
//                User a = as.searchByPseudoPassU(email, mdp);
//                System.out.println(a);
//                if (a != null &&(mp.getText().equals( a.getPassword()))) {
//                    as.login(email,mdp);
//                    NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
//                    nav.navigate(event, "Sidebar", "/pidevjava/gui/Back.fxml");
//                    System.out.println(a.getImage());
//                    //API Notification lors de l'ajout d'un evenement
////                    Notifications notificationBuilder = Notifications.create()
////                            .title("Welcome  " + login_txt.getText())
////                            .text("Bienvenue à Tabaani Travel Agency")
////                            .hideAfter(javafx.util.Duration.seconds(4))
////                            .position(Pos.TOP_CENTER);
////                    notificationBuilder.show();
////                    
////                        Notification notif = new Notification();
////              notif.notification("Login","Bienvenue à Above & beyond",NotificationType.SUCCESS);
////                   
//                } else {
//                    Alert alert2 = new Alert(Alert.AlertType.WARNING);
//                    alert2.setTitle("Erreur");
//                    alert2.setContentText("Veuillez vérifier votre email ou mot de passe");
//                    alert2.setHeaderText(null);
//                    alert2.show();
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        }

 }

//           private boolean validateInputs() throws SQLException {
//
//        if (ai.getText().isEmpty()) {
//            Alert alert1 = new Alert(Alert.AlertType.WARNING);
//            alert1.setTitle("Erreur");
//            alert1.setContentText("Veuillez saisir votre email");
//            alert1.setHeaderText("Controle de saisie");
//            alert1.show();
//            return false;
//        }
//        if (mp.getText().isEmpty()) {
//            Alert alert1 = new Alert(Alert.AlertType.WARNING);
//            alert1.setTitle("Erreur");
//            alert1.setContentText("Veuillez saisir votre mot de passe");
//            alert1.setHeaderText("Controle de saisie");
//            alert1.show();
//            return false;
//        }
//        return true;
//      
//    }    
//
//    
//}
