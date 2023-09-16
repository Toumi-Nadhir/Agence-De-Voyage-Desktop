/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import GUI.AbController;
import Entities.Absence;




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
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author 21696
 */
public class AbsenceController implements Initializable {

    @FXML
    private TableView<Absence> absTable;
    @FXML
    private TableColumn<Absence, Integer> col_id_ab;
    @FXML
    private TableColumn<Absence, Integer> col_id;
    @FXML
    private TableColumn<Absence, String> col_etat_absence;
    @FXML
    private TableColumn<Absence, String> col_date_absence;
    @FXML
    private  TextField etat_absence;
    @FXML
    private TextField date_absence;
    @FXML
    private TextField id;
    @FXML
    private TextField search;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
   
    
Connection mc;
    PreparedStatement ste;
    
    
     ObservableList<Absence>absenceList;
    /**
     * Initializes the controller class.
     */
       @Override
    public void initialize(URL url, ResourceBundle rb) {
      
      
        
        
          mc=MaConnexion.getInstance().getCnx();

        absenceList = FXCollections.observableArrayList();
        
        String sql="select * from absence ";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Absence a = new Absence();
                a.setId_ab(rs.getInt("id_ab"));
               
               
                a.setId(rs.getInt("id"));
                 a.setEtat_ab(rs.getString("etat_ab"));
                 a.setDate_ab(rs.getString("date_ab"));


                absenceList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

        col_id_ab.setCellValueFactory(new PropertyValueFactory<Absence, Integer>("id_ab"));
         col_id.setCellValueFactory(new PropertyValueFactory<Absence, Integer>("id"));
        
        
          col_etat_absence.setCellValueFactory(new PropertyValueFactory<Absence, String>("etat_ab"));
        
         col_date_absence.setCellValueFactory(new PropertyValueFactory<Absence, String>("date_ab"));





        
        absTable.setItems(absenceList);


        
        
        
        
        
    }      
 @FXML
    private void getSelected(MouseEvent event) {
        
           Absence clickedEven = absTable.getSelectionModel().getSelectedItem();
         
        id.setText(String.valueOf(clickedEven.getId()));
         etat_absence.setText(String.valueOf(clickedEven.getEtat_ab()));
        date_absence.setText(String.valueOf(clickedEven.getDate_ab()));

       
        
    }
    
    @FXML
    private void refreshChamp(MouseEvent event) {
        
           
             id.setText(null);
             etat_absence.setText(null);
          date_absence.setText(null);
       
    }

    @FXML
    private void print(MouseEvent event) throws IOException, SQLException, DocumentException {
        
        
           String sql = "SELECT * from absence";
    ste=mc.prepareStatement(sql);
    ResultSet rs=ste.executeQuery();

    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("./HoraireAbsence.pdf"));

    doc.open();
    //Image image = Image.getInstance("C:\\Users\\drwhoo\\Desktop\\Projet3eme\\JavaApplication\\src\\HolidaysHiatus\\images\\logo.png");

