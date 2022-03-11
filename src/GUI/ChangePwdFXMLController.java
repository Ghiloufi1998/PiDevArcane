/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Service.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.Mailing;
import static utils.PatternEmail.validate;

/**
 * FXML Controller class
 *
 * @author Ghiloufi
 */
public class ChangePwdFXMLController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private Button recuperer_btn;
    @FXML
    private TextField oldmdp;
    @FXML
    private TextField newmdp;
    @FXML
    private TextField confirmmdp;
    UserService us = new UserService();
    @FXML
    private TextField codeeconf;
    @FXML
    private Button btnchange;
    static String codeconf = getSaltString();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            email.setText(us.getUserlogged().getEmail());
        } catch (SQLException ex) {
            Logger.getLogger(ChangePwdFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnchange.setDisable(false);

    }

    @FXML
    private void envoyer_code(ActionEvent event) throws SQLException {
        if (validate(email.getText())) {
            UserService usr = new UserService();

            User user = new User();
            user = usr.getUserByEmail(email.getText());
            if (user != null) {
                
                System.out.println("Code Confirmation pour " + user.getEmail() + " est : " + codeconf);

            
                Mailing m = new Mailing();
                String body = "Bonjour Mme/mr " + user.getName() + "\n"
                        + "Votre Code de Confirmation " + codeconf;
                m.sendEmail(email.getText(), "Code De Confirmation", body);
                email.setVisible(false);
                System.out.println("Code envoyé par email");
            } else {
                System.out.println("Utilisateur introuvable");
            }
        }
    }

    @FXML
    private void change(ActionEvent event) throws SQLException {
        if ((codeeconf.getText().equals(codeconf)) && (newmdp.getText().equals(confirmmdp.getText())) && (us.checkoldmdp(email.getText(), oldmdp.getText()))) {
            System.out.println(codeeconf);
            System.out.println(codeconf);
           us.changePassword(newmdp.getText(), email.getText());
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Mot de Passe");
            tr.setMessage("Changé avec succès");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
        } else {
             System.out.println(codeeconf.getText());
            System.out.println(codeconf);
            System.out.println("erreur");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Erreur");
            tr.setMessage("Lors de modification de mot de passe");
            tr.setNotificationType(NotificationType.ERROR);
            tr.showAndDismiss(Duration.millis(5000));
        }
    }

   static protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 4) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
