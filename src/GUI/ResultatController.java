/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Questions;
import Entities.Réponses;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Service.ServiceQuestions;
import Service.ServiceRéponses;
import Service.ServiceSondage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.MyDB;



/**
 * FXML Controller class
 *
 * @author user
 */
public class ResultatController implements Initializable {

    @FXML
    private PieChart rst;
  
    @FXML
    private TableView<Questions> tableau;
    private TableColumn<Questions, Integer> id;
    @FXML
    private TableColumn<Questions, String> question;
    @FXML
    private TableColumn<Questions, String> type;
    
    ServiceRéponses sr =new ServiceRéponses();
    @FXML
    private TableView<Réponses> tab;
    @FXML
    private TableColumn<Réponses, String> rep;
    @FXML
    private BarChart<String, Integer> RateChart;
    @FXML
    private NumberAxis nbr;
    @FXML
    private CategoryAxis Stars;
    ServiceQuestions sq = new ServiceQuestions();
    @FXML
    private ComboBox<String> CombSujet;
     XYChart.Series<String,Integer> series = new XYChart.Series<>(); 
      Connection cnx;
    @FXML
    private TextField nbrYes;
    @FXML
    private TextField nbrNon;
    ServiceSondage ss = new ServiceSondage();
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
    @FXML
    private Button btnpréc;
    
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //ObservableList<String> list = FXCollections.observableArrayList("Avis","bbb");
          ServiceSondage ss = new ServiceSondage();
         ObservableList<String> list= ss.afficherSujet();
         CombSujet.setItems(null);
         CombSujet.setItems(list);
         
