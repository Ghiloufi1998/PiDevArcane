/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cours;
import Entities.Guide;
import Service.CoursService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ghiloufi
 */
public class StudyFXMLController implements Initializable {

    @FXML
    private WebView video;
    @FXML
    private TextArea text;
    @FXML
    private Text tit;
    int x;
    CoursService cs = new CoursService();
    @FXML
    private Button goex;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
      public void getInfoByID(int idc){
      tit.setText(String.valueOf(idc)); 
      x = Integer.parseInt(tit.getText());
  
       
        setCoursContent();
     
    }

    private void setCoursContent() {
      
        Cours c = cs.get(x);
        video.getEngine().load(c.getContenu());
       // System.out.println(x);

    }
@FXML
    private void Addg(ActionEvent event) {
        Cours c = cs.get(x);
          try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/ExercicesTakeFXML.fxml"));
            Parent root = loader.load();
           
            ExercicesTakeFXMLController exerController = loader.getController();
              System.out.println(c.getID_crs());
            exerController.getInfoByID(c.getID_crs());
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
    }
    
}
