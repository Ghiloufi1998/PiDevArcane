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
 * @author Skander
 */
public class Main {
    
    public static void main(String[] args) {
        MyDB aa = new MyDB();
        ServiceVoyageorganise sv = new ServiceVoyageorganise();
        ServiceVol vv = new ServiceVol();
        Vol v = new Vol(2,"sklmodi", "Cmodi", "s56modi");
        //Hebergement h = new Hebergement(1,"one","two",1,"li","ooo");
        //Transport t= new Transport(1,"aa","zz",1,15,"s",h);
        //System.out.println(vv.read(v));
        //System.out.println(vv.afficher());
        //Voyageorganise vo = new Voyageorganise("12", "23", "56", 0, 0, v, h, t);
        //sv.ajout(vo);
        //vv.ajout(v);
        //vv.modifier(v);
        //vv.supprimer(4);
        //System.out.println(vv.afficher());
        //System.out.println(sv.afficher());
        System.out.println(vv.FindByDestination("iheb"));
        System.out.println(vv.FindByDepart("Chiheb"));
        
    }
    
}