    //document.add(new Paragraph("test\n  test"));
   // doc.add(image);
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" ***************************************** Horaire Des absences ************************************* "));
    doc.add(new Paragraph("   "));

    PdfPTable table = new PdfPTable(1);
    table.setWidthPercentage(50);
    PdfPCell cell;

    cell = new PdfPCell(new Phrase("Date des absences", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);

    table.addCell(cell);

//    cell = new PdfPCell(new Phrase("Classement", FontFactory.getFont("Comic Sans MS", 15)));
//    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//    table.addCell(cell);

    while (rs.next()) {

       Absence a = new Absence();
      // e.setId(rs.getInt("id"));
        a.setDate_ab(rs.getString("date_ab"));
       // e.setClassement(rs.getInt("classement"));
       
      
        cell = new PdfPCell(new Phrase(a.getDate_ab(), FontFactory.getFont("Comic Sans MS", 12)));
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
    Desktop.getDesktop().open(new File("./HoraireAbsence.pdf"));
        
        
      
        
    }


    @FXML
    private void rechercheAq(MouseEvent event) {
        

        
           //prodList = MaConnexion.getDatausers();
        absTable.setItems(absenceList);
        FilteredList<Absence> filteredData = new FilteredList<>(absenceList, b -> true);  
 search.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getDate_ab().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (person.getEtat_ab().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    
    }
    else if (String.valueOf(person.getId()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Absence> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(absTable.comparatorProperty());  
  absTable.setItems(sortedData);     
        
    }

    @FXML
    private void addabs(MouseEvent event) {
         Absence e1= new Absence();
        
        AbController eq2 = new AbController();
         
        int id_user = Integer.parseInt(id.getText());
         String etat = etat_absence.getText();
         String date = date_absence.getText();
           
        
        
         if (id.getText().isEmpty()|etat.isEmpty()|date.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Insérer toutes les informations avant de valider l'insertion");
             alert.showAndWait();
               
//         } else if (eq2.checkLivraison(e1)==true){
//              Alert alert = new Alert(Alert.AlertType.ERROR);
//             alert.setHeaderText("ERROR");
//             alert.setContentText("Element existant");
//             alert.showAndWait();


           
         }else{
             
            Absence e=new Absence(1,id_user,etat, date);
             AbController ec = new AbController();
             ec.ajouterAbsence(e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("Absence Ajoutée!");
                alert.showAndWait();             
        
        
         }
         
          refresh();
          
          
               id.setText(null);
          etat_absence.setText(null);
        date_absence.setText(null);
         
          
        
    }

     @FXML
    private void deleteAbs(MouseEvent event) throws SQLException {
        
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
                 
        
        
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        
        
        
        
        mc=MaConnexion.getInstance().getCnx();
         String sql = "delete from absence where id = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, id.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "Absence supprimé" );
        
           refresh();
            
        }else{
            
          id.setText(null);
          etat_absence.setText(null);
        date_absence.setText(null);
         
            
        }
        
        
        
        
    }

    @FXML
    private void updateAbs(MouseEvent event) {
         Absence clickedEvent = absTable.getSelectionModel().getSelectedItem();
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
                 
        
        
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
                
        try{
             mc=MaConnexion.getInstance().getCnx();
              
               String value1 = id.getText();
             String value2 = etat_absence.getText();
             String value3 = date_absence.getText();
             
             
             
             String sql = "update absence set id= '"+value1+"', etat_ab='"+value2+"', date_ab='"+value3+"',' where id ='"+value1+"'";
             ste=mc.prepareStatement(sql);
             ste.execute();
            JOptionPane.showMessageDialog(null, "Absence modifié");
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        }else{
               id.setText(null);
          etat_absence.setText(null);
        date_absence.setText(null);
         
          
        }
        
        
        refresh();
        
    }
    public void refresh(){
        
         absenceList.clear();
       
          
          mc=MaConnexion.getInstance().getCnx();

        absenceList = FXCollections.observableArrayList();
        
        String sql="select * from absence";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Absence e = new Absence();
                e.setId_ab(rs.getInt("id_ab"));
               
               
                e.setId(rs.getInt("id"));
                 e.setEtat_ab(rs.getString("etat_ab"));
                 e.setDate_ab(rs.getString("date_ab"));

                absenceList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         absTable.setItems(absenceList);
        
        
        
    }
     public void textfieldDesign(){
        
        if(id.isFocused()){
            
            id.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            etat_absence.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            date_absence.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            search.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
           
            
        }else if(etat_absence.isFocused()){
            
            id.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            etat_absence.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
            date_absence.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            search.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
           
        }else if(date_absence.isFocused()){
            
            id.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            etat_absence.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
           date_absence.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
           search.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
           
            
        }else if(search.isFocused()){
            
            id.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
            etat_absence.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
           date_absence.setStyle("-fx-border-width:1px; -fx-background-color:transparent");
           search.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
           
            
        }}
     public void defaultId(){
        
        id.setStyle("-fx-border-width:2px; -fx-background-color:#fff");
        
    }
    
    
}
