/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Dhia
 */

import entities.Hebergement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

public class ServiceHebergement implements IService<Hebergement>{
private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    Connection conn;

    public ServiceHebergement() {
        conn = MyDB.getInstance().getConnection();
    }

    
    public void ajout(Hebergement h) {

        String req = "insert into hebergement (adresse,image,description,Disponibilité,Type,Prix) values "
                + "('" + h.getAdresse()+ "','" + h.getImage()  + "','" + h.getDescription() + "','" + h.getDisponibilité() + "','" + h.getType() +"','" + h.getPrix() + "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Hebergement ajouté avec succes");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
   
    
    public void modifier(Hebergement h) {
        
        String requeteUpdate = "UPDATE  `Hebergement` set `Disponibilité`='" + h.getDisponibilité()+ "',`adresse`='" + h.getAdresse() + "',`Type`='" + h.getType()+ "',`image`='" + h.getImage() + "',`description`='" + h.getDescription() + "',`Prix`='" + h.getPrix() + "' where `Hebergement_id`='" + h.getHebergement_id()+ "' ";

        try {
            ste = conn.createStatement();
             ste.executeUpdate(requeteUpdate);
              System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void supprimer(int id) {
        String Req = " delete from Hebergement where Hebergement_id="+id+" ";
        try {
            ste = conn.createStatement();
            ste.execute(Req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
 public List readidHebergement() {
        String req = "select id from Hebergement";

        List list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getInt("id"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    public Hebergement get(String id) {
        
        Hebergement v = new Hebergement();
        try {
             String req ="select * from Hebergement where Upper(Description) = '"+id.toUpperCase()+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
        while (rs.next()){    
         v.setHebergement_id(rs.getInt(("Hebergement_id")));
         v.setDescription(rs.getString("Description"));
         v.setDisponibilité(rs.getInt("Disponibilité"));
         v.setImage(rs.getString("Image"));
         v.setPrix(rs.getInt("prix"));
           v.setType(rs.getString("type"));
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    public List afficher() {
        List<Hebergement> list = new ArrayList<>();
        try {
            String req ="select * from Hebergement";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Hebergement v = new Hebergement();
                v.setHebergement_id(rs.getInt("Hebergement_id"));
                v.setAdresse(rs.getString("Adresse"));
                v.setDescription(rs.getString("Hebergement.Description"));
                v.setDisponibilité(rs.getInt("Disponibilité"));
                v.setImage(rs.getString("Hebergement.Image"));
                v.setType(rs.getString("Type"));
                v.setPrix(rs.getInt("Prix"));
                list.add(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    }    

