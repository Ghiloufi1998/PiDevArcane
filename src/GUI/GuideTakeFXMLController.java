/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Guide;
import Service.GuideService;
import Service.VolService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ghiloufi
 */
public class GuideTakeFXMLController implements Initializable {

  GuideService gs = new GuideService();
    VolService vs = new VolService();
    FilteredList<Guide> filter = new FilteredList<>(getTableList(), e -> true);
    SortedList<Guide> sort = new SortedList<>(filter);
    int index =-1;
    @FXML
    private TableView<Guide> table;
    @FXML
    private TableColumn<Guide, String> tit;
    @FXML
    private TableColumn<Guide, String> coun;
    @FXML
    private TableColumn<Guide, String> lv;
    @FXML
    private TableColumn<Guide, String> idvo;
    @FXML
    private TextField chercher;
    @FXML
    private Text titvolnum;
    @FXML
    private Text titdestcoun;
//    private ComboBox<?> combo;
//    @FXML
//    private ComboBox<?> combolev;
//    @FXML
//    private ComboBox<?> combocoun;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ShowTable();
     //   setInfo();
        
        // TODO
    }    

       private ObservableList<Guide> getTableList() {
      

        ObservableList<Guide> List = null;
      try {
          List = gs.getByPays("Tunisia");
      } catch (SQLException ex) {
          Logger.getLogger(GuideTakeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
      }
        return List;

    }
      public void ShowTable() {
        ObservableList<Guide> list = getTableList();
       
        table.setItems(list);
      //  idvo.setCellValueFactory(new PropertyValueFactory<>("id_g"));
        tit.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        coun.setCellValueFactory(new PropertyValueFactory<>("Pays"));
        lv.setCellValueFactory(new PropertyValueFactory<>("Level"));
        idvo.setCellValueFactory(new PropertyValueFactory<>("id_vol"));

    }

        



     @FXML
    private void Chercher(KeyEvent event) {
        chercher.setOnKeyReleased(e -> {
            chercher.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(t -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (t.getTitre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });

            });
            sort.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sort);

        });
    }
       public static ObservableList<Integer> getVolslist(){
        VolService vs = new VolService();
        
         ObservableList<Integer> listid = vs.GetAllnumVols();
                 return listid;
        
    }
//
//    private void setInfo() {
//        titdestcoun.setText("Pays de Déstination  : " + vs.get(idv).getDestination());
//        titvolnum.setText("Guides de Vol Numéro : "+ vs.get(idv).getVol_id());
//    }
       @FXML
    private void Addg(ActionEvent event) {
        Guide g = table.getSelectionModel().getSelectedItem();
          try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/CoursTakeFXML.fxml"));
            Parent root = loader.load();
           
            CoursTakeFXMLController coursController = loader.getController();
            coursController.getInfoByID(g.getID_g());
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
    }
       
    
}
