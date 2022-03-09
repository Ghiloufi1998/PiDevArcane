/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Guide;
import Entities.Vol;
import com.sun.deploy.uitoolkit.impl.fx.ui.FXAboutDialog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
            String req = "INSERT INTO guide (Titre,Pays,Level,id_vol) VALUES"
                    + " ( '"+ t.getTitre()+ "','" + t.getPays()+ "','" + t.getLevel()+"','" + t.getVol().getVol_id()+ "')";
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
            String req = "update guide set Titre = ?, Pays = ? , Level = ? , id_vol = ? where ID_g = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getTitre());
            ps.setString(2, t.getPays());
            ps.setInt(3, t.getLevel());
            ps.setInt(4, t.getVol().getVol_id());
            ps.setInt(5, t.getID_g());
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
    public ObservableList<Guide> Read() {
         ObservableList<Guide> list = FXCollections.observableArrayList();
        try {
            String req ="select * from guide,vols where guide.id_vol = vols.id_vol";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Vol v = new Vol();
                Guide o = new Guide(v);
                o.setID_g(rs.getInt("id_g"));
                o.setTitre(rs.getString("Titre"));
                o.setPays(rs.getString("Pays"));
                o.setLevel(rs.getInt("Level"));
                o.getVol().setDépart(rs.getString("Depart"));
                o.getVol().setDestination(rs.getString("Destination"));
                o.getVol().setImage(rs.getString("Image"));
                 o.setId_vol(rs.getInt("id_vol"));
                list.add(o);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
   public Guide get(int id_g) {
        
        Guide g = new Guide();
        try {
             String req ="select * from Guide where id_g = "+id_g;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
        while (rs.next()){    
         g.setTitre(rs.getString(("Titre")));
         g.setPays(rs.getString("Pays"));
         g.setLevel(rs.getInt("Level"));
         g.setId_vol(rs.getInt("id_vol"));
         g.setID_g(rs.getInt("id_g"));
        
        }
        } catch (SQLException ex) {
            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g;
    }
public ObservableList<Guide>  getByVolDest(String Destination) throws SQLException{
         ObservableList<Guide> list = FXCollections.observableArrayList();
     try {
            String req ="select * from Guide,vols where Guide.id_vol = vols.id_vol AND ('"+Destination.toUpperCase()+"') = UPPER(guide.Pays)";
            Statement st = cnx.createStatement();
           ResultSet rs = st.executeQuery(req);
        while (rs.next()){  
           Guide g2 = new Guide();
         g2.setID_g(rs.getInt("id_g"));
            g2.setId_vol(rs.getInt("id_vol"));
          g2.setLevel(rs.getInt("Level"));
           g2.setPays(rs.getString("Pays"));
           g2.setTitre(rs.getString("Titre"));
           list.add(g2);
        }  
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
}
public ObservableList<Guide>  getBytitre(String Titre) throws SQLException{
         ObservableList<Guide> list = FXCollections.observableArrayList();
     try {
            String req ="select * from Guide where '"+Titre.toUpperCase()+"' = UPPER(Titre)";
            Statement st = cnx.createStatement();
           ResultSet rs = st.executeQuery(req);
        while (rs.next()){  
           Guide g2 = new Guide();
         g2.setID_g(rs.getInt("id_g"));
            g2.setId_vol(rs.getInt("id_vol"));
          g2.setLevel(rs.getInt("Level"));
           g2.setPays(rs.getString("Pays"));
           g2.setTitre(rs.getString("Titre"));
           list.add(g2);
        }  
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
}
public ObservableList<Guide>  getByPays(String Pays) throws SQLException{
         ObservableList<Guide> list = FXCollections.observableArrayList();
     try {
            String req ="select * from Guide where '"+Pays.toUpperCase()+"' = UPPER(pays)";
            Statement st = cnx.createStatement();
           ResultSet rs = st.executeQuery(req);
        while (rs.next()){  
           Guide g2 = new Guide();
         g2.setID_g(rs.getInt("id_g"));
            g2.setId_vol(rs.getInt("id_vol"));
          g2.setLevel(rs.getInt("Level"));
           g2.setPays(rs.getString("Pays"));
           g2.setTitre(rs.getString("Titre"));
           list.add(g2);
        }  
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
}
   public int nbrv(Guide c) {
        
        int x=0;
        try {
             String req ="select COUNT(*) n from guide where Upper(titre) = '"+c.getTitre().toUpperCase()+"'";
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

}

   
    
