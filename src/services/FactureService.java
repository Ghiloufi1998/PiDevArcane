/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Facture;
import entities.Paiement;
import entities.Réservation;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author bensa
 */
public class FactureService {

    private static String USER_PASSWORD = "1234567";
    private static String OWNER_PASSWORD = "javatpoint";
//    public List afficherFacture() {
//        List ls = new ArrayList();
//        try {
//
//            String requete = "SELECT * From Réservation CROSS JOIN Facture Where Réservation.ID_rev=Facture.ID_fac";
//
//            Statement st = new ConnectionDB().getConn().createStatement();
//            ResultSet rs = st.executeQuery(requete);
//            while (rs.next()) {
//                Réservation f = new Réservation();
//                Facture o = new Facture();
//
//                f.setDate_deb(rs.getDate("Date_deb"));
//                f.setDate_fin(rs.getDate("Date_fin"));
//                f.setType(rs.getString("Type"));
//                f.setNbr_adultes(rs.getInt("Nbr_adultes"));
//                f.setNbr_enfants(rs.getInt("Nbr_enfants"));
//                o.setDate_ech(rs.getDate("Date_ech"));
//                o.setMontant(rs.getDouble("Montant_ttc"));
//                o.setEtat(rs.getString("Etat"));
//                o.setID_pai(rs.getInt("ID_Pai"));
//
//                ls.add(f);
//
//            }
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        return ls;
//
//    }

