package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import utils.MyDB;
import utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author user
 */
public class STATTRANSPORTController implements Initializable {

    @FXML
    private PieChart pieChart;
    private Connection conn;
    private ObservableList data;
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
    private AnchorPane ap;
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
    public void initialize(URL url, ResourceBundle rb) {
        buildData();
        pieChart.getData().addAll(data);
    }

    public void buildData() {
        conn = MyDB.getInstance().getConnection();

        data = FXCollections.observableArrayList();
        try {
            //SQL FOR SELECTING NATIONALITY OF CUSTOMER
            String SQL = "SELECT COUNT(Transport_id), "
                    + "type FROM TRANSPORT "
                    + "GROUP BY Type";

            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                //adding data on piechart data
                data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
            }
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            return;
        }

    }

    @FXML
    private void Hebergement(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
      nav.navigate(event, "Hebergement", "/gui/Hebergement.fxml");
   }
     @FXML
    private void Transport(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
      nav.navigate(event, "Transport", "/gui/BackTransport.fxml");
   }

}