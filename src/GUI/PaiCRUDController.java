/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Paiement;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.FactureService;
import services.PaiementCRUD;

/**
 * FXML Controller class
 *
 * @author bensa
 */
public class PaiCRUDController implements Initializable {
@FXML
    private TableView<Paiement> TV;

    @FXML
    private TableColumn<Paiement,Integer> PID;

    @FXML
    private TableColumn<Paiement, Date> D;

    @FXML
    private TableColumn<Paiement, String> m;

    @FXML
    private TableColumn<Paiement,String> Mp;
     @FXML
    private Button mod;

    @FXML
    private Button supp;

    @FXML
    private TextField ip;

    @FXML
    private TextField im;

    @FXML
    private ComboBox iM;

    @FXML
    private DatePicker iDx;

PaiementCRUD ps =new PaiementCRUD();
FactureService pc =new FactureService();

    @FXML
    void del(ActionEvent event) {
   Paiement vo = TV.getSelectionModel().getSelectedItem();
   pc.FactureSupp(vo.getID_PAi());
     ShowTable();
//        sv.supprimer(vo.getVoyage_id());
//        ShowTable();
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList x = FXCollections.observableArrayList(type);
            iM.setItems(x);
        ShowTable();
        
    }    
    //    
     private ObservableList<Paiement> getTableList() {
       
        ObservableList<Paiement> List = ps.readAll();
        return List ;
        
}
     public void ShowTable() {
        ObservableList<Paiement> list = getTableList();
        TV.setItems(list);
       PID.setCellValueFactory(new PropertyValueFactory<>("Pai_ID"));
        D.setCellValueFactory(new PropertyValueFactory<>("Date"));
        m.setCellValueFactory(new PropertyValueFactory<>("Montant"));
        Mp.setCellValueFactory(new PropertyValueFactory<>("Mode_Pay"));
       
       
       
    }
     String type[]
            = {"en ligne", "versement", "agence"};
    @FXML
    void modif(ActionEvent event) {
Paiement vo = TV.getSelectionModel().getSelectedItem();
       
       
        Date x =Date.valueOf(iDx.getValue());
       
        String prix  = im.getText();
        String ty=(String) iM.getValue();      
      Paiement v = new Paiement(vo.getID_PAi(),x, prix,ty);
              
            ps.update(v);
            ShowTable();
    }
//p
//       TV.getSelectionModel().selectedItemProperty().addListener((obs,oldselection,newselection)->{
//        if (newselection != null){
//            Modifier.setDisable(false);
//            Supprimer.setDisable(false);
//            PID.setText(String.valueOf(newselection.getID_PAi()));
//            prixv.setText((newselection.getDescription()).toString());
//            imagev.setText(newselection.getImage());
//            Integer s = newselection.getPrix();
//            String ss=s.toString();
//            prixv.setText(ss);
//        }else{
//               
//               Modifier.setDisable(true);
//               Supprimer.setDisable(true);
//        }
//        });  
  
}
//public class ModifierVoyageController implements Initializable {
//        ServiceVol vv = new ServiceVol();
//
//    ServiceVoyageorganise sv = new ServiceVoyageorganise();
//    @FXML
//    private TableView<Voyageorganise> Voyages;
//    @FXML
//    private TableColumn<Voyageorganise, Integer> Voyage_id;
//    @FXML
//    private TableColumn<Voyageorganise, String> Description;
//    @FXML
//    private TableColumn<Voyageorganise, String> Image;
//    @FXML
//    private TableColumn<Voyageorganise, Integer> Prix;
//    @FXML
//    private TableColumn<Voyageorganise, Integer> Vol_id;
//    @FXML
//    private TableColumn<Voyageorganise, Integer> Transport_id;
//    @FXML
//    private Button Supprimer;
//    @FXML
//    private Button Modifier;
//    @FXML
//    private TextField Descriptionv;
//    @FXML
//    private TextField prixv;
//    @FXML
//    private TextField imagev;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//        addListenerForTable();
//        ShowTable();
//        
//    }  
//    
//     private ObservableList<Voyageorganise> getTableList() {
//       
//        ObservableList<Voyageorganise> List = sv.afficher();
//        return List ;
//        
//    }
//    
//    public void ShowTable() {
//        ObservableList<Voyageorganise> list = getTableList();
//        Voyages.setItems(list);
//        Voyage_id.setCellValueFactory(new PropertyValueFactory<>("Voyage_id"));
//        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
//        Image.setCellValueFactory(new PropertyValueFactory<>("Image"));
//        Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
//        //Nbre_places.setCellValueFactory(new PropertyValueFactory<>("Nbre_places"));
//        Vol_id.setCellValueFactory(new PropertyValueFactory<>("Vol_id"));
//        Transport_id.setCellValueFactory(new PropertyValueFactory<>("Transport_id"));
//       
//    }
//  
//    
//    
//    
//    private void addListenerForTable(){
//        Voyages.getSelectionModel().selectedItemProperty().addListener((obs,oldselection,newselection)->{
//        if (newselection != null){
//            Modifier.setDisable(false);
//            Supprimer.setDisable(false);
//            Descriptionv.setText(newselection.getDescription());
//            //prixv.setText((newselection.getDescription()).toString());
//            imagev.setText(newselection.getImage());
//            Integer s = newselection.getPrix();
//            String ss=s.toString();
//            prixv.setText(ss);
//        }else{
//               
//               Modifier.setDisable(true);
//               Supprimer.setDisable(true);
//        }
//        }); 
//    
//    }
//
//    @FXML
//    private void Delete(ActionEvent event) {
//        
//        Voyageorganise vo = Voyages.getSelectionModel().getSelectedItem();
//        sv.supprimer(vo.getVoyage_id());
//        ShowTable();
//
//        
//        
//    }
//
//    @FXML
//    private void Update(ActionEvent event) {
//     Voyageorganise vo = Voyages.getSelectionModel().getSelectedItem();
//        String desc = Descriptionv.getText();
//        String image = imagev.getText();
//        String prix  = prixv.getText();
//        Integer prixvv=Integer.parseInt(prix);
//        Vol v1=vv.get(vo.getVol_id());
//        Transport t1=sv.gett(vo.getTransport_id());
//        Voyageorganise v = new Voyageorganise(vo.getVoyage_id(),desc, image, prixvv, 0, v1, t1);
//              
//            sv.modifier(v);
//            ShowTable();
//
//        
//        
//        
//    }
//    }    
//  
//

