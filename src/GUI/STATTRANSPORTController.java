package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import utils.MyDB;
import utils.GuiNavigate;

/**
 * FXML Controller class
 *
 * @author user
 */
public class STATTRANSPORTController implements Initializable {

    @FXML
    private PieChart pieChart;
    private Connection conn;
    private ObservableList data;
     private ObservableList data1;
     private ObservableList data2;
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
    private AnchorPane ap;
    @FXML
    private ImageView user_image;
    @FXML
    private Button profile_btn;
    @FXML
    private Button mdp_btn;
    @FXML
    private Button logout;
    @FXML
    private PieChart piechart1;
    private PieChart piechart2;
    @FXML
    private BarChart<?, ?> bar;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
      
    }
@FXML
   

private void click(ActionEvent event){  buildData();
        pieChart.getData().addAll(data);}
    @FXML
    private void click1(ActionEvent event){  buildData1();
        piechart1.getData().addAll(data1);}
    
    private void click2(ActionEvent event){  buildData2();
        piechart2.getData().addAll(data2);}
   
        
        public void buildData() {
        conn = MyDB.getInstance().getConnection();

        data = FXCollections.observableArrayList();
        try {
            //SQL FOR SELECTING NATIONALITY OF CUSTOMER
            String SQL = "SELECT COUNT(Transport_id), "
                    + "type FROM TRANSPORT "
                    + "GROUP BY Type";

            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                //adding data on piechart data
                data.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
            }
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            return;
        }

    }
           public void buildData1() {
        conn = MyDB.getInstance().getConnection();

        data1 = FXCollections.observableArrayList();
        try {
            //SQL FOR SELECTING NATIONALITY OF CUSTOMER
            String SQL = "SELECT COUNT(Hebergement_id), "
                    + "Adresse FROM hebergement "
                    + "GROUP BY Adresse";

            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                //adding data on piechart data
                data1.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
            }
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            return;
        }

    }
 public void buildData2() {
        conn = MyDB.getInstance().getConnection();
        data2 = FXCollections.observableArrayList();
        try {
            //SQL FOR SELECTING NATIONALITY OF CUSTOMER
            String SQL = "SELECT COUNT(Hebergement_id), "
                    + "Adresse FROM hebergement "
                    + "GROUP BY Adresse";
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while (rs.next()) {
                //adding data on piechart data
                data2.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
            }
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            return;
        }

    }

    @FXML
    private void Hebergement(ActionEvent event) throws IOException {
        GuiNavigate nav = new GuiNavigate();
      nav.navigate(event, "Hebergement", "/gui/Hebergement.fxml");
   }
     @FXML
    private void Transport(ActionEvent event) throws IOException {
        GuiNavigate nav = new GuiNavigate();
      nav.navigate(event, "Transport", "/gui/BackTransport.fxml");
   }

    @FXML
     private void click3(ActionEvent event){
        
        int a = 0;
        int j=0;
        int n=-1;
        XYChart.Series set =new XYChart.Series <>();
        set.setName("");
        List heber=new ArrayList<>();
        List trans=new ArrayList<>();
        conn = MyDB.getInstance().getConnection();
        String query;
        query = "select * from hebergement";
        
        try {
            PreparedStatement ste;
            ste = conn.prepareStatement(query);
            ResultSet rs;
            rs = ste.executeQuery();
            
            //rs.next();
            while(rs.next()){
                heber.add(rs.getInt("Hebergement_id"));
                //int b = rs.getInt("id_hbg");
                trans.add(rs.getInt("Transport_id"));
                //String c = rs.getString("nom_hotel");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
               
        System.out.println(heber);
        System.out.println(trans);
        int i;
        
                for(i=0;i<heber.size();i++){
                   
                    String query1;
                    query1 = "select * from transport";
                    
                    try {j=0;
                            PreparedStatement ste1;
                            ste1 = conn.prepareStatement(query1);
                            ResultSet rs1 ;
                            rs1 = ste1.executeQuery();
                            while(rs1.next())
                            {
                                j++;
                            }
                            System.out.println(j);
                            
                                        
                            
                            
            
            
                        } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                         }
                                        n=n+1;
                                        set.getData().add(new XYChart.Data(trans.get(n),j));
                                        
                                        System.out.println(n);
                                        
                                         bar.getData().add(set);
                    
                                            }
               bar.getData().addAll(set);
                
               
    }
        
}