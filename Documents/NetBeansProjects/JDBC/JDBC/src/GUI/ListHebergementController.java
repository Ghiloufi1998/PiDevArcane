package gui;

import entities.Hebergement;
import entities.Transport;
import static gui.TOTOController.id_Transport;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import static jdk.nashorn.internal.runtime.Debug.id;
import services.ServiceTransport;
import utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ListHebergementController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button voyage_btn;
    @FXML
    private Button heb_btn;
    @FXML
    private Button transport_btn;
    @FXML
    private Button prom_btn;
    @FXML
    private Button reserv_btn;
    @FXML
    private Button sondage_btn;
    @FXML
    private Button prod_btn;
    @FXML
    private Button cmd_btn;
    @FXML
    private Button forum_btn;
    @FXML
    private Button reclam_btn;
    @FXML
    private AnchorPane Hotels;
    @FXML
    private AnchorPane ap;
    @FXML
    private GridPane grid;
    @FXML
    private ImageView user_image;
    @FXML
    private Button profile_btn;
    @FXML
    private Button mdp_btn;
    @FXML
    private Button logout;
    private final List<Hebergement> Hebergements = new ArrayList<>();
    ServiceTransport hs = new ServiceTransport();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Hebergements .addAll(hs.listHebergement(id_Transport));
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < Hebergements.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/gui/HebergementL.fxml"));
                AnchorPane anchorPane = fxmlloader.load();

                HebergementLController hc = fxmlloader.getController();
                hc.setData(Hebergements.get(i));
                if (column == 5) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
             
                }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        // TODO
    }    

    @FXML
    private void Hebergement(ActionEvent event) throws IOException {
        
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "test", "/gui/frontHotel.fxml");
    }

    @FXML
    private void Transport(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "test", "/gui/frontTransport.fxml");
    }
    
}