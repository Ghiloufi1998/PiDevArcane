/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Offres;
import Service.OffresService;
import Service.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ghiloufi
 */
public class FrontOffresFXMLController implements Initializable {

    @FXML
    private Text pts;
    @FXML
    private TableView<Offres> table;
    @FXML
    private TableColumn<Offres, String> desc;
    @FXML
    private TableColumn<Offres, String> dest;
    @FXML
    private TableColumn<Offres, String> pourc;
    @FXML
    private TableColumn<Offres, String> ptsreq;
    OffresService os = new OffresService(); 
    UserService us = new UserService() ; 
    private int CurrUserpoints;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowTable(); 
        setPts();
        
    }    
  private ObservableList<Offres> getTableList() {
       ObservableList<Offres> List = os.Read();
        return List ;
    }
        public void ShowTable() {
        ObservableList<Offres> list = getTableList();
        table.setItems(list);
        desc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        dest.setCellValueFactory(new PropertyValueFactory<>("Destination"));
        pourc.setCellValueFactory(new PropertyValueFactory<>("Pourcentage_red"));
        ptsreq.setCellValueFactory(new PropertyValueFactory<>("Nb_points_req"));
        
       
    }
        private void setPts() {
        try {
            CurrUserpoints= us.getUserlogged().getNb_point();
        } catch (SQLException ex) {
            Logger.getLogger(FrontOffresFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            pts.setText("Mes Points : "+ CurrUserpoints);
        }
}

