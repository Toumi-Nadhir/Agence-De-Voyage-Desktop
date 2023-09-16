/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.logging.Logger;
import gestionuyilisateur.com.esprit.Entite.Reclamation;
import connexion.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author Ranim Ben Aafia
 */
public class ListDesReclamationFXMLController implements Initializable {
    
    @FXML
    TableView<Reclamation> reclamtionTableView;
    @FXML
    TableColumn<Reclamation , Integer> reclamationIdColumn;
    @FXML
    TableColumn<Reclamation , Integer> userIdColumn;
    @FXML
    TableColumn<Reclamation , String> SubjectColumn;
    @FXML
    TableColumn<Reclamation , String> messageColumn;
    @FXML
     TextField searchTextField;
    
    ObservableList<Reclamation> reclamationObservableList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                Connection con;
             Statement ste;
             con = MaConnexion.getInstance().getCnx();
             
             String reclamationQuery = "SELECT id , user_id, subject , message FROM reclamation";
             
             try{
                  ste = con.createStatement();
                  ResultSet reclamationResult =  ste.executeQuery(reclamationQuery);
                  
                  while (reclamationResult.next()) {                     
                     Integer reclamationIdQuery = reclamationResult.getInt("id");
                    
                     Integer userIdQuery = reclamationResult.getInt("user_id");
                     String subjectQuery = reclamationResult.getString("subject");
                     String messageQuery = reclamationResult.getString("message");
                      System.out.print("user id from data base is : "+ userIdQuery);
                     
                     Reclamation reclamation = new Reclamation(reclamationIdQuery, userIdQuery, subjectQuery, messageQuery);
                     
                     reclamationObservableList.add(reclamation);
                 }
                  reclamationIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                  userIdColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
               SubjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
                  messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
                  
                  reclamtionTableView.setItems(reclamationObservableList);
                 FilteredList<Reclamation> filtredData = new FilteredList<>(reclamationObservableList , b->true);
                 
                 searchTextField.textProperty().addListener((observable , oldValue , newValue) -> {
                     filtredData.setPredicate((reclamation) -> {
                          if(newValue == null|| newValue.isEmpty()){
                             return true;
                         }
                             
                         String searchKeyWord = newValue.toLowerCase();
                         if(reclamation.getSubject().toLowerCase().indexOf(searchKeyWord)> -1){
                             return true;
                         }else if(reclamation.getMessage().toLowerCase().indexOf(searchKeyWord)> -1){
                             return true;
                         }
                         else 
                             return false;
                     });
                        
                 });
                 SortedList<Reclamation> sortedData = new SortedList<>(filtredData);
                 sortedData.comparatorProperty().bind(reclamtionTableView.comparatorProperty());
                 reclamtionTableView.setItems(sortedData);
             }catch(SQLException e){
                 Logger.getLogger(ListDesReclamationFXMLController.class.getName()).log(Level.SEVERE ,null,e );
             }
    }    
    
}
