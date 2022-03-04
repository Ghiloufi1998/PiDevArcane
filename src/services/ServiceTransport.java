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
import entities.Transport;
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
public class ServiceTransport {
   private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    Connection conn;

    public ServiceTransport() {
        conn = MyDB.getInstance().getConnection();
    }
 
    public void ajout(Transport h) {

        String req = "insert into Transport (Type,Description,Disponibilité,Prix,Image,Hebergement_id) values "
                + "('" + h.getType()+ "','" + h.getDescription()+ "','" + h.getDisponibilité()+ "','" + h.getPrix()+ "','" + h.getImage()+ "','" + h.getHebergement().getHebergement_id()+ "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("transport ajouté avec succes");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
   public void ajoutt(Transport h) {

        String req = "insert into Trasnportt (type,description,disponiblité,Prix) values "
                + "('" + h.getType()+ "','" + h.getDescription()+ "','" + h.getDisponibilité()+ "','" + h.getPrix()+ "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("transport ajouté avec succes");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
   
    
    public void modifier(Transport h) {
        
        String requeteUpdate = "UPDATE  `Transport` set `Disponibilité`='" + h.getDisponibilité()+ "',`Prix`='" + h.getPrix()+ "',`Type`='" + h.getType()+ "',`image`='" + h.getImage() + "',`description`='" + h.getDescription() + "' where `Hebergement_id`='" + h.getHebergement().getHebergement_id()+ "' ";

        try {
            ste = conn.createStatement();
             ste.executeUpdate(requeteUpdate);
              System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void supprimer(int id) {
        String Req = " delete from Transport where Transport_id="+id+" ";
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
    
    public List listHebergement(int id) {
        String req = "SELECT Hebergement.Description,Hebergement.Type,Hebergement.Disponibilité,Hebergement.Adresse,hebergement.Image,Hebergement.Prix, FROM Transport INNER JOIN Hebergement ON Transport.Hebergement_id=Hebergement_id where Transport.Transport_id ='" + id + "' ";

        List<Hebergement> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Hebergement(rs.getString("Description"), rs.getString("Type"), rs.getInt("Disponibilite"), rs.getString("Adresse"), rs.getString("Image"),  rs.getInt("Prix")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
     public List<Transport> afficher() {
        List<Transport> list = new ArrayList<>();
        try {
            String req ="select * from Transport , Hebergement where Transport.Hebergement_id=Hebergement.Hebergement_id ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Hebergement h = new Hebergement();
                Transport t = new Transport(h);
                t.setTransport_id(rs.getInt(1));
                t.setDescription(rs.getString("Description"));
                t.setDisponibilité(rs.getInt("Disponibilité"));
                t.setImage(rs.getString("Image"));
                t.setPrix(rs.getInt("Prix"));
                t.setType(rs.getString("Type"));
                t.setHebergement_id(rs.getInt("Hebergement.Hebergement_id"));
                t.getHebergement().setHebergement_id(rs.getInt("Hebergement.Hebergement_id"));
                t.getHebergement().setHebergement_id(rs.getInt("Hebergement_id"));
                t.getHebergement().setAdresse(rs.getString("Hebergement.Adresse"));
                t.getHebergement().setDescription(rs.getString("Hebergement.Description"));
                t.getHebergement().setDisponibilité(rs.getInt("Hebergement.Disponibilité"));
                t.getHebergement().setImage(rs.getString("Hebergement.Image"));
                t.getHebergement().setType(rs.getString("Hebergement.Type"));
                t.getHebergement().setPrix(rs.getInt("Hebergement.Prix"));
                list.add(t);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTransport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<Transport> afficcher() {
        List<Transport> list = new ArrayList<>();
        try {
            String req ="select * from Trasnsportt , Hebergement where Transport.Hebergement_id=Hebergement.Hebergement_id ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Hebergement h = new Hebergement();
                Transport t = new Transport(h);
                t.setTransport_id(rs.getInt(1));
                t.setDescription(rs.getString("Description"));
                t.setDisponibilité(rs.getInt("Disponibilité"));
                t.setImage(rs.getString("Image"));
                t.setPrix(rs.getInt("Prix"));
                t.setType(rs.getString("Type"));
                
                list.add(t);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTransport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public List<Transport> FindByType(String D){
                List<Transport> list = new ArrayList<>();
                try {
            String req ="select * from Transport where Upper(Type)= '"+D.toUpperCase()+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Transport t = new Transport();
                t.setTransport_id(rs.getInt(1));
                t.setDescription(rs.getString("Description"));
                t.setDisponibilité(rs.getInt("Disponibilité"));
                t.setImage(rs.getString("Image"));
                t.setPrix(rs.getInt("Prix"));
                t.setType(rs.getString("Type"));
                list.add(t);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
   public int nbrv(Transport v) {
        
        int x=0;
        try {
             String req ="select COUNT(*) n from Transport where Upper(Description) = '"+v.getDescription().toUpperCase()+"' and prix ="+v.getPrix();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
        while (rs.next()){    
         x=rs.getInt("n");
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    }
    
    
    

