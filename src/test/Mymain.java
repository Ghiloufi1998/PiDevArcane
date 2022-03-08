/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.itextpdf.text.Document;
import com.mysql.jdbc.log.Log;
import entities.Facture;
import entities.Paiement;
import entities.Réservation;
import entities.User;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import services.FactureService;
import services.PaiementCRUD;
import services.RéservationCRUD;
import services.ServiceVol;
import utils.MyDB;
   import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
    import java.net.InetAddress;
import java.net.UnknownHostException;
import sun.net.www.http.HttpClient;
/**
 *
 * @author bensa
 */

public class Mymain {
    public Location_Use_Bean get_ip_Details(String ip) {
        String key = "9d6jinufcfdfacc213c7ddf4ef911dfe97b55e4696be3732bf8302876c09ebad06b";
        ip = ip.trim();
        Location_Use_Bean obj_Location_Use_Bean = new Location_Use_Bean();
        System.out.println("The ip adress is before " + ip + "  split");
        try {
            if (ip.contains(",")) {
                String temp_ip[] = ip.split(",");
                ip = temp_ip[1].trim();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("The ip adress is after " + ip + " split");
        URL url;
        try {
            url = new URL("https://api.ipinfodb.com/v3/ip-city/?key=" + key + "&ip=" + ip);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp = "";
            String temporaray = "";
            String temp_array[] = null;
            while (null != (strTemp = br.readLine())) {
                temporaray = strTemp;
            }
            temp_array = temporaray.split(";");
            System.out.println("Str length is " + temp_array.length);
            int length = temp_array.length;
            
            if (length == 11) {
                obj_Location_Use_Bean.setIp_address(ip);
                if (temp_array[3] != null) {
                    obj_Location_Use_Bean.setCountry_code(temp_array[3]);
                }
                if (temp_array[4] != null) {
                    obj_Location_Use_Bean.setCountry(temp_array[4]);
                }
                if (temp_array[5] != null) {
                    obj_Location_Use_Bean.setState(temp_array[5]);
                }
                if (temp_array[6] != null) {
                    obj_Location_Use_Bean.setCity(temp_array[6]);
                }
                if (temp_array[7] != null) {
                    obj_Location_Use_Bean.setZip(temp_array[7]);
                }
                if (temp_array[8] != null) {
                    obj_Location_Use_Bean.setLat(temp_array[8]);
                }
                if (temp_array[9] != null) {
                    obj_Location_Use_Bean.setLon(temp_array[9]);
                }
                if (temp_array[10] != null) {
                    obj_Location_Use_Bean.setUtc_offset(temp_array[10]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_Location_Use_Bean;
    }
    public static void main(String[] args) {
        Get_Location_From_IP obj_Get_Location_From_IP = new Get_Location_From_IP();
        Location_Use_Bean obj_Location_Use_Bean = obj_Get_Location_From_IP.get_ip_Details("49.213.63.255");
        System.out.println("IP Address--" + obj_Location_Use_Bean.getIp_address());
        System.out.println("Country Code-- " + obj_Location_Use_Bean.getIp_address());
        System.out.println("Country--" + obj_Location_Use_Bean.getCountry());
        System.out.println("State--" + obj_Location_Use_Bean.getState());
        System.out.println("City--" + obj_Location_Use_Bean.getCity());
        System.out.println("ZIP--" + obj_Location_Use_Bean.getZip());
        System.out.println("Lat--" + obj_Location_Use_Bean.getLat());
        System.out.println("Lon--" + obj_Location_Use_Bean.getLon());
        System.out.println("Offset--" + obj_Location_Use_Bean.getUtc_offset());
    }
}




//        String st ="2022-02-17";
//         Date date =Date.valueOf(st);
//      
//          String st1 ="2022-02-20";
//         Date d =Date.valueOf(st1);
//      
//          String s2 ="2022-03-20";
//         Date d1 =Date.valueOf(s2);
//        
//       
//        MyDB mc= new MyDB();
//          mc.getConn();
//          
//          User  u= new User(4, "this is test number 2");
//          
//         // Réservation r =new Réservation(d1, d, "this is asf_çufjçsudfsdçuf", 2, 4, 5, 1, u);
//          
////       // Paiement p =new Paiement(d,22.0,"enligne",3);
////       
////          
//  RéservationCRUD rc =new  RéservationCRUD();
//  //rc.insert(r);
//  
//    System.out.println(rc.readAll());
//        FactureService fs =new FactureService();
//        
//      //  fs.pdfversion();
//        //System.out.println(fs.readAll()); 
////   Facture f= new Facture(date,3546570,"fdsd");
////        
////        
////       Paiement p =new Paiement(d,21.0,"ert,kfnqefzergzrgkglnqfgklertg");
////       
////       fs.FactureInsert(f, p);
////       
//               
//     //  rc.insert(r);
// // rc.update(r);
//      //rc.delete(21);
//     //  Réservation r2 =new Réservation(18,date,d1,"hvhjfhkgfhjgchgv ",2,5,1,10,9,4); 
//      //rc.update(r2);
//    //   rc.delete(19);
//      /*
//        Réservation r1 =new Réservation(d1,d1f,"vol = Boeing 716 \n Hébergement = Garden Hotel  ",3,2,3);
//      
//       
//       
//       System.out.println(rc.afficherRéservation());
//      rc.ajouterResrvation2(r);
//       /* rc.SupprimerResrvation(9);
//        rc.SupprimerResrvation(11);
//        rc.SupprimerResrvation(10);
//         rc.SupprimerResrvation(1);
//        
//      //modifier
//    rc.ModifierResrvation(r2, 11);
//       
//           
//         Paiement p1 =new Paiement(10, date, 25.4, "heheheheh", 1);
//        PaiementCRUD pc =new   PaiementCRUD();
//       pc.insert(p);
//      pc.delete(6);
//      pc.update(p1);
//      //  pc.ajouterPaiement(p);
//       // pc.afficherPaiement();
//       // pc.SupprimerPaiement(4);
//        // Paiement p1 =new Paiement(date,2556,"en ligne",1);
//        // pc.ModifierPaiement(p1, 2);*/
//    //  Facture f =new Facture(Date date_ech, double Montant, String etat, int ID_rev, int ID_pai,Réservation Réservation ,Paiement p)
////   Paiement p1 =new Paiement(10, date, 25.4, "heheheheh", 1);
////   p1.getID_PAi();
////    Réservation r =new Réservation(21,date,d,"vol = Airbus \n Hébergement = defsfdfsdfsdfegegthtrkrlkg ",4,2,1,2,5,4);
////   Facture f= new Facture(date,10,"<aaaaaaaaaaaaaaaaaaaaaaaaaa",14,13);
////   FactureService g=new FactureService();
////   g.FactureInsert(f);
// 
    