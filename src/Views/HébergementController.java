/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package GUI;
//
//import java.io.IOException;
//import java.net.URL;
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
//import javafx.stage.Stage;
//import services.FactureService;
//import services.RéservationCRUD;
//import services.ServiceHebergement;
//import services.ServiceVol;
//
///**
// * FXML Controller class
// *
// * @author bensa
// */
//public classimplements Initializable {
//
//   private Scene scene;
//    private Parent root;
//    private Stage stage;
// @FXML
//    private Button btn;
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
//        ServiceHebergement sp = new ServiceHebergement();
//        List d= sp();
//        ObservableList list = FXCollections.observableArrayList(d);
//        cpr.setItems(list);
//        
//        
//    }    
//    public void sui(ActionEvent event ) throws IOException{
//        
//        
//        String x = (String) cpr.getValue();
//       
//        ServiceHebergement sc =new ServiceHebergement();
//        
//      int t= sc.address(x);
//        RéservationCRUD rc =new   RéservationCRUD();
//         rc.updateIdHeb(t);
//       
//        root = FXMLLoader.load(getClass().getResource("facture.fxml"));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//        
//        
//    }
//     private void addListenerForTable() {
//        RéservationCRUD rc = new RéservationCRUD();
//             FactureService fc =new FactureService();
//        Voyages.getSelectionModel().selectedItemProperty().addListener((obs, oldselection, newselection) -> {
//            //ServiceVoyageorganise sv = new ServiceVoyageorganise();
//            
//            
//            if (newselection != null) {
//                String txt = newselection.getDépart();
//                int x= vv.vol(txt);
//              int tx=newselection.getPrix();
//               rc.updateIdVol(x);   
//                fc.updateIdprix(tx);  
//
//
//            }
//            
//
//        });
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

import entities.Hebergement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.FactureService;
import services.RéservationCRUD;
import services.ServiceHebergement;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class HébergementController implements Initializable {

    ServiceHebergement sh = new ServiceHebergement();
    private Scene scene;
    private Parent root;
    private Stage stage;
    @FXML
    private TableView<Hebergement> Voyages;
    @FXML
    private TableColumn<Hebergement, String> adresse;
    @FXML
    private TableColumn<Hebergement, Integer> prix;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ShowTable();
        try {
            addListenerForTable();
        } catch (SQLException ex) {
            Logger.getLogger(HébergementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ShowTable() {
        ObservableList<Hebergement> lt = sh.affichervol();
        Voyages.setItems(lt);

        adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));

    }
int ttc=0;
    private void addListenerForTable() throws SQLException {
        RéservationCRUD rc = new RéservationCRUD();
        ServiceHebergement sc = new ServiceHebergement();
        FactureService fc = new FactureService();
        

        Voyages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Hebergement>() {
            @Override
            public void changed(ObservableValue<? extends Hebergement> obs, Hebergement oldselection, Hebergement newselection) {
                //ServiceVoyageorganise sv = new ServiceVoyageorganise();
                
                if (newselection != null) {
                    String x = newselection.getAdresse();
                    int t = sc.address(x);
                    rc.updateIdHeb(t);
                    int tx = newselection.getPrix();
                    
                    try {
                        ttc=fc.getdprix()+tx;
                    } catch (SQLException ex) {
                        Logger.getLogger(HébergementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                            fc.updateIdprix(ttc);
                            
                }
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

    

