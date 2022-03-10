/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entities.Cours;
import Entities.Guide;
import Entities.Exercices;
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
 * @author Ghiloufi
 */
public class ExerciceService implements IService<Exercices> {
Connection cnx;

    public ExerciceService() {
        cnx = MyDB.getInstance().getConnection();
    }
    
    @Override
    public void Create(Exercices t) {
       try {
            String req = "INSERT INTO exercices (Type,Question,Reponse,Hint,ID_crs) VALUES"
                    + " ( '" + t.getType()+ "','" + t.getQuestion()+ "','" + t.getReponse()+ "','"+ t.getHint()+ "','" + t.getCours().getID_crs()+ "')";
            Statement st = cnx.createStatement();
             System.out.println("hehehehehe");
            st.executeUpdate(req);
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Update(Exercices t) {
      try {
            String req = "update Exercices set Type = ? , Question = ?, Reponse = ?, Hint = ?  , id_crs= ? where ID_ex = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getType());
            ps.setString(2, t.getQuestion());
            ps.setString(3, t.getReponse());
            ps.setString(4, t.getHint());
            ps.setInt(5, t.getCours().getID_crs());
            ps.setInt(6, t.getID_ex());
            ps.executeUpdate();
              System.out.println("donenenenenen");
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Delete(int id) {
         try {
            String req = "delete from exercices where ID_ex = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
             System.out.println("deleteddd");
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Exercices> Read() {
      ObservableList<Exercices> list = FXCollections.observableArrayList();
        try {
            String req ="select * from exercices,cours where exercices.ID_crs = cours.ID_crs";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                  Cours c = new Cours();
                Exercices o = new Exercices(c);
              
                o.setID_ex(rs.getInt("id_ex"));
                o.setType(rs.getString("Type"));
                o.setQuestion(rs.getString("Question"));
                o.setHint(rs.getString("Hint"));
                o.setReponse(rs.getString("Reponse"));
             
               
                o.setID_crs(rs.getInt("ID_crs"));
                list.add(o);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public Exercices get(int id_ex) {
        
        Exercices e = new Exercices();
        try {
             String req ="select * from Exercices where id_ex = "+id_ex;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
        while (rs.next()){    
         e.setQuestion(rs.getString(("Question")));
         e.setReponse(rs.getString("Reponse"));
         e.setHint(rs.getString("Hint"));
         e.setType(rs.getString("Type"));
         e.setID_crs(rs.getInt("id_ex"));
         e.setID_ex(rs.getInt("id_ex"));
        
        }
        } catch (SQLException ex) {
            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }
         public ObservableList<String> GetAllTitleCours() {
         ObservableList<String> list = FXCollections.observableArrayList();
        try {
            String req ="select titre from cours";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                list.add(rs.getString("Titre"));
           //    list.add(rs.getInt("id_g"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
          public ObservableList<Exercices> GetQuestionByidcrs(int id_crs) {
      ObservableList<Exercices> list = FXCollections.observableArrayList();
        try {
            String req ="select * from exercices,cours where exercices.ID_crs = cours.ID_crs and exercices.ID_crs='"+ id_crs+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                  Cours c = new Cours();
                Exercices o = new Exercices(c);
              
                o.setID_ex(rs.getInt("id_ex"));
                o.setType(rs.getString("Type"));
                o.setQuestion(rs.getString("Question"));
                o.setReponse(rs.getString("Reponse"));
                o.setHint(rs.getString("Hint"));
                o.setID_crs(rs.getInt("ID_crs"));
                list.add(o);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
            public int nbrv(Exercices c) {
        
        int x=0;
        try {
             String req ="select COUNT(*) n from exercices where Upper(question) = '"+c.getQuestion().toUpperCase()+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
        while (rs.next()){    
         x=rs.getInt("n");
        }
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

         

    
}
