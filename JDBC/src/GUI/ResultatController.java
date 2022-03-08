/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Questions;
import entities.Réponses;
import entities.Sondage;
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
import javafx.fxml.Initializable;
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
import javafx.scene.input.MouseEvent;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.ServiceQuestions;
import services.ServiceRéponses;
import services.ServiceSondage;
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
    @FXML
    private TableColumn<Questions, Integer> id;
    @FXML
    private TableColumn<Questions, String> question;
    @FXML
    private TableColumn<Questions, String> type;
    @FXML
    private TableColumn<Questions, String> sondageid;
    
    ServiceRéponses sr =new ServiceRéponses();
    @FXML
    private TableView<Réponses> tab;
    @FXML
    private TableColumn<Réponses,Integer> idd;
    @FXML
    private TableColumn<Réponses, String> rep;
    @FXML
    private TableColumn<Réponses, String> idquestt;
    @FXML
    private BarChart<String, Integer> RateChart;
    @FXML
    private NumberAxis nbr;
    @FXML
    private CategoryAxis Stars;
    ServiceQuestions sq = new ServiceQuestions();
    @FXML
    private ComboBox<String> CombSujet;
     XYChart.Series<String,Integer> series = new XYChart.Series();
      Connection cnx;
    @FXML
    private TextField nbrYes;
    @FXML
    private TextField nbrNon;
    
    

    
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
          ObservableList<Questions> list = getTableListSujet(CombSujet.getValue().toString());
        tableau.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("Question_id"));
        question.setCellValueFactory(new PropertyValueFactory<>("question"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        sondageid.setCellValueFactory(new PropertyValueFactory<>("sondage_id"));
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
        id.setCellValueFactory(new PropertyValueFactory<>("Réponses_id"));
        rep.setCellValueFactory(new PropertyValueFactory<>("réponse"));
        idquestt.setCellValueFactory(new PropertyValueFactory<>("Sondage_id"));
        
        
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
            //String req ="select * from Questions , Sondage where Sondage.sujet= '"+CombSujet.getValue()+"'";
            String req ="select * from Questions , Sondage where Sondage.sujet= '"+CombSujet.getValue()+"'";
            String req1 ="select * from Réponses , Questions where Questions.question= '"+question.getCellData(0)+"'";
            cnx = MyDB.getInstance().getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            
            XSSFWorkbook wb =new  XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Stat");
            XSSFRow Header = sheet.createRow(0);
            XSSFRow Body = sheet.createRow(1);
            Header.createCell(0).setCellValue("Sujet");
            
            Header.createCell(1).setCellValue(CombSujet.getValue());
            Body.createCell(0).setCellValue("Question");
            Body.createCell(1).setCellValue("Type");
            Body.createCell(2).setCellValue("réponse");
            int index = 2;
            XSSFRow row = sheet.createRow(index);
            while(rs.next()){
                
                
                row.createCell(0).setCellValue(rs.getString("question"));
                row.createCell(1).setCellValue(rs.getString("type"));
                
                index ++;
               
            }
//            ResultSet rs1 = st.executeQuery(req1);
//            while(rs1.next()){
//                
//                
//                row.createCell(2).setCellValue(rs1.getString("réponse"));
//                
//                
//                index ++;
//               
//            }
//             
//            
             try (FileOutputStream FileOut = new FileOutputStream ("C:\\Users\\user\\Desktop\\Réponse"+CombSujet.getValue()+".xlsx")) {
                 wb.write(FileOut);
             }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
        
    }
        
    