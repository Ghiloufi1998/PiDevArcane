/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Vol;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author Ghiloufi
 */
public class VolService {
    
    Connection cnx;

    public VolService() {
        cnx = MyDB.getInstance().getConnection();
    }
    public Vol get(int vol_id) {
        
        Vol v = new Vol();
        try {
             String req ="select * from vols where id_vol = "+vol_id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
        while (rs.next()){    
         v.setVol_id(rs.getInt(("id_vol")));
         v.setDestination(rs.getString("Destination"));
         v.setDépart(rs.getString("Depart"));
         v.setImage(rs.getString("Image"));
        }
        } catch (SQLException ex) {
            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    
   public ObservableList<Integer> GetAllnumVols() {
         ObservableList<Integer> list = FXCollections.observableArrayList();
        try {
            String req ="select id_vol from vols";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                list.add(rs.getInt("id_vol"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