    public void FactureInsert(int x) {
        try {
            String req = " INSERT INTO facture (Date_ech, Montant_ttc, Etat, rev_ID, Pai_ID)"
                    + " VALUES (NULL,NULL,NULL,(select rev_ID from reservation order by rev_ID desc LIMIT 1),NULL);";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(req);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         Paiement p=new Paiement();
        try {
            String req = " INSERT INTO paiement(Date, Montant, Mode_Pay)"
                    + " VALUES (NULL,NULL,NULL);";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(req);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        try {
            String req = "  UPDATE facture SET Pai_ID= (select Pai_ID from paiement order by Pai_ID desc LIMIT 1) where ID_fac = (select ID_fac  from facture order by ID_fac desc LIMIT 1) ; ";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(req);

            ps.executeUpdate();
            System.out.println("votre Facture ajoutée avec succés ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    public void FactureSupp(int id) {
     
        try {
            String req1 = "DELETE FROM Facture WHERE ID_fac=" + id + ";";
            PreparedStatement ps1 = new MyDB().getConn().prepareStatement(req1);
            ps1.executeUpdate();
            System.out.println("votre Facture supprimée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
           try {
            String req = "DELETE FROM Paiement WHERE Pai_ID=" + id + ";";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(req);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
public void upadateprix(int X) {
        try {
            String req = " UPDATE facture SET Montant_ttc=" +X+ ",WHERE ID_fac=?";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(req);
            ps.executeUpdate();
            System.out.println("votre Facture modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void upadate(Facture f) {
        try {
            String req = " UPDATE facture SET Date_ech=" + f.getDate_ech() + ",Montant_ttc=" + f.getMontant() + ","
                    + "Etat=" + f.getEtat() + "WHERE ID_fac=?";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(req);
            ps.executeUpdate();
            System.out.println("votre Facture modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Facture> readAll() {

        List<Facture> ls = new ArrayList();
        try {

            String requete = "SELECT * From Facture,reservation,Paiement";
            Statement st = new MyDB().getConn().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Facture f = new Facture();
                f.setID_fac(rs.getInt("ID_fac"));
                f.setDate_ech(rs.getDate("Date_ech"));
                f.setMontant(rs.getDouble("Montant_ttc"));
                f.setEtat(rs.getString("Etat"));
                f.getR().setID_rev(rs.getInt("reservation.ID_rev"));
                f.getR().setDate_deb(rs.getDate("reservation.Date_Deb"));
                f.getR().setDate_fin(rs.getDate("reservation.Date_fin"));
                f.getR().setType("reservation.Type");
                f.getR().setNbr_adultes(rs.getInt("reservation.Nbr_adultes"));
                f.getR().setNbr_enfants(rs.getInt("reservation.Nbr_enfants"));
                f.getR().setID_heb(rs.getInt("reservation.ID_heb"));
                f.getR().setID_Vol(rs.getInt("reservationID_Vol"));
                f.getP().setID_PAi(rs.getInt("Paiement.ID_Pai"));
                f.getP().setDate(rs.getDate("Paiement.Date"));
                f.getP().setMontant(rs.getInt("Paiement.Montant"));
                f.getP().setMode_Pay(rs.getString("Paiement.Mode_Pay"));

                ls.add(f);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ls;

    }
     
     public int getdprix() throws SQLException {
         int y=0;
     
        try {
            String requete = " Select Montant_ttc from facture order by  ID_fac desc LIMIT 1 ";
                 Statement st = new MyDB().getConn().createStatement();
                ResultSet rs = st.executeQuery(requete);
           
              while (rs.next()) {
              
               
               y=rs.getInt("Montant_ttc");

              }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
        return y;
        
     }
      
     public String ge() throws SQLException {
         String u="";
     
        try {
            String requete = " Select Montant_ttc from facture order by  ID_fac desc LIMIT 1 ";
                 Statement st = new MyDB().getConn().createStatement();
                ResultSet rs = st.executeQuery(requete);
           
              while (rs.next()) {
              
               
               u=rs.getString("Montant_ttc");

              }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
        return u;
        
     }
    
     public void updateIdprix(int x) {
        try {
            String requete = " UPDATE facture SET Montant_ttc=? order by ID_fac desc LIMIT 1 ";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(requete);
            ps.setInt(1, x);
            ps.executeUpdate();
            System.out.println("votre facture modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
       public void updateIdDate(Date x) {
        try {
            String requete = " UPDATE facture SET Date_ech=? order by ID_fac desc LIMIT 1 ";
            PreparedStatement ps = new MyDB().getConn().prepareStatement(requete);
            ps.setDate(1,x);
            ps.executeUpdate();
            System.out.println("votre facture modifée avec succés ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     

//facture.ID_fac = reservation.ID_rev=Paiement.ID_Pai;
    public void pdfversion() throws SQLException {
String requete ="SELECT * From reservation ,facture,hebergement,vol where reservation.Hebergement_id=hebergement.Hebergement_id AND reservation.vol_ID=vol.Vol_id order by ID_fac desc LIMIT 1";
 Statement st = new MyDB().getConn().createStatement();
            ResultSet rs = st.executeQuery(requete); 
            while(rs.next()){
try {

            try (//location where PDF will be generated
                    FileOutputStream fos = new FileOutputStream("C:\\Users\\bensa\\Desktop\\hgze.pdf")) {
                System.out.println("Password Protected File Generated.");
//creates an instance of PDF document
                Document doc = new Document();
//doc writer for the PDF
                PdfWriter writer = PdfWriter.getInstance(doc, fos);
                writer.setEncryption(USER_PASSWORD.getBytes(), OWNER_PASSWORD.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
//opens the PDF
                doc.open();
//adding paragraphs to the PDF
                doc.add(new Paragraph("     Above & Beyond              "));
                doc.add(new Paragraph("                                       "));
                doc.add(new Paragraph("Nom: "));
                doc.add(new Paragraph("Prenom: xxx-xxx-xxx-234"));
                doc.add(new Paragraph("Email:  Los Angeles"));
                doc.add(new Paragraph("Destination: "+rs.getString("Destination")));
                doc.add(new Paragraph("Date début"+rs.getDate("Date_Deb")));
                doc.add(new Paragraph("Date echeance: "+rs.getDate("Date_ech")));
                doc.add(new Paragraph("Address du Hébergement :"+rs.getString("Adresse")));
                doc.add(new Paragraph("Nom de aéroport:"+rs.getString("Départ")));
                doc.add(new Paragraph("Monatnt:"+rs.getString("Montant_ttc")));
                
//closes the document
                doc.close();
            }
        } //catch the exception if any    //catch the exception if any   
        catch (DocumentException | IOException e) {
//prints the occurred exception   
            System.err.println(e.getMessage());
        }
    }
}
}
