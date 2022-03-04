/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Entities.Cours;
import Entities.Exercices;
import Entities.Guide;
import Entities.Offres;
import Entities.Vol;
import Service.CertifService;
import Service.CoursService;
import Service.ExerciceService;
import Service.GuideService;
import Service.OffresService;
import Service.VolService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author Ghiloufi
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        //    OffresService so = new OffresService();
        //    Offres o = new Offres("qsdqsdqs", 26, "sqdqsd", 20000);
        //  Offres oup = new Offres(22, "hellloo im updated", 15, "heloooo", 500);
        //  so.Create(o);
        //  System.out.println(sp.Read());
     //    VolService vs = new VolService();
    //     System.out.println(vs);
        //      System.out.println(vs.GetAllnumVols());
    //   CoursService cs = new CoursService();
    //    System.out.println(cs.get(86));
      //   GuideService gs = new GuideService();
       //  System.out.println(gs.getByPays("Tunisia"));
      //  System.out.println(cs.GetByidG(84));
      //  Cours c = new Cours("cours 1", "titre 1 ", "contenu1", gs.get(83));
     //   cs.Create(c);
    
     //   System.out.println(gs.getByVolDest("tunis"));
     //   System.out.println(gs.getBytitre("Titre Guide"));
        //     System.out.println(gs.Read());
        //     System.out.println(gs.getByVolDest("sousse"));
        //   Vol v = new Vol(1, "Sousse", "Tunis", "this is image");
        //   Guide g = new Guide(39, "hhhhhhhhhhhhhhhhhhhhhhhh", "hhhhhhhhhhhhhhh", 0, 1);
        //   gs.Create(g);
        //   System.out.println(gs.Read()); 
        //  gs.Update(g);
   //     System.out.println((gs.getBytitre("Guide de Tunisie")).get(0).getID_g());

        // Guide g = new Guide(4, "Sousse", 2, v, 1);
        //    gs.Create(g);
        //  Cours c = new Cours("hehehehehhe", "hahahahahhaa", gs.get(39).getID_g(), gs.get(39));
        //  System.out.println(gs.Read());
        // CoursService cs = new CoursService();
        // Cours c = new Cours("test type", "test contenu", gs.get(75) );
        //   System.out.println(cs.Read());
        //cs.Create(c);
        //System.out.println(gs.get(75)); 
        // cs.Delete(17);
        //System.out.println(cs.Read());
       ExerciceService es = new ExerciceService();
      ObservableList <Exercices> e = es.GetQuestionByidcrs(86);
       
          System.out.println(e.get(1).getReponse());
     
//           System.out.println(es.GetAllTitleCours());
//           CoursService cs = new CoursService();
//             Exercices e = new Exercices("QCM", "Question test", "Reponse test", cs.get(86));
//           es.Create(e);
  //         System.out.println(cs.getBytitre("texte"));
      
        //      Exercices e = new Exercices( "QCM", "test question 2 ", "test reponse 2", c, 18);
        //     Exercices e2 = new Exercices( "QCCCCMMM", "hehehehehe", "dededede", c, 18);
        //  es.Create(e2);
        //   es.Create(e);
        //   System.out.println(es.Read());
        // es.Delete(11);
        //  System.out.println(cs.Read());
        //       CertifService cs = new CertifService();
        //     try {
        //        cs.getUserlogged();
        //     } catch (SQLException ex) {
        //          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        //      }
        //cs.CertifPDF();
    }
    
}
