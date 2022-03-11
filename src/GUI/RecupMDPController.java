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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Service.UserService;
import utils.Mailing;
import utils.GuiNavigate;
import static utils.PatternEmail.validate;

/**
 * FXML Controller class
 *
 * @author Souha saffar
 */
public class RecupMDPController implements Initializable {

    @FXML
    private Button recuperer_btn;
    @FXML
    private TextField email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
@FXML
    private void envoyer_mdp(ActionEvent event) throws SQLException, IOException {
        if (validate(email.getText())) {
            UserService usr = new UserService();

            User user = new User();
            user = usr.getUserByEmail(email.getText());
            if (user != null) {
                String plainpassword = getSaltString();
                System.out.println("Le nouveau mot de passe de " + user.getEmail()+ " est : " + plainpassword);

                usr.changePassword(plainpassword, email.getText());
                Mailing m = new Mailing();
                String body="Bonjour Mme/mr "+ user.getName()+"\n"
                        + "Votre nouveau mot de passe est "+plainpassword;
                m.sendEmail(email.getText(), "Récupérer mot de passe", body);
                email.setVisible(false);
                System.out.println("Mot de passe envoyé par email");
            } else {
                System.out.println("Utilisateur introuvable");
            }

        }
        GuiNavigate nav= new GuiNavigate();
            nav.navigate(event, "Login", "../GUI/Login.fxml");
    }
       protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 9) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    }
    
