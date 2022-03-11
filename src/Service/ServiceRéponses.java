/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Questions;
import Entities.Réponses;
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
public class ServiceRéponses implements IService<Réponses> {
      Connection cnx;

    public ServiceRéponses() {
        cnx = MyDB.getInstance().getConnection();
    }


    public void ajout(Réponses t) {
        try {
            String req = "insert into Réponses (réponse,Question_id) values"
                    + " ( '" + t.getRéponse() + "', '" + t.getQuestions().getQuestion_id() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRéponses.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void modifier(Réponses t) {
        try {
            String req = "update Réponses set réponse = ?, Question_id=? where réponse_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getRéponse());
            ps.setInt(2, t.getQuestions().getQuestion_id());
            ps.setInt(3, t.getRéponses_id());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRéponses.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
 
    public void supprimer(int réponses_id) {
        try {
            String req = "delete from Réponses where réponses_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, réponses_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRéponses.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public List<Réponses> afficher(Questions q) {
        List<Réponses> list = new ArrayList<>();
        try {
            String req ="select * from Réponses, Questions where Réponses.Question_id ="+q.getQuestion_id()+" and Questions.Question_id = Réponses.Question_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Questions q1 = new Questions();
                Réponses r = new Réponses(q1);
                r.setRéponses_id(rs.getInt(1));
                r.setRéponse(rs.getString("réponse"));
                r.setQuestion_id(rs.getInt("Question_id"));
                r.getQuestions().setQuestion_id(rs.getInt("Question_id"));
                r.getQuestions().setQuestion(rs.getString("Question"));
               
             
                list.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     
     public ObservableList<Réponses> afficher() { 
             ObservableList<Réponses> list = FXCollections.observableArrayList();
             
              try {
            String req ="select * from Réponses, Questions where Questions.Question_id = Réponses.Question_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Questions q1 = new Questions();
                Réponses r = new Réponses(q1);
                r.setRéponses_id(rs.getInt(1));
                r.setRéponse(rs.getString("réponse"));
                r.setQuestion_id(rs.getInt("Question_id"));
                r.getQuestions().setQuestion_id(rs.getInt("Question_id"));
                r.getQuestions().setQuestion(rs.getString("Question"));
               
             
                list.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void Create(Réponses t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(Réponses t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Réponses> Read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
               
    
}
