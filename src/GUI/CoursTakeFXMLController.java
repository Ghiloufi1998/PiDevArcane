/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cours;
import Service.CoursService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ghiloufi
 */
public class CoursTakeFXMLController implements Initializable {

    @FXML
    private Text title;
    CoursService cs = new CoursService();
     int x;
    @FXML
    private Button gotoCrs;
    private Text idhide;
    @FXML
    private TableView<Cours> table;
    @FXML
    private TableColumn<Cours, String> titre;
    @FXML
    private TableColumn<Cours, String> type;
    @FXML
    private TextField hidee;
     
   // private TextFlow ssss;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
          
    }    
     
   
    public void getInfoByID(int idg){
       hidee.setText(String.valueOf(idg)); 
       x = Integer.parseInt(hidee.getText());
       ShowTable();
       //  System.out.println(x);
     
    }
      private ObservableList<Cours> getTableList() {
          System.out.println(x);
 
        ObservableList<Cours> List = cs.GetByidG(x);
        return List;

    }
         public void ShowTable() {       
             ObservableList<Cours> list = getTableList();
             System.out.println(list);
     
        table.setItems(list);
    
       titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
      

   }
             @FXML
    private void Addg(ActionEvent event) {
        Cours c = table.getSelectionModel().getSelectedItem();
        if ( c.getType().equals("Vidéo")){
          try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/StudyFXML.fxml"));
            Parent root = loader.load();
           
            StudyFXMLController StudyController = loader.getController();
            StudyController.getInfoByID(c.getID_crs());
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
    }else  {  try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/StudytextFXML.fxml"));
            Parent root = loader.load();
           
            StudytextFXMLController StudytextController = loader.getController();
            StudytextController.getInfoByID(c.getID_crs());
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
            
            
        }
    }
        

    
}
    
   

