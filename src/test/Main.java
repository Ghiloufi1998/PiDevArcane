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
import Service.CoursService;
import Service.ExerciceService;
import Service.GuideService;
import Service.OffresService;
/**
 *
 * @author Ghiloufi
 */
public class Main {
       public static void main(String[] args) {
        OffresService so = new OffresService();
        Offres o = new Offres("qsdqsdqs", 26, "sqdqsd", 20000);
      //  Offres oup = new Offres(22, "hellloo im updated", 15, "heloooo", 500);
       //  so.Create(o);
      //  System.out.println(sp.Read());
        
           GuideService gs = new GuideService();
         
      //     Guide g = new Guide("Turkie",3);
       //    gs.Create(g);
        //   System.out.println(gs.Read()); 
       Vol v = new Vol(1, "Sousse", "Tunis", "this is image");
      Guide g = new Guide(4, "Sousse", 2, v, 1);
      gs.Create(g);
     Cours c = new Cours(18,"test", "hehehehehehee",4, g);
    
        
         CoursService cs = new CoursService();
          cs.Create(c);
         // cs.Delete(17);
           //System.out.println(cs.Read());
           ExerciceService es = new ExerciceService();
          // Exercices e = new Exercices("QCM", "test question 2 ", "test reponse 2");
        Exercices e = new Exercices( "QCM", "test question 2 ", "test reponse 2", c, 18);
        Exercices e2 = new Exercices( "QCCCCMMM", "hehehehehe", "dededede", c, 18);
         es.Create(e2);
         es.Create(e);
         // System.out.println(es.Read());
       // es.Delete(11);
         //  System.out.println(cs.Read());
    }
    
}

