/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Cours;
import Entities.Guide;
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
 * @author Ghiloufi
 */
public class CoursService implements IService<Cours> {

    Connection cnx;

    public CoursService() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void Create(Cours t) {
        try {
            String req = "INSERT INTO Cours (Type,Titre,Contenu,ID_g) VALUES"
                    + " ( '" + t.getType() + "','" + t.getTitre() + "','" + t.getContenu() + "','" + t.getGuide().getID_g() + "')";
            Statement st = cnx.createStatement();
            System.out.println("hehehehehe2");
            st.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Update(Cours t) {
        try {
            String req = "update cours set Type = ? , Titre= ? ,Contenu = ?, id_g= ? where ID_crs = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getType());
            ps.setString(2, t.getTitre());
            ps.setString(3, t.getContenu());
            ps.setInt(4, t.getGuide().getID_g());
            ps.setInt(5, t.getID_crs());
            ps.executeUpdate();
            System.out.println("donenenenenen");

        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Delete(int id) {
        try {
            String req = "delete from cours where ID_crs = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("deleteddd");
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Cours> Read() {
        ObservableList<Cours> list = FXCollections.observableArrayList();
        try {
            String req = "select * from cours,guide where cours.Id_g = guide.ID_g";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Guide g = new Guide();
                Cours o = new Cours(g);

                o.setID_crs(rs.getInt("id_crs"));
                o.setType(rs.getString("Type"));
                o.setTitre(rs.getString("Titre"));
                o.setContenu(rs.getString("Contenu"));
                o.getGuide().setID_g(rs.getInt("guide.ID_g"));
                o.getGuide().setLevel(rs.getInt("Level"));
                o.getGuide().setTitre(rs.getString("Titre"));
                o.getGuide().setPays(rs.getString("Pays"));
                o.getGuide().setId_vol(rs.getInt("Id_vol"));
                o.setID_g(rs.getInt("guide.ID_g"));

                list.add(o);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Cours get(int id_crs) {

        Cours c = new Cours();
        try {
            String req = "select * from cours where id_crs = " + id_crs;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                c.setID_crs(rs.getInt(("id_crs")));
                c.setTitre(rs.getString("Titre"));
                c.setContenu(rs.getString("Contenu"));
                c.setType(rs.getString("Type"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(VolService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public ObservableList<String> GetAllTitleGuide() {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            String req = "select titre from guide";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                list.add(rs.getString("titre"));
                //    list.add(rs.getInt("id_g"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<Cours> getBytitre(String Titre) throws SQLException {
        ObservableList<Cours> list = FXCollections.observableArrayList();
        try {
            String req = "select * from cours where '" + Titre.toUpperCase() + "' = UPPER(Titre)";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Cours c = new Cours();
                c.setID_crs(rs.getInt(("id_crs")));
                c.setTitre(rs.getString("Titre"));
                c.setContenu(rs.getString("Contenu"));
                c.setType(rs.getString("Type"));

                list.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<Cours> GetByidG(int id_g) {
        ObservableList<Cours> list = FXCollections.observableArrayList();
        try {
            String req = "select * from cours,guide where cours.ID_g = Guide.ID_g AND cours.ID_g = '" + id_g+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Guide g = new Guide();
                Cours o = new Cours(g);

                o.setID_crs(rs.getInt("id_crs"));
                o.setType(rs.getString("Type"));
                o.setTitre(rs.getString("Titre"));
                o.setContenu(rs.getString("Contenu"));
                o.getGuide().setID_g(rs.getInt("guide.ID_g"));
                o.getGuide().setLevel(rs.getInt("Level"));
                o.getGuide().setTitre(rs.getString("Titre"));
                o.getGuide().setPays(rs.getString("Pays"));
                o.getGuide().setId_vol(rs.getInt("Id_vol"));
                o.setID_g(rs.getInt("guide.ID_g"));

                list.add(o);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public int nbrv(Cours c) {
        
        int x=0;
        try {
             String req ="select COUNT(*) n from Cours where Upper(titre) = '"+c.getTitre().toUpperCase();
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
