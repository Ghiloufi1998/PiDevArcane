package GUI;

import entities.Hebergement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HebergementLController implements Initializable {

    @FXML
    private ImageView imagelab;
    
    @FXML
    private Label prix;
    @FXML
    private Label description;
    @FXML
    private Label disponibilite;
    @FXML
    private Label type;
    @FXML
    private Label image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Hebergement t) {
        description.setText(String.valueOf(t.getDescription()));
        disponibilite.setText(String.valueOf(t.getDisponibilité()));
        prix.setText(String.valueOf(t.getPrix()));
        type.setText(String.valueOf(t.getType()));
       image.setText(String.valueOf(t.getImage()));
       //Image image = new Image(getClass().getResourceAsStream(h.getImage()));
       //imagelab.setImage(image);
    }
}