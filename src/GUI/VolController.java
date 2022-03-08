///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package GUI;
//
//import entities.Réservation;
//import entities.User;
//import entities.Vol;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.stage.Stage;
//import services.FactureService;
//import services.RéservationCRUD;
//import services.ServiceVol;
//import utils.MyDB;
//
///**
// * FXML Controller class
// *
// * @author bensa
// */
//public class implements Initializable {
//     private Scene scene;
//    private Parent root;
//    private Stage stage;
// 
//    @FXML
//    private ComboBox cpr;
//    @FXML
//    private ComboBox cdp ;
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//        ServiceVol sp = new ServiceVol();
//        List d= sp.affichervol();
//        List x =sp.afficherprix();
//        ObservableList list = FXCollections.observableArrayList(d);
//         ObservableList li = FXCollections.observableArrayList(x);
//        cpr.setItems(list);
//        cdp.setItems(li);
//        
//        
//    }    
//    public void sui(ActionEvent event ) throws IOException{
//        
//        
//        String x = (String) cpr.getValue();
//         int p = (int) cdp.getValue();
//        ServiceVol sc =new ServiceVol();
//        
//      int t= sc.vol(x);
//      
//        RéservationCRUD rc =new   RéservationCRUD();
//        FactureService fc =new FactureService();
//         rc.updateIdVol(t);
//         fc.updateIdprix(p);
//       
//        root = FXMLLoader.load(getClass().getResource("Hébergement.fxml"));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//        
//        
//    }
//   
//}

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Vol;
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
import java.sql.Blob;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TablePosition;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.FactureService;
import services.RéservationCRUD;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class VolController implements Initializable {

    ServiceVol vv = new ServiceVol();
    private Scene scene;
    private Parent root;
    private Stage stage;
    @FXML
    private TableView<Vol> Voyages;
    @FXML
    private TableColumn<Vol, String> Départ;
    @FXML
    private TableColumn<Vol, String> prix;
     @FXML
    private TableColumn<Vol, String> image;
   @FXML
   private Button btn;
  
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
Voyages.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
Voyages.getSelectionModel().setCellSelectionEnabled(false);
        ShowTable();
   addListenerForTable();

    }

  

    public void ShowTable() {
        ObservableList<Vol> lt = vv.affichervol();
        Voyages.setItems(lt);

      Départ.setCellValueFactory(new PropertyValueFactory<>("Départ"));       
      prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
      image.setPrefWidth(80);
      image.setCellValueFactory(new PropertyValueFactory<>("Image"));
    }

    private void addListenerForTable() {
        RéservationCRUD rc = new RéservationCRUD();
             FactureService fc =new FactureService();
        Voyages.getSelectionModel().selectedItemProperty().addListener((obs, oldselection, newselection) -> {
            //ServiceVoyageorganise sv = new ServiceVoyageorganise();
            
            
            if (newselection != null) {
                String txt = newselection.getDépart();
                int x= vv.vol(txt);
              int tx=newselection.getPrix();
            
               rc.updateIdVol(x);   
                fc.updateIdprix(tx);  


            }
            

        });
        
     
    }

    @FXML
    void clickone(MouseEvent event) {
        
        
          TablePosition tablePosition=Voyages.getSelectionModel().getSelectedCells().get(0);
        int row=tablePosition.getRow();
        Vol item=Voyages.getItems().get(row);
        TableColumn tableColumn=tablePosition.getTableColumn();
        String data= (String) tableColumn.getCellObservableValue(item).getValue();
        System.out.println(data);
    }
    @FXML
    public void sui(ActionEvent event) throws IOException {
           TablePosition tablePosition=Voyages.getSelectionModel().getSelectedCells().get(0);
          int row=tablePosition.getRow();
           Vol item=Voyages.getItems().get(row);
        if (item== null){
        	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Test Connection");

		// alert.setHeaderText("Results:");
		alert.setContentText("Connect to the database successfully!");

		alert.showAndWait();
	
        }
        else{
            try {
                root = FXMLLoader.load(getClass().getResource("Hébergement.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(VolController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
  
    
}
    }


