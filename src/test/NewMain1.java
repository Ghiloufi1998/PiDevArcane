/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Offre;
import entities.Reclamation;
import entities.User;
import java.sql.Date;
import service.ReclamationService;

/**
 *
 * @author Souha saffar
 */
public class NewMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      String str="2015-03-22";     
      Date date=Date.valueOf(str);
      ReclamationService rs = new ReclamationService();
      Offre f2=new Offre(2, "iheb", "iheb", 2, 2);
      User u2= new User(7, "", "", "", date, "", "", "", "", "", 0, 0, f2);
      Reclamation r = new Reclamation(0, "hd1hd", date, "type", 0, u2);
      Reclamation r1 = new Reclamation(15,0, "hd1hdmodif", date, "typemodif", 2, u2);
      Reclamation r2 = new Reclamation(18,0, "iheb", date, "yout", 2, u2);
     // rs.insert(r2);
      //rs.update(r1);
        System.out.println(rs.readAll());
    }
    
}
