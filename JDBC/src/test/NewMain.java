/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Hebergement;
import entities.Personne;
import entities.Transport;
import entities.Vol;
import entities.Voyageorganise;
import services.ServicePersonne;
import services.ServiceVol;
import services.ServiceVoyageorganise;
import utils.MyDB;

/**
 *
 * @author Acer
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyDB aa = new MyDB();
        ServiceVoyageorganise sv = new ServiceVoyageorganise();
        ServiceVol vv = new ServiceVol();
        Vol v1 = new Vol(3,"iheb", "Chiheb", "moncef");
        Hebergement h = new Hebergement(2,"un","deux",0,"trois","pdf");
        Transport t= new Transport(3,"uno","dos",0,5,"x",h);
        //System.out.println(vv.read(v));
        //System.out.println(vv.afficher());
        Voyageorganise vo = new Voyageorganise(13,"ihebmodi", "yahmdoi", 22, 33, v1,t);
        
        Voyageorganise v = new Voyageorganise(13,"Ko", "doi", 600, 38, v1,t);
        
        //sv.modifier(vo);
       //sv.supprimer(9);
        //sv.ajout(vo1);
        //vv.ajout(v1);
        
        System.out.println(""+vv.getx(3));
        System.out.println(""+vv.gety(3));
               
                
        //System.outs.println(sv.afficher());     
        //System.out.println(sv.GetAllTitle());;
        //System.out.println(sv.nbrv(v));
        //System.out.println(vv.nbrv(1));;
       // System.out.println((sv.consulterstat()).get(0));
        //System.out.println(sv.get("zz"));
        //System.out.println(sv.Cheap());
        //System.out.println(sv.Cheapnbr());
    }
    
}
