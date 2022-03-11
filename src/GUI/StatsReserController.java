/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import Service.FactureService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.GuiNavigate;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class StatsReserController implements Initializable {

    @FXML
    private Button cr;
    @FXML
    private Button mnt;
    @FXML
    private TextField total;
    @FXML
    private TextField mois;
    @FXML
    private TextField rev;
    FactureService fs = new FactureService();
    @FXML
    private TextField an;
    @FXML
    private TextField rev1;
    @FXML
    private LineChart<String, Integer> ls;
    @FXML
    private NumberAxis xn;
    @FXML
    private CategoryAxis xm;
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
    private Button retour;
    
            

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        total.setDisable(true);
        rev.setDisable(true);
        rev1.setDisable(true);
        
        
        
        // TODO
    }    

    @FXML
    private void courbe(ActionEvent event) {
        XYChart.Series<String , Integer> series = new XYChart.Series<String , Integer>();
        //series.setName("Revenues par mois "); 
        series.getData().add(new XYChart.Data("Jan", fs.RevM(1))); 
            series.getData().add(new XYChart.Data("Feb", fs.RevM(2)));
            series.getData().add(new XYChart.Data("March", fs.RevM(3)));
            series.getData().add(new XYChart.Data("April", fs.RevM(4)));
            series.getData().add(new XYChart.Data("May", fs.RevM(5)));
            series.getData().add(new XYChart.Data("June", fs.RevM(6)));
            series.getData().add(new XYChart.Data("July", fs.RevM(7)));
            series.getData().add(new XYChart.Data("Aug", fs.RevM(8)));
            series.getData().add(new XYChart.Data("Sep", fs.RevM(9)));
            series.getData().add(new XYChart.Data("Oct", fs.RevM(10)));
            series.getData().add(new XYChart.Data("Nov", fs.RevM(11)));
            series.getData().add(new XYChart.Data("Dec", fs.RevM(12)));
            
            ls.getData().add(series);
        
    }

    @FXML
    private void Montant(ActionEvent event) {
        
        total.setText(fs.tot().toString());
        
        //&& ( an.getText().matches(".*[2000-3500]"))&& ( mois.getText()b.matches(".*[1-12]"))
//         String a=an.getText();
//         String b= mois.getText();
        
        if ( (!an.getText().equals("") ) && (!mois.getText().equals("") ) ){
        
        Integer m = Integer.parseInt(mois.getText());
        Integer y = Integer.parseInt(an.getText());
        if (  (0 < m && m < 13) && (1000 < y && y < 3000) )    {
        Integer r = fs.RevM(m);
        Integer r1 = fs.RevY(y);
        rev.setText(r.toString());
        rev1.setText(r1.toString());
        total.setText(fs.tot().toString());
        
        }
        else {
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Erreur de saisie ");
            tr.setMessage("Format Invalide");
            tr.setNotificationType(NotificationType.WARNING);
            tr.showAndDismiss(Duration.millis(5000));
        }
        
        
    }
    
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
      nav.navigate(event, "Gestion", "/gui/stats.fxml");
        
    }
}
