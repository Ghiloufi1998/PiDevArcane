/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author Ghiloufi
 */
import Entities.Offres;
import Entities.User;
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
import utils.BCrypt;
import utils.MyDB;

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

    Connection cnx;

    public UserService() {
        cnx = MyDB.getInstance().getConnection();
    }

    public void InsertUser(User h) {
        String hachedMdp = BCrypt.hashpw(h.getPassword(), BCrypt.gensalt());
        try {
            String req = "INSERT INTO user( nom, prénom, sexe, date_de_naissance, adresse, email, mot_de_passe, role,image, nombre_de_points,id_offre) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(req);
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
            pst.setInt(11, 1);
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
        PreparedStatement pst = cnx.prepareStatement(req5);
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

            PreparedStatement pst = cnx.prepareStatement(requeteUpdate);
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
            pst.setInt(11, 1);
            pst.setInt(12, h.getId());

            pst.executeUpdate();
            System.out.println("modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void UpdateUserWithoutOffre(User h) {
        try {
            String requeteUpdate = "UPDATE  User set `nom`=?,`prénom`=?,`sexe`=?,`date_de_naissance`=?,`adresse`=?,`email`=?,`role`=? ,`image`=?,`nombre_de_points`=? where `id`=? ";

            PreparedStatement pst = cnx.prepareStatement(requeteUpdate);
            pst.setString(1, h.getName());
            pst.setString(2, h.getFname());
            pst.setString(3, h.getGender());
            pst.setDate(4, new java.sql.Date(h.getBirthday().getTime()));
            pst.setString(5, h.getAdresse());
            pst.setString(6, h.getEmail());
            pst.setString(7, h.getRole());
            pst.setString(8, h.getImage());
            pst.setInt(9, h.getNb_point());
            pst.setInt(10, h.getId());

            pst.executeUpdate();
            System.out.println("modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void deleteUser(int id) {
        try {
            String requete = " DELETE FROM User WHERE ID=?;";
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println(" supprimée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<User> afficherUser() {
        List<User> list_user = new ArrayList();
        try {

            String requete = "SELECT * From User,Offres where User.id_offre = Offres.ID_off ";
            PreparedStatement st = cnx.prepareStatement(requete);

            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Offres o = new Offres();
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

    public boolean login(String email, String pwd) {
        try {
            String requete = "SELECT email,mot_de_passe From User WHERE UPPER(email) = '" + email.toUpperCase() + "'";
            Statement st = cnx.prepareStatement(requete);
            ResultSet rss = st.executeQuery(requete);
            while (rss.next()) {
                String hashp = rss.getString("mot_de_passe");
                if (rss.getString("email").equals(email) && BCrypt.checkpw(pwd, hashp)) {
                    try {

                        String requetee = "SELECT ID,mot_de_passe,role From User WHERE UPPER(email) = '" + email.toUpperCase() + "'";
                        Statement stt = cnx.prepareStatement(requetee);
                        ResultSet rs = stt.executeQuery(requetee);
                        
                        while (rs.next()) {

                            String req = "INSERT INTO Logger VALUES (?) ";
                            PreparedStatement pst = cnx.prepareStatement(req);

                         
                            pst.setString(1, rs.getString("id"));
                            // pst.setString(2,rs.getString("mot_de_passe"));
                            //pst.setString(3, rs.getString("role"));
                            pst.executeUpdate();
                            /*System.out.println(rs.getInt("ID"));
               System.out.println(rs.getString("mot_de_passe"));*/
                            System.out.println(" Bien connecté ");

                        }
                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                    }
                    return true;
                } else {
                    System.out.println("Mot de Passe ou Email erroné");
                    return false;
                }
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;

    }

    public void Logout(int id) {
        try {
            String requete = " DELETE FROM Logger WHERE ID=?;";
            PreparedStatement ps = cnx.prepareStatement(requete);

            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("  Logout  ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public User getUserlogged() throws SQLException {
        User ut = new User();

        String req = "SELECT * FROM logger ";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {

            String req2 = "SELECT * FROM user WHERE id='" + rs.getInt("id") + "'";
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(req2);
            while (rs2.next()) {
                ut = new User();

                ut.setId(rs2.getInt("id"));
                ut.setName(rs2.getString("nom"));
                ut.setFname(rs2.getString("prénom"));
                ut.setGender(rs2.getString("sexe"));
                ut.setBirthday(rs2.getDate("Date_de_naissance"));
                ut.setAdresse(rs2.getString("Adresse"));
                ut.setEmail(rs2.getString("Email"));
                ut.setPassword(rs2.getString("mot_de_passe"));
                ut.setRole(rs2.getString("Role"));
                ut.setImage(rs2.getString("image"));
                ut.setNb_point(rs2.getInt("nombre_de_points"));
                //   ut.setId_offre(rs2.getInt("id_offre"));
                //   ut.getOffre().setDescription(rs2.getString("Description"));

            }

        }
        return ut;

    }

    public ObservableList<String> GetListEmails() {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            String req = "select email from user";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                list.add(rs.getString("email"));
                //    list.add(rs.getInt("id_g"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void UpdateUserOffre(int id_o, int id) {
        try {
            String requeteUpdate = "UPDATE  User set `id_offre`=? where `id`=? ";

            PreparedStatement pst = cnx.prepareStatement(requeteUpdate);

            pst.setInt(1, id_o);
            pst.setInt(2, id);

            pst.executeUpdate();
            System.out.println("modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public User getUserByEmail(String Email) throws SQLException {
        User a = null;
        String req = "SELECT * FROM user WHERE email = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, Email);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            a = new User();
            a.setId(rs.getInt("id"));
            a.setName(rs.getString("nom"));
            a.setFname(rs.getString("prénom"));
            a.setGender(rs.getString("sexe"));
            a.setBirthday(rs.getDate("Date_de_naissance"));
            a.setAdresse(rs.getString("Adresse"));
            a.setEmail(rs.getString("Email"));
            //  a.setPassword(rs.getString("mot_de_passe"));
            a.setRole(rs.getString("Role"));
            a.setImage(rs.getString("image"));
            a.setNb_point(rs.getInt("nombre_de_points"));
            a.setId_offre(rs.getInt("id_offre"));
            //     a.getOffre().setDescription(rs.getString("Description"));

        }
        return a;
    }

    public void SignUp(User h) {
        String hachedMdp = BCrypt.hashpw(h.getPassword(), BCrypt.gensalt());
        try {
            String req = "INSERT INTO user( nom, prénom, sexe, date_de_naissance, adresse, email, mot_de_passe, role,image) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(req);
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

            //pst.setInt(13,h.getId());
            pst.executeUpdate();
            System.out.println("Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public boolean checkoldmdp(String email, String pwd) {
        try {
            String requete = "SELECT email,mot_de_passe From User WHERE UPPER(email) = '" + email.toUpperCase() + "'";
            Statement st = cnx.prepareStatement(requete);
            ResultSet rss = st.executeQuery(requete);
            while (rss.next()) {
                String hashp = rss.getString("mot_de_passe");
                if (rss.getString("email").equals(email) && BCrypt.checkpw(pwd, hashp)) {
                    
                    return true;
                } else {
                    System.out.println("Mot de Passe ou Email erroné");
                    return false;
                }
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;

    }

}
