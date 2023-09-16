/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Services.ChauffeurService;
import Services.VoitureService;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entites.Chauffeur;
import entites.Reservation_voiture;
import entites.Voiture;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author ahmed
 */
public class PdfGeneration  extends Thread{
     @Override
    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void ReservationPDF(ArrayList<Reservation_voiture> List) {
        try {
            Document document = new Document();
            //Simple Paragraph Pdf
            String desktopPath = System.getProperty("user.home");
            System.out.print(desktopPath.replace("\\", "/"));
            PdfWriter.getInstance(document, new FileOutputStream(desktopPath+"\\Reservation.pdf"));
            document.open();
            //document.add(new Paragraph("exemple"));
            //document.close();

            //-------------------------------------------------------
            //Add Table
            Font font = new Font();
            font.setColor(new BaseColor(255, 255, 255));
            font.setSize(10);
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            PdfPCell c = new PdfPCell(new Phrase("Reference", font));
            c.setBackgroundColor(new BaseColor(76, 175, 80));
                
            c.setBorder(Rectangle.NO_BORDER);
            table.addCell(c);
            c = new PdfPCell(new Phrase("Voiture", font));
            c.setBackgroundColor(new BaseColor(76, 175, 80));
            c.setBorder(Rectangle.NO_BORDER);
            table.addCell(c);
            c = new PdfPCell(new Phrase("Chauffeur", font));
            c.setBackgroundColor(new BaseColor(76, 175, 80));
            c.setBorder(Rectangle.NO_BORDER);
            table.addCell(c);
            c = new PdfPCell(new Phrase("Date de debut", font));
            c.setBackgroundColor(new BaseColor(76, 175, 80));
            c.setBorder(Rectangle.NO_BORDER);
            table.addCell(c);
            c = new PdfPCell(new Phrase("Date fin", font));
            c.setBackgroundColor(new BaseColor(76, 175, 80));
            c.setBorder(Rectangle.NO_BORDER);
            table.addCell(c);
            
            table.setHeaderRows(1);
            Font font2 = new Font();
            font2.setColor(new BaseColor(0, 0, 0));
            font2.setSize(10);
            for (int i = 0; i < List.size(); i++) {
                Voiture v = new VoitureService().chercherVoiture(List.get(i).getId_voiture());
                Chauffeur ch = new ChauffeurService().chercherChauffeur(List.get(i).getId_chauffeur());
                if (i % 2 != 0) {
                    
                    c = new PdfPCell(new Phrase(String.valueOf(List.get(i).getId_rv()), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    table.addCell(c);
                    c = new PdfPCell(new Phrase(v.getMarque(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    table.addCell(c);
                    c = new PdfPCell(new Phrase(ch.getNom_chauffeur()+" "+ch.getPrenom_chauffeur(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    table.addCell(c);
                    c = new PdfPCell(new Phrase(List.get(i).getDateDebut().toLocalDate().toString(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    table.addCell(c);
                    c = new PdfPCell(new Phrase(List.get(i).getDatefin().toLocalDate().toString(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    table.addCell(c);
                  
                }else{
                    c = new PdfPCell(new Phrase(String.valueOf(List.get(i).getId_rv()), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    c.setBackgroundColor(new BaseColor(242, 242, 242));
                    table.addCell(c);
                    c = new PdfPCell(new Phrase(v.getMarque(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    c.setBackgroundColor(new BaseColor(242, 242, 242));
                    table.addCell(c);
                    c = new PdfPCell(new Phrase(ch.getNom_chauffeur()+" "+ch.getPrenom_chauffeur(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    c.setBackgroundColor(new BaseColor(242, 242, 242));
                    table.addCell(c);
                    c = new PdfPCell(new Phrase(List.get(i).getDateDebut().toLocalDate().toString(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    c.setBackgroundColor(new BaseColor(242, 242, 242));
                    table.addCell(c);
                    c = new PdfPCell(new Phrase(List.get(i).getDatefin().toLocalDate().toString(), font2));
                    c.setBorder(Rectangle.NO_BORDER);
                    c.setBackgroundColor(new BaseColor(242, 242, 242));
                    table.addCell(c);
                   
                }

            }
            Paragraph parag=new Paragraph("Liste des résérvations effectuées");
            parag.setAlignment(Element.ALIGN_CENTER);
            parag.setSpacingAfter(50f);
            document.add(parag);
            document.add(table);
            document.close();
            Desktop.getDesktop().open(new File(desktopPath+"\\Reservation.pdf"));

        } catch (Exception e) {

        }
    }
    
}
