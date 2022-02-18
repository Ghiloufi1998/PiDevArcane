/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Cours;
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
public class CoursService implements IService<Cours>{
Connection cnx;

    public CoursService() {
        cnx = MyDB.getInstance().getConnection();
    }
    @Override
    public void Create(Cours t) {
        try {
            String req = "INSERT INTO Cours (Type,Contenu,ID_g) VALUES"
                    + " ( '" + t.getType()+ "','" + t.getContenu()+ "','" + t.getGuide().getID_g()+ "')";
            Statement st = cnx.createStatement();
            System.out.println("hehehehehe2");
            st.executeUpdate(req);
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Update(Cours t) {
         try {
            String req = "update cours set Type = ? , Contenu = ? where ID_crs = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getType());
            ps.setString(1, t.getContenu());
            ps.setInt(2, t.getID_crs());
            ps.executeUpdate();
              System.out.println("donenenenenen");
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Delete(int id) {
         try {
            String req = "delete from cours where ID_crs = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
             System.out.println("deleteddd");
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Cours> Read() {
    List<Cours> list = new ArrayList<>();
        try {
            String req ="select * from cours,guide where cours.Id_g = guide.ID_g";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Guide g =new Guide();
                Cours o = new Cours(g);

                o.setID_crs(rs.getInt(1));
                o.setType(rs.getString("Type"));
                o.setContenu(rs.getString("Contenu"));
                o.getGuide().setID_g(rs.getInt("guide.ID_g"));
                o.getGuide().setLevel(rs.getInt("Level"));
                o.getGuide().setPays(rs.getString("Pays"));
                o.getGuide().setId_vol(rs.getInt("Id_vol"));
                o.setID_g(rs.getInt("guide.ID_g"));
                list.add(o);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
