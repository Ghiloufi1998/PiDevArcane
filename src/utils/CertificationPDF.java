/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Ghiloufi
 */
public class CertificationPDF {
    
    
  public static void main(String[] args) throws IOException {

         try {

            try (//location where PDF will be generated
                    FileOutputStream fos = new FileOutputStream("C:\\Users\\Ghiloufi\\Desktop\\hahahahahah.pdf")) {
                System.out.println("Password Protected File Generated.");
//creates an instance of PDF document
                Document doc = new Document();
//doc writer for the PDF
                PdfWriter writer = PdfWriter.getInstance(doc, fos);
         //      writer.setEncryption(USER_PASSWORD.getBytes(), OWNER_PASSWORD.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
//opens the PDF
                doc.open();
//adding paragraphs to the PDF
                doc.add(new Paragraph("     Above & Beyond              "));
                doc.add(new Paragraph("                                       "));
                doc.add(new Paragraph("Nom: "));
                doc.add(new Paragraph("Prenom: xxx-xxx-xxx-234"));
                doc.add(new Paragraph("Email:  Los Angeles"));
                doc.add(new Paragraph("Destination: 18743"));
                doc.add(new Paragraph("Date echeance: +1 (xxxx)-xxx-456"));
                doc.add(new Paragraph("Address: P.O. Box 1421, PC 111, CPO, New York (USA)"));
                doc.add(new Paragraph("Debit Card Number: xxxx-xxxx-xxxx-0987"));
                doc.add(new Paragraph("e-mail: rachel@gmial.com"));
                doc.add(new Paragraph("Toll Free Number: 18000xxxxx"));
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
