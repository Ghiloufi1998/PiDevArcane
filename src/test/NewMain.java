/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Offre;
import entities.User;
import java.sql.Date;
import service.UserService;

/**
 *
 * @author Souha saffar
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String str="2015-03-22";     
        Date date=Date.valueOf(str);
        UserService us =new UserService();
        Offre f1=new Offre(1, "iii", "oo", 0, 0);
        Offre f2=new Offre(2, "iheb", "iheb", 2, 2);
        User u1=new User("11", "djjf", "dhjd", date, "sjfk","r", "st", "s", "sr", 0, 0, f1);
        User u2= new User(7, "", "", "", date, "", "", "", "", "", 0, 0, f2);
        //us.InsertUser(u1);
        //us.UpdateUser(u2);
        System.out.println(us.afficherUser());
    }
    
}
