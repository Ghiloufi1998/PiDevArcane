/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Reclamation;
import Entities.User;
import java.sql.Connection;
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
 * @author souha saffar
 */
public class ReclamationService implements IService <Reclamation> {
 Connection cnx;
  public ReclamationService() {
        cnx = MyDB.getInstance().getConnection();
    }
    @Override
    public void Create(Reclamation t) {
            try {
        String req = "INSERT INTO reclamation (etat , description,id_user) "
                + "VALUES(?,?,?)";
       
             PreparedStatement pst=cnx.prepareStatement(req);
       
         pst.setInt(1,t.getEtat());
         pst.setString(2,t.getDescription());
         
         //pst.setInt(3,t.getUser().getId());
         pst.setInt(3,t.getUser_id());
         
           
            pst.executeUpdate();
         System.out.println("Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Reclamation> readAll() {
                    ObservableList<Reclamation> list_rec = FXCollections.observableArrayList();

        try {
          
            String requete="SELECT * From Reclamation,User Where Reclamation.id_user = User.id";
            Statement st =  cnx.prepareStatement(requete);
             ResultSet rs=  st.executeQuery(requete);
           while(rs.next()){
              //User u =new User();
              Reclamation rec = new Reclamation();
              rec.setId(rs.getInt("id"));
              rec.setEtat(rs.getInt("etat"));
              rec.setDescription(rs.getString("Description"));
//              rec.setDate(rs.getDate("date"));
//              rec.setType(rs.getString("Type"));
              rec.setUser_id(rs.getInt("id_user"));
              rec.setUser(rs.getString("User.nom"));
           
             // rec.getUser().setName(rs.getString("User.nom"));
               list_rec.add(rec);
               
           }
            
           
                    } catch (SQLException ex) {
           System.err.println(ex.getMessage());
                    }
             return list_rec;
     
    }
    public int logged() {
            int x =0;
        try {
          
            String requete="SELECT id From Logger";
            Statement st = cnx.prepareStatement(requete);
             ResultSet rs=  st.executeQuery(requete);
           while(rs.next()){
              //User u =new User();
              
              x=(rs.getInt("id"));       
           }
            
           
                    } catch (SQLException ex) {
           System.err.println(ex.getMessage());
                    }
             return x;
     
    }
    @Override
    public void Update(Reclamation t) {
           try {
        String requeteUpdate = "UPDATE  Reclamation set `etat`=?,`Description`=?,`id_user`=? WHERE`id`=? ";

          
            PreparedStatement pst = cnx.prepareStatement(requeteUpdate);
           
               pst.setInt(1,t.getEtat());
               pst.setString(2,t.getDescription());
//               pst.setDate(3,t.getDate());
//              pst.setString(4,t.getType());
              pst.setInt(3,t.getUser_id());
                 pst.setInt(4,t.getId());
            
            pst.executeUpdate();
            System.out.println("modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      }
    
     public void Valider(Reclamation t) {
           try {
        String requeteUpdate = "UPDATE  Reclamation set `etat`=1 WHERE`id`=? ";

          
            PreparedStatement pst = cnx.prepareStatement(requeteUpdate);
           
               //pst.setInt(1,t.getEtat());
              
                 pst.setInt(1,t.getId());
            
            pst.executeUpdate();
            System.out.println("Validé ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      }

    @Override
    public void Delete(int t) {
        try {
            String requete=" DELETE FROM Reclamation WHERE Id=?;";
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1,t);
            
            ps.executeUpdate();
            System.out.println(" supprimée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


   

    @Override
    public List<Reclamation> Read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    

   
    
    }
    

