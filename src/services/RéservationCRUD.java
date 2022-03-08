/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Hebergement;
import entities.Réservation;
import entities.User;
import entities.Vol;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author bensa
 */
public class RéservationCRUD implements IService<Réservation> {

    // reservation vol 
    public void reservavationV(Réservation r) {
        try {
            String requete = " INSERT INTO reservation (Date_Deb,Date_Fin,Type,Nbr_adultes,Nbr_enfants,destination,Hebergement_id,vol_ID,ID_User) VALUES "
                    + "(?,?,?,?,?,?,NULL,?,?);";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(requete);
            ps.setDate(1, new java.sql.Date(r.getDate_deb().getTime()));
            ps.setDate(2, new java.sql.Date(r.getDate_deb().getTime()));
            ps.setString(3, r.getType());
            ps.setInt(4, r.getNbr_adultes());
            ps.setInt(5, r.getNbr_enfants());
            ps.setString(6,r.getDestination());
            ps.setInt(7, r.getVol().getVol_id());
            ps.setInt(8, r.getUser().getID_user());
            ps.executeUpdate();
            System.out.println("votre reservation avec vol ajoutée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    // reservation hebergement
    public void reservavationH(Réservation r) {
        try {
            String requete = " INSERT INTO reservation (Date_Deb,Date_Fin,Type,Nbr_adultes,Nbr_enfants,destination,Hebergement_id,vol_ID,ID_User) VALUES "
                    + "(?,?,?,?,?,?,?,NULL,?);";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(requete);
            ps.setDate(1, new java.sql.Date(r.getDate_deb().getTime()));
            ps.setDate(2, new java.sql.Date(r.getDate_deb().getTime()));
            ps.setString(3, r.getType());
            ps.setInt(4, r.getNbr_adultes());
            ps.setInt(5, r.getNbr_enfants());
            ps.setString(6,r.getDestination());
            ps.setInt(7, r.getHebergement().getHebergement_id());
            ps.setInt(8, r.getUser().getID_user());
            ps.executeUpdate();
            System.out.println("votre reservation avec Hebergement ajoutée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    //reservation date debut fin destination 
    public void insertDes(Réservation r){
         try {
            String requete = " INSERT INTO reservation (Date_Deb,Date_Fin,Type,Nbr_adultes,Nbr_enfants,destination,Hebergement_id,vol_ID,ID_User) VALUES "
                    + "(?,?,?,?,?,?,NULL,NULL,NULL);";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(requete);
             ps.setDate(1, new java.sql.Date(r.getDate_deb().getTime()));
            ps.setDate(2, new java.sql.Date(r.getDate_deb().getTime()));
            ps.setString(3, r.getType());
            
          
              ps.setInt(4,r.getNbr_adultes());
                ps.setInt(5,r.getNbr_enfants());
                ps.setString(6, r.getDestination());
           
            ps.executeUpdate();
            System.out.println("votre reservation ajoutée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    // resevervation hebrgement vol

    @Override
    public void insert(Réservation r) {
        try {
            String requete = " INSERT INTO reservation (Date_Deb,Date_Fin,Type,Nbr_adultes,Nbr_enfants,Hebergement_id,vol_ID,ID_User) VALUES "
                    + "(?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(requete);
             ps.setDate(1, new java.sql.Date(r.getDate_deb().getTime()));
            ps.setString(2, String.valueOf(r.getDate_deb()));
            ps.setString(3, r.getType());
            ps.setInt(4, r.getNbr_adultes());
            ps.setInt(5, r.getNbr_enfants());
            ps.setString(6,r.getDestination());
            ps.setInt(7, r.getHebergement().getHebergement_id());
            ps.setInt(8, r.getVol().getVol_id());
            ps.setInt(9, r.getUser().getID_user());
            ps.executeUpdate();
            System.out.println("votre reservation ajoutée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    // update id vol 
    public void updateIdVol(int x) {
        try {
            String requete = " UPDATE reservation SET vol_ID=?  order by  rev_ID desc LIMIT 1";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(requete);
            ps.setInt(1, x);
            ps.executeUpdate();
            System.out.println("votre vol modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    // update id heb
    public void updateIdHeb(int x) {
        try {
            String requete = " UPDATE reservation SET Hebergement_id=? order by  rev_ID desc LIMIT 1 ";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(requete);
            ps.setInt(1, x);

            ps.executeUpdate();
            System.out.println("votre Hebergement modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    //update destination
     public void updatedes(Réservation r) {
        try {
            String requete = " UPDATE reservation SET Destination=? WHERE rev_ID=?; ";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(requete);
            ps.setString(1, r.getDestination());
            ps.setInt(2, r.getID_rev());

            ps.executeUpdate();
            System.out.println("votre destination modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     public ObservableList<Réservation> Rall(){
        ObservableList<Réservation> list = FXCollections.observableArrayList();
        try {
            String req ="select rev_ID,Date_Deb,Date_Fin,Nbr_adultes,Nbr_enfants,Type,Hebergement_id,vol_ID from reservation  ";
            Statement st =  new MyDB().getConn().createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Réservation p = new Réservation();
                p.setID_rev(rs.getInt(1));
                p.setDate_deb(rs.getDate("Date_Deb"));
                p.setDate_fin(rs.getDate("Date_Fin"));
                p.setType(rs.getString("Type"));
                p.setNbr_adultes(rs.getInt("Nbr_adultes"));
                p.setNbr_enfants(rs.getInt("Nbr_enfants"));  
               
               list.add(p); 
            }
            
        } catch (SQLException ex) {
              System.err.println(ex.getMessage());
        }
        return list;
    }    

    @Override
    public ObservableList<Réservation> readAll() {
        ObservableList<Réservation> list = FXCollections.observableArrayList();
        try {

            String requete = "SELECT * From reservation ,user,hebergement,vol where reservation.Hebergement_id=hebergement.Hebergement_id AND reservation.vol_ID=vol.Vol_id ;";

            Statement st = new MyDB().getConn().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                User u = new User();
                Hebergement h =new  Hebergement();
                Vol v =new Vol();
                Réservation p = new Réservation();

                p.setID_rev(rs.getInt("rev_ID"));
                p.setDate_deb(rs.getDate("Date_Deb"));
                p.setDate_fin(rs.getDate("Date_Fin"));
                p.setType(rs.getString("Type"));
                p.setNbr_adultes(rs.getInt("Nbr_adultes"));
                p.setNbr_enfants(rs.getInt("Nbr_enfants"));
//                p.getHebergement().setHebergement_id(rs.getInt("Hebergement_id"));
//                p.getHebergement().setDescription(rs.getString("Description"));
//                p.getHebergement().setType(rs.getString("Type"));
//                p.getHebergement().setDisponibilité(rs.getInt("Disponibilité"));
//                p.getHebergement().setAdresse(rs.getString("Adresse"));
//                p.getHebergement().setImage(rs.getString("Image"));
//                p.getVol().setVol_id(rs.getInt("vol_ID"));
//                p.getVol().setDestination(rs.getString("Destination"));
//                p.getVol().setDépart(rs.getString("Départ"));
//                p.getVol().setDépart(rs.getString("Image"));
//                p.getUser().setID_user(rs.getInt("user.Id_user"));
//                p.getUser().setNom(rs.getString("Nom"));

                list.add(p);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public void update(Réservation new_r) {
        try {
            String requete = " UPDATE reservation SET Date_Deb=?,Date_Fin=?"
                    + ",Type=?,Nbr_adultes=?,"
                    + "Nbr_enfants=? WHERE rev_ID=?; ";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(requete);
            ps.setDate(1, new java.sql.Date(new_r.getDate_deb().getTime()));
            ps.setDate(2, new java.sql.Date(new_r.getDate_deb().getTime()));
            ps.setString(3, new_r.getType());
            ps.setInt(4, new_r.getNbr_adultes());
            ps.setInt(5, new_r.getNbr_enfants());
            ps.setInt(6, new_r.getID_rev());
       

            ps.executeUpdate();
            System.out.println("votre reservation modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int t) {
        try {
            String requete = " DELETE FROM reservation "
                    + "WHERE "
                    + "rev_ID=?;";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(requete);
            ps.setInt(1, t);

            ps.executeUpdate();
            System.out.println("votre reservation supprimée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    // select prix vol
    public void prixvol() {
        try {

            String requete = "SELECT prix From Réservation,Vol,user where user.ID_user = Réservation.rev_ID;";

            Statement st = new MyDB().getConn().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
             System.out.println(rs.getDouble("prix"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void prixHébergement() {
        try {

            String requete = "SELECT prix From Réservation,Hebergement,user"
                    + " where user.ID_user = Réservation.rev_ID;";

            Statement st = new MyDB().getConn().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                System.out.println(rs.getDouble("prix"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void prixHV() {
        try {

            String requete = "SELECT SUM(Hebergement.prix)+SUM(vol.prix) From Réservation,Hebergement,vol"
                    + " where user.ID_user= Réservation.rev_ID";

            Statement st = new MyDB().getConn().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                System.out.println(rs.getDouble("prix"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void prixVO() {
        try {

            String requete = "SELECT SUM(Voyageorganise.prix) From Réservation,voyageorganise where voyageorganise.Voyage_id = Réservation.rev_ID";

            Statement st = new MyDB().getConn().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                System.out.println(rs.getDouble("prix"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     public int idrev (String x){
     int y=0;
        try {

            String requete = "SELECT rev_ID From reservation where Départ='"+x+"'";

            Statement st = new MyDB().getConn().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
              
               
               y=rs.getInt("Vol_id");
              
                

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       
        return y;
     
    }

    
}
