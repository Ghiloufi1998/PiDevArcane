/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Questions;
import Entities.Sondage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import Service.ServiceQuestions;
import Service.ServiceSondage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author user
 */
public class QuestionsController implements Initializable {

    @FXML
    private ChoiceBox<String> comb;
    @FXML
    private TableView<Questions> tablequestion;
    @FXML
    private TableColumn<Questions, String> colquestion;
    @FXML
    private TableColumn<Questions, String> coltype;
    @FXML
    private TextField txtquestion;
    
    ServiceQuestions sq = new ServiceQuestions();
    ServiceSondage ss = new ServiceSondage();
    @FXML
    private Button btnAjout;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnSupp;
    @FXML
    private TextField sjt;
    @FXML
    private Button btnafficher;
    private Button stat;
    
    ValidationSupport validationSupport= new ValidationSupport();
    @FXML
    private BorderPane bp;
    @FXML
    private Button voyage_btn;
    @FXML
    private Button heb_btn;
    @FXML
    private Button transport_btn;
    @FXML
    private Button prom_btn;
    @FXML
    private Button reserv_btn;
    @FXML
    private Button sondage_btn;
    @FXML
    private Button prod_btn;
    @FXML
    private Button cmd_btn;
    @FXML
    private Button forum_btn;
    @FXML
    private Button reclam_btn;
    @FXML
    private Button reclam_btn1;
    @FXML
    private AnchorPane Transports;
    @FXML
    private ImageView user_image;
    @FXML
    private Button profile_btn;
    @FXML
    private Button mdp_btn;
    @FXML
    private Button logout;
    @FXML
    private Button btnpréc;

    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ObservableList<String> list = FXCollections.observableArrayList("YES/NO","Rate", "Text");
         comb.setItems(list);
         addListenerForTable();
          validationSupport.registerValidator(txtquestion, Validator.createEmptyValidator("Text obligatoire"));
          validationSupport.registerValidator(comb, Validator.createEmptyValidator("Selection obligatoire"));
         
      
        
         
    }    

    @FXML
    private void select(MouseEvent event) {
       
        
    }
    
     private ObservableList<Questions> getTableList() {
       
        ObservableList<Questions> List = sq.afficher();
        ObservableList<Questions> newList = 
        List.stream()
            .filter((Questions q)-> (q.getSondage().getSujet().equals(sjt.getText())))
            .collect(Collector.of(
                FXCollections::observableArrayList,
                ObservableList::add,
                (l1, l2) -> { l1.addAll(l2); return l1; })
            );
        //ObservableList<Questions> l=List.stream().filter((Questions q)-> (q.getSondage().getSujet().equals(sjt.getText()))).collect(Collectors.toCollection(FXCollections::observableArrayList));
        return newList;       
        
    }
    
     public void ShowTable() {
        ObservableList<Questions> list = getTableList();
        
        tablequestion.setItems(list);
       // colidq.setCellValueFactory(new PropertyValueFactory<>("Question_id"));
        colquestion.setCellValueFactory(new PropertyValueFactory<>("question"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
       // colidsondg.setCellValueFactory(new PropertyValueFactory<>("sondage_id"));
        
        
        
       
    }
     
      @FXML
    private void AfficherQ(ActionEvent event) {
        ShowTable();
    }
  
    
    private void Insert() {
        Questions question = tablequestion.getSelectionModel().getSelectedItem();
        String quest =txtquestion.getText();
        String type = comb.getValue();
        String sujet =sjt.getText();
        Sondage s =ss.get(sujet);
        Questions q = new Questions(quest, s, type);
      // if (!quest.equals("") && type.equals("") ){
      if(sq.nbrq(q)==0){
            sq.ajout(q);
              System.out.println("Questions ajoutééé");
            TrayNotification tr = new TrayNotification();
            AnimationType typee = AnimationType.POPUP;
            tr.setAnimationType(typee);
            tr.setTitle("Gérer Questions");
            tr.setMessage("Ajoutée avec succés");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            ShowTable();
           txtquestion.setText("");
            comb.setValue("");
      }else{
          
            TrayNotification tr = new TrayNotification();
            AnimationType typee = AnimationType.POPUP;
            tr.setAnimationType(typee);
            tr.setTitle("Gérer questions");
            tr.setMessage("ERROR: Sondage déja existe  ");
            tr.setNotificationType(NotificationType.ERROR);
            tr.showAndDismiss(Duration.millis(5000));
      }
            
       //}
    } 
   
    
    private void addListenerForTable(){
        tablequestion.getSelectionModel().selectedItemProperty().addListener((obs,oldselection,newselection)->{
        if (newselection != null){
            btnModif.setDisable(false);
            btnSupp.setDisable(false);
            txtquestion.setText(newselection.getQuestion());
            comb.setValue(newselection.getType());
        }else{
               txtquestion.setText("");
               comb.setValue("");
               btnModif.setDisable(true);
               btnSupp.setDisable(true);
        }
        }); 
    
    }

 

    @FXML
    private void AjouterQ(ActionEvent event) {
        
        if (!testQuest() || comb.getValue()==null){
             TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Question");
            tr.setMessage("Ajoutée failed ");
            tr.setNotificationType(NotificationType.ERROR);
            tr.showAndDismiss(Duration.millis(5000));
        }else{
            if( (sq.nbryesno()<3) && (sq.nbrtext()<1) &&(sq.nbrtext()<1) ){
            Insert();
             TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Question");
            tr.setMessage("Ajoutée en succés ");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));}
            else {
                 TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Nombre Questions dépassé ");
            tr.setMessage("Espace insuffisant   ");
            tr.setNotificationType(NotificationType.ERROR);
            tr.showAndDismiss(Duration.millis(5000));
            
            
            
            
            }
            
        }
        
    }

  
    @FXML
    private void SupprimerQ(ActionEvent event) {
        Questions question = tablequestion.getSelectionModel().getSelectedItem();
        int id = question.getQuestion_id();
        
         // BOX CONFIRMATION 
//        Stage stage = (Stage)MyAnchorPane.getScene().getWindow();
        Stage stage = new Stage();
        
        Alert.AlertType type =Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");
        alert.initModality(Modality.APPLICATION_MODAL);
       // alert.initOwner(stage);
        alert.getDialogPane().setContentText("Voulez-vous supprimer le question?");
        alert.getDialogPane().setHeaderText("Confirmation");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get()== ButtonType.OK){
              sq.supprimer(id);

            ShowTable();
            
        //NOTIFICATION
        
         System.out.println("Sondage Modifiée");
            TrayNotification tr = new TrayNotification();
            AnimationType typeE = AnimationType.POPUP;
            tr.setAnimationType(typeE);
            tr.setTitle("Sondage");
            tr.setMessage("Modifiée avec succés");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
           
        }else if (result.get()== ButtonType.CANCEL){
            System.out.println("jj");
        }
       
              
    }
    
    public void showInformation(String sujet){
        sjt.setText(sujet);
    }

