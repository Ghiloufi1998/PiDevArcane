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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ghiloufi
 */
public class StudytextFXMLController implements Initializable {

    private WebView video;
    @FXML
    private Text tit;
    @FXML
    private Button goex;
    @FXML
    private Text cou;
 int x;
    CoursService cs = new CoursService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void getInfoByID(int idc){
      tit.setText(String.valueOf(idc)); 
      x = Integer.parseInt(tit.getText());
  
       
        setCoursContent();
     
    }
 private void setCoursContent() {
      
        Cours c = cs.get(x);
       cou.setText(c.getContenu());
        
      

    }
   @FXML
    private void Addg(ActionEvent event) {
        Cours c = cs.get(x);
        if(c.getType().equals("QCM")){
          try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/ExercicesTakeFXML.fxml"));
            Parent root = loader.load();
           
            ExercicesTakeFXMLController exerController = loader.getController();
            exerController.getInfoByID(c.getID_crs());
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
    }else {
             try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/ExerciceTakeNormalFXML.fxml"));
            Parent root = loader.load();
           
            ExerciceTakeNormalFXMLController exernorController = loader.getController();
            exernorController.getInfoByID(c.getID_crs());
            
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
