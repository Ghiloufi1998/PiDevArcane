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
    public List<Offres> Read() {
        List<Offres> list = new ArrayList<>();
        try {
            String req ="select * from offres";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Offres o = new Offres();
                o.setID(rs.getInt(1));
                o.setDescription(rs.getString("Description"));
                o.setDestination(rs.getString("Destination"));
                o.setNb_points_req(rs.getInt(1));
                o.setPourcentage_red(rs.getInt(1));
                list.add(o);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

   
    
}