//    @FXML
//    private void TerminerSdg(ActionEvent event) {
//        
//           try {
//             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/Reponses.fxml"));
//            Parent root = loader.load();
//           
//            ReponsesController reponsesController = loader.getController();
//          
//            reponsesController.showInformation(colquestion.getCellData(0),colquestion.getCellData(1),colquestion.getCellData(2));
//            
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    @FXML
    private void Modif(ActionEvent event) {
        
        Questions question = tablequestion.getSelectionModel().getSelectedItem();
        String sujet =sjt.getText();
        Sondage s =ss.get(sujet);
        String quest = txtquestion.getText();
        String type = comb.getValue();
        Questions q = new Questions (question.getQuestion_id(),s,quest,type);
        
         // BOX CONFIRMATION 
//        Stage stage = (Stage)MyAnchorPane.getScene().getWindow();
        Stage stage = new Stage();
        
        Alert.AlertType typee =Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(typee,"");
        alert.initModality(Modality.APPLICATION_MODAL);
       // alert.initOwner(stage);
        alert.getDialogPane().setContentText("Voulez-vous modifier le question?");
        alert.getDialogPane().setHeaderText("Confirmation");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get()== ButtonType.OK){
            sq.modifier(q);
            ShowTable();
            
        //NOTIFICATION
        
         System.out.println("Sondage Modifiée");
            TrayNotification tr = new TrayNotification();
            AnimationType typeE = AnimationType.POPUP;
            tr.setAnimationType(typeE);
            tr.setTitle("Sondage");
            tr.setMessage("Modifiée avec succés");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
           
        }else if (result.get()== ButtonType.CANCEL){
            System.out.println("jj");
        }
       
       
          
    }

    private void showStat(ActionEvent event) {
        
         
           try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/Resultat.fxml"));
             Parent root = loader.load();
           
           
            Scene scene = new Scene(root);
            Stage stage =(Stage)stat.getScene().getWindow();
            
            stage.setScene(scene);
            stage.show();
             
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @FXML
    private boolean testQuest() {
      //  String regexquest = "^[a-zA-Z0-9_+&*-]+(?:\\?";
          Pattern pat = Pattern.compile("([^.?!]*)\\?");
        if (txtquestion.getText() == null) {
            return false;
        }

        if (pat.matcher(txtquestion.getText()).matches() == false) {
         //   emailCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            //emailCheckMark.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    

        
    }

    @FXML
    private void Hebergement(ActionEvent event) {
    }

    @FXML
    private void Transport(ActionEvent event) {
    }

    @FXML
    private void STAT(ActionEvent event) {
    }

    @FXML
    private void goBack(ActionEvent event) {
           try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/Sondage.fxml"));
            Parent root = loader.load();
          
            Scene scene = new Scene(root);
            Stage stage =(Stage)btnpréc.getScene().getWindow();
            stage.setScene(scene);
            //stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
    }
          

   
    }
    

