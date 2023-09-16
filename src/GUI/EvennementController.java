/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.EController;
import Entity.Evennement;
import Models.APIConnector;
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
import java.net.MalformedURLException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author mdhah
 */
public class EvennementController implements Initializable {

    @FXML
    private TextField search;
    @FXML
    private TextField detail;
    @FXML
    private TextField datedeb;
    @FXML
    private TextField idEv;
    @FXML
    private TextField dure;
    @FXML
    private TextField commentaire;
    @FXML
    private TableView<Evennement> evenTable;
    @FXML
    private TableColumn<Evennement, Integer> idEven;
    @FXML
    private TableColumn<Evennement, String> detailEv;
    @FXML
    private TableColumn<Evennement, String> dateEv;
    @FXML
    private TableColumn<Evennement, Integer> dureEv;
    @FXML
    private TableColumn<Evennement, String> commEv;

      Connection mc;
    PreparedStatement ste;
    
    
     ObservableList<Evennement>eventList;
    @FXML
    private TextField city;
    @FXML
    private Text affcihe;
    private final String cityAPI = "https://www.metaweather.com/api/location/search/?query=";

    private final String weatherAPI = "https://www.metaweather.com/api/location/";
    @FXML
    private Button show;
    @FXML
    private Button corona;

    /**
     * Initializes the controller class.
     */
   
     @Override
    public void initialize(URL url, ResourceBundle rb) {
      idEv.setVisible(false);
      idEven.setVisible(false);
        
        
          mc=MaConnexion.getInstance().getCnx();

        eventList = FXCollections.observableArrayList();
        
        String sql="select * from evenement order by duree_ev ASC";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Evennement e = new Evennement();
                e.setId_even(rs.getInt("id_even"));
                e.setDetail(rs.getString("detail"));
                e.setDate_debut(rs.getString("date_debut"));
                e.setDuree_ev(rs.getInt("duree_ev"));
                 e.setCommantaire(rs.getString("commantaire"));


                eventList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

        
         idEven.setCellValueFactory(new PropertyValueFactory<Evennement, Integer>("id_even"));
        detailEv.setCellValueFactory(new PropertyValueFactory<Evennement, String>("detail"));
        // dateEv.setCellValueFactory(new PropertyValueFactory<Evennement, String>("date_debut"));
          dateEv.setCellValueFactory(new PropertyValueFactory<Evennement, String>("date_debut"));
        dureEv.setCellValueFactory(new PropertyValueFactory<Evennement, Integer>("duree_ev"));
         commEv.setCellValueFactory(new PropertyValueFactory<Evennement, String>("commantaire"));





        
        evenTable.setItems(eventList);


        
        
        
        
        
    }    

    @FXML
    private void getSelected(MouseEvent event) {
        
           Evennement clickedEven = evenTable.getSelectionModel().getSelectedItem();
         idEv.setText(String.valueOf(clickedEven.getId_even()));
        detail.setText(String.valueOf(clickedEven.getDetail()));
         datedeb.setText(String.valueOf(clickedEven.getDate_debut()));
         dure.setText(String.valueOf(clickedEven.getDuree_ev()));

        commentaire.setText(String.valueOf(clickedEven.getCommantaire()));
        
    }
    
    
//    @FXML
//    private void addWeather(ActionEvent event) throws MalformedURLException {
//         JSONObject todaysWeather = GetTodaysWeatherInformation(getWoeid());
//
//        System.out.println(todaysWeather);
//
//        showWeather.setText(
//            "Min temperature: " + todaysWeather.get("min_temp") +
//            "\nCurrent temperature: " + todaysWeather.get("the_temp") +
//            "\nMax temperature: " + todaysWeather.get("max_temp")
//        );
//    }
//    public String getWoeid() throws MalformedURLException {
//        APIConnector apiConnectorCity = new APIConnector(cityAPI);
//
//        JSONObject jsonData = (JSONObject) (apiConnectorCity.getJSONArray(weatherTxt.getText())).get(0);
//
//        return jsonData.get("woeid").toString();
//    }
//
//    public JSONObject GetTodaysWeatherInformation(String woeid) throws MalformedURLException {
//        APIConnector apiConnectorWeather = new APIConnector(weatherAPI);
//
//        JSONObject weatherJSONObject = apiConnectorWeather.getJSONObject(woeid + "/");
//
//        JSONArray weatherArray = (JSONArray) weatherJSONObject.get("consolidated_weather");
//
//        return  (JSONObject) weatherArray.get(0);
//    }
//    

    @FXML
    private void refreshChamp(MouseEvent event) {
        
           
             idEv.setText(null);
          detail.setText(null);
        datedeb.setText(null);
         dure.setText(null);
          commentaire.setText(null);
    }

    @FXML
    private void print(MouseEvent event) throws IOException, SQLException, DocumentException {
        
        
           String sql = "SELECT * from evenement";
    ste=mc.prepareStatement(sql);
    ResultSet rs=ste.executeQuery();

    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("./HoraireEvennements.pdf"));

    doc.open();
    //Image image = Image.getInstance("C:\\Users\\drwhoo\\Desktop\\Projet3eme\\JavaApplication\\src\\HolidaysHiatus\\images\\logo.png");

