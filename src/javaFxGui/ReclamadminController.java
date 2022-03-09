/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFxGui;

import entities.Reclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Souha saffar
 */
public class ReclamadminController implements Initializable {

    @FXML
    private TableView<Reclamation> tv;
    @FXML
    private TableColumn<Reclamation, Integer> dd;
    @FXML
    private TableColumn<Reclamation, String> rec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
