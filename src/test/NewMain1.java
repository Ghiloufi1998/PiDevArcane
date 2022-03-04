/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Hebergement;
import entities.Transport;
import services.ServiceHebergement;
import services.ServiceTransport;

/**
 *
 * @author Dhia
 */
public class NewMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ServiceTransport st = new ServiceTransport();
        
        ServiceHebergement sh = new ServiceHebergement();
        Hebergement k = new Hebergement(1, "sidisi", "sohoo", 25, "mnbbb", "5646");
        Hebergement k2 = new Hebergement(22, "sbuifpq", "sohoo", 25, "didoooi", "5646",89);
        Hebergement k3 = new Hebergement(2, "did", "doo", 25, "dwes", "45",9);
        Hebergement k4 = new Hebergement(39,"8", "alzaororar", 4, "Adresse", "Image",789);
    sh.modifier(k4);
          System.out.println(sh.afficher());
       //
        //System.out.println(sh.get("8"));
        
       Transport t = new Transport(33,"newww", "new", 88, 88, "neww", k4);
       Transport t1 = new Transport("newwwww", "new", 88, 88, "neww", k4);
        Transport t2 = new Transport("bus", "new", 88, 88, "neww", k4);
        Transport t3 = new Transport("type", "desckjri",1,2);
       Transport t6 = new Transport("Type", "Descaaription", 55,55);
        //System.out.println(sh.afficher());
        //sh.supprimer(3);
        // st.ajout(t2);
         //st.ajout(t1);
        //String n = "bus";
        // System.out.println(st.FindByType(n));
          }
    
}
