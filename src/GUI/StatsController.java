/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Stat;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import Service.ServiceVoyageorganise;
import utils.GuiNavigate;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class StatsController implements Initializable {

    private PieChart heber;
    @FXML
    private AnchorPane stats;
    @FXML
    private BarChart<String, Integer> bar;
    @FXML
    private NumberAxis count;
    @FXML
    private CategoryAxis valeurs;
    @FXML
    private Button button;
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
                ServiceVoyageorganise sv = new ServiceVoyageorganise();
               String h=sv.consulterstat().get(0).getMaxh();
               String t = sv.consulterstat().get(0).getMaxT();
               String v = sv.consulterstat().get(0).getMaxv();
               int h1= sv.consulterstat().get(0).getCounth();
               int t1= sv.consulterstat().get(0).getCountt();
               int v1= sv.consulterstat().get(0).getCountv();
               double avg= sv.consulterstat().get(0).getAvgprix();
               int somme= sv.consulterstat().get(0).getCount();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Max heber , trans , vol ,Nbre voyages organisé");
        series.getData().add(new XYChart.Data<>("Hebergement : "+h ,h1));
        series.getData().add(new XYChart.Data<>("Transport : "+t,t1));
        series.getData().add(new XYChart.Data<>("Meilleur Dest :"+v,v1));
        series.getData().add(new XYChart.Data<>("Nbre voyages organisé ",somme));
        
        bar.getData().add(series);
       /*ObservableList<Data> l = FXCollections.observableArrayList(
        new PieChart.Data("Moncef", 25),
        new PieChart.Data("3abla", 25),
        new PieChart.Data("ghc", 25),
        new PieChart.Data("mob", 25) );
        
    
        heber.setData(l);*/
    }    

    @FXML
    private void affichstats(ActionEvent event) {
      try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Stats2.fxml"));
            Parent root = loader.load();
            Stats2Controller controller = loader.getController();
            Scene s = new Scene(root);
            Stage sg = new Stage();
            sg.setScene(s);
            sg.show();
            //nom.getScene().setRoot(root);
        } catch (IOException ex) { 
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
    

    }

    @FXML
    private void Rese(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StatsReser.fxml"));
            Parent root = loader.load();
            StatsReserController controller = loader.getController();
            Scene s = new Scene(root);
            Stage sg = new Stage();
            sg.setScene(s);
            sg.show();
            //nom.getScene().setRoot(root);
        } catch (IOException ex) { 
            Logger.getLogger(StatsController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        GuiNavigate nav = new GuiNavigate();
      nav.navigate(event, "Gestion", "/gui/Gest.fxml");
    }

}
