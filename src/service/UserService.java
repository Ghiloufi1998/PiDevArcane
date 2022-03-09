/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Offre;
import entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.BCrypt;
import utils.DB;

/**
 *
 * @author souha saffar
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class UserService {

    public void InsertUser(User h) {
        String hachedMdp = BCrypt.hashpw(h.getPassword(), BCrypt.gensalt());
        try {
            String req = "INSERT INTO user( nom, prénom, sexe, date_de_naissance, adresse, email, mot_de_passe, role,image, nombre_de_points,id_offre) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = new DB().getCnx().prepareStatement(req);
            //pst.setInt(1,h.getId());
            pst.setString(1, h.getName());
            pst.setString(2, h.getFname());
            pst.setString(3, h.getGender());
            pst.setDate(4, new java.sql.Date(h.getBirthday().getTime()));
            pst.setString(5, h.getAdresse());
            pst.setString(6, h.getEmail());
            pst.setString(7, hachedMdp);
            pst.setString(8, h.getRole());
            pst.setString(9, h.getImage());
            pst.setInt(10, h.getNb_point());
            pst.setInt(11, h.getOffre().getId_offre());
            //pst.setInt(13,h.getId());

            pst.executeUpdate();
            System.out.println("Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void SignUp(User h) {
        String hachedMdp = BCrypt.hashpw(h.getPassword(), BCrypt.gensalt());
        try {
            String req = "INSERT INTO user( nom, prénom, sexe, date_de_naissance, adresse, email, mot_de_passe, role,image,id_offre) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = new DB().getCnx().prepareStatement(req);
            //pst.setInt(1,h.getId());
            pst.setString(1, h.getName());
            pst.setString(2, h.getFname());
            pst.setString(3, h.getGender());
            pst.setDate(4, new java.sql.Date(h.getBirthday().getTime()));
            pst.setString(5, h.getAdresse());
            pst.setString(6, h.getEmail());
            pst.setString(7, hachedMdp);
            pst.setString(8, "Client");
            pst.setString(9, h.getImage());
            pst.setInt(10, h.getOffre().getId_offre());

            //pst.setInt(13,h.getId());
            pst.executeUpdate();
            System.out.println("Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void changePassword(String mdp, String email) throws SQLException {

        String hachedMdp = BCrypt.hashpw(mdp, BCrypt.gensalt());
        String req5 = "UPDATE user SET mot_de_passe = ?  WHERE email = ?";
        PreparedStatement pst = new DB().getCnx().prepareStatement(req5);
        pst.setString(1, hachedMdp);
        pst.setString(2, email);
        int rowUpdated = pst.executeUpdate();
        if (rowUpdated > 0) {
            System.out.println("Mdp modifié");
        } else {
            System.out.println("ERR");
        }
    }

    public void UpdateUser(User h) {
        try {
            String requeteUpdate = "UPDATE  User set `nom`=?,`prénom`=?,`sexe`=?,`date_de_naissance`=?,`adresse`=?,`email`=?,`mot_de_passe`=?,`role`=? ,`image`=?,`nombre_de_points`=?,`id_offre`=? where `id`=? ";

            PreparedStatement pst = new DB().getCnx().prepareStatement(requeteUpdate);
            pst.setString(1, h.getName());
            pst.setString(2, h.getFname());
            pst.setString(3, h.getGender());
            pst.setDate(4, new java.sql.Date(h.getBirthday().getTime()));
            pst.setString(5, h.getAdresse());
            pst.setString(6, h.getEmail());
            pst.setString(7, h.getPassword());
            pst.setString(8, h.getRole());
            pst.setString(9, h.getImage());
            pst.setInt(10, h.getNb_point());
            pst.setInt(11, h.getId_offre());
            pst.setInt(12, h.getId());

            pst.executeUpdate();
            System.out.println("modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void deleteUser(int id) {
        try {
            String requete = " DELETE FROM User WHERE ID=?;";
            PreparedStatement ps = new DB().getCnx().prepareStatement(requete);
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println(" supprimée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ObservableList<User> afficherUser() {
        ObservableList<User> list_user = FXCollections.observableArrayList();
        try {

            String requete = "SELECT * From User,Offre where User.id_offre = Offre.id_offre ";
            Statement st = new DB().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Offre o = new Offre();
                User ut = new User(o);
                ut.setId(rs.getInt("id"));
                ut.setName(rs.getString("nom"));
                ut.setFname(rs.getString("prénom"));
                ut.setGender(rs.getString("sexe"));
                ut.setBirthday(rs.getDate("Date_de_naissance"));
                ut.setAdresse(rs.getString("Adresse"));
                ut.setEmail(rs.getString("Email"));
                ut.setPassword(rs.getString("mot_de_passe"));
                ut.setRole(rs.getString("Role"));
                ut.setImage(rs.getString("image"));
                ut.setNb_point(rs.getInt("nombre_de_points"));
                ut.setId_offre(rs.getInt("id_offre"));
                ut.getOffre().setDescription(rs.getString("Description"));

                list_user.add(ut);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list_user;

    }

    public void login(String email, String pwd) {
        try {
            String requete = "SELECT email,mot_de_passe From User WHERE UPPER(email) = '" + email.toUpperCase() + "'";
            Statement st = new DB().getCnx().createStatement();
            ResultSet rss = st.executeQuery(requete);
            while (rss.next()) {
                String hashp = rss.getString("mot_de_passe");
                if (rss.getString("email").equals(email) && BCrypt.checkpw(pwd, hashp)) {
                    try {

                        String requetee = "SELECT ID,mot_de_passe,role From User WHERE UPPER(email) = '" + email.toUpperCase() + "'";
                        Statement stt = new DB().getCnx().createStatement();
                        ResultSet rs = stt.executeQuery(requetee);
                        // User u =new User();
                        //System.out.println(rs);
                        while (rs.next()) {

                            String req = "INSERT INTO Logger(id) VALUES (?) ";
                            PreparedStatement pst = new DB().getCnx().prepareStatement(req);

                            //pst.setInt(1,h.getId());
                            pst.setString(1, rs.getString("id"));
                  //          pst.setString(2, rs.getString("mot_de_passe"));
                            //pst.setString(3, rs.getString("role"));
                            pst.executeUpdate();
                            /*System.out.println(rs.getInt("ID"));
               System.out.println(rs.getString("mot_de_passe"));*/
                            System.out.println(" Bien connecté ");

                        }
                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                    }

                } else {
                    System.out.println("Mot de Passe ou Email erroné");
                }
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void Logout(int id) {
        try {
            String requete = " DELETE FROM Logger WHERE ID=?;";
            PreparedStatement ps = new DB().getCnx().prepareStatement(requete);
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("  Logout  ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public User getUserById(int id) throws SQLException {
        User a = null;
        String req = "SELECT * FROM admin WHERE id = ?";
        PreparedStatement pst = new DB().getCnx().prepareStatement(req);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            a = new User();
            a.setId(rs.getInt("id"));
            a.setName(rs.getString("name"));
            a.setFname(rs.getString("lastname"));
            a.setEmail(rs.getString("email"));
            a.setPassword(rs.getString("password"));
            a.setImage(rs.getString("image"));
            a.setGender(rs.getString("gender"));
            a.setBirthday(rs.getDate("birthday"));

        }
        return a;
    }

    public User searchByPseudoPassU(String email, String mdp) throws SQLException {
        User ad = null;
        String req = "SELECT * FROM user WHERE email = ?";
        PreparedStatement pst = new DB().getCnx().prepareStatement(req);
        //PreparedStatement pst = new DB().getCnx().getInstance().getConnection().prepareStatement(req);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            ad = new User();
            ad = UserService.this.getUserById(rs.getInt("id"));
            if (BCrypt.checkpw(ad.getPassword(), BCrypt.hashpw(mdp, BCrypt.gensalt()))) {
                return ad;
            }
        }
        return ad;
    }

    public int succLogin(String e, String mdp) throws SQLException {
        boolean x = false;
        int s = 0;
        String req = "SELECT count(*) m FROM user WHERE email = ? and mot_de_passe = ? ";
        PreparedStatement pst = new DB().getCnx().prepareStatement(req);
        pst.setString(1, e);
        pst.setString(2, mdp);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

            s = rs.getInt("m");
        }
        return s;

    }

}
