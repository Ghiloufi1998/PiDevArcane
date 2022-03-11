/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import Entities.Offres;
import Entities.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import Service.UserService;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import static utils.PatternEmail.validate;



/**
 * FXML Controller class
 *
 * @author Souha saffar
 */
public class AjouterUserController implements Initializable {

  
    
  private javafx.scene.image.Image image;
    @FXML
    private PasswordField mdp;
    @FXML
    private DatePicker ddn;
    @FXML
    private TextField address;
    @FXML
    private TextField email;
    @FXML
    private ComboBox gend;
    @FXML
    private ComboBox rol;
    
    @FXML
    private TextField imag;
    @FXML
    private TextField no;
    @FXML
    private TextField preno;
    @FXML
    private TableView<User> tab;
 
    @FXML
    private TableColumn<User, String> n;
    @FXML
    private TableColumn<User, String> p;
    @FXML
    private TableColumn<User, Date> d;
    @FXML
    private TableColumn<User, String> a;
    @FXML
    private TableColumn<User, String> e;
    @FXML
    private TableColumn<User, String> m;
    @FXML
    private TableColumn<User, String> r;
    @FXML
    private TableColumn<User,String > i;
    @FXML
    private TableColumn<User, Integer> nm;
    @FXML
    private TableColumn<User, Integer> io;
    UserService u =new UserService();
     int index =-1;
    /**
     * Initializes the controller class.
     */
    FilteredList<User> filter = new FilteredList<>(getTableList(), e -> true);
    SortedList<User> sort = new SortedList<>(filter);
    @FXML
    private TextField chercher;
    @FXML
    private ImageView img;
    private Button pacourir;
    @FXML
    private Button parcourir;
    @FXML
    private TableColumn<?, ?> gnd;
    @FXML
    private Label gendre;
    @FXML
    private Button ajout;
    @FXML
    private Button modi;
    @FXML
    private Button supp;
    @FXML
    private BorderPane bp;
    @FXML
    private Button pay_btn;
    @FXML
    private Button heb_btn;
    @FXML
    private Button transport_btn;
    @FXML
    private Button stat_btn;
    @FXML
    private Button reserv_btn;
    @FXML
    private Button sondage_btn;
    @FXML
    private Button guide_btn;
    @FXML
    private Button cours_btn;
    @FXML
    private Button exer_btn;
    @FXML
    private Button reclam_btn;
    @FXML
    private Button vol_btn;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<String> list = FXCollections.observableArrayList("Femme", "Homme");
         gend.setItems(list);
         ObservableList<String> lis = FXCollections.observableArrayList("admin", "client");
         rol.setItems(lis);
//         addListenerForTable();
     ShowTable();
    }
    
    
    private ObservableList<User> getTableList() {
       UserService ts = new UserService();
        ObservableList<User> List = FXCollections.observableArrayList(ts.afficherUser());
        return List ;
        
    }
      
    public void ShowTable() {
        ObservableList<User> list = getTableList();
        tab.setItems(list);
        n.setCellValueFactory(new PropertyValueFactory<>("name"));
        p.setCellValueFactory(new PropertyValueFactory<>("fname"));
        d.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        a.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        e.setCellValueFactory(new PropertyValueFactory<>("email"));
        m.setCellValueFactory(new PropertyValueFactory<>("password"));
        r.setCellValueFactory(new PropertyValueFactory<>("role"));
        i.setCellValueFactory(new PropertyValueFactory<>("image"));
        nm.setCellValueFactory(new PropertyValueFactory<>("Nb_point"));
        io.setCellValueFactory(new PropertyValueFactory<>("id_offre"));
 gnd.setCellValueFactory(new PropertyValueFactory<>("gender"));
       
        
     


  }

    @FXML
    private void addPerson(ActionEvent event) {
        try {
            
            if (validateInputs()); 
            else{
            String nom=no.getText();
            String prénom=preno.getText();
            Date datenaissance=java.sql.Date.valueOf(ddn.getValue());
            String motpasse=mdp.getText();
            String addresse =address.getText();
            String e_mail=email.getText();
            String imagee=imag.getText();
            String g=(String) gend.getValue();
            String rr=(String) rol.getValue();
            
             
          User u1 = new User(nom, prénom, g, datenaissance, addresse, e_mail, motpasse, imagee, rr, 0, 1 );
                    UserService us = new UserService();
                    us.InsertUser(u1);
            }
                    FXMLLoader loader = new FXMLLoader (getClass().getResource("ajouterUser.fxml")); 
                    Parent root = loader.load();
                    AjouterUserController auc =loader.getController();
                  //  auc.setAffiche(us.afficherUser().toString());
                    no.getScene().setRoot(root);
                    TrayNotification tr = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;
                    tr.setAnimationType(type);
                    tr.setTitle("add");
                    tr.setMessage("Created succefully");
                    tr.setNotificationType(NotificationType.SUCCESS);
                    tr.showAndDismiss(Duration.millis(5000));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void seelect(ActionEvent event) {
        String s= gend.getSelectionModel().getSelectedItem().toString();
        
        
    }

    @FXML
    private void selecti(ActionEvent event) {
         String t= rol.getSelectionModel().getSelectedItem().toString();
    }


    @FXML
   private void modifi(ActionEvent event) {
       int x = tab.getSelectionModel().getSelectedItem().getId();
       Offres o =tab.getSelectionModel().getSelectedItem().getOffre();
       int o1= tab.getSelectionModel().getSelectedItem().getId_offre();
       Date da= (Date.valueOf(ddn.getValue()));
       UserService ss=new UserService();
       User u = new User(x, no.getText(), preno.getText(), (gend.getValue()).toString(),da, address.getText(), email.getText(),mdp.getText(), imag.getText(), (rol.getValue()).toString(), 0, o1, o);
       //User u1 = new User(x,no.getText() , fname, gender, da, adresse, email, password, image, role, x, index, o)
            ss.UpdateUser(u);
            ShowTable();
   }

   
    @FXML
    private void Chercher(ActionEvent event) {
        chercher.setOnKeyReleased(e -> {
            chercher.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(h -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (h.getRole().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });

            });
            sort.comparatorProperty().bind(tab.comparatorProperty());
            tab.setItems(sort);
        });
    }
    @FXML
    private void Pparcourir_User(ActionEvent event) {
        Stage primaryStage = new Stage();
        primaryStage.onShowingProperty();
        primaryStage.setTitle("selectionner une image !!!");
        FileChooser filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files ", "*.png", "*.jpg", "*.gif"),
        new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        parcourir.setOnAction(e -> {
            File file = filechooser.showOpenDialog(primaryStage);
            if (file != null) {
                //String s = file.getAbsolutePath();
                String F = file.toURI().toString();
                imag.setText(F);
                  image = new javafx.scene.image.Image(file.toURI().toString(), 150, 100, true, true);
               Image imagee;
                    img.setImage(image);

            } else {
                JOptionPane.showMessageDialog(null, "Impossible d'ajouter");
            }
        });
    }
