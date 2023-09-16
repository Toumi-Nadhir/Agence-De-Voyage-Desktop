/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.OController;
import Entity.Evennement;
import Entity.Offre;
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
 * @author mdhah
 */
public class OffreController implements Initializable {

    @FXML
    private TextField search;
    
    
    @FXML
    private TextField dure;
   
    @FXML
    private TableView<Offre> offreTable;
    @FXML
    private TextField id_off;
   
    private TableColumn<Offre, Integer> dure_o;
    @FXML
    private TableColumn<Offre, Integer> id_u;

      Connection mc;
    PreparedStatement ste;
    
    
     ObservableList<Offre>offreList;
    @FXML
    private TextField id_user;
    @FXML
    private Text id_userT;
    @FXML
    private TableColumn<Offre, Integer> duree;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        id_off.setVisible(false);
         
          mc=MaConnexion.getInstance().getCnx();

        offreList = FXCollections.observableArrayList();
        
        String sql="select * from offre ";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Offre o = new Offre();
                o.setId_offre(rs.getInt("id_offre"));
//                o.getId_user(rs.getInt("id_user"));
//                o.getDure(rs.getInt("dure"));
                o.setDure(rs.getInt("dure"));
                    o.setId(rs.getInt("id"));
                     

                offreList.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

         duree.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("dure"));
         id_u.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("id"));
       
       





        
        offreTable.setItems(offreList);


        
        
    }    

    @FXML
    private void getSelected(MouseEvent event) {
        
           Offre clickedEven = offreTable.getSelectionModel().getSelectedItem();
         id_off.setText(String.valueOf(clickedEven.getId_offre()));
        
         dure.setText(String.valueOf(clickedEven.getDure()));

        id_user.setText(String.valueOf(clickedEven.getId()));
        
    }

    @FXML
    private void refreshChamp(MouseEvent event) {
        
           
             id_off.setText(null);
          
         dure.setText(null);
          id_u.setText(null);
    }

    @FXML
    private void print(MouseEvent event) throws IOException, SQLException, DocumentException {
        
        
           String sql = "SELECT * from offre";
    ste=mc.prepareStatement(sql);
    ResultSet rs=ste.executeQuery();

    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("./Offre.pdf"));

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

    cell = new PdfPCell(new Phrase("Planification des offres", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);

    table.addCell(cell);

//    cell = new PdfPCell(new Phrase("Classement", FontFactory.getFont("Comic Sans MS", 15)));
//    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//    table.addCell(cell);

    while (rs.next()) {

        Offre o = new Offre();
      //  e.setId(rs.getInt("id"));
        o.setDure(rs.getInt("dure"));
       // e.setClassement(rs.getInt("classement"));
       
     
     cell = new PdfPCell(new Phrase(o.getDure()));
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
    Desktop.getDesktop().open(new File("./Offre.pdf"));
        
        
      
        
    }


    @FXML
    private void rechercheOq(MouseEvent event) {
         offreTable.setItems(offreList);
        FilteredList<Offre> filteredData = new FilteredList<>(offreList, b -> true);  
 search.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if(String.valueOf(person.getDure()).indexOf(lowerCaseFilter)!=-1) {
     return true; // Filter matches username
    } else if (String.valueOf(person.getId_offre()).indexOf(lowerCaseFilter)!=-1) {
     return true; // Filter matches password
    
    }
    else if (String.valueOf(person.getId()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Offre> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(offreTable.comparatorProperty());  
  offreTable.setItems(sortedData);     
        
        
        
    }

    @FXML
    private void addOffre(MouseEvent event) {
        
          Offre o1= new Offre();
        
        OController oq2 = new OController();
       
               int id_offre = Integer.parseInt(id_off.getText());

        int id = Integer.parseInt(id_u.getText());
            int duree = Integer.parseInt(dure.getText());
        
        
         if (dure.getText().isEmpty()){
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
             
             Offre o=new Offre(1,1,duree);
             OController oc = new OController();
             oc.ajouterOffre(o);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("Offre Ajoutée!");
                alert.showAndWait();             
        
        
         }
         
          refresh();
          
          
               id_off.setText(null);
          id_u.setText(null);
         dure.setText(null);
          
        
        
        
        
    }

    @FXML
    private void updateOffre(MouseEvent event) {
        
          Offre clickedEvent = offreTable.getSelectionModel().getSelectedItem();
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
                 
        
        
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
                
        try{
             mc=MaConnexion.getInstance().getCnx();
              String value1 = id_off.getText();
            String value2 = id_u.getText();
             String value3 = dure.getText();
             
             
             String sql = "update offre set id_offre = '"+value1+"', id = '"+value2+"', dure='"+value3+"' where id_offre ='"+value1+"'";
             ste=mc.prepareStatement(sql);
             ste.execute();
            JOptionPane.showMessageDialog(null, "Offre modifié");
        }catch(Exception o){
               JOptionPane.showMessageDialog(null,o);

        }
        }else{
         id_off.setText(null);
         dure.setText(null);
         id_u.setText(null);
            
        }
        
        
        refresh();
        
        
    }

    @FXML
    private void deleteOffre(MouseEvent event) throws SQLException {
        
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
                 
        
        
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        
        
        
        
        mc=MaConnexion.getInstance().getCnx();
         String sql = "delete from offre where id_offre = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, id_off.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "Offre supprimé" );
        
           refresh();
            
        }else{
            
            
             id_off.setText(null);
         
         dure.setText(null);
          id_u.setText(null);
        
            
        }
        
        
        
        
    }

     public void refresh(){
        
         offreList.clear();
       
          
          mc=MaConnexion.getInstance().getCnx();

        offreList = FXCollections.observableArrayList();
        
        String sql="select * from offre order by dure ASC";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Offre o = new Offre();
                 o.setId_offre(rs.getInt("id_offre"));
                
                o.setDure(rs.getInt("dure"));
                o.setId(rs.getInt("id"));

                 

               offreList.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         offreTable.setItems(offreList);
        
        
        
    }



     
     
     
     
     
     
     
     
     
     

//      //  public void recherEvent(){
//        FilteredList<Evennement>filteredData = new FilteredList<>(eventList, b->true);
//        
//     //   Equipe equipe = new Equipe();
//        search.textProperty().addListener((observable, oldValue, newValue)->{
//            
//            filteredData.setPredicate(evennement->{
//                
//                if(newValue == null || newValue.isEmpty()){
//                    return true;
//                }
//                
//                String lowerCaseFilter = newValue.toLowerCase();
//                
//                if(evennement..toLowerCase().indexOf(lowerCaseFilter) != -1){
//                    return true;
//                }else if(String.valueOf(eve).indexOf(lowerCaseFilter) != -1){
//                    return true;
//                }else{
//                return false;
//                }
//                
//                
//            });
//            
//            
//            
//            
//        });
//        
//        SortedList<Evennement>sortedData = new SortedList<>(filteredData);
//        
//        sortedData.comparatorProperty().bind(evenTable.comparatorProperty());
//        
//        evenTable.setItems(sortedData);
//        
//        }  

}
