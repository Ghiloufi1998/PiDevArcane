/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Paiement;
import entities.Réservation;
import entities.Voyageorganise;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import services.RéservationCRUD;

/**
 * FXML Controller class
 *
 * @author bensa
 */
public class ReservationfxmlController implements Initializable {
RéservationCRUD rc= new RéservationCRUD();
    @FXML
    private TableView<Réservation> tR;

    @FXML
    private TableColumn<Réservation, Date> DD;

    @FXML
    private TableColumn<Réservation, Date> Df;

    @FXML
    private TableColumn<Réservation, String> t;

    @FXML
    private TableColumn<Réservation, Integer> na;

    @FXML
    private TableColumn<Réservation, Integer> ne;
    
    @FXML
    private TableColumn<Réservation, Integer> edicol;
 @FXML
    private TableColumn<Réservation,Integer > hb;

    @FXML
    private TableColumn<Réservation, Integer> vol;
    @FXML
    private TextField input1;
 @FXML
    private ComboBox<?> input5;

    @FXML
    private ComboBox<?> input4;

    @FXML
    private ComboBox<?> input6;

    @FXML
    private DatePicker input2;

    @FXML
    private DatePicker input3;
 @FXML
    private Button supp;

    @FXML
    private Button mod;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ShowTable();
           String type[] =
                   {  "Individuelle", "Voyage Organisé" };
 
        // Create a combo box
     ObservableList    x =FXCollections.observableArrayList(type);
      input4.setItems(x);
       ArrayList values =new ArrayList(Arrays.asList("1","2","3","4","5","6","7","8","9")) ;

           
        ObservableList    nb =FXCollections.observableArrayList(values);
        input5.setItems(nb);
       input6.setItems(nb);
        
    }     
    private ObservableList<Réservation> getTableList() {
       
        ObservableList<Réservation> List = rc.Rall();
        return List ;
        
}
     public void ShowTable() {
        ObservableList<Réservation> list = getTableList();
        tR.setItems(list);
        DD.setCellValueFactory(new PropertyValueFactory<>("Date_deb"));
        Df.setCellValueFactory(new PropertyValueFactory<>("Date_fin"));
        t.setCellValueFactory(new PropertyValueFactory<>("Type"));
       na.setCellValueFactory(new PropertyValueFactory<>("Nbr_adultes"));
       ne.setCellValueFactory(new PropertyValueFactory<>("Nbr_enfants"));
    }
      @FXML
    void del(ActionEvent event) {
        
Réservation vo = tR.getSelectionModel().getSelectedItem();

       Date d = Date.valueOf(input2.getValue());
       Date f = Date.valueOf(input3.getValue());
        String x = (String) input4.getValue();
       String ty=(String) input5.getValue();
       int fd=Integer.parseInt(ty);
       String z=(String) input6.getValue();
       int  sq=Integer.parseInt(z);
   
       Réservation v = new Réservation(vo.getID_rev(),d,f, x,fd,sq);
              
            rc.update(v);
            ShowTable();

    }

    @FXML
    void supe(ActionEvent event) {
  
     Réservation   vo = tR.getSelectionModel().getSelectedItem();
        rc.delete(vo.getID_rev());
        ShowTable();

    }
}