/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.RController;
import Entities.Reservation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author 21696
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private TableColumn<Reservation, Integer> dure;
    @FXML
    private TableView<Reservation> reservTable;
    @FXML
    private TableColumn<Reservation, Integer> id_re;
    @FXML
    private TableColumn<Reservation, Integer> montant;
    @FXML
    private TextField id_res;
    @FXML
    private TextField dure_t;
    @FXML
    private TextField montant_t;
    @FXML
    private TextField id_v;
    @FXML
    private TableColumn<Reservation, Integer> idEv;
Connection mc;
    PreparedStatement ste;
    
    
     ObservableList<Reservation>reservList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_res.setVisible(false);
          mc=MaConnexion.getInstance().getCnx();

        reservList = FXCollections.observableArrayList();
        
        String sql="select * from reservation_ev ";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Reservation r = new Reservation();
                r.setId_re(rs.getInt("id_re"));
                r.setId_even(rs.getInt("id_even"));
                r.setMontant(rs.getInt("montant"));
                r.setDure(rs.getInt("dure"));
               
                  
                     

                reservList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

         
         id_re.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("id_re"));
         idEv.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("id_even"));
         montant.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("montant"));
         dure.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("dure"));
         
       
       





        
        reservTable.setItems(reservList);

    }    

    @FXML
    private void getSelected(MouseEvent event) {
           Reservation clickedEven = reservTable.getSelectionModel().getSelectedItem();
         id_res.setText(String.valueOf(clickedEven.getId_re()));
         id_v.setText(String.valueOf(clickedEven.getId_even()));
         montant_t.setText(String.valueOf(clickedEven.getMontant()));
        dure_t.setText(String.valueOf(clickedEven.getDure()));

       
        
        
        
    }

    @FXML
    private void refreshChamp(MouseEvent event) {
         
             id_res.setText(null);
          
             id_v.setText(null);
             montant.setText(null);
              dure.setText(null);

    }

    @FXML
    private void print(MouseEvent event) throws SQLException, FileNotFoundException, DocumentException, IOException {
         String sql = "SELECT * from reservation_ev";
    ste=mc.prepareStatement(sql);
    ResultSet rs=ste.executeQuery();

    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("./Reservation.pdf"));

    doc.open();
    //Image image = Image.getInstance("C:\\Users\\drwhoo\\Desktop\\Projet3eme\\JavaApplication\\src\\HolidaysHiatus\\images\\logo.png");

    //document.add(new Paragraph("test\n  test"));
   // doc.add(image);
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" ***************************************** Les Offres ************************************* "));
    doc.add(new Paragraph("   "));

    PdfPTable table = new PdfPTable(1);
    table.setWidthPercentage(50);
    PdfPCell cell;

    cell = new PdfPCell(new Phrase("les reservations", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);

    table.addCell(cell);

//    cell = new PdfPCell(new Phrase("Classement", FontFactory.getFont("Comic Sans MS", 15)));
//    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//    table.addCell(cell);

    while (rs.next()) {

        Reservation r = new Reservation();
      //  e.setId(rs.getInt("id"));
        r.setDure(rs.getInt("dure"));
       // e.setClassement(rs.getInt("classement"));
       
     
     cell = new PdfPCell(new Phrase(r.getDure()));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);
        
        
//        cell = new PdfPCell(new Phrase(e.getClassement()));
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//        table.addCell(cell);
        
        
        //Image image1 = Image.getInstance("C:\\Users\\drwhoo\\Desktop\\Projet3eme\\SymfonyApplication\\public\\uploads\\" + c.getLien_icon());
        //PdfPCell cell1 = new PdfPCell(image1, true);

      //  table.addCell(cell1);

    }

    doc.add(table);
    doc.close();
    Desktop.getDesktop().open(new File("./Reservation.pdf"));
        
        
      
        
    }

    @FXML
    private void addReservation(MouseEvent event) {
         Reservation r1= new Reservation();
        
        RController rq2 = new RController();
       
        int id_re = Integer.parseInt(id_res.getText());

        int id_even= Integer.parseInt(id_v.getText());
        int montant = Integer.parseInt(montant_t.getText());
        int dure = Integer.parseInt(dure_t.getText());
        
        
         if (dure_t.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Insérer toutes les informations avant de valider l'insertion");
             alert.showAndWait();           
         }else{
             
             Reservation r=new Reservation(1,id_even,montant,dure);
             RController rc = new RController();
             rc.ajouterReservation(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("reservation Ajoutée!");
                alert.showAndWait();             
        
        
         }
         
          refresh();
          
          
          id_res.setText(null);
          id_v.setText(null);
          montant_t.setText(null);
          dure_t.setText(null);
          
        
        
        
        
    }

    @FXML
    private void updateReservation(MouseEvent event) {
        Reservation clickedEvent = reservTable.getSelectionModel().getSelectedItem();
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
                 
        
        
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
                
        try{
             mc=MaConnexion.getInstance().getCnx();
            String value1 = id_res.getText();
            String value2 = id_v.getText();
            String value3 = montant_t.getText();
            String value4 = dure_t.getText();
             
             
             String sql = "update reservation_ev set id_re = '"+value1+"', id_even = '"+value2+"', montant='"+value3+"', dure = '"+value4+"' where id_re ='"+value1+"'";
             ste=mc.prepareStatement(sql);
             ste.execute();
            JOptionPane.showMessageDialog(null, "reservation modifié");
        }catch(Exception r){
               JOptionPane.showMessageDialog(null,r);

        }
        }else{
         id_res.setText(null);
         id_v.setText(null);
         montant.setText(null);
         dure_t.setText(null);
            
        }
        
        
        refresh();
        
        
    }

    @FXML
    private void deleteReservation(MouseEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
                 
        
        
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        
        
        
        
        mc=MaConnexion.getInstance().getCnx();
         String sql = "delete from reservation_ev where id_re = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, id_res.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "reservation supprimé" );
        
           refresh();
            
        }else{
            
            
         id_re.setText(null);
         idEv.setText(null);
         montant.setText(null);
         dure.setText(null);
          
        
            
        }
        
        
    }

    @FXML
    private void rechercheRq(MouseEvent event) {
         reservTable.setItems(reservList);
        FilteredList<Reservation> filteredData = new FilteredList<>(reservList, b -> true);  
 search.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if(String.valueOf(person.getDure()).indexOf(lowerCaseFilter)!=-1) {
     return true; // Filter matches username
    } else if (String.valueOf(person.getId_even()).indexOf(lowerCaseFilter)!=-1) {
     return true; // Filter matches passwo} rd
    
    } else if (String.valueOf(person.getMontant()).indexOf(lowerCaseFilter)!=-1) {
     return true; // Filter matches passwo} rd
    
    }
    else if (String.valueOf(person.getId_re()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Reservation> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(reservTable.comparatorProperty());  
  reservTable.setItems(sortedData);     
        
    }
    
    
      public void refresh(){
        
         reservList.clear();
       
          
          mc=MaConnexion.getInstance().getCnx();

        reservList = FXCollections.observableArrayList();
        
        String sql="select * from reservation_ev order by dure ASC";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Reservation r = new Reservation();
                 r.setId_re(rs.getInt("id_re"));
                r.setId_even(rs.getInt("id_even"));
                r.setMontant(rs.getInt("montant"));
                r.setDure(rs.getInt("dure"));
                

                 

               reservList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         reservTable.setItems(reservList);
        
        
        
    }

    
    
}
