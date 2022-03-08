/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.FactureService;
import services.PaiementCRUD;

/**
 * FXML Controller class
 *
 * @author bensa
 */
public class FactureController implements Initializable {

    @FXML
    private Label mon;

    @FXML
    private TextField input;
    @FXML
    private ComboBox choix;
    String type[]
            = {"en ligne", "versement", "agence"};
    FactureService f = new FactureService();

    // Create a combo box
    ObservableList x = FXCollections.observableArrayList(type);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ObservableList x = FXCollections.observableArrayList(type);
            choix.setItems(x);
            LocalDate today = LocalDate.now();
            LocalDate next = today.plusDays(20);

            input.getText();
            input.setText(next.toString());
            input.setDisable(true);

            mon.setText(Integer.toString(f.getdprix()));

        } catch (SQLException ex) {
            Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void insert(ActionEvent event) throws IOException {

        try {
            String ty = (String) choix.getValue();
            
            LocalDate x = LocalDate.parse(input.getText());
            Date u = java.sql.Date.valueOf(x);
            String z = mon.getText();
            
            f.updateIdDate(u);
            PaiementCRUD pc = new PaiementCRUD();
            pc.updatemode(ty, u, z);
            showConfirmation();
        } catch (SQLException ex) {
            Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showConfirmation() throws SQLException {
        FactureService f = new FactureService();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Télecharger version PDF");
        alert.setHeaderText("voulez vous télecharger une copie PDF?");
        ButtonType con = new ButtonType("Télecharger");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(con );

        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() != con) {

            f.pdfversion();
        }
        else {
		  f.pdfversion();
        }

    }
}
