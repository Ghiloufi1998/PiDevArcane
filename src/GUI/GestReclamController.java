/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
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
import Service.ReclamationService;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Souha saffar
 */
public class GestReclamController implements Initializable {

    @FXML
    private TableColumn<Reclamation, String> Description;
    @FXML
    private TableColumn<Reclamation, Integer> Et;
    @FXML
    private Button Del;
    @FXML
    private Button Val;
    @FXML
    private TableColumn<Reclamation, String> User;
    @FXML
    private TableView<Reclamation> tr;
    ReclamationService rs = new ReclamationService();
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
        addListenerForTable();
        ShowTable();
        // TODO
    }   
    
    private ObservableList<Reclamation> getTableList() {
       
        ObservableList<Reclamation> List = rs.readAll();
        return List ;
        
    }
    public void ShowTable() {
        ObservableList<Reclamation> list = getTableList();
        tr.setItems(list);
     
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Et.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        User.setCellValueFactory(new PropertyValueFactory<>("User"));
       
    }
    private void addListenerForTable(){
        tr.getSelectionModel().selectedItemProperty().addListener((obs,oldselection,newselection)->{
        if (newselection != null){
            Val.setDisable(false);
            Del.setDisable(false);
            
        }else{
               
               Val.setDisable(true);
               Del.setDisable(true);
        }
        }); 
    
    }

    @FXML
    private void Supprimer(ActionEvent event) {
                Reclamation r = tr.getSelectionModel().getSelectedItem();
                rs.Delete(r.getId());
                ShowTable();

    }

    @FXML
    private void Valider(ActionEvent event) {
        Reclamation r = tr.getSelectionModel().getSelectedItem();
                rs.Valider(r);
                ShowTable();
                
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
