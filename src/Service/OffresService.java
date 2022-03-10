/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entities.Offres;
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
import utils.Mailing;
import utils.MyDB;

/**
 *
 * @author Ghiloufi
 */
public class OffresService implements IService<Offres>{
 Connection cnx;

    public OffresService() {
        cnx = MyDB.getInstance().getConnection();
    }
    
    @Override
    public void Create(Offres o) {
         try {
            String req = "INSERT INTO offres (Description,Nb_point_req,Destination,Pourcentage_red) VALUES"
                    + " ( '" + o.getDescription()+ "','" + o.getNb_points_req() + "','" + o.getDestination() + "','" +o.getPourcentage_red()+ "')";
            Statement st = cnx.createStatement();
             System.out.println("hehehehehe");
            st.executeUpdate(req);
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
         UserService us = new UserService();
         Mailing m = new Mailing();
     try {
         String body="Bonjour Mme/mr "+ us.getUserlogged().getName()+"\n Des nouveau offres pour "+o.getDestination()+" sont arjoutées récement! \n consulter cette offre :\n"+o.getDescription();
         m.sendEmail(us.getUserlogged().getEmail(), "Nouveaux Offres pour" + o.getDestination(), body);
     } catch (SQLException ex) {
         Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    @Override
    public void Update(Offres t) {
          try {
            String req = "update offres set Description = ? , Nb_point_req = ? , Destination = ? , Pourcentage_red = ? where ID_off = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getDescription());
            ps.setInt(2, t.getNb_points_req());
            ps.setString(3, t.getDestination());
            ps.setInt(4, t.getPourcentage_red());
            ps.setInt(5, t.getID());
            ps.executeUpdate();
              System.out.println("donenenenenen");
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    @Override
    public void Delete(int id) {
         try {
            String req = "delete from offres where ID_off = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
             System.out.println("deleteddd");
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    @Override
    public ObservableList<Offres> Read() {
        ObservableList<Offres> list = FXCollections.observableArrayList();
        try {
            String req ="select * from offres";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Offres o = new Offres();
                o.setID(rs.getInt("ID_off"));
                o.setDescription(rs.getString("Description"));
                o.setDestination(rs.getString("Destination"));
                o.setNb_points_req(rs.getInt("Nb_point_req"));
                o.setPourcentage_red(rs.getInt("Pourcentage_red"));
                list.add(o);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
 public Offres get(int id_off) {
        
        Offres o = new Offres();
        try {
             String req ="select * from Offres where id_off = "+id_off;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
        while (rs.next()){    
        o.setID(rs.getInt("ID_off"));
                o.setDescription(rs.getString("Description"));
                o.setDestination(rs.getString("Destination"));
                o.setNb_points_req(rs.getInt("Nb_point_req"));
                o.setPourcentage_red(rs.getInt("Pourcentage_red"));
        }
        } catch (SQLException ex) {
            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }
      public int nbrv(Offres c) {
        
        int x=0;
        try {
             String req ="select COUNT(*) n from offres where Upper(destination) = '"+c.getDestination().toUpperCase()+"' and Pourcentage_red ="+c.getPourcentage_red()+" and Nb_point_req ="+c.getNb_points_req()+"";
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

       public void UpdatePriceFact(int id, String Destination) {
          try {
            String req = "UPDATE facture set facture.Montant_ttc = ( facture.Montant_ttc - (facture.Montant_ttc*( select offres.Pourcentage_red from reservation,offres,user,facture\n" +
" WHERE reservation.rev_ID=facture.rev_ID and offres.ID_off=user.id_offre and reservation.ID_user=user.id) /100)) \n" +
"  WHERE facture.ID_fac=(select facture.ID_fac from reservation,offres,user,facture WHERE reservation.rev_ID=facture.rev_ID \n" +
"and offres.ID_off=user.id_offre and reservation.ID_user=user.id and user.id = ? and UPPER(?)=UPPER(reservation.Destination));";
            PreparedStatement ps = cnx.prepareStatement(req);
               ps.setInt(1,id );
               ps.setString(2,Destination );
            ps.executeUpdate();
              System.out.println("Offre appliqué avec succès");
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
}
