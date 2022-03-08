/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Hotel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import service.hotelService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.NavigationEntreInterfaces;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BackHotelController implements Initializable {

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
    private TextField chercher;
    @FXML
    private AnchorPane ap;
    @FXML
    private GridPane grid;
    @FXML
    private ImageView user_image;
    @FXML
    private Button profile_btn;
    @FXML
    private Button mdp_btn;
    @FXML
    private Button logout;
    @FXML
    private TextField PriceField;
    @FXML
    private TextField ImageField;
    @FXML
    private TextField DescriptionField;
    private javafx.scene.image.Image image;
    private FileChooser filechooser;
    private File file;
    private String filePath;
    FilteredList<Hotel> filter = new FilteredList<>(getlistHotel(), e -> true);
    SortedList<Hotel> sort = new SortedList<>(filter);
    @FXML
    private AnchorPane Hotels;
    @FXML
    private TextField NomField;
    @FXML
    private TextField AdresseField;
    @FXML
    private DatePicker Datedebut;
    @FXML
    private DatePicker Datefin;
    @FXML
    private Button parcourir;
    @FXML
    private ComboBox<?> combo;
    @FXML
    private TableView<Hotel> Table_Hotel;
    @FXML
    private TableColumn<?, ?> nomHotel;
    @FXML
    private TableColumn<?, ?> adresseHotel;
    @FXML
    private TableColumn<?, ?> priceHotel;
    @FXML
    private TableColumn<?, ?> imageHotel;
    @FXML
    private TableColumn<?, ?> descriptionHotel;
    @FXML
    private TableColumn<?, ?> datedebutHotel;
    @FXML
    private TableColumn<?, ?> datefinHotel;
    @FXML
    private TableColumn<?, ?> id_promo;
    @FXML
    private Button Ajouter_hotel;
    @FXML
    private Button Modifier_hotel;
    @FXML
    private Button Supprimer_hotel;
    int index = -1;
    @FXML
    private ComboBox<?> comboTransport;
    @FXML
    private TableColumn<?, ?> id_transport;

    public BackHotelController() throws SQLException {
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AfficherTable();
            combo.getItems().addAll(getlistPromo());
            comboTransport.getItems().addAll(getlistTransport());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

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

    public static ObservableList<Hotel> getlistHotel() throws SQLException {
        hotelService hs = new hotelService();
        ObservableList<Hotel> listhotel = FXCollections.observableArrayList(hs.readAll());
        return listhotel;
    }

    public static List getlistPromo() {
        hotelService hs = new hotelService();
        List listpromo = FXCollections.observableArrayList(hs.readidPromo());
        return listpromo;
    }

    public static List getlistTransport() {
        hotelService hs = new hotelService();
        List listpromo = FXCollections.observableArrayList(hs.readidTransport());
        return listpromo;
    }

    public void AfficherTable() throws SQLException {
        ObservableList<Hotel> list = getlistHotel();
        Table_Hotel.setItems(list);
        //idHotel.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomHotel.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresseHotel.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        priceHotel.setCellValueFactory(new PropertyValueFactory<>("price"));
        imageHotel.setCellValueFactory(new PropertyValueFactory<>("image"));
        descriptionHotel.setCellValueFactory(new PropertyValueFactory<>("description"));
        datedebutHotel.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datefinHotel.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        id_promo.setCellValueFactory(new PropertyValueFactory<>("id_promo"));
        id_transport.setCellValueFactory(new PropertyValueFactory<>("id_transport"));

    }

    @FXML
    private void Hebergement(ActionEvent event) {
    }

    @FXML
    private void Transport(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
        nav.navigate(event, "Hebergement", "/gui/BackTransport.fxml");
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
                    if (h.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });

            });
            sort.comparatorProperty().bind(Table_Hotel.comparatorProperty());
            Table_Hotel.setItems(sort);
        });
    }

    @FXML
    private void getSelected(MouseEvent event) {
        index = Table_Hotel.getSelectionModel().getSelectedIndex();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (index <= -1) {
            return;
        }
        NomField.setText(String.valueOf(nomHotel.getCellData(index)));
        AdresseField.setText(String.valueOf(adresseHotel.getCellData(index)));
        PriceField.setText(String.valueOf(priceHotel.getCellData(index)));
        ImageField.setText(String.valueOf(imageHotel.getCellData(index)));
        DescriptionField.setText(String.valueOf(descriptionHotel.getCellData(index)));
        Datedebut.setValue(LocalDate.parse(datedebutHotel.getCellData(index).toString(), formatter));
        Datefin.setValue(LocalDate.parse(datefinHotel.getCellData(index).toString(), formatter));
        //combo.setValue(String.valueOf(id_promo.getCellData(index)));
    }

    @FXML
    private void Parcourir_Hotel(ActionEvent event) {
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
                ImageField.setText(F);
                //     image = new javafx.scene.image.Image(file.toURI().toString(), 150, 100, true, true);
                //     img1.setImage(image);

            } else {
                JOptionPane.showMessageDialog(null, "Impossible d'ajouter");
            }
        });
    }

    @FXML
    private void afficherPromo(ActionEvent event) {
        getlistPromo();
    }

    @FXML
    private void Ajouter_Hotel(ActionEvent event) throws SQLException {
        hotelService ts = new hotelService();
        Double prix = ParseDouble(PriceField.getText());
        Date date_d = (Date.valueOf(Datedebut.getValue()));
        Date date_f = (Date.valueOf(Datefin.getValue()));

        if (combo.getValue() != null && comboTransport.getValue() != null) {
            int id = (Integer) combo.getValue();
            int idT = (Integer) comboTransport.getValue();

            Hotel t = new Hotel(NomField.getText(), AdresseField.getText(), prix, ImageField.getText(), DescriptionField.getText(), date_d, date_f, id, idT);
            ts.insertwithtransportandwithpromo(t);
            AfficherTable();

        } else if (combo.getValue() != null && comboTransport.getValue() == null) {
            int id = (Integer) combo.getValue();

            Hotel t = new Hotel(NomField.getText(), AdresseField.getText(), prix, ImageField.getText(), DescriptionField.getText(), date_d, date_f, id);
            ts.insertwithPromoandwithoutTransp(t);
            AfficherTable();

        } else if (comboTransport.getValue() != null && combo.getValue() == null) {
            int idT = (Integer) comboTransport.getValue();

            Hotel t = new Hotel(NomField.getText(), AdresseField.getText(), prix, ImageField.getText(), DescriptionField.getText(), date_d, date_f, idT);
            ts.insertwithtransportandwithoutpromo(t);
            AfficherTable();

        } else if (combo.getValue() == null && comboTransport.getValue() == null) {
            Hotel t = new Hotel(NomField.getText(), AdresseField.getText(), prix, ImageField.getText(), DescriptionField.getText(), date_d, date_f);
            ts.insert(t);
            AfficherTable();
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Hotel");
            tr.setMessage("Hotel succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));

        }
        System.out.println("hotel ajoutéé");
        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("BackHotel.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            AfficherTable();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Modifier_Hotel(ActionEvent event) throws SQLException {
        try {

            Date date_d = (Date.valueOf(Datedebut.getValue()));
            Date date_f = (Date.valueOf(Datefin.getValue()));
            int id = Table_Hotel.getSelectionModel().getSelectedItem().getId();
            Hotel newH = new Hotel(id, NomField.getText(), AdresseField.getText(), ParseDouble(PriceField.getText()), ImageField.getText(), DescriptionField.getText(), date_d, date_f);
            hotelService ts = new hotelService();
            ts.update(newH);
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Hotel");
            tr.setMessage("Updated succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            AfficherTable();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        AfficherTable();
    }

    @FXML
    private void Supprimer_Hotel(ActionEvent event) throws SQLException {

        try {
            hotelService hs = new hotelService();
            hs.delete(Table_Hotel.getSelectionModel().getSelectedItem().getId());
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Hotel");
            tr.setMessage("Deleted succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("BackHotel.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            AfficherTable();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        AfficherTable();
    }

    @FXML
    private void afficherTransport(ActionEvent event) {
        getlistTransport();

    }

}