/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.BackTransportController.getlistASC;
import entities.Hebergement;
import entities.Hebergement;
import entities.Transport;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.sort;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import services.ServiceHebergement;
import services.ServiceHebergement;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author Dhia
 */
public class HebergementController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button voyage_btn;
    @FXML
    private Button heb_btn;
    @FXML
    private Button Hebergement_btn;
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
    private AnchorPane Hebergements;
    @FXML
    private AnchorPane ap;
    @FXML
    private GridPane grid;
    @FXML
    private TextField chercher;
    @FXML
    private TextField DescriptionField;
    @FXML
    private TextField DisponibiliteField;
    @FXML
    private TextField ImageField;
    @FXML
    private TableView<Hebergement> Table_Hebergement;
    @FXML
    private TableColumn<?, ?> descriptionHebergement;
    @FXML
    private TableColumn<?, ?> disponibiliteHebergement;
    @FXML
    private TableColumn<?, ?> priceHebergement;
    @FXML
    private TableColumn<?, ?> typeHebergement;
    @FXML
    private TableColumn<?, ?> ImageHebergement;
    @FXML
    private Button Modifier_Hebergement;
    @FXML
    private TextField PriceField;
    @FXML
    private TextField TypeField;
    private TextField AdresseFiled;
    @FXML
    private Button parcourir;
    @FXML
    private TextField ImageeField;
    @FXML
    private ImageView user_image;
    @FXML
    private Button profile_btn;
    @FXML
    private Button mdp_btn;
    @FXML
    private Button logout;
    int index =-1;
    FilteredList<Hebergement> filter = new FilteredList<>(getlistASC(), e -> true);
    SortedList<Hebergement> sort = new SortedList<>(filter);
    @FXML
    private TableColumn<?, ?> AdresseHebergement;
    @FXML
    private ImageView Img;
    private javafx.scene.image.Image image;
    @FXML
    private TextField AdresseField;
public HebergementController() throws SQLException {
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AfficherTable();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // TODOHebergement
    }

    public void AfficherTable() throws SQLException {
        ObservableList<Hebergement> list = getlistASC();

        Table_Hebergement.setItems(list);
        //idHebergement.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionHebergement.setCellValueFactory(new PropertyValueFactory<>("description"));
        disponibiliteHebergement.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        priceHebergement.setCellValueFactory(new PropertyValueFactory<>("prix"));
        typeHebergement.setCellValueFactory(new PropertyValueFactory<>("type"));
        ImageHebergement.setCellValueFactory(new PropertyValueFactory<>("image"));
        AdresseHebergement.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    

    }

    public static ObservableList<Hebergement> getlistASC() throws SQLException {
        ServiceHebergement ts = new ServiceHebergement();
        ObservableList<Hebergement> listHebergement = FXCollections.observableArrayList(ts.afficher());
        return listHebergement;
    }

    @FXML
    public void AfficherTableASC() throws SQLException {
        ObservableList<Hebergement> list = getlistASC();
        Table_Hebergement.setItems(list);
        //idHebergement.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionHebergement.setCellValueFactory(new PropertyValueFactory<>("description"));
        disponibiliteHebergement.setCellValueFactory(new PropertyValueFactory<>("disponibilité"));
        priceHebergement.setCellValueFactory(new PropertyValueFactory<>("prix"));
        typeHebergement.setCellValueFactory(new PropertyValueFactory<>("type"));
        ImageHebergement.setCellValueFactory(new PropertyValueFactory<>("image"));
          AdresseHebergement.setCellValueFactory(new PropertyValueFactory<>("adresse"));

    }

    @FXML
    private void Transport(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
      nav.navigate(event, "Transport", "/gui/BackTransport.fxml");
   }
@FXML
    private void Hebergement(ActionEvent event) {
    }
    
