/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utils.GuiNavigate;

/**
 * FXML Controller class
 *
 * @author Ghiloufi
 */
public class SelectFrontBackFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void front(ActionEvent event) throws IOException {
          GuiNavigate nav = new GuiNavigate();
        nav.navigate(event, "PIDEV", "/GUI/GuideTakeFXML.fxml");
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
    
      GuiNavigate nav = new GuiNavigate();
        nav.navigate(event, "PIDEV", "/GUI/GuideAddFXML.fxml");
}

   
  
}
