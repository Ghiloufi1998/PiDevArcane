/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Voyageorganise;
import Entities.voyage_desc_prix;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Service.ServiceVoyageorganise;
import utils.GuiNavigate;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class Stats2Controller implements Initializable {

    @FXML
    private Button af;
    @FXML
    private PieChart avg;
    ServiceVoyageorganise sv = new ServiceVoyageorganise();
    private TextField prix;
    @FXML
    private TableView<Voyageorganise> chpv;
    @FXML
    private TableView<Voyageorganise> expv;
    @FXML
    private TableColumn<Voyageorganise, String> exp;
    @FXML
    private TableColumn<Voyageorganise, String> chv;
    @FXML
    private TableColumn<Voyageorganise, Integer> prixc;
    @FXML
    private TableColumn<Voyageorganise, Integer> prixv;
    @FXML
    private TextField Pm;
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
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        addListenerForTable();
        ShowTable();
        Double a=((sv.consulterstat()).get(0)).getAvgprix();
        Pm.setText(a.toString());
        Pm.setDisable(true);
    }    

    @FXML
    private void afficher(ActionEvent event) {
        
        ObservableList<Data> l = FXCollections.observableArrayList(
        new PieChart.Data("Expensive", sv.expensivenbr()),
        new PieChart.Data("Cheap", sv.Cheapnbr()) );
     
    
        avg.setData(l);
    }
    
   private ObservableList<Voyageorganise> getTableList() {
       
        ObservableList<Voyageorganise> List = sv.Cheap();
        return List ;
        
    }
   
    private ObservableList<Voyageorganise> getTableList1() {
       
        ObservableList<Voyageorganise> List1 = sv.expensive();
        return List1 ;
        
    }
    
    public void ShowTable() {
        ObservableList<Voyageorganise> list = getTableList();
        ObservableList<Voyageorganise> list1 = getTableList1();
        
        chpv.setItems(list);
        chv.setCellValueFactory(new PropertyValueFactory<>("Description"));
        prixc.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        
        expv.setItems(list1);
        exp.setCellValueFactory(new PropertyValueFactory<>("Description"));
        prixv.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        
        
        
       
    }
     private void addListenerForTable(){
        chpv.getSelectionModel().selectedItemProperty().addListener((obs,oldselection,newselection)->{
        if (newselection != null){
            prix.setDisable(false);
            
        }else{
               
              prix.setDisable(true);
              
        }
        }); 
    
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
      nav.navigate(event, "Gestion", "/gui/stats.fxml");
        
    }
}
