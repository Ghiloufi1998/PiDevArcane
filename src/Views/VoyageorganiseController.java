/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import entities.Voyageorganise;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceVol;
import services.ServiceVoyageorganise;
import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import services.FactureService;
import services.RéservationCRUD;
import services.ServiceHebergement;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class VoyageorganiseController implements Initializable {

    ServiceVol vv = new ServiceVol();
    private Scene scene;
    private Parent root;
    private Stage stage;
    ServiceVoyageorganise sv = new ServiceVoyageorganise();
    @FXML
    private TableView<Voyageorganise> Voyages;
    @FXML
    private TableColumn<Voyageorganise, String> Description;
    @FXML
    private TableColumn<Voyageorganise, String> Image;
    @FXML
    private TableColumn<Voyageorganise, Integer> Prix;
    @FXML
    private TableColumn<Voyageorganise, String> Vol_d;
    @FXML
    private TableColumn<Voyageorganise, String> Transport_d;
    @FXML
    private Button Supprimer;
    @FXML
    private Button Modifier;
    @FXML
    private TextField Descriptionv;
    @FXML
    private TextField prixv;
    @FXML
    private TextField imagev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ShowTable();
        addListenerForTable();

    }

    private ObservableList<Voyageorganise> getTableList() {

        ObservableList<Voyageorganise> List = sv.afficher();
        return List;

    }

    public void ShowTable() {
        ObservableList<Voyageorganise> list = getTableList();
        Voyages.setItems(list);

        Description.setCellValueFactory( new PropertyValueFactory<Voyageorganise, String>("Description"));
        Image.setCellValueFactory(new PropertyValueFactory<Voyageorganise, String>("Image"));
        Prix.setCellValueFactory(new PropertyValueFactory<Voyageorganise,Integer>("Prix"));
        //Nbre_places.setCellValueFactory(new PropertyValueFactory<>("Nbre_places"));

        Vol_d.setCellValueFactory((CellDataFeatures<Voyageorganise, String> param) -> new SimpleStringProperty(param.getValue().getVol().getDestination()));
        Transport_d.setCellValueFactory((CellDataFeatures<Voyageorganise, String> param) -> new SimpleStringProperty(param.getValue().getTransport().getDescription()));

    }

    private void addListenerForTable() {
        FactureService fc =new FactureService();
        ServiceHebergement sh= new ServiceHebergement();
        Voyages.getSelectionModel().selectedItemProperty().addListener((obs, oldselection, newselection) -> {
            ServiceVoyageorganise sv = new ServiceVoyageorganise();
            RéservationCRUD rc = new RéservationCRUD();
            if (newselection != null) {
                int txt = newselection.getPrix();
                String tx = newselection.getTransport().getDescription();
                int t = sv.vol(txt);
                int x = sv.trans(tx);
                int p1 =sh.prix(x);
                rc.updateIdVol(t);
                rc.updateIdHeb(x);
                int z=txt+p1;
                fc.updateIdprix(z);
            }

        });
    }

    public void sui(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("facture.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