         showPieChart (0, 0);
       
    
   
    }   
    
      public void showPieChart (int yes, int no){
        
      ObservableList<Data> l = FXCollections.observableArrayList(
        new PieChart.Data("YES",yes ),
        new PieChart.Data("NO", no) 
                );
      rst.setData(l);
     
    }
     
      
     public void ShowBarChart(int star1, int star2,int star3 ,int star4 ,int star5){
     
        series.setName("Rating");
         
        series.getData().add(new XYChart.Data<>("1",star1));
        series.getData().add(new XYChart.Data<>("2",star2));
        series.getData().add(new XYChart.Data<>("3",star3));
        series.getData().add(new XYChart.Data<>("4",star4));
        series.getData().add(new XYChart.Data<>("5",star5));
        RateChart.getData().add(series);
         
     }
     
     private ObservableList<Questions> getTableListSujet(String Sujet) {
             ObservableList<Questions> List = sq.afficher();
        ObservableList<Questions> newList = 
        List.stream()
            .filter((Questions q)-> (q.getSondage().getSujet().equals(Sujet)))
            .collect(Collector.of(
                FXCollections::observableArrayList,
                ObservableList::add,
                (l1, l2) -> { l1.addAll(l2); return l1; })
            ); return newList;}
        
    
     public void ShowTable() {
          ObservableList<Questions> list = getTableListSujet(CombSujet.getValue());
        tableau.setItems(list);
       // id.setCellValueFactory(new PropertyValueFactory<>("Question_id"));
        question.setCellValueFactory(new PropertyValueFactory<>("question"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
       // sondageid.setCellValueFactory(new PropertyValueFactory<>("sondage_id"));
         System.out.println(question.getText());
        
     }


    
     private ObservableList<Réponses> getTableList(int id) {
             ObservableList<Réponses> List = sr.afficher();
        ObservableList<Réponses> newList = 
        List.stream()
            .filter((Réponses r)-> (r.getQuestion_id()==id))
            .collect(Collector.of(
                FXCollections::observableArrayList,
                ObservableList::add,
                (l1, l2) -> { l1.addAll(l2); return l1; })
            ); return newList;}
        
      
    public void ShowTableRep(ObservableList<Réponses> l){
        
        tab.setItems(l);
        
//        tableau.setItems(list);
     //   id.setCellValueFactory(new PropertyValueFactory<>("Réponses_id"));
        rep.setCellValueFactory(new PropertyValueFactory<>("réponse"));
      //  idquestt.setCellValueFactory(new PropertyValueFactory<>("Sondage_id"));
        
        
     }

    @FXML
    private void afficherstat(MouseEvent event) {
   tableau.getSelectionModel().selectedItemProperty().addListener((obs,oldselection,newselection)->{
       String type =newselection.getType();
       int idq = newselection.getQuestion_id();
        if (newselection != null){
        
     
     if (type.equals("YES/NO")){
          
   
    
            System.out.println(idq);
    int nbryes = sq.quesyes(idq);
    nbrYes.setText(Integer.toString(nbryes));
    int nbrno = sq.quesno(idq);
    nbrNon.setText(Integer.toString(nbrno));
        System.out.println(nbryes);
        System.out.println(nbrno);
        
        showPieChart (nbryes, nbrno);
     }else if ( type.equals("Text")){
         ObservableList<Réponses> listrep = getTableList(newselection.getQuestion_id());
         ShowTableRep(listrep);
            
        
    
    }else if(type.equals("Rate")){
       
      //  int idq = newselection.getQuestion_id();
       
        int star1=sq.questRate1(idq);
        int star2=sq.questRate2(idq);
        int star3=sq.questRate3(idq);
        int star4=sq.questRate4(idq);
        int star5=sq.questRate5(idq);
        // System.out.println(star2);
       ShowBarChart(star1, star2, star3 , star4 , star5);
        
        
      
        
    }
      }
    });
  
    }

    @FXML
    private void AfficherQuest(ActionEvent event) {
       
        ShowTable();
    }

    @FXML
    private void Excel(ActionEvent event) throws FileNotFoundException, IOException {
       
        
         try {
              int a=ss.getid(CombSujet.getValue());
        System.out.println(a);
           
            String req ="SELECT questions.question q ,COUNT(questions.Question_id) nbreYes FROM questions, réponses WHERE Questions.Question_id=réponses.Question_id and Questions.type='YES/NO' and réponses.réponse='YES' and questions.sondage_id='"+a+"' GROUP by questions.Question_id";
           String req1 ="SELECT questions.question qn ,COUNT(questions.Question_id) nbreNon FROM questions, réponses WHERE Questions.Question_id=réponses.Question_id and Questions.type='YES/NO' and réponses.réponse='NO' and questions.sondage_id='"+a+"' GROUP by questions.Question_id";
            String req2="SELECT questions.question question ,AVG(réponse) avgrate FROM questions, réponses WHERE questions.Question_id=réponses.Question_id and questions.type='Rate' and questions.sondage_id='"+a+"'";
            String req3="SELECT Questions.question quest ,réponses.réponse rep FROM `questions`,réponses WHERE type='Text' and questions.Question_id=réponses.Question_id and questions.sondage_id='"+a+"'";
            cnx = MyDB.getInstance().getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
          
            
           //Creat EXCEL 
            XSSFWorkbook wb =new  XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Stat");
            XSSFRow Header = sheet.createRow(0);
            Header.createCell(0).setCellValue("Sujet");
            Header.createCell(1).setCellValue(CombSujet.getValue());
            
            XSSFRow Body = sheet.createRow(1);
            Body.createCell(0).setCellValue("Question");
            Body.createCell(1).setCellValue("Nombre YES");
           
           
             int index=2;
            
            while(rs.next()) {
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("q"));
                row.createCell(1).setCellValue(rs.getString("nbreYes"));
                index ++;
                
               
            }
            
            ResultSet rs1 = st.executeQuery(req1); 
            XSSFRow Body2 = sheet.createRow(6);
            Body2.createCell(0).setCellValue("Question");
            Body2.createCell(1).setCellValue("Nombre NON");
              
             int index1=7;
            while (rs1.next()){
                 XSSFRow row = sheet.createRow(index1);
                row.createCell(0).setCellValue(rs1.getString("qn"));
                row.createCell(1).setCellValue(rs1.getString("nbreNon"));
               index1 ++;
                
            }
            
             ResultSet rs2 = st.executeQuery(req2);
             XSSFRow Body3 = sheet.createRow(11);
            Body3.createCell(0).setCellValue("Question");
            Body3.createCell(1).setCellValue("Average");
              
             int index2=12;
            while (rs2.next()){
                 XSSFRow row = sheet.createRow(index2);
                row.createCell(0).setCellValue(rs2.getString("question"));
              
                row.createCell(1).setCellValue(rs2.getString("avgrate"));
               
               index2 ++;
                
            }
            
            
             ResultSet rs3 = st.executeQuery(req3);
             XSSFRow Body4 = sheet.createRow(16);
            Body4.createCell(0).setCellValue("Question");
            Body4.createCell(1).setCellValue("Réponse");
              
             int index3=17;
            while (rs3.next()){
                 XSSFRow row = sheet.createRow(index3);
                row.createCell(0).setCellValue(rs3.getString("quest"));
                row.createCell(1).setCellValue(rs3.getString("rep"));
               index3 ++;
                
            }
            
         
             try (FileOutputStream FileOut = new FileOutputStream ("C:\\Users\\user\\Desktop\\Réponse"+CombSujet.getValue()+".xlsx")) {
                 wb.write(FileOut);
                 FileOut.close(); 
                 wb.close(); 
             }
             TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Excel");
            tr.setMessage("Fichier Excel généré ");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
 
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
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
    private void goBack(ActionEvent event) {
          try {
             FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/GestHomePage.fxml"));
            Parent root = loader.load();
          
            Scene scene = new Scene(root);
            Stage stage =(Stage)btnpréc.getScene().getWindow();
            stage.setScene(scene);
            //stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
    }
        
    }
        
    