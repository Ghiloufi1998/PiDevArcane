/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Hebergement;
import entities.Personne;
import entities.Transport;
import entities.Vol;
import entities.Voyageorganise;
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
public class ServiceVoyageorganise implements IService<Voyageorganise> {
   
    Connection cnx;
    public ServiceVoyageorganise() {
        cnx = MyDB.getInstance().getConnection();
    }
    public void ajout(Voyageorganise o ) {
        try {
            String req = "insert into Voyageorganise (Description,Image,Prix,Nbre_Places,Vol_id,Transport_id,Hebergement_id) values"
                    + " ( '" + o.getDescription() + "','" + o.getImage()+ "','" + o.getPrix()+ "','" + o.getNbre_Places()+ "','" + o.getVol().getVol_id()+ "','" + o.getTransport().getTransport_id() + "','" + o.getHebergement().getHebergement_id() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void modifier(Voyageorganise o) {
        try {
            String req = "update Voyageorganise set  Description = ? , Image = ?, Prix = ?, Nbre_Places = ?, Vol_id = ?, Transport_id = ?, Hebergement_id = ? where Voyage_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, o.getDescription());
            ps.setString(2, o.getImage());
            ps.setInt (3, o.getPrix());
            ps.setInt (4, o.getNbre_Places());
            ps.setInt (5, o.getVol().getVol_id());
            ps.setInt (6, o.getTransport().getTransport_id());
            ps.setInt (7, o.getHebergement().getHebergement_id());
            ps.setInt(8, o.getVoyage_id());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void supprimer(int id) {
        try {
            String req = "delete from Voyageorganise where Voyage_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Voyageorganise> afficher() {
        List<Voyageorganise> list = new ArrayList<>();
        try {
            String req ="select * from Voyageorganise , vol ,Hebergement ,Transport  where Voyageorganise.Vol_id=Vol.Vol_id and Voyageorganise.Hebergement_id=Hebergement.Hebergement_id and Voyageorganise.Transport_id=Transport.Transport_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
           
           
            while(rs.next()){
                
                Vol v1 = new Vol();
                Hebergement h1= new Hebergement();
                Transport t = new Transport(h1);
                Voyageorganise v = new Voyageorganise(v1,h1,t);
                
                v.setVoyage_id(rs.getInt(1));
                //v.setDestination(rs.getString("Destination"));
                v.setDescription(rs.getString("Description"));
                v.setPrix(rs.getInt("prix"));
                v.setImage(rs.getString("Image"));
                v.setVol_id(rs.getInt("Vol.Vol_id"));
                v.setTransport_id(rs.getInt("Transport.Transport_id"));
                v.setHebergement_id(rs.getInt("Hebergement.Hebergement_id"));
                //v.setNbre_Places(rs.getInt("Nbre_Places"));
                v.getVol().setVol_id(rs.getInt("Vol_id"));
                v.getVol().setDestination(rs.getString("Vol.Destination"));
                v.getVol().setDépart(rs.getString("Départ"));
                v.getVol().setImage(rs.getString("Vol.Image"));
                //v.getHebergement().setHebergement_id(rs.getInt("Hebergement_id"));
                //v.getHebergement().setAdresse(rs.getString("Adresse"));
                //v.getHebergement().setDescription(rs.getString("Hebergement.Description"));
                //v.getHebergement().setDisponibilité(rs.getInt("Disponibilité"));
                //v.getHebergement().setImage(rs.getString("Hebergement.Image"));
                //v.getHebergement().setType(rs.getString("Type"));
                v.getTransport().setTransport_id(rs.getInt("Transport_id"));
                v.getTransport().setDescription(rs.getString("Transport.Description"));
                v.getTransport().setDisponibilité(rs.getInt("Transport.Disponibilité"));
                v.getTransport().setImage(rs.getString("Transport.Image"));
                v.getTransport().setPrix(rs.getInt("Transport.Prix"));
                v.getTransport().setType(rs.getString("Transport.Type"));
                v.getTransport().getHebergement().setHebergement_id(rs.getInt("Hebergement_id"));
                v.getTransport().getHebergement().setAdresse(rs.getString("Adresse"));
                v.getTransport().getHebergement().setDescription(rs.getString("Hebergement.Description"));
                v.getTransport().getHebergement().setDisponibilité(rs.getInt("Disponibilité"));
                v.getTransport().getHebergement().setImage(rs.getString("Hebergement.Image"));
                v.getTransport().getHebergement().setType(rs.getString("Type"));
                
                list.add(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
    
}