@FXML
    private void STAT (ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
      nav.navigate(event, "STAT", "/gui/STATTRANSPORT.fxml");
   }
   

    @FXML
    private void getSelected(MouseEvent event) {
        int index = Table_Hebergement.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        DescriptionField.setText(String.valueOf(descriptionHebergement.getCellData(index)));
        DisponibiliteField.setText(String.valueOf(disponibiliteHebergement.getCellData(index)));
        PriceField.setText(String.valueOf(priceHebergement.getCellData(index)));
        TypeField.setText(String.valueOf(typeHebergement.getCellData(index)));
        AdresseField.setText(String.valueOf(AdresseHebergement.getCellData(index)));
       ImageeField.setText(String.valueOf(ImageHebergement.getCellData(index)));
        
    }

    @FXML
    private void dut(ZoomEvent event) {
    }

    double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch (Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else {
            return 0;
        }
    }
        int ParseInt(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Integer.parseInt(strNumber);
            } catch (Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else {
            return 0;
        }
    }
     @FXML
    private void Ajouter_Hebergement(ActionEvent event) throws SQLException {
        ServiceHebergement ts = new ServiceHebergement();

       if (controleTextFieldNonNumerique(DescriptionField) || controleTextFieldNonNumerique(AdresseField) || controleTextFieldNonNumerique(TypeField)); else 
        {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment ajouter");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {       
        int id = Integer.parseInt(DisponibiliteField.getText());
             int id2 = Integer.parseInt(PriceField.getText());
 
        ServiceHebergement sh = new ServiceHebergement();
        //Hebergement k4 = new Hebergement(52,"8", "Tdhidhooo", 4, "Adresse", "Image",789);
        //sh.ajout(k4);
            Hebergement t = new Hebergement(DescriptionField.getText(), TypeField.getText(), id2, AdresseField.getText(), ImageeField.getText(), id);
            ts.ajout(t);
            System.out.println("Hebergement ajoutééé");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Hebergement");
            tr.setMessage("Created succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
        }}
        AfficherTableASC();

        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("BackHebergement.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            AfficherTableASC();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        AfficherTableASC();
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
    private void Modifier_Hebergement(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment modifier");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
        try {
             
             ServiceHebergement ts = new ServiceHebergement();
                 ServiceHebergement sh = new ServiceHebergement();
             int id = Integer.parseInt(DisponibiliteField.getText());
             int id2 = Integer.parseInt(PriceField.getText());
             int x = Table_Hebergement.getSelectionModel().getSelectedItem().getHebergement_id();
          //  Hebergement t = new Hebergement(idd ,DescriptionField.getText(), TypeField.getText(), id2, AdresseFiled.getText(), ImageeField.getText(), id);
           // Hebergement t1 = new Hebergement(36, DescriptionField.getText(), TypeField.getText(), id,  AdresseFiled.getText(), ImageeField.getText(), id2);
            Hebergement h = new Hebergement(x, DescriptionField.getText(), TypeField.getText(), id, "adress", "img");
          
          
            ts.modifier(h);
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Hebergement");
            tr.setMessage("Updated succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            AfficherTableASC();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        AfficherTableASC();
    }}
    //}

    @FXML
    private void Supprimer_Hebergement(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment supprimer");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
        ServiceHebergement ts = new ServiceHebergement();
        
        ts.supprimer(Table_Hebergement.getSelectionModel().getSelectedItem().getHebergement_id());
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("Hebergement.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            AfficherTableASC();
            TrayNotification tr=new TrayNotification();
            AnimationType type=AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Hebergement");
            tr.setMessage("Deleted succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            AfficherTableASC();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        AfficherTableASC();
    }}
    @FXML
    private void Parcourir_Hebergement(ActionEvent event) {
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
                ImageeField.setText(F);
                   image = new javafx.scene.image.Image(file.toURI().toString(), 150, 100, true, true);
                   Img.setImage(image);

            } else {
                JOptionPane.showMessageDialog(null, "Impossible d'ajouter");
            }
        });
    }
//@FXML
//private void Chercher(ActionEvent event) {
//    chercher.setOnKeyReleased(e -> {
//        chercher.textProperty().addListener((observable, oldValue, newValue) -> {
//            filter.setPredicate(t -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                String lowerCaseFilter = newValue.toLowerCase();
//                if (t.getAdresse().toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else {
//                    return false;
//                }
//            });
//
//        });
//        sort.comparatorProperty().bind(Table_Hebergement.comparatorProperty());
//        Table_Hebergement.setItems(sort);
//    });
     @FXML
private void Chercher (ActionEvent event) throws SQLException {
        FilteredList<Hebergement> filter = new FilteredList<>(getlistASC(), e -> true);
        chercher.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(h -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (h.getAdresse().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if(h.getDescription().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    } else if(h.getDescription().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    
                    /*} else if(h.getDate_ajout().toLowerCase().contains(lowerCaseFilter)){
                        return true;*/
                    } else if(String.valueOf(h.getType()).contains(lowerCaseFilter)){
                        return true;
                    } else if(String.valueOf(h.getImage()).contains(lowerCaseFilter)){
                        return true;
                    } else if(String.valueOf(h.getPrix()).contains(lowerCaseFilter)){
                        return true;
                    }else 
                        return false;
                });

            });
        Table_Hebergement.setItems(filter);
    }

    }
