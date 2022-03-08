/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.FactureService;

/**
 * FXML Controller class
 *
 * @author gorgi
 */
public class PaiementController implements Initializable {

    @FXML
    private TextField carte;
    @FXML
    private TextField month;
    @FXML
    private TextField cvc;
    @FXML
    private TextField year;
    @FXML
    private TextField prix;
    @FXML
    private Button valider;
    @FXML
    private Button Annuler;

    public void setPrix(String prix) {
        this.prix.setText(prix);
    }
    FactureService f = new FactureService();

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            /*try {*/
            // prix.setDisable(true);
            // TODO
            this.prix.setText(String.valueOf(f.getdprix()));

            valider.setOnAction((ActionEvent event) -> {

                if (controleDeSaisi()) {

                    System.out.print("transaction reussie");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("transaction reussie");
                    alert.setHeaderText("Payment avec succes! Vérifier Email");
                    ButtonType con = new ButtonType("OK");
                    alert.getButtonTypes().clear();
                    alert.getButtonTypes().addAll(con);
                    Optional<ButtonType> option = alert.showAndWait();
                    if (option.get() == con) {
                        // Recipient's email ID needs to be mentioned.
                        String to = "bensalahamine546@gmail.com";

                        // Sender's email ID needs to be mentioned
                        String from = "amobs145@gmail.com";

                        // Assuming you are sending email from localhost
                        String host = "localhost";

                        // Get system properties
                        Properties properties = System.getProperties();

                        // Setup mail server
                        properties.setProperty("mail.smtp.host", host);

                        // Get the default Session object.
                        Session session = Session.getDefaultInstance(properties);

                        try {
                            // Create a default MimeMessage object.
                            MimeMessage message = new MimeMessage(session);

                            // Set From: header field of the header.
                            message.setFrom(new InternetAddress(from));

                            // Set To: header field of the header.
                            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                            // Set Subject: header field
                            message.setSubject("This is the Subject Line!");

                            // Send the actual HTML message, as big as you like
                            message.setContent("<h1>This is actual message</h1>", "text/html");

                            // Send message
                            Transport.send(message);
                            System.out.println("Sent message successfully....");
                        } catch (MessagingException mex) {
                            mex.printStackTrace();
                        }
                    }
                }
            }
        );

        Annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.close();

            }

        });

    }
    catch (SQLException ex

    
        ) {
           Logger.getLogger(PaiementController.class.getName()).log(Level.SEVERE, null, ex);
    }

}

private boolean controleDeSaisi() {  

        if (carte.getText().isEmpty() || month.getText().isEmpty() || year.getText().isEmpty()
                || cvc.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", carte.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la reference ! ");
                carte.requestFocus();
                carte.selectEnd();
                return false;
            }

           if (!Pattern.matches("[0-9]*", month.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le Mois ! ");
                month.requestFocus();
                month.selectEnd();
                return false;
            }if (!Pattern.matches("[0-9]*", year.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez l'année ! ");
                year.requestFocus();
                year.selectEnd();
                return false;
            }if (!Pattern.matches("[0-9]*", cvc.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le cvc ! ");
                cvc.requestFocus();
                cvc.selectEnd();
                return false;
            }
           
        }
        return true;
         
    }
    
    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
    @FXML
    void email(ActionEvent event) {

    }
}
