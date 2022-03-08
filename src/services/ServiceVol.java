/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

//import entities.Personne;
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
        cnx =  new MyDB().getConn();
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

   // @Override
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
    
    

//@Override
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
//select Destination
       public List<String> afficherdestiantaion() {
        List<String> list = new ArrayList<>();
        try {
            String req ="select Destination from Vol";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
            
               String x =rs.getString("Destination");
                list.add(x);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }    
       //client
       public ObservableList<Vol> affichervol() {
        ObservableList<Vol> list = FXCollections.observableArrayList();
        try {
            String req ="select image,Départ,prix from vol";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                  
               
               list.add(new Vol(rs.getString(1),rs.getString(2),rs.getInt(3))); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }    
       public List<String> afficherprix() {
        List<String> list = new ArrayList<>();
        try {
            String req ="select prix from Vol";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
              
                String y=rs.getString("prix");
               
                
                 list.add(y);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVol.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }    
        public int vol (String x){
     int y=0;
        try {

            String requete = "SELECT Vol_id From vol where Départ='"+x+"'";

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
    @Override
    public void insert(Vol t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vol> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Vol t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
     
     
}
