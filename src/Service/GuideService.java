/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Guide;

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
public class GuideService implements  IService<Guide>{
    Connection cnx;
 public GuideService() {
        cnx = MyDB.getInstance().getConnection();
    }
    @Override
    public void Create(Guide t) {
      try {
            String req = "INSERT INTO guide (Pays,Level,id_vol) VALUES"
                    + " ( '" + t.getPays()+ "','" + t.getLevel()+"','" + t.getVol().getVol_id()+ "')";
            Statement st = cnx.createStatement();
             System.out.println("hehehehehe");
            st.executeUpdate(req);
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Update(Guide t) {
        try {
            String req = "update guide set Pays = ? , Level = ? where ID_g = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getPays());
            ps.setInt(2, t.getLevel());
            ps.setInt(3, t.getID_g());
            ps.executeUpdate();
              System.out.println("donenenenenen");
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Delete(int id) {
        try {
            String req = "delete from guide where ID_g = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
             System.out.println("deleteddd");
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Guide> Read() {
         List<Guide> list = new ArrayList<>();
        try {
            String req ="select * from guide";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Guide o = new Guide();
                o.setID_g(rs.getInt(1));
                o.setPays(rs.getString("Pays"));
                o.setLevel(rs.getInt(1));
                list.add(o);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }



   
    
}
