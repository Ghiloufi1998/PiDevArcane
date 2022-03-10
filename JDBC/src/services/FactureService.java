/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Personne;
import java.sql.Connection;
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
 * @author Acer
 */
public class FactureService {
    
     Connection cnx;

    public FactureService() {
        cnx = MyDB.getInstance().getConnection();
    }
    
    
    public Integer RevM(int x) {
        int y =0;
        try {
            String req ="select SUM(Montant_ttc) p from facture where Month(Date_ech)= "+x;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                y=rs.getInt("p");
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return y;
    }
    
     public Integer RevY(int x) {
        int y =0;
        try {
            String req ="select SUM(Montant_ttc) p from facture where Year(Date_ech)= "+x;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                y=rs.getInt("p");
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return y;
    }


       public Integer tot() {
        int y =0;
        try {
            String req ="select SUM(Montant_ttc) p from facture ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                y=rs.getInt("p");
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return y;
    }
    
    
    
}
