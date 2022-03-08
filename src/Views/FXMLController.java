/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import entities.Réservation;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.FactureService;
import services.RéservationCRUD;
import services.ServiceVol;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author bensa
 */
public class FXMLController implements Initializable {

    private Scene scene;
    private Parent root;
    private Stage stage;

    @FXML
    private AnchorPane pane;
    @FXML
    private DatePicker date_deb;
    @FXML
    private DatePicker date_fin;
    @FXML
    private ComboBox Type;
    @FXML
    private Button btn;
    @FXML
    private ComboBox des;
    @FXML
    private ComboBox nbr_adu;
    @FXML
    private Button getip;
    @FXML
    private ComboBox nbr_enf;
    RéservationCRUD r = new RéservationCRUD();
    Réservation rs = new Réservation();
    FactureService fs =new FactureService();

    /**
     * Initializes the controller class.
     */
//      
    private ValidationSupport validationSupport = new ValidationSupport();
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Callback<DatePicker, DateCell> dayCellFactory = date_deb -> new DateCell()
        {
            @Override
            public void updateItem(LocalDate item, boolean empty)
            {
                super.updateItem(item, empty);

                if(item.isBefore(LocalDate.now()) || item.isAfter(LocalDate.now().plusYears(1)))
                {
                    setStyle("color:red");
                  Platform.runLater(() -> { 
                setDisable(true); 
            });
                }
            }
        };
        // TODO
//  Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
         date_deb.setDayCellFactory(dayCellFactory);
         date_fin.setDayCellFactory(Factory());
        date_deb.setValue(LocalDate.now());
     
        ServiceVol sp = new ServiceVol();
        List destination = sp.afficherdestiantaion();
        ObservableList list = FXCollections.observableArrayList(destination);
        des.setItems(list);
          // Weekdays
        String type[] =
                   {  "Individuelle", "Voyage Organisé" };
 
        // Create a combo box
     ObservableList    x =FXCollections.observableArrayList(type);
      Type.setItems(x);
       ArrayList values =new ArrayList(Arrays.asList(1,2,3,4,5,6,7,8,9)) ;

           
        ObservableList    nb =FXCollections.observableArrayList(values);
        nbr_adu.setItems(nb);
        nbr_enf.setItems(nb);
        

    }

    @FXML
    private void insert(ActionEvent event) throws IOException {
Type.getItems();
         validationSupport.registerValidator( Type, Validator.createEmptyValidator("hbhzefze"));
     
         nbr_adu.getItems();
         validationSupport.registerValidator(nbr_adu, Validator.createEmptyValidator("hbhzefze"));
         des.getItems();
         validationSupport.registerValidator(des, Validator.createEmptyValidator("hbhzefze"));
         nbr_enf.getItems();
         validationSupport.registerValidator( nbr_enf, Validator.createEmptyValidator("hbhzefze"));
        validationSupport.registerValidator(date_deb,
            Validator.createEmptyValidator("hbhzefze"));
         validationSupport.registerValidator(date_fin,
            Validator.createEmptyValidator("hbhzefze"));
//         try {
//            
//          FXMLLoader loader = new FXMLLoader(getClass().getResource("vol.fxml"));
//            Parent root = loader.load();
//        } catch (IOException ex) {
//            Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
//        }
   // Vol Interface
   
       java.util.Date date =
               java.util.Date.from(date_deb.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
       java.sql.Date sqlDate = new java.sql.Date(date.getTime());
       java.util.Date d = java.util.Date.from(date_fin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
       java.sql.Date sqlDat = new java.sql.Date(d.getTime());
       
       
       String x = (String) des.getValue();
       String ty=(String) Type.getValue();
       int nb1=(int) nbr_adu.getValue();
       int nb2=(int) nbr_enf.getValue();
       
       
       
       Réservation p = new Réservation(sqlDate, sqlDat, ty,nb1,nb2,x);
       r.insertDes(p);
       fs.FactureInsert(p.getID_rev());
       if (!ty.equals("Voyage Organisé")){
           root = FXMLLoader.load(getClass().getResource("vol.fxml"));
           stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
       }else{
           root = FXMLLoader.load(getClass().getResource("voyageorganise.fxml"));
           stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
           
       }
   
if(  (("".equals(des.getValue()) || "".equals(Type.getValue())) || "".equals( nbr_adu.getValue()))||"".equals( nbr_enf.getValue()) ){
       Alert alert = new Alert(AlertType.INFORMATION);
       alert.setTitle("Champs vide ");
       alert.setContentText("verifier les données");
       alert.showAndWait();
   }

   }
 
  private Callback<DatePicker, DateCell> getDayCellFactory() {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                      
                        if (item.isBefore(LocalDate.now()) || item.isAfter(LocalDate.now().plusYears(1)) ) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }
  private Callback<DatePicker, DateCell> Factory() {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                      
                        if (item.isEqual(LocalDate.now()) || item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }

    @FXML
    void nexti(ActionEvent event) {
        try {
            InetAddress ip =InetAddress.getLocalHost();
            root = FXMLLoader.load(getClass().getResource("FXML1.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}  



