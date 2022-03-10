/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Offres;
import Service.OffresService;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextArea;
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
public class OffreAddFXMLController implements Initializable {

    @FXML
    private Button add;
    @FXML
    private Button mod;
    @FXML
    private Button del;
    @FXML
    private TableView<Offres> table;
    @FXML
    private TextField search;
    @FXML
    private TableColumn<Offres, String> Description;
    @FXML
    private TableColumn<Offres, Integer> nbpts;
    @FXML
    private TableColumn<Offres, String> destination;
    @FXML
    private ComboBox<String> Dest;
    @FXML
    private TextArea desc;

    OffresService os = new OffresService();
        FilteredList<Offres> filter = new FilteredList<>(getTableList(), e -> true);
    SortedList<Offres> sort = new SortedList<>(filter);
    @FXML
    private TableColumn<Offres, Integer> pource;
    @FXML
    private TextField nbptsa;
    @FXML
    private TextField pourcered;
    int index = -1 ; 
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
        ObservableList<String> list2 = FXCollections.observableArrayList("Afghanistan",
                "Åland Islands",
                "Albania",
                "Algeria",
                "American Samoa",
                "Andorra",
                "Angola",
                "Anguilla",
                "Antarctica",
                "Antigua and Barbuda",
                "Argentina",
                "Armenia",
                "Aruba",
                "Australia",
                "Austria",
                "Azerbaijan",
                "Bahamas (the)",
                "Bahrain",
                "Bangladesh",
                "Barbados",
                "Belarus",
                "Belgium",
                "Belize",
                "Benin",
                "Bermuda",
                "Bhutan",
                "Bolivia (Plurinational State of)",
                "Bonaire, Sint Eustatius and Saba",
                "Bosnia and Herzegovina",
                "Botswana",
                "Bouvet Island",
                "Brazil",
                "British Indian Ocean Territory (the)",
                "Brunei Darussalam",
                "Bulgaria",
                "Burkina Faso",
                "Burundi",
                "Cabo Verde",
                "Cambodia",
                "Cameroon",
                "Canada",
                "Cayman Islands (the)",
                "Central African Republic (the)",
                "Chad",
                "Chile",
                "China",
                "Christmas Island",
                "Cocos (Keeling) Islands (the)",
                "Colombia",
                "Comoros (the)",
                "Congo (the Democratic Republic of the)",
                "Congo (the)",
                "Cook Islands (the)",
                "Costa Rica",
                "Croatia",
                "Cuba",
                "Curaçao",
                "Cyprus",
                "Czechia",
                "Côte d'Ivoire",
                "Denmark",
                "Djibouti",
                "Dominica",
                "Dominican Republic (the)",
                "Ecuador",
                "Egypt",
                "El Salvador",
                "Equatorial Guinea",
                "Eritrea",
                "Estonia",
                "Eswatini",
                "Ethiopia",
                "Falkland Islands (the) [Malvinas]",
                "Faroe Islands (the)",
                "Fiji",
                "Finland",
                "France",
                "French Guiana",
                "French Polynesia",
                "French Southern Territories (the)",
                "Gabon",
                "Gambia (the)",
                "Georgia",
                "Germany",
                "Ghana",
                "Gibraltar",
                "Greece",
                "Greenland",
                "Grenada",
                "Guadeloupe",
                "Guam",
                "Guatemala",
                "Guernsey",
                "Guinea",
                "Guinea-Bissau",
                "Guyana",
                "Haiti",
                "Heard Island and McDonald Islands",
                "Holy See (the)",
                "Honduras",
                "Hong Kong",
                "Hungary",
                "Iceland",
                "India",
                "Indonesia",
                "Iran (Islamic Republic of)",
                "Iraq",
                "Ireland",
                "Isle of Man",
                "Israel",
                "Italy",
                "Jamaica",
                "Japan",
                "Jersey",
                "Jordan",
                "Kazakhstan",
                "Kenya",
                "Kiribati",
                "Korea (the Democratic People's Republic of)",
                "Korea (the Republic of)",
                "Kuwait",
                "Kyrgyzstan",
                "Lao People's Democratic Republic (the)",
                "Latvia",
                "Lebanon",
                "Lesotho",
                "Liberia",
                "Libya",
                "Liechtenstein",
                "Lithuania",
                "Luxembourg",
                "Macao",
                "Madagascar",
                "Malawi",
                "Malaysia",
                "Maldives",
                "Mali",
                "Malta",
                "Marshall Islands (the)",
                "Martinique",
                "Mauritania",
                "Mauritius",
                "Mayotte",
                "Mexico",
                "Micronesia (Federated States of)",
                "Moldova (the Republic of)",
                "Monaco",
                "Mongolia",
                "Montenegro",
                "Montserrat",
                "Morocco",
                "Mozambique",
                "Myanmar",
                "Namibia",
                "Nauru",
                "Nepal",
                "Netherlands (the)",
                "New Caledonia",
                "New Zealand",
                "Nicaragua",
                "Niger (the)",
                "Nigeria",
                "Niue",
                "Norfolk Island",
                "Northern Mariana Islands (the)",
                "Norway",
                "Oman",
                "Pakistan",
                "Palau",
                "Palestine, State of",
                "Panama",
                "Papua New Guinea",
                "Paraguay",
                "Peru",
                "Philippines (the)",
                "Pitcairn",
                "Poland",
                "Portugal",
                "Puerto Rico",
                "Qatar",
                "Republic of North Macedonia",
                "Romania",
                "Russian Federation (the)",
                "Rwanda",
                "Réunion",
                "Saint Barthélemy",
                "Saint Helena, Ascension and Tristan da Cunha",
                "Saint Kitts and Nevis",
                "Saint Lucia",
                "Saint Martin (French part)",
                "Saint Pierre and Miquelon",
                "Saint Vincent and the Grenadines",
                "Samoa",
                "San Marino",
                "Sao Tome and Principe",
                "Saudi Arabia",
                "Senegal",
                "Serbia",
                "Seychelles",
                "Sierra Leone",
                "Singapore",
                "Sint Maarten (Dutch part)",
                "Slovakia",
                "Slovenia",
                "Solomon Islands",
                "Somalia",
                "South Africa",
                "South Georgia and the South Sandwich Islands",
                "South Sudan",
                "Spain",
                "Sri Lanka",
                "Sudan (the)",
                "Suriname",
                "Svalbard and Jan Mayen",
                "Sweden",
                "Switzerland",
                "Syrian Arab Republic",
                "Taiwan (Province of China)",
                "Tajikistan",
                "Tanzania, United Republic of",
                "Thailand",
                "Timor-Leste",
                "Togo",
                "Tokelau",
                "Tonga",
                "Trinidad and Tobago",
                "Tunisia",
                "Turkey",
                "Turkmenistan",
                "Turks and Caicos Islands (the)",
                "Tuvalu",
                "Uganda",
                "Ukraine",
                "United Arab Emirates (the)",
                "United Kingdom of Great Britain and Northern Ireland (the)",
                "United States Minor Outlying Islands (the)",
                "United States of America (the)",
                "Uruguay",
                "Uzbekistan",
                "Vanuatu",
                "Venezuela (Bolivarian Republic of)",
                "Viet Nam",
                "Virgin Islands (British)",
                "Virgin Islands (U.S.)",
                "Wallis and Futuna",
                "Western Sahara",
                "Yemen",
                "Zambia",
                "Zimbabwe");
        Dest.setItems(list2);
        ShowTable();
        // TODO
    }

    private ObservableList<Offres> getTableList() {

        ObservableList<Offres> List = os.Read();
        return List;

    }

    public void ShowTable() {
        ObservableList<Offres> list = getTableList();
        System.out.println(list);
        table.setItems(list);
        //  idvo.setCellValueFactory(new PropertyValueFactory<>("id_g"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        destination.setCellValueFactory(new PropertyValueFactory<>("Destination"));
        nbpts.setCellValueFactory(new PropertyValueFactory<>("Nb_points_req"));
        pource.setCellValueFactory(new PropertyValueFactory<>("Pourcentage_red"));

    }

    @FXML
    private void insert(ActionEvent event) {
      Offres o = new Offres();
        o.setDescription(desc.getText());
        o.setDestination(Dest.getValue());
        o.setNb_points_req(Integer.parseInt(nbptsa.getText()));
        o.setPourcentage_red(Integer.parseInt(pourcered.getText()));
        
       if(os.nbrv(o)==0){
              os.Create(o);
                 System.out.println("Offre ajouté");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Offre");
            tr.setMessage("Créé avec succès");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
        }
        else {
              System.out.println("Offre deja existe");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Offre");
            tr.setMessage("Déja existe");
            tr.setNotificationType(NotificationType.ERROR);
            tr.showAndDismiss(Duration.millis(5000));
        }

      
        ShowTable();
        
    }
    @FXML
    private void update(ActionEvent event) {
         Offres g = table.getSelectionModel().getSelectedItem();
        String descc = desc.getText();
        String destt = Dest.getValue();
       
        Integer nbtsaa = Integer.parseInt(nbptsa.getText());
        Integer pr = Integer.parseInt(pourcered.getText());
       

        Offres of = new Offres(g.getID(), descc, nbtsaa, destt, pr);

        os.Update(of);
        ShowTable();
    }

   
    @FXML
    private void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment Supprimer ");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
         Offres o = table.getSelectionModel().getSelectedItem();
        os.Delete(o.getID());
        ShowTable();
    }
    }

    @FXML
    private void getSelected(MouseEvent event) {
     index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        desc.setText(String.valueOf(Description.getCellData(index)));
        Dest.setValue(String.valueOf(destination.getCellData(index)));
        nbptsa.setText(String.valueOf(nbpts.getCellData(index)));
        pourcered.setText(String.valueOf(pource.getCellData(index)));

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
                    if (t.getDestination().toLowerCase().contains(lowerCaseFilter)) {
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