public boolean controleTextFieldNonNumerique(TextField textField) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez saisir des lettres");
            alert.showAndWait();
            return true;
        }
        return false;
    }


    @FXML
    private void getSelected(MouseEvent event) {
        index = tab.getSelectionModel().getSelectedIndex();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (index <= -1) {
            return;
        }
        no.setText(String.valueOf(n.getCellData(index)));
        address.setText(String.valueOf(a.getCellData(index)));
        preno.setText(String.valueOf(p.getCellData(index)));
        gend.setValue(String.valueOf(gnd.getCellData(index)));
        email.setText(String.valueOf(e.getCellData(index)));
        ddn.setValue(LocalDate.parse(d.getCellData(index).toString(), formatter));
        rol.setValue(String.valueOf(r.getCellData(index)));
        mdp.setText(String.valueOf(m.getCellData(index)));
        imag.setText(String.valueOf(i.getCellData(index)));
    }
    @FXML
    private void Supprimer_User(ActionEvent event) throws SQLException {
        UserService ts = new UserService();
        ts.deleteUser(tab.getSelectionModel().getSelectedItem().getId());
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("ajouterUser.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
           ShowTable();
            TrayNotification tr=new TrayNotification();
            AnimationType type=AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("user");
            tr.setMessage("Deleted succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            ShowTable();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       ShowTable();
    }
    private boolean validateInputs() {
        if (no.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veuillez saisir votre Prenom");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        } else if ((preno.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre Nom");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if ((email.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
         
        } else if ((mdp.getText().isEmpty())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez saisir votre mot de passe");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
       
        } else if (mdp.getText().length() < 8) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Mot de passe doit dépasser les 6 caractères");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if (!(validate(email.getText()))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez vérifier votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if (ddn.getValue().isAfter(LocalDate.now())) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veuillez vérifier votre date de naissance");
            alert2.setHeaderText(null);
            alert2.show();
            return false; } 
        return true;
} 

    @FXML
    private void aff(MouseEvent event) {
        
    }

    @FXML
    private void pay(ActionEvent event) {
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
    private void res(ActionEvent event) {
    }

    @FXML
    private void sondage(ActionEvent event) {
    }

    @FXML
    private void guide(ActionEvent event) {
    }

    @FXML
    private void cours(ActionEvent event) {
    }

    @FXML
    private void exercice(ActionEvent event) {
    }

    @FXML
    private void rec(ActionEvent event) {
    }

    @FXML
    private void vol(ActionEvent event) {
    }


}



 
 

