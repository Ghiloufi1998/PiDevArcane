/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Reclamation;
import entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DB;

/**
 *
 * @author souha saffar
 */
public class ReclamationService implements IService <Reclamation> {

    @Override
    public void insert(Reclamation t) {
            try {
        String req = "INSERT INTO reclamation (etat , description, date, type, id_user) "
                + "VALUES(?,?,?,?,?)";
       
             PreparedStatement pst=new DB().getCnx().prepareStatement(req);
       
         pst.setInt(1,t.getEtat());
         pst.setString(2,t.getDescription());
         pst.setDate(3,t.getDate());
         pst.setString(4,t.getType());
         pst.setInt(5,t.getUser().getId());
         
           
            pst.executeUpdate();
         System.out.println("Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> readAll() {
            List<Reclamation> list_rec=new ArrayList();
        try {
          
            String requete="SELECT * From Reclamation,User Where Reclamation.id_user = User.id";
            Statement st =  new DB().getCnx().createStatement();
             ResultSet rs=  st.executeQuery(requete);
           while(rs.next()){
              User u =new User();
              Reclamation rec = new Reclamation(u);
              rec.setId(rs.getInt("id"));
              rec.setEtat(rs.getInt("etat"));
              rec.setDescription(rs.getString("Description"));
              rec.setDate(rs.getDate("date"));
              rec.setType(rs.getString("Type"));
              rec.setUser_id(rs.getInt("id_user"));
              rec.getUser().setName(rs.getString("User.nom"));
               list_rec.add(rec);
               
           }
            
           
                    } catch (SQLException ex) {
           System.err.println(ex.getMessage());
                    }
             return list_rec;
     
    }

    @Override
    public void update(Reclamation t) {
           try {
        String requeteUpdate = "UPDATE  Reclamation set `etat`=?,`Description`=?,`date`=?,`Type`=?,`id_user`=? WHERE`id`=? ";

          
            PreparedStatement pst = new DB().getCnx().prepareStatement(requeteUpdate);
           
               pst.setInt(1,t.getEtat());
               pst.setString(2,t.getDescription());
               pst.setDate(3,t.getDate());
              pst.setString(4,t.getType());
              pst.setInt(5,t.getUser_id());
                 pst.setInt(6,t.getId());
            
            pst.executeUpdate();
            System.out.println("modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      }
    

    @Override
    public void delete(int t) {
        try {
            String requete=" DELETE FROM Reclamation WHERE Id=?;";
            PreparedStatement ps = new DB().getCnx().prepareStatement(requete);
            ps.setInt(1,t);
            
            ps.executeUpdate();
            System.out.println(" supprimée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    }
    

