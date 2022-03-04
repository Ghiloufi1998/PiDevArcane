/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cours;
import Entities.Exercices;
import Service.CoursService;
import Service.ExerciceService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.GuiNavigate;

/**
 * FXML Controller class
 *
 * @author Ghiloufi
 */
public class ExerciceFxmlController implements Initializable {

    @FXML
    private Button add;
    @FXML
    private Button mod;
    @FXML
    private Button del;
    @FXML
    private TableView<Exercices> table;
    @FXML
    private TextField search;
    @FXML
    private ComboBox<String> comb;
    ExerciceService es = new ExerciceService(); 
    CoursService cs = new CoursService();
    int index = -1;
    @FXML
    private TableColumn<Exercices, String> typ;
    @FXML
    private TableColumn<Exercices, String> qs;
    @FXML
    private TableColumn<Exercices, String> ans;
    @FXML
    private TableColumn<Exercices, Integer> idcrs;
    @FXML
    private TextField question;
    @FXML
    private TextField reponse;
    private TextField id_crs;
        FilteredList<Exercices> filter = new FilteredList<>(getTableList(), e -> true);
    SortedList<Exercices> sort = new SortedList<>(filter);
    @FXML
    private ComboBox<String> titleee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            titleee.getItems().addAll(getListTitle());
        ObservableList<String> list = FXCollections.observableArrayList("QCM", "Simple");
         comb.setItems(list);
    ShowTable();
    }    
    private ObservableList<Exercices> getTableList() {
       ObservableList<Exercices> List = es.Read();
        return List ;
    }
       public static ObservableList<String> getListTitle() {
        ExerciceService cs = new ExerciceService();
        ObservableList<String> listtitle = cs.GetAllTitleCours();
        return listtitle;

    }
       
        public void ShowTable() {
        ObservableList<Exercices> list = getTableList();
        table.setItems(list);
        typ.setCellValueFactory(new PropertyValueFactory<>("Type"));
        qs.setCellValueFactory(new PropertyValueFactory<>("Question"));
        ans.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        idcrs.setCellValueFactory(new PropertyValueFactory<>("ID_crs"));
        
       
    }
    @FXML
    private void insert(ActionEvent event) throws SQLException {
        if (controleSaisieString(question) || controleSaisieString(reponse)); else {
        if (validateInputs()){
         Exercices e = new Exercices();
        e.setType(comb.getValue());
        e.setQuestion(question.getText());
        e.setReponse(reponse.getText());
          String x = titleee.getValue();
        int z = (cs.getBytitre(x)).get(0).getID_crs();
      
           e.setCours(cs.get(z));
           if(es.nbrv(e)==0){
              es.Create(e);
                 System.out.println("Exercice ajouté");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Exercice");
            tr.setMessage("Créé avec succès");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
        }
        else {
              System.out.println("Exercice deja existe");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Exercice");
            tr.setMessage("Déja existe");
            tr.setNotificationType(NotificationType.ERROR);
            tr.showAndDismiss(Duration.millis(5000));
        }

              ShowTable();
    }
        }
    }
    @FXML
        private void getSelected(MouseEvent event) {
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        comb.setValue(String.valueOf(typ.getCellData(index)));
        question.setText(String.valueOf(qs.getCellData(index)));
        reponse.setText(String.valueOf(ans.getCellData(index)));
      //  id_crs.setText(String.valueOf(idcrs.getCellData(index)));
        Exercices e = table.getSelectionModel().getSelectedItem();
        String g = cs.get(e.getID_crs()).getTitre();
        System.out.println(g);
          titleee.setValue(String.valueOf(g));
    }


     @FXML
    private void update(ActionEvent event) {
         if (validateInputs()){
          Exercices e = table.getSelectionModel().getSelectedItem();
        String typp = comb.getValue();
        String qss = question.getText();
        String ress = reponse.getText();
        String tet = titleee.getValue();
        int z = 0;
        try {
            z = (cs.getBytitre(tet)).get(0).getID_crs();
        } catch (SQLException ex) {
            Logger.getLogger(CoursAddFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Integer idcc = Integer.parseInt(idcou);
        Cours c1=cs.get(z);
        Exercices ex = new Exercices(e.getID_ex(), typp, qss, ress, c1);
              
            es.Update(ex);
            ShowTable();
    
    }
    }
       @FXML
    private void delete(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment Supprimer ");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
       Exercices e = table.getSelectionModel().getSelectedItem();
        es.Delete(e.getID_ex());
        ShowTable();
    }
    }
     @FXML
    private void Chercher(KeyEvent event) {
        search.setOnKeyReleased(e -> {
            search.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(t -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (t.getType().toLowerCase().contains(lowerCaseFilter)) {
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
       private boolean validateInputs() {
        if (question.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez saisir une question");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        } else if ((comb.getValue().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez selectionner un type");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if ((reponse.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir une réponse");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if ((titleee.getValue().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez selectionner un titre de guide");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        
        }
        return true;
    }
       public boolean controleSaisieString(TextField textField) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez saisir des lettres");
            alert.showAndWait();
            return true;
        }
        return false;
    }

   @FXML
    private void cours(ActionEvent event) throws IOException {
        
     
        GuiNavigate nav = new GuiNavigate();
        nav.navigate(event, "PIDEV", "/GUI/CoursAddFXML.fxml");
    }

    @FXML
    private void Exercice(ActionEvent event) throws IOException {

        GuiNavigate nav = new GuiNavigate();
        nav.navigate(event, "PIDEV", "/GUI/ExerciceFxml.fxml");
    }
    

    @FXML
    private void guide(ActionEvent event) throws IOException {
 GuiNavigate nav = new GuiNavigate();
        nav.navigate(event, "PIDEV", "/GUI/GuideAddFXML.fxml");
    }

    @FXML
    private void offres(ActionEvent event) throws IOException {
 GuiNavigate nav = new GuiNavigate();
        nav.navigate(event, "PIDEV", "/GUI/OffreAddFXML.fxml");
    }
}
