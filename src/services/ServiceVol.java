/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Personne;
import entities.Vol;
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


/**
 *
 * @author Acer
 */
public class ServiceVol implements IService<Vol> {
    
    Connection cnx;

    public ServiceVol() {
        cnx = MyDB.getInstance().getConnection();
    }
   
    @Override
    public void ajout(Vol t) {
        try {
            String req = "insert into Vol (Destination,Départ,Image) values"
                    + " ( '" + t.getDestination() + "', '" + t.getDépart() + "','" + t.getImage() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void modifier(Vol v) {
        try {
            String req = "update Vol set Destination = ? , Départ = ? , Image = ? where Vol_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, v.getDestination());
            ps.setString(2, v.getDépart());
            ps.setString(3, v.getImage());
            ps.setInt(4, v.getVol_id());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "delete from Vol where Vol_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Vol read(Vol vol) {
        
        Vol v = new Vol();
        try {
             String req ="select * from Vol where Vol_id = "+vol.getVol_id();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
         v.setVol_id(rs.getInt(("Vol_id")));
         v.setDestination(rs.getString("Destination"));
         v.setDépart(rs.getString("Départ"));
         v.setImage(rs.getString("Image"));
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    
    

@Override
    public List<Vol> afficher() {
        List<Vol> list = new ArrayList<>();
        try {
            String req ="select * from Vol";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Vol v = new Vol();
                v.setVol_id(rs.getInt(1));
                v.setDestination(rs.getString("Destination"));
                v.setDépart(rs.getString("Départ"));
                v.setImage(rs.getString("Image"));
                list.add(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }    
    
    
}
