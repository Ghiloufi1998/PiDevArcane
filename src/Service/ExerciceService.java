/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entities.Cours;
import Entities.Guide;
import Entities.Exercices;
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
 * @author Ghiloufi
 */
public class ExerciceService implements IService<Exercices> {
Connection cnx;

    public ExerciceService() {
        cnx = MyDB.getInstance().getConnection();
    }
    
    @Override
    public void Create(Exercices t) {
       try {
            String req = "INSERT INTO exercices (Type,Question,Reponse,ID_crs) VALUES"
                    + " ( '" + t.getType()+ "','" + t.getQuestion()+ "','" + t.getReponse()+ "','" + t.getCours().getID_crs()+ "')";
            Statement st = cnx.createStatement();
             System.out.println("hehehehehe");
            st.executeUpdate(req);
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Update(Exercices t) {
      try {
            String req = "update Exercices set Type = ? , Question = ?, Reponse = ? where ID_ex = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getType());
            ps.setString(2, t.getQuestion());
            ps.setString(3, t.getReponse());
            ps.setInt(4, t.getID_ex());
            ps.executeUpdate();
              System.out.println("donenenenenen");
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Delete(int id) {
         try {
            String req = "delete from exercices where ID_crs = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
             System.out.println("deleteddd");
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Exercices> Read() {
      List<Exercices> list = new ArrayList<>();
        try {
            String req ="select * from exercices,cours where exercices.ID_crs = cours.ID_crs";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                  Cours c = new Cours();
                Exercices o = new Exercices(c);
              
                o.setID_ex(rs.getInt(1));
                o.setType(rs.getString("Type"));
                o.setQuestion(rs.getString("Question"));
                o.setReponse(rs.getString("Reponse"));
                o.setType(rs.getString("Type"));
                o.getCours().setID_crs(rs.getInt("ID_crs"));
                o.getCours().setType(rs.getString("Type"));
                o.getCours().setContenu(rs.getString("Contenu"));
                o.setID_crs(rs.getInt("Cours.ID_crs"));
                list.add(o);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OffresService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
