/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import Entities.Reclamation;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Service.ReclamationService;
import Service.UserService;
import java.sql.SQLException;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.Notification;

/**
 * FXML Controller class
 *
 * @author Souha saffar
 */
public class ReclamationController implements Initializable {

    @FXML
    private TextArea Descriptions;
    @FXML

    private Button env;
    @FXML
    private BorderPane bp;
    @FXML
    private Button pay_btn;
    @FXML
    private Button heb_btn;
    @FXML
    private Button transport_btn;
    @FXML
    private Button stat_btn;
    @FXML
    private Button reserv_btn;
    @FXML
    private Button sondage_btn;
    @FXML
    private Button guide_btn;
    @FXML
    private Button cours_btn;
    @FXML
    private Button exer_btn;
    @FXML
    private Button reclam_btn;
    @FXML
    private Button vol_btn;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void envoyer(ActionEvent event) throws SQLException {

        if (!Descriptions.getText().equals("")) {
            ReclamationService rs = new ReclamationService();
            UserService us = new UserService();
            Reclamation r = new Reclamation();
            r.setDescription(Descriptions.getText());
            r.setEtat(0);
            r.setUser_id(us.getUserlogged().getId());
            rs.Create(r);
        } else {

            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Erreur");
            tr.setMessage("Non ajouté");
            tr.setNotificationType(NotificationType.ERROR);
            tr.showAndDismiss(Duration.millis(5000));

        }
    }

    @FXML
    private void pay(ActionEvent event) {
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
    private void res(ActionEvent event) {
    }

    @FXML
    private void sondage(ActionEvent event) {
    }

    @FXML
    private void guide(ActionEvent event) {
    }

    @FXML
    private void cours(ActionEvent event) {
    }

    @FXML
    private void exercice(ActionEvent event) {
    }

    @FXML
    private void rec(ActionEvent event) {
    }

    @FXML
    private void vol(ActionEvent event) {
    }

}
