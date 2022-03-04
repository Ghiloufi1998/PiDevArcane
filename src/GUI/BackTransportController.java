/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


/**
 * FXML Controller class
 *
 * @author Dhia
 */
import entities.Hebergement;
import entities.Transport;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
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
import services.ServiceTransport;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.NavigationEntreInterfaces;


/**
 * FXML Controller class
 *
 * @author user
 */
public class BackTransportController implements Initializable {

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
    private AnchorPane Transports;
    @FXML
    private AnchorPane ap;
    @FXML
    private GridPane grid;
    @FXML
    private TextField ImageField;
    @FXML
    private TextField chercher;
    @FXML
    private TextField DescriptionField;
    @FXML
    private TextField DisponibiliteField;
    @FXML
    private TableView<Transport> Table_transport;
    @FXML
    private TableColumn<?, ?> descriptionTransport;
    @FXML
    private TableColumn<?, ?> disponibiliteTransport;
    @FXML
    private TableColumn<?, ?> priceTransport;
    @FXML
    private TableColumn<?, ?> typeTransport;
    @FXML
    private Button Modifier_Transport;
    @FXML
    private TextField PriceField;
    @FXML
    private TextField TypeField;
    @FXML
    private ImageView user_image;
    @FXML
    private Button profile_btn;
    @FXML
    private Button mdp_btn;
    @FXML
    private Button logout;
    int index = -1;
    FilteredList<Transport> filter = new FilteredList<>(getlistASC(), e -> true);
    SortedList<Transport> sort = new SortedList<>(filter);
    @FXML
    private TextField heb;
    @FXML
    private TableColumn<?, ?> ImageTransport;
private FileChooser filechooser;
    private File file;
    private String filePath;
    @FXML
    private Button parcourir;
    @FXML
    private TextField ImageeField;
    @FXML
    private ImageView Img;
     private javafx.scene.image.Image image;
    @FXML
    private TableColumn<?, ?> HebIdTransport;
    public BackTransportController() throws SQLException {
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
        // TODO
    }

    public void AfficherTable() throws SQLException {
        ObservableList<Transport> list = getlistASC();
        System.out.println(list);
        Table_transport.setItems(list);
        //idTransport.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionTransport.setCellValueFactory(new PropertyValueFactory<>("description"));
        disponibiliteTransport.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        priceTransport.setCellValueFactory(new PropertyValueFactory<>("price"));
        typeTransport.setCellValueFactory(new PropertyValueFactory<>("type"));
        ImageTransport.setCellValueFactory(new PropertyValueFactory<>("image"));
       

    }

    public static ObservableList<Transport> getlistASC() throws SQLException {
        ServiceTransport ts = new ServiceTransport();
        ObservableList<Transport> listTransport = FXCollections.observableArrayList(ts.afficher());
        return listTransport;
    }

    @FXML
    public void AfficherTableASC() throws SQLException {
        ObservableList<Transport> list = getlistASC();
        System.out.println(list);
        Table_transport.setItems(list);
        //idTransport.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionTransport.setCellValueFactory(new PropertyValueFactory<>("description"));
        disponibiliteTransport.setCellValueFactory(new PropertyValueFactory<>("disponibilité"));
        priceTransport.setCellValueFactory(new PropertyValueFactory<>("prix"));
        typeTransport.setCellValueFactory(new PropertyValueFactory<>("type"));
        ImageTransport.setCellValueFactory(new PropertyValueFactory<>("image"));
       HebIdTransport.setCellValueFactory(new PropertyValueFactory<>("Hebergement_id"));

    }

    @FXML
    private void Hebergement(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
      nav.navigate(event, "Hebergement", "/gui/Hebergement.fxml");
   }

    @FXML
    private void Transport(ActionEvent event) {
    }

 @FXML
    private void STAT (ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
      nav.navigate(event, "STAT", "/gui/STATTRANSPORT.fxml");
   }
    @FXML
    private void Chercher(ActionEvent event) {
        chercher.setOnKeyReleased(e -> {
            chercher.textProperty().addListener((observable, oldValue, newValue) -> {
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
            sort.comparatorProperty().bind(Table_transport.comparatorProperty());
            Table_transport.setItems(sort);
        });

    }

    @FXML
    private void getSelected(MouseEvent event) {
        index = Table_transport.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        DescriptionField.setText(String.valueOf(descriptionTransport.getCellData(index)));
        DisponibiliteField.setText(String.valueOf(disponibiliteTransport.getCellData(index)));
        PriceField.setText(String.valueOf(priceTransport.getCellData(index)));
        TypeField.setText(String.valueOf(typeTransport.getCellData(index)));
        heb.setText(String.valueOf(HebIdTransport.getCellData(index)));
       
        
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
    private void Ajouter_Transport(ActionEvent event) throws SQLException {
        ServiceTransport ts = new ServiceTransport();

        //if (controleTextFieldNonNumerique(DescriptionField) || controleTextFieldNonNumerique(DisponibiliteField) || controleTextFieldNonNumerique(TypeField)); else 
        {
        int id = Integer.parseInt(DisponibiliteField.getText());
             int id2 = Integer.parseInt(PriceField.getText());
 
        ServiceHebergement sh = new ServiceHebergement();
        //Hebergement k4 = new Hebergement(52,"8", "Tdhidhooo", 4, "Adresse", "Image",789);
        //sh.ajout(k4);
        Hebergement k4=sh.get(heb.getText());
            Transport t = new Transport(TypeField.getText(), DescriptionField.getText(),id, id2,ImageField.getText(),k4);
            if((ts.nbrv(t)==0))
            {ts.ajout(t);
            System.out.println("Transport ajoutééé");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Transport");
            tr.setMessage("Created succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
        }
        AfficherTableASC();

        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("BackTransport.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            AfficherTableASC();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        AfficherTableASC();
    }}

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
    private void Modifier_Transport(ActionEvent event) throws SQLException {
        try {
             ServiceTransport ts = new ServiceTransport();
                 ServiceHebergement sh = new ServiceHebergement();
int id = Integer.parseInt(DisponibiliteField.getText());
             int id2 = Integer.parseInt(PriceField.getText());
Hebergement k4=sh.get(Table_transport.getSelectionModel().getSelectedItem().getHebergement().getDescription());
            int idd = Table_transport.getSelectionModel().getSelectedItem().getTransport_id();
            Transport tt = new Transport(idd,TypeField.getText(), DescriptionField.getText(),id, id2,ImageField.getText(),k4);
            
            
          
          
            ts.modifier(tt);
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Transport");
            tr.setMessage("Updated succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            AfficherTable();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        AfficherTable();
    }
    //}

    @FXML
    private void Supprimer_Transport(ActionEvent event) throws SQLException {
        ServiceTransport ts = new ServiceTransport();
        ts.supprimer(Table_transport.getSelectionModel().getSelectedItem().getTransport_id());
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("BackTransport.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            AfficherTableASC();
            TrayNotification tr=new TrayNotification();
            AnimationType type=AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Transport");
            tr.setMessage("Deleted succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            AfficherTableASC();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        AfficherTableASC();
    }
    @FXML
    private void Parcourir_Transport(ActionEvent event) {
        Stage primaryStage = new Stage();
        primaryStage.onShowingProperty();
        primaryStage.setTitle("selectionner une image !!!");
        filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files ", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        parcourir.setOnAction(e -> {
            file = filechooser.showOpenDialog(primaryStage);
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

}