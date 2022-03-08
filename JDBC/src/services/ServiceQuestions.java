/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Questions;
import entities.Réponses;
import entities.Sondage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author user
 */
public class ServiceQuestions implements IService<Questions> {
      Connection cnx;

    public ServiceQuestions() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajout(Questions t) {
        try {
            String req = "insert into Questions (question,type,Sondage_id) values"
                    + " ( '" + t.getQuestion()+ "', '" + t.getType() +"', '" +t.getSondage().getSondage_id()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void modifier(Questions t) {
        try {
            String req = "update Questions set question = ? , type = ?  , Sondage_id = ?  where Question_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getQuestion());
            ps.setString(2, t.getType());
            ps.setInt(3, t.getSondage().getSondage_id());
            ps.setInt(4, t.getQuestion_id());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void supprimer(int Question_id) {
        try {
            String req = "delete from Questions where Question_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, Question_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public ObservableList<Questions> afficher() {
       ObservableList<Questions> list = FXCollections.observableArrayList();
        try {
            String req ="select * from Questions , Sondage where Questions.Sondage_id=Sondage.Sondage_id ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Sondage s = new Sondage();
                Questions q = new Questions(s);
                q.setQuestion_id(rs.getInt(1));
                q.setQuestion(rs.getString("question"));
                q.setType(rs.getString("type"));
                q.setSondage_id(rs.getInt("Sondage.Sondage_id"));
                q.getSondage().setSondage_id(rs.getInt("Sondage.Sondage_id"));
                q.getSondage().setCatégorie(rs.getString("Sondage.Catégorie"));
                q.getSondage().setSujet(rs.getString("Sondage.Sujet"));
                list.add(q);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;}
        
         public Questions get(String s) {
             Questions q = new Questions();
        
        try {
            String req ="select * from Questions  where upper(Questions.question)= '"+s.toUpperCase()+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            
            while (rs.next()){
//                //Sondage s = new Sondage();
//                
            q.setQuestion_id(rs.getInt("Question_id"));
            q.setType(rs.getString("type"));
            q.setQuestion(rs.getString("question"));
           // q.getSondage().setSondage_id(rs.getInt("sondage_id"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSondage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
         }
         
         
       public int quesyes(int q_id) {
       int n = 0;
        try {
            String req ="SELECT COUNT(questions.Question_id) nbre FROM questions, réponses  WHERE questions.Question_id=réponses.Question_id and questions.type='YES/NO' and réponses.réponse='yes' and Questions.Question_id="+q_id+ " GROUP by questions.Question_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
               n=rs.getInt("nbre");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;}
       
       public int quesno(int q_id) {
       int n = 0;
        try {
            String req ="SELECT COUNT(questions.Question_id) nbre FROM questions, réponses  WHERE questions.Question_id=réponses.Question_id and questions.type='YES/NO' and réponses.réponse='no' and Questions.Question_id="+q_id+ " GROUP by questions.Question_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
               n=rs.getInt("nbre");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;}
       
       public int nbrq(Questions s) {
        
        int x=0;
        try {
             String req ="select COUNT(*) n from Sondage where Upper(sujet)= '"+s.getQuestion().toUpperCase()+"'and Upper(type)="+s.getType()+"'";
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(req);
             while (rs.next()){    
                x=rs.getInt("n");
                System.out.println(x);
               }
             } catch (SQLException ex) {
            Logger.getLogger(ServiceSondage.class.getName()).log(Level.SEVERE, null, ex);
             }
        return x;
    }
       
         
       public int questRate1(int q_id) {
       int n = 0;
        try {
            String req ="SELECT COUNT(questions.Question_id) nbre FROM questions, réponses  WHERE questions.Question_id=réponses.Question_id and questions.type='Rate' and réponses.réponse='1.0' and Questions.Question_id="+q_id+ " GROUP by questions.Question_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
               n=rs.getInt("nbre");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;}
       
         
       public int questRate2(int q_id) {
       int n = 0;
        try {
            String req ="SELECT COUNT(questions.Question_id) nbre FROM questions, réponses  WHERE questions.Question_id=réponses.Question_id and questions.type='Rate' and réponses.réponse='2.0' and Questions.Question_id="+q_id+ " GROUP by questions.Question_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
               n=rs.getInt("nbre");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;}
       
         
       public int questRate3(int q_id) {
       int n = 0;
        try {
            String req ="SELECT COUNT(questions.Question_id) nbre FROM questions, réponses  WHERE questions.Question_id=réponses.Question_id and questions.type='Rate' and réponses.réponse='3.0' and Questions.Question_id="+q_id+ " GROUP by questions.Question_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
               n=rs.getInt("nbre");
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;}
       
         
       public int questRate4(int q_id) {
       int n = 0;
        try {
            String req ="SELECT COUNT(questions.Question_id) nbre FROM questions, réponses  WHERE questions.Question_id=réponses.Question_id and questions.type='Rate' and réponses.réponse='4.0' and Questions.Question_id="+q_id+ " GROUP by questions.Question_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
               n=rs.getInt("nbre");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;}
       
         
       public int questRate5(int q_id) {
       int n = 0;
        try {
            String req ="SELECT COUNT(questions.Question_id) nbre FROM questions, réponses  WHERE questions.Question_id=réponses.Question_id and questions.type='Rate' and réponses.réponse='5.0' and Questions.Question_id="+q_id+ " GROUP by questions.Question_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
               n=rs.getInt("nbre");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;}
       
       
       
       
       
        
         
         
    }
        
        
         
         
    
    
    

  