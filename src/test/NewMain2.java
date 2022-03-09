/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Offre;
import entities.User;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.UserService;

/**
 *
 * @author Souha saffar
 */
public class NewMain2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        String str = "2015-03-22";
        Date date = Date.valueOf(str);
        Offre f1 = new Offre(1, "", "", 0, 0);
        
       UserService us = new UserService();
     us.login("azeazea@azeazea.com", "azertyuiop");
  //     User u1 = new User("nom", "prénom", "g", date, "addresse", "e_mail", "motpasse", "image",f1);
    //   us.SignUp(u1);
//
//        //User u1=new User("11", "djjf", "dhjd", date, "sjfk","r", "st", "s", "sr", 0, 0, f2);
//       User u1 = new User("hh", "saffar", "f", date, "zaeazeaz1", "azeazeaz2", "azeazea3", "azeazea4", "azeazea5", 5, 5, f1);
//        //
//        
//        
//        //System.out.println(us.succLogin("aze", "$2y$10$I5y/2CaD/3AA7d7Bu8DmiuUyIF8Fk1E69AfvnB3kc6CDkicLdNnie"));
//        //us.login("r", "st");
//        //us.InsertUser(u1);
//        // us.changePassword(str, str);
//        // us.InsertUser(u1);
//        // us.login(u1.getName(),u1.getPassword());
//        //us.Logout(17);
//       // us.changePassword("123456", "souhe@saffar.com");
//        System.out.println(us.succLogin("souhe@saffar.com", "123456"));
        // us.login("azeazeaz2", "azeazea3");
        // System.out.println(u1.getName()+ "   " +u1.getPassword());
       // us.login("souhe@saffar.com", "123456");
        //  us.login(str, str);
        //
    }

}
