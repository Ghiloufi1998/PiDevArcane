/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Exercices;
import Service.ExerciceService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ghiloufi
 */
public class ExerciceTakeNormalFXMLController implements Initializable {

    @FXML
    private Text title;
    int x ;
    ExerciceService es = new ExerciceService();
    @FXML
    private AnchorPane ex1;
    @FXML
    private Text qs1;
    @FXML
    private TextField ans1;
    @FXML
    private AnchorPane ex2;
    @FXML
    private Text qs2;
    @FXML
    private TextField ans2;
    @FXML
    private Button btn;
    int score=0;
    @FXML
    private Button btn2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     btn2.setDisable(true);
    }    
     public void getInfoByID(int idc){
      title.setText(String.valueOf(idc));
      x = Integer.parseInt(title.getText());
         System.out.println(idc);
      
    setCoursContent();
     
    }
      private void setCoursContent() {
         // System.out.println(x);
       ObservableList <Exercices> e = es.GetQuestionByidcrs(x);
     
          System.out.println(e);
        qs1.setText(e.get(0).getQuestion());
         // System.out.println(e.get(0).getQuestion());
        qs2.setText(e.get(1).getQuestion());
          
       // CalculRslt(e);
      }

    @FXML
    private void CalculRslt(ActionEvent event) {  ObservableList <Exercices> e = es.GetQuestionByidcrs(x);
       if (ans1.getText().equals(e.get(0).getReponse()) && (ans2.getText().equals(e.get(1).getReponse()))){
              score = 200;   
              btn2.setDisable(false);
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.setTitle("Félicitations ");
                    alert2.setContentText("Veuillez vérifier vos Réponses , Votre Score est "+String.valueOf(score));
                    alert2.setHeaderText(null);
                    alert2.show();
          
          }
          else if ((ans1.getText().equals(e.get(0).getReponse()) || (ans2.getText().equals(e.get(1).getReponse())))){
              score = 100; 
              btn2.setDisable(false);
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.setTitle("Félicitations ");
                    alert2.setContentText("Votre Score est "+String.valueOf(score));
                    alert2.setHeaderText(null);
                    alert2.show();
          
      }
       
          else 
          {
              score =0 ; 
              
              Alert alert2 = new Alert(Alert.AlertType.WARNING);
                    alert2.setTitle("Malheuresement ");
                    alert2.setContentText("Veuillez vérifier vos Réponses , Votre Score est "+String.valueOf(score));
                    alert2.setHeaderText(null);
                    alert2.show();
                    
          }
        System.out.println("hhhhhhhhhhhhhhhhhhh");
      }
}
