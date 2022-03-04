/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Voyageorganise;
import entities.voyage_desc_prix;
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
import services.ServiceVoyageorganise;

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
}
