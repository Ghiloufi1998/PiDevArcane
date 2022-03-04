/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Personne;
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
 * @author Skander
 */
public class ServicePersonne implements IService<Personne> {

    Connection cnx;

    public ServicePersonne() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajout(Personne t) {
        try {
            String req = "insert into personne (prenom,nom,email) values"
                    + " ( '" + t.getPrenom() + "', '" + t.getNom() + "','" + t.getEmail() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void modifier(Personne t) {
        try {
            String req = "update personne set prenom = ? , nom = ? , email = ? where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getPrenom());
            ps.setString(2, t.getNom());
            ps.setString(3, t.getEmail());
            ps.setInt(4, t.getId());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "delete from personne where id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Personne> afficher() {
        List<Personne> list = new ArrayList<>();
        try {
            String req ="select * from personne";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setEmail(rs.getString("email"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
