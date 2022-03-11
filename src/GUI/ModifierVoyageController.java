/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Transport;
import Entities.Voyageorganise;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Service.ServiceVol;
import Service.ServiceVoyageorganise;
import Entities.Vol;
import java.io.IOException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
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
 * @author Acer
 */
public class ModifierVoyageController implements Initializable {
        ServiceVol vv = new ServiceVol();

    ServiceVoyageorganise sv = new ServiceVoyageorganise();
    @FXML
    private TableView<Voyageorganise> Voyages;
    @FXML
    private TableColumn<Voyageorganise, Integer> Voyage_id;
    @FXML
    private TableColumn<Voyageorganise, String> Description;
    @FXML
    private TableColumn<Voyageorganise, Integer> Prix;
    @FXML
    private TableColumn<Voyageorganise, Integer> Vol_id;
    @FXML
    private TableColumn<Voyageorganise, Integer> Transport_id;
    @FXML
    private Button Supprimer;
    @FXML
    private Button Modifier;
    @FXML
    private TextField Descriptionv;
    @FXML
    private TextField prixv;
    @FXML
    private TextField imagev;
    @FXML
    private TableColumn<Voyageorganise, Integer> nbr;
    @FXML
    private TextField Vol;
    @FXML
    private TextField tr;
    @FXML
    private ComboBox<String> cb;
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
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cb.getItems().addAll(getListTitle());

        addListenerForTable();
        ShowTable();
        
    }  
    
     private ObservableList<Voyageorganise> getTableList() {
       
        ObservableList<Voyageorganise> List = sv.afficher();
        return List ;
        
    }
     
     public static ObservableList<String> getListTitle() {
        ServiceVoyageorganise cs = new ServiceVoyageorganise();
        ObservableList<String> listtitle = cs.GetAllTitle();
        return listtitle;

    }
    public void ShowTable() {
        ObservableList<Voyageorganise> list = getTableList();
        Voyages.setItems(list);
     //   Voyage_id.setCellValueFactory(new PropertyValueFactory<>("Voyage_id"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        //Image.setCellValueFactory(new PropertyValueFactory<>("Image"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        nbr.setCellValueFactory(new PropertyValueFactory<>("Nbre_Places"));
        Vol_id.setCellValueFactory(new PropertyValueFactory<>("Vol_id"));
        //Transport_id.setCellValueFactory(new PropertyValueFactory<>("Transport_id"));
       
    }
  
    
    
    
    private void addListenerForTable(){
        Voyages.getSelectionModel().selectedItemProperty().addListener((obs,oldselection,newselection)->{
        if (newselection != null){
            Modifier.setDisable(false);
            Supprimer.setDisable(false);
            Descriptionv.setText(newselection.getDescription());
            //prixv.setText((newselection.getDescription()).toString());
            tr.setDisable(true);
            Integer o=newselection.getNbre_Places();
            imagev.setText(o.toString());
            Integer s = newselection.getPrix();
            String ss=s.toString();
            Integer v = newselection.getVol_id();
            Vol.setText(v.toString());
            prixv.setText(ss);
            tr.setText(sv.gett(newselection.getTransport_id()).getDescription());
        }else{
               
               Modifier.setDisable(true);
               Supprimer.setDisable(true);
        }
        }); 
    
    }

    @FXML
    private void Delete(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment Supprimer");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
        
        Voyageorganise vo = Voyages.getSelectionModel().getSelectedItem();
        sv.supprimer(vo.getVoyage_id());
        ShowTable();
        TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Voyage supprimé");
            tr.setMessage("deleted succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
        }
        
    }

    @FXML
    private void Update(ActionEvent event) {
     
        if (!Descriptionv.getText().equals("") && !Prix.getText().equals("") && !imagev.getText().equals("") && !Vol.getText().equals("") && ((Double.parseDouble(prixv.getText())>0 ) ) && ((Double.parseDouble(imagev.getText())>0 ) )  ){
        
        Voyageorganise vo = Voyages.getSelectionModel().getSelectedItem();
        String desc = Descriptionv.getText();
        String nbreplace = imagev.getText();
        Integer nb = Integer.parseInt(nbreplace);
        String prix  = prixv.getText();
        
        //Integer prixvv=Integer.parseInt(prix);
        Double oo=Double.parseDouble(prix);
        Integer prixvv=(int)Math.round(oo);
        String iheb=Vol.getText();
        Integer iheb1=Integer.parseInt(iheb);
        Vol v1=vv.get(iheb1);
        //Vol v1=vv.get(vo.getVol_id());
        Transport t1=sv.get(cb.getValue());
        //Transport t1=sv.gett(vo.getTransport_id());
        Voyageorganise v = new Voyageorganise(vo.getVoyage_id(),desc, "", prixvv, nb, v1, t1);
        
        

        
            sv.modifier(v);
            ShowTable();
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Voyage modifié ");
            tr.setMessage("updated succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));

        
        
        
    }
    }    

    @FXML
    private void map(ActionEvent event) {
       Voyageorganise vo = Voyages.getSelectionModel().getSelectedItem();

        Double x =vv.getx(vo.getVol_id());
        Double y =vv.gety(vo.getVol_id());
        
        MapsController.test(x,y);
        
        
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

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        GuiNavigate nav = new GuiNavigate();
      nav.navigate(event, "Gestion", "/gui/AjouterVoyage"
              + ".fxml");
        
    }

   
}    
  

