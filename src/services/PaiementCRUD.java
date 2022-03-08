/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Paiement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author bensa
 */
public class PaiementCRUD implements IService<Paiement>{
    @Override
    public void insert(Paiement p) {
          try {
            String requete = " INSERT INTO paiement (Date,Montant,Mode_Pay,ID_fac) VALUES "
                    + "(?,?,?,?);";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(requete);
            ps.setDate(1, new java.sql.Date(p.getDate().getTime()));
            ps.setDouble(2, p.getMontant());
            ps.setString(3, p.getMode_Pay());
            ps.setInt(4, p.getID_fac());
            ps.executeUpdate();
            System.out.println("votre Paiement ajoutée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public  ObservableList<Paiement> readAll() {
            ObservableList<Paiement> ls = FXCollections.observableArrayList();
        try {

            String requete = "SELECT * From paiement";
            Statement st = new MyDB().getConn().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Paiement p = new Paiement();
                p.setID_PAi(rs.getInt("Pai_ID"));
                p.setDate(rs.getDate("Date"));
                p.setMontant(rs.getDouble("Montant"));
                p.setMode_Pay(rs.getString("Mode_Pay"));

                ls.add(p);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ls;

    }
  
    public void updatemode(String t,Date x ,String y) {
        try {
            String requete = "UPDATE Paiement SET Date=? ,Mode_Pay= ?, Montant=? where Pai_ID = (select ID_fac  from facture order by ID_fac desc LIMIT 1)";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(requete);
            ps.setDate(1,x);
            ps.setString(2,t);
            ps.setString(3,y);
        
            ps.executeUpdate();
            System.out.println("votre  Paiement modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void update(Paiement new_p) {
        try {
            String requete = "UPDATE Paiement SET Date=?,Montant=?,Mode_Pay=?,ID_fac=? WHERE ID_Pai=?; ";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(requete);
            ps.setDate(1, new java.sql.Date(new_p.getDate().getTime()));
            ps.setDouble(2, new_p.getMontant());
            ps.setString(3, new_p.getMode_Pay());
            ps.setInt(4, new_p.getID_fac());
            ps.setInt(5, new_p.getID_PAi());
            ps.executeUpdate();
            System.out.println("votre  Paiement modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void delete(int t) {
          try {
            String requete = " DELETE FROM Paiement,facture "
                    + "WHERE Pai_ID=?;";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(requete);
            ps.setInt(1, t);
             String we = " DELETE FROM Paiement,facture "
                    + "WHERE ID_fac=?;";
  ps.setInt(1, t);
            ps.executeUpdate();
            System.out.println("votre Paiement supprimée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