    //document.add(new Paragraph("test\n  test"));
   // doc.add(image);
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" ***************************************** Horaire Des évennements ************************************* "));
    doc.add(new Paragraph("   "));

    PdfPTable table = new PdfPTable(1);
    table.setWidthPercentage(50);
    PdfPCell cell;

    cell = new PdfPCell(new Phrase("Planification des evennements", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);

    table.addCell(cell);

//    cell = new PdfPCell(new Phrase("Classement", FontFactory.getFont("Comic Sans MS", 15)));
//    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//    table.addCell(cell);

    while (rs.next()) {

        Evennement e = new Evennement();
      // e.setId(rs.getInt("id"));
        e.setDate_debut(rs.getString("date_debut"));
       // e.setClassement(rs.getInt("classement"));
       
      
        cell = new PdfPCell(new Phrase(e.getDate_debut(), FontFactory.getFont("Comic Sans MS", 12)));
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
    Desktop.getDesktop().open(new File("./HoraireEvennements.pdf"));
        
        
      
        
    }


    @FXML
    private void rechercheEq(MouseEvent event) {
        

        
           //prodList = MaConnexion.getDatausers();
        evenTable.setItems(eventList);
        FilteredList<Evennement> filteredData = new FilteredList<>(eventList, b -> true);  
 search.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getDetail().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (person.getCommantaire().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    
    }
    else if (String.valueOf(person.getId_even()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Evennement> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(evenTable.comparatorProperty());  
  evenTable.setItems(sortedData);     
        
    }
        
    
    

    @FXML
    private void addEven(MouseEvent event) {
        
          Evennement e1= new Evennement();
        
        EController eq2 = new EController();
         String det = detail.getText();
        int duree = Integer.parseInt(dure.getText());
         String date = datedeb.getText();
         String comm = commentaire.getText();
           
        
        
         if (det.isEmpty()){
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
             
             Evennement e=new Evennement(1,duree,det, comm,date);
             EController ec = new EController();
             ec.ajouterEvent(e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("Evennement Ajoutée!");
                alert.showAndWait();             
        
        
         }
         
          refresh();
          
          
               idEv.setText(null);
          detail.setText(null);
        datedeb.setText(null);
         dure.setText(null);
          commentaire.setText(null);
        
        
        
        
    }

    @FXML
    private void updateEven(MouseEvent event) {
        
          Evennement clickedEvent = evenTable.getSelectionModel().getSelectedItem();
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
                 
        
        
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
                
        try{
             mc=MaConnexion.getInstance().getCnx();
              String value1 = idEv.getText();
             String value2 = detail.getText();
             String value3 = datedeb.getText();
             String value4 = dure.getText();
             String value5 = commentaire.getText();
             
             String sql = "update evenement set id_even = '"+value1+"', detail = '"+value2+"', date_debut='"+value3+"', duree_ev='"+value4+"', commantaire='"+value5+"' where id_even ='"+value1+"'";
             ste=mc.prepareStatement(sql);
             ste.execute();
            JOptionPane.showMessageDialog(null, "Equipe modifié");
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        }else{
             idEv.setText(null);
          detail.setText(null);
        datedeb.setText(null);
         dure.setText(null);
          commentaire.setText(null);
            
        }
        
        
        refresh();
        
        
    }

    @FXML
    private void deleteEven(MouseEvent event) throws SQLException {
        
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
                 
        
        
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        
        
        
        
        mc=MaConnexion.getInstance().getCnx();
         String sql = "delete from evenement where id_even = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, idEv.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "Evennement supprimé" );
        
           refresh();
            
        }else{
            
            
             idEv.setText(null);
          detail.setText(null);
        datedeb.setText(null);
         dure.setText(null);
          commentaire.setText(null);
        
            
        }
        
        
        
        
    }

     public void refresh(){
        
         eventList.clear();
       
          
          mc=MaConnexion.getInstance().getCnx();

        eventList = FXCollections.observableArrayList();
        
        String sql="select * from evenement order by duree_ev ASC";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Evennement e = new Evennement();
                 e.setId_even(rs.getInt("id_even"));
                e.setDetail(rs.getString("detail"));
                e.setDate_debut(rs.getString("date_debut"));
                e.setDuree_ev(rs.getInt("duree_ev"));
                 e.setCommantaire(rs.getString("commantaire"));

                eventList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         evenTable.setItems(eventList);
        
        
        
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

    @FXML
    private void meteo(ActionEvent event) throws MalformedURLException {
        JSONObject todaysWeather = GetTodaysWeatherInformation(getWoeid());

        System.out.println(todaysWeather);

        affcihe.setText(
            "Min temperature: " + todaysWeather.get("min_temp") +
            "\nCurrent temperature: " + todaysWeather.get("the_temp") +
            "\nMax temperature: " + todaysWeather.get("max_temp")
        );
    }
    public String getWoeid() throws MalformedURLException {
        APIConnector apiConnectorCity = new APIConnector(cityAPI);

        JSONObject jsonData = (JSONObject) (apiConnectorCity.getJSONArray(city.getText())).get(0);

        return jsonData.get("woeid").toString();
    }

    public JSONObject GetTodaysWeatherInformation(String woeid) throws MalformedURLException {
        APIConnector apiConnectorWeather = new APIConnector(weatherAPI);

        JSONObject weatherJSONObject = apiConnectorWeather.getJSONObject(woeid + "/");

        JSONArray weatherArray = (JSONArray) weatherJSONObject.get("consolidated_weather");

        return  (JSONObject) weatherArray.get(0);
        
        
    }

    @FXML
    private void coronastat(MouseEvent event) throws IOException {
        Stage primaryStage = new Stage();
         Parent parent = FXMLLoader.load(getClass().getResource("coronastat.fxml"));
       Scene scene = new Scene(parent);
        
        primaryStage.setTitle("Corona!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
}
