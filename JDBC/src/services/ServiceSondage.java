/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


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
public class ServiceSondage implements IService<Sondage>  {
     Connection cnx;

    public ServiceSondage() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajout(Sondage t) {
        try {
            String req = "insert into Sondage (sujet,catégorie) values"
                    + " ( '" + t.getSujet() + "', '" + t.getCatégorie() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSondage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void modifier(Sondage t) {
        try {
            String req = "update Sondage set sujet = ? , catégorie = ?  where sondage_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getSujet());
            ps.setString(2, t.getCatégorie());
            ps.setInt(3, t.getSondage_id());
           
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSondage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void supprimer(int sondage_id) {
        try {
            String req = "delete from Sondage where sondage_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, sondage_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSondage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Sondage> afficher() {
        ObservableList<Sondage> list = FXCollections.observableArrayList();
        try {
            String req ="select * from Sondage";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Sondage s = new Sondage();
                s.setSondage_id(rs.getInt(1));
                s.setSujet(rs.getString("sujet"));
                s.setCatégorie(rs.getString("catégorie"));
                
               
                list.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSondage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Sondage get(String S) {
        Sondage so = new Sondage();
        
        try {
            String req ="select * from Sondage where Upper(Sondage.Sujet)= '"+S.toUpperCase()+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()){
            so.setSondage_id(rs.getInt("Sondage_id"));
            so.setSujet(rs.getString("Sujet"));
            so.setCatégorie(rs.getString("Catégorie"));
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSondage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return so;
    }
    
    public int nbrs(String s) {
        
        int x=0;
        try {
             String req ="select COUNT(*) n from Sondage where Upper(sujet)= '"+s.toUpperCase()+"'";
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
      public ObservableList<String> afficherSujet() {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            String req ="select Sujet from Sondage";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
              //  Sondage s = new Sondage();
                
                list.add(rs.getString("sujet"));
                
                
               
              //  list.add(s.toString());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSondage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
     
}
    
