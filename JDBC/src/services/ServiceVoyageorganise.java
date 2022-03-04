/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Hebergement;
import entities.Personne;
import entities.Stat;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
            String req = "insert into Voyageorganise (Description,Image,Prix,Nbre_Places,Vol_id,Transport_id) values"
                    + " ( '" + o.getDescription() + "','" + o.getImage()+ "','" + o.getPrix()+ "','" + o.getNbre_Places()+ "','" + o.getVol().getVol_id()+ "','" + o.getTransport().getTransport_id() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void modifier(Voyageorganise o) {
        try {
            String req = "update Voyageorganise set  Description = ? , Image = ?, Prix = ?, Nbre_Places = ?, Vol_id = ?, Transport_id = ? where Voyage_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, o.getDescription());
            ps.setString(2, o.getImage());
            ps.setInt (3, o.getPrix());
            ps.setInt (4, o.getNbre_Places());
            ps.setInt (5, o.getVol().getVol_id());
            ps.setInt (6, o.getTransport().getTransport_id());
           // ps.setInt (7, o.getHebergement().getHebergement_id());
            ps.setInt(7, o.getVoyage_id());
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
    //
    public ObservableList<Voyageorganise> afficher() {
       // ObservableList<Voyageorganise> list = new ObservableList<>();
        ObservableList<Voyageorganise> list = FXCollections.observableArrayList();
        try {
            String req ="select * from Voyageorganise , vol ,Hebergement ,Transport  where Voyageorganise.Vol_id=Vol.Vol_id and Transport.Hebergement_id=Hebergement.Hebergement_id and Voyageorganise.Transport_id=Transport.Transport_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
           
           
            while(rs.next()){
                
                Vol v1 = new Vol();
                Hebergement h1= new Hebergement();
                Transport t = new Transport(h1);
                Voyageorganise v = new Voyageorganise(v1,t);
                
                v.setVoyage_id(rs.getInt(1));
                //v.setDestination(rs.getString("Destination"));
                v.setDescription(rs.getString("Description"));
                v.setPrix(rs.getInt("prix"));
                v.setImage(rs.getString("Image"));
                
                v.setVol_id(rs.getInt("Vol.Vol_id"));
                v.setTransport_id(rs.getInt("Transport.Transport_id"));
                //v.setHebergement_id(rs.getInt("Hebergement.Hebergement_id"));
                v.setNbre_Places(rs.getInt("Nbre_Places"));
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
                v.getTransport().getHebergement().setHebergement_id(rs.getInt("Transport.Hebergement_id"));
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

    
        public List<Stat> consulterstat() {
        List list = new ArrayList<>();
        try {
            String req = "SELECT COUNT(*),AVG(prix) FROM voyageorganise ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
           String rq="SELECT COUNT(v.Transport_id) MaxTransport,t.description maxT FROM voyageorganise v,transport t WHERE t.Transport_id=v.Transport_id GROUP by v.Transport_id HAVING COUNT(v.Transport_id) = (   SELECT COUNT(Transport_id) FROM voyageorganise GROUP BY Transport_id ORDER BY COUNT(Transport_id) DESC LIMIT 1 ) ";
           Statement st1 = cnx.createStatement();
           ResultSet rs1 = st1.executeQuery(rq);
           

           String a="SELECT COUNT(vo.vol_id) Maxvol,v.destination maxV FROM voyageorganise vo,vol v WHERE v.vol_id=vo.vol_id GROUP by vo.vol_id HAVING COUNT(vo.vol_id) = (   SELECT COUNT(vol_id) FROM voyageorganise GROUP BY vol_id ORDER BY COUNT(vol_id) DESC LIMIT 1 )";
           Statement st2 = cnx.createStatement();
           ResultSet rs2 = st2.executeQuery(a);

           String b ="SELECT COUNT(t.hebergement_id) Maxheber,h.Description maxH FROM voyageorganise vo,transport t, hebergement h WHERE h.Hebergement_id=t.Hebergement_id AND vo.transport_id=t.transport_id GROUP by t.transport_id HAVING COUNT(t.transport_id) = (   SELECT COUNT(transport_id) FROM voyageorganise GROUP BY transport_id ORDER BY COUNT(transport_id) DESC LIMIT 1 )";
           Statement st3 = cnx.createStatement();
           ResultSet rs3 = st3.executeQuery(b);
           
           
           while ( (rs.next() ) && (rs1.next() ) && (rs2.next() ) && (rs3.next() )  ) {
                Stat s = new Stat();
                s.setCount(rs.getInt("COUNT(*)"));
                s.setAvgprix(rs.getDouble("AVG(prix)"));
                s.setCountt(rs1.getInt("MaxTransport"));
                s.setMaxT(rs1.getString("maxT"));
                s.setCountv(rs2.getInt("Maxvol"));
                s.setMaxv(rs2.getString("maxV"));
                s.setCounth(rs3.getInt("Maxheber"));
                s.setMaxh(rs3.getString("maxH"));
                
                list.add(s);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        
        
        
        public ObservableList<Voyageorganise> expensive(){
//           Hebergement h = new Hebergement();
//            Transport t = new Transport(h);
//            Vol vol = new Vol();
            //Voyageorganise v = new Voyageorganise(vol, t);
        
           ObservableList<Voyageorganise> list = FXCollections.observableArrayList();
        try {
            
            String req = "SELECT v.Description,v.Prix FROM voyageorganise v WHERE v.Prix > (SELECT AVG(prix) FROM voyageorganise)";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
           
           
           while (rs.next() )   {
               
               Voyageorganise v = new Voyageorganise(0,rs.getString("v.Description") , "", rs.getInt("Prix"), 0);
               //v.setDescription(rs.getString("v.Description"));
//             v.setNbre_Places(rs.getInt("v.Nbre_Places"));
//               v.setPrix(rs.getInt("v.Prix"));
                list.add(v);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        }
        
        public ObservableList<Voyageorganise> Cheap(){
//           Hebergement h = new Hebergement();
//            Transport t = new Transport(h);
//            Vol vol = new Vol();
            //Voyageorganise v = new Voyageorganise(vol, t);
        
           ObservableList<Voyageorganise> list = FXCollections.observableArrayList();
        try {
            
            String req = "SELECT v.Description,v.Prix FROM voyageorganise v WHERE v.Prix < (SELECT AVG(prix) FROM voyageorganise)";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
           
           
           while (rs.next() )   {
               
               Voyageorganise v = new Voyageorganise(0,rs.getString("v.Description") , "", rs.getInt("Prix"), 0);
               //v.setDescription(rs.getString("v.Description"));
//             v.setNbre_Places(rs.getInt("v.Nbre_Places"));
//               v.setPrix(rs.getInt("v.Prix"));
                list.add(v);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        }
        
        public int expensivenbr(){
            int res=0;
            
            try {
            
            String req = "SELECT count(*) nbr  FROM voyageorganise  WHERE Prix > (SELECT AVG(prix) FROM voyageorganise) ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
             
          while (rs.next() )   {
            
             res =rs.getInt("nbr"); 
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res ;
        }
        public int Cheapnbr(){
            int res=0;
            
            try {
            
            String req = "SELECT count(*) nbr  FROM voyageorganise  WHERE Prix < (SELECT AVG(prix) FROM voyageorganise) ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
             
          while (rs.next() )   {
            
             res =rs.getInt("nbr"); 
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res ;
        }
        
        
       /*public List<Object> FindByDestination(String Dest){
                List<Object> list = new ArrayList<>();
                try {
            String req ="select * from Vol v,Voyageorganise vo where vo.vol_id=v.vol_id and  Upper(Destination)= '"+Dest.toUpperCase()+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                System.out.println(rs.getArray(req));
                
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }*/
    
        
        public Transport get(String Description) {
        Hebergement h = new Hebergement();
        Transport v = new Transport(h);
        try {
             String req ="select * from Transport,Hebergement where Upper(Transport.Description)= '"+Description.toUpperCase()+"'"+"and Hebergement.Hebergement_id=Transport.Hebergement_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()){
                v.setTransport_id(rs.getInt("Transport.Transport_id"));
                v.setDescription(rs.getString("Transport.Description"));
                v.setDisponibilité(rs.getInt("Transport.Disponibilité"));
                v.setImage(rs.getString("Transport.Image"));
                v.setPrix(rs.getInt("Transport.Prix"));
                v.setType(rs.getString("Transport.Type"));
                v.getHebergement().setHebergement_id(rs.getInt("Transport.Hebergement_id"));
                v.getHebergement().setAdresse(rs.getString("Adresse"));
                v.getHebergement().setDescription(rs.getString("Hebergement.Description"));
                v.getHebergement().setDisponibilité(rs.getInt("Disponibilité"));
                v.getHebergement().setImage(rs.getString("Hebergement.Image"));
                v.getHebergement().setType(rs.getString("Type"));

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
        
        
        public Transport gett(int t_id) {
        Hebergement h = new Hebergement();
        Transport v = new Transport(h);
        try {
             String req ="select * from Transport,Hebergement where Transport_id = "+t_id+" and Hebergement.Hebergement_id=Transport.Hebergement_id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()){
                v.setTransport_id(rs.getInt("Transport.Transport_id"));
                v.setDescription(rs.getString("Transport.Description"));
                v.setDisponibilité(rs.getInt("Transport.Disponibilité"));
                v.setImage(rs.getString("Transport.Image"));
                v.setPrix(rs.getInt("Transport.Prix"));
                v.setType(rs.getString("Transport.Type"));
                v.getHebergement().setHebergement_id(rs.getInt("Transport.Hebergement_id"));
                v.getHebergement().setAdresse(rs.getString("Adresse"));
                v.getHebergement().setDescription(rs.getString("Hebergement.Description"));
                v.getHebergement().setDisponibilité(rs.getInt("Disponibilité"));
                v.getHebergement().setImage(rs.getString("Hebergement.Image"));
                v.getHebergement().setType(rs.getString("Type"));

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    } 
        
    public ObservableList<String> GetAllTitle() {
           ObservableList<String> list = FXCollections.observableArrayList();
        try {
             String req ="select Description from Transport ";
             Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()){
                
                list.add((rs.getString("Description")));
                

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public int nbrv(Voyageorganise v) {
        
        int x=0;
        try {
             String req ="select COUNT(*) n from Voyageorganise where Upper(Description) = '"+v.getDescription().toUpperCase()+"' and prix ="+v.getPrix();
            Statement st = cnx.createStatement();
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
