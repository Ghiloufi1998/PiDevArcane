package gui;

import entities.Hebergement;
import entities.Transport;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author user
 */
public class TOTOController implements Initializable {

    @FXML
    private ImageView imagelab;
    private Label nom;
    @FXML
    private Label adresse;
    private Label Dated;
    private Label Datef;
    @FXML
    private Label prix;
    private Label description;
    @FXML
    private Button b_voyage;
    @FXML
    private Label id;

    public static int id_hotel;
    @FXML
    private Button b_transport;
    @FXML
    private Label disponibilité;
    @FXML
    private Label image;
    @FXML
    private Label type;
     public static int id_Transport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void setData(Transport h) {

        id.setText(String.valueOf(h.getTransport_id()));
        prix.setText(String.valueOf(h.getPrix()));
        type.setText(String.valueOf(h.getType()));
        disponibilité.setText(String.valueOf(h.getDisponibilité()));
        description.setText(String.valueOf(h.getDescription()));
       
        Image image = new Image(getClass().getResourceAsStream(h.getImage()));
        imagelab.setImage(image);
    }

    @FXML
    private void List_Voyage(ActionEvent event) throws IOException {

        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "test", "/gui/listVoyage.fxml");
        id_hotel = Integer.parseInt(id.getText().toString());

    }

    @FXML
    private void List_Hebergement(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "test", "/gui/listHebergement.fxml");
        id_hotel = Integer.parseInt(id.getText().toString());
    }

}