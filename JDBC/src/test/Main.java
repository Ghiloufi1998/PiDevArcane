/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Questions;
import entities.Sondage;
import entities.Réponses;
import services.ServiceQuestions;
import services.ServiceSondage;
import services.ServiceRéponses;
import utils.MyDB;

/**
 *
 * @author Skander
 */
public class Main {
    
    public static void main(String[] args) {
         MyDB db = new MyDB();
         //--------------Service Question------------------
      ServiceQuestions sq = new ServiceQuestions();
//         ServiceSondage ss = new ServiceSondage();
//         ServiceRéponses sr = new ServiceRéponses();
//        Sondage s = new Sondage(1,"Avis","Voyage");
//        Sondage s2 = new Sondage(3 ,"hhh","oooo");
//        Questions q = new Questions("aaa",s, "text");
       // Questions q1 = new Questions( s2, "iii", "qlib", "none", "none");
        //Questions q1 = new Questions(1,s2,"iaaaaaaaa?", "video");
        //Questions q2= new Questions(5, s, "hjnmhdgcvshbsb", "qcm");
        //sq.modifier(q1);
       // sq.ajout(q);
       // System.out.println(sq.questRate3(27));
        //System.out.println(sq.afficher());
       // System.out.println(sq.get("aVis"));
       //-----------------SERVICE REPONSE---------------------- 
       /* ServiceRéponses sr = new ServiceRéponses();
        Réponses r = new Réponses("hjnmhdgcvshbsb",1);
         Réponses r2 = new Réponses("hello",2);
       // sr.ajout(r);
       System.out.println(sr.afficher());
       //sr.modifier(r2);
       // System.out.println(sr.afficher());
        sr.supprimer(1);
        System.out.println(sr.afficher());*/
        
        //--------------SERVICE SONDAGE-----------------------------
        
       
        /*Sondage s2 = new Sondage(3 ,"hhh","oooo");
        //ss.ajout(s);
       // ss.ajout(s1);
        //ss.supprimer(2);
        ss.modifier(s2);*/
//         ss =new ServiceSondage();
//        System.out.println(ss.getid("Avis"));
//        
        System.out.println(sq.nbrrate());
        System.out.println(sq.nbrtext());
        System.out.println(sq.nbryesno());
         
        
        
    }
    
}

    

