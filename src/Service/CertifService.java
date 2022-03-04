/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Offres;
import utils.MyDB;
import Entities.User;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.scenario.effect.ImageData;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ghiloufi
 */
public class CertifService {

    private int xd;
    Connection cnx;

    public CertifService() {
        cnx = MyDB.getInstance().getConnection();
    }

    public User getUserlogged() throws SQLException {
        User ut = new User();

        String req = "SELECT * FROM logger ";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            xd = rs.getInt("id");

            String req2 = "SELECT * FROM user WHERE id='" + xd + "'";
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(req2);
            while (rs2.next()) {
                ut = new User();

                ut.setId(rs2.getInt("id"));
                ut.setName(rs2.getString("nom"));
                ut.setFname(rs2.getString("prénom"));
                ut.setGender(rs2.getString("sexe"));
                ut.setBirthday(rs2.getDate("Date_de_naissance"));
                ut.setAdresse(rs2.getString("Adresse"));
                ut.setEmail(rs2.getString("Email"));
                ut.setPassword(rs2.getString("mot_de_passe"));
                ut.setRole(rs2.getString("Role"));
                ut.setImage(rs2.getString("image"));
                ut.setNb_point(rs2.getInt("nombre_de_points"));
                //   ut.setId_offre(rs2.getInt("id_offre"));
                //   ut.getOffre().setDescription(rs2.getString("Description"));
             
                
         

        }
 
        }
        return ut;
       
        
    }

    public void CertifPDF(int score) throws SQLException {
        try {

            try (//location where PDF will be generated
                    FileOutputStream fos = new FileOutputStream("C:\\Users\\Ghiloufi\\Desktop\\Certification\\Certification "+getUserlogged().getFname()+".pdf")) {
                System.out.println(" File Generated.");
//creates an instance of PDF document
                Document doc = new Document();
//doc writer for the PDF
                PdfWriter writer = PdfWriter.getInstance(doc, fos);
                //      writer.setEncryption(USER_PASSWORD.getBytes(), OWNER_PASSWORD.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
//opens the PDF
                doc.open();
//adding paragraphs to the PDF
               
      // Creating an ImageData object       
    Image img = Image.getInstance("C:\\Users\\Ghiloufi\\Desktop\\logo try 1-01.png");
      // Adding image to the document     
      img.setAbsolutePosition(400f, 400f);
img.scaleToFit(200f, 200f);
                // Adding image to the document       
                doc.add(img);
                doc.addTitle("Certification De Réussite De"+ getUserlogged().getFname());
                doc.add(new Paragraph("              Merci pour votre participation au notre formation                  "));
               
          //      doc.add(new Image("C:\\Users\\Ghiloufi\\Desktop\\logo try 1-01.png"));
                doc.add(new Paragraph());
                doc.add(new Paragraph("Email: "+ getUserlogged().getEmail()));
                doc.add(new Paragraph("Bravo vous avez completer votre Formation de communication"));
                doc.add(new Paragraph("Votre Score est : " + score ));
              
//closes the document
                doc.close();
            }
        } //catch the exception if any    //catch the exception if any   
        catch (DocumentException | IOException e) {
//prints the occurred exception   
            System.err.println(e.getMessage());
        }
        //"C:\\Users\\Ghiloufi\\Desktop\logo try 1-01.png"
    }
}
