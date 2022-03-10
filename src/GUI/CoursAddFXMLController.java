/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cours;
import Entities.Guide;
import Service.CoursService;
import Service.GuideService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Cell;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
public class CoursAddFXMLController implements Initializable {

    @FXML
    private ComboBox<String> type;
    @FXML
    private Button add;
    @FXML
    private Button mod;
    @FXML
    private Button del;
    @FXML
    private TableView<Cours> table;
    @FXML
    private TableColumn<Cours, String> typ;
    @FXML
    private TableColumn<Cours, String> content;
    @FXML
    private TableColumn<Guide, String> g_title;
    @FXML
    private TextField search;
    @FXML
    private TextField contenu;
    private TextField idg;

    GuideService gs = new GuideService();
    CoursService cs = new CoursService();
    FilteredList<Cours> filter = new FilteredList<>(getTableList(), e -> true);
    SortedList<Cours> sort = new SortedList<>(filter);
    int index = -1;
    @FXML
    private ComboBox<String> title;
    @FXML
    private TextField titlecours;
    @FXML
    private TableColumn<?, ?> titlecourss;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        title.getItems().addAll(getListTitle());
        ObservableList<String> list = FXCollections.observableArrayList("Vidéo", "Texte");
        type.setItems(list);
        ShowTable();
    }

    private ObservableList<Cours> getTableList() {

        ObservableList<Cours> List = cs.Read();
        return List;

    }

    public static ObservableList<String> getListTitle() {
        CoursService cs = new CoursService();
        ObservableList<String> listtitle = cs.GetAllTitleGuide();
        return listtitle;

    }

    public void ShowTable() {

        ObservableList<Cours> list = getTableList();
     //       GuideService gss = new GuideService();
     //   System.out.println(list);
    // Cours c = new Cours(); 
//    String g = gs.get(c.getID_g()).getTitre();
//      System.out.println(g);
       
        table.setItems(list);
      
        typ.setCellValueFactory(new PropertyValueFactory<>("Type"));
        titlecourss.setCellValueFactory(new PropertyValueFactory<>("titre"));
        content.setCellValueFactory(new PropertyValueFactory<>("Contenu"));
        g_title.setCellValueFactory(new PropertyValueFactory("ID_g"));

    }

    @FXML
    private void insert(ActionEvent event) throws SQLException {
        if (controleSaisieString(contenu) || controleSaisieString(titlecours)); else {
        if (validateInputs()){
        Cours c = new Cours();
        c.setType(type.getValue());
        c.setTitre(titlecours.getText());
        c.setContenu(contenu.getText());
        String x = title.getValue();
        int z = (gs.getBytitre(x)).get(0).getID_g();

        c.setGuide(gs.get(z));
        if(cs.nbrv(c)==0){
              cs.Create(c);
                 System.out.println("Cours ajouté");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Cours");
            tr.setMessage("Créé avec succès");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
        }
        else {
              System.out.println("Offre deja existe");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Cours");
            tr.setMessage("Déja existe");
            tr.setNotificationType(NotificationType.ERROR);
            tr.showAndDismiss(Duration.millis(5000));
        }

 
        ShowTable();
    }
    }
    }

    @FXML
    private void update(ActionEvent event) {
        if (validateInputs()){
        Cours c = table.getSelectionModel().getSelectedItem();
        String typp = type.getValue();
        String tit = titlecours.getText();
        String contnu = contenu.getText();
        String idggg = title.getValue();
        int z = 0;
        try {
            z = (gs.getBytitre(idggg)).get(0).getID_g();
        } catch (SQLException ex) {
            Logger.getLogger(CoursAddFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Guide g1 = gs.get(z);
        Cours cu = new Cours(c.getID_crs(), typp, tit, contnu, g1);

        cs.Update(cu);
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
        Cours c = table.getSelectionModel().getSelectedItem();
        cs.Delete(c.getID_crs());
        ShowTable();
    }
    }

    @FXML
    private void getSelected(MouseEvent event) {
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        Cours c = table.getSelectionModel().getSelectedItem();
        String g = gs.get(c.getID_g()).getTitre();
        System.out.println(g);
        type.setValue(String.valueOf(typ.getCellData(index)));
        titlecours.setText(String.valueOf(titlecourss.getCellData(index)));
        contenu.setText(String.valueOf(content.getCellData(index)));
        title.setValue(String.valueOf(g));
      //  idg.setText(String.valueOf());

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
     private boolean validateInputs() {
        if (contenu.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez saisir un contenu");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        } else if ((title.getValue().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez selectionner un titre du guide");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if ((titlecours.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir un titre de cours");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if ((type.getValue().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez selectionner un type");
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
    private void Hebergement(ActionEvent event) {
    }

    @FXML
    private void Transport(ActionEvent event) {
    }

    @FXML
    private void STAT(ActionEvent event) {
    }


}
