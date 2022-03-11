/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entities.Vol;
import Entities.Voyageorganise;
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
 * @author Acer
 */
public class ServiceVol implements IService<Vol> {
    
    Connection cnx;

    public ServiceVol() {
        cnx = MyDB.getInstance().getConnection();
    }
   
    public void ajout(Vol t) {
        try {
            String req = "insert into Vol (Destination,Départ,Image) values"
                    + " ( '" + t.getDestination() + "', '" + t.getDépart() + "','" + t.getImage() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServiceVol.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    public void supprimer(int id) {
        try {
            String req = "delete from Vol where Vol_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Vol get(int vol_id) {
        
        Vol v = new Vol();
        try {
             String req ="select * from Vol where Vol_id = "+vol_id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
        while (rs.next()){    
         v.setVol_id(rs.getInt(("Vol_id")));
         v.setDestination(rs.getString("Destination"));
         v.setDépart(rs.getString("Départ"));
         v.setImage(rs.getString("Image"));
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    
    public int nbrv(int vol_id) {
        
        int x=0;
        try {
             String req ="select COUNT(*) n from Vol where Vol_id = "+vol_id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
        while (rs.next()){    
         x=rs.getInt("n");
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    
    


    public ObservableList<Vol> afficher() {
        ObservableList<Vol> list = FXCollections.observableArrayList();
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
            Logger.getLogger(ServiceVol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }    
    
    public List<Vol> FindByDestination(String Dest){
                List<Vol> list = new ArrayList<>();
                try {
            String req ="select * from Vol where Upper(Destination)= '"+Dest.toUpperCase()+"'";
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
            Logger.getLogger(ServiceVol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public List<Vol> FindByDepart(String D){
                List<Vol> list = new ArrayList<>();
                try {
            String req ="select * from Vol where Upper(Départ)= '"+D.toUpperCase()+"'";
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
            Logger.getLogger(ServiceVol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     
      public ObservableList<Integer> GetAllvol() {
           ObservableList<Integer> list = FXCollections.observableArrayList();
        try {
             String req ="select Vol_id from Vol ";
             Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()){
                
                list.add((rs.getInt("Vol_id")));
                

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public Double getx(int vol_id) {
         Double v =0.0;
        
        try {
             String req ="select x from Vol where Vol_id = "+vol_id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
        while (rs.next()){    
         v=rs.getDouble("x");
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
     public Double gety(int vol_id) {
         Double v =0.0;
        
        try {
             String req ="select y from Vol where Vol_id = "+vol_id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
        while (rs.next()){    
         v=rs.getDouble("y");
        }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    @Override
    public void Create(Vol t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(Vol t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vol> Read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
