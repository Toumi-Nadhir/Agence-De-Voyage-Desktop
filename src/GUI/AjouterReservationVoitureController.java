/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.ChauffeurService;
import Services.Reservation_Voiture_Service;
import Services.VoitureService;
import entites.Chauffeur;
import entites.Reservation_voiture;
import entites.Voiture;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AjouterReservationVoitureController implements Initializable {
    // ajouter id selon la session
    int idUser = 1;
    @FXML
    private ComboBox<String> cbVoiture;
    @FXML
    private ComboBox<String> cbChauffeur;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
        List<Voiture> allvoiture = new VoitureService().afficherVoiture();
        List<Chauffeur> allchauffeur = new ChauffeurService().afficherChauffeur();
              List<String> Listvoiture = new ArrayList<>();
              List<String> Listchauffeur = new ArrayList<>();
    Reservation_voiture rv = new Reservation_voiture();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        allvoiture.forEach((v) -> { 
            Listvoiture.add(v.getId_voiture()+" "+v.getMarque());
        });
                    cbVoiture.setItems(FXCollections.observableArrayList(Listvoiture));

                     allchauffeur.forEach((v) -> { 
            Listchauffeur.add(v.getId_chauffeur()+" "+v.getNom_chauffeur()+" "+v.getPrenom_chauffeur());
        });
                    cbChauffeur.setItems(FXCollections.observableArrayList(Listchauffeur));

        DatePicker minDate = new DatePicker(); // DatePicker, used to define max date available, you can also create another for minimum date
        minDate.setValue(LocalDate.now());
        DatePicker maxDate = new DatePicker(); // DatePicker, used to define max date available, you can also create another for minimum date
        maxDate.setValue(LocalDate.now().plusDays(1));// Max date available will be 2015-01-01
        final Callback<DatePicker, DateCell> dayCellFactory;

        dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(minDate.getValue())) { //Disable all dates after required date
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); //To set background on different color
                }
            }
        };
                datedebut.setDayCellFactory(dayCellFactory);

        final Callback<DatePicker, DateCell> dayCellFactory2;

        dayCellFactory2 = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(maxDate.getValue())) { //Disable all dates after required date
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); //To set background on different color
                }
            }
        };
        datefin.setDayCellFactory(dayCellFactory2);
    }    

    @FXML
    private void ChoisirVoiture(TouchEvent event) {
        
        
        //cbVoiture.selectionModelProperty().get().getSelectedItem()
    }

    @FXML
    private void ChoisirChauffeur(TouchEvent event) {
    }

    private void ajouterReservation(KeyEvent event) {
       
        
    }

    @FXML
    private void ajouterReservation(MouseEvent event) {
         
        if (cbVoiture.getValue()==null||cbChauffeur.getValue()==null||datedebut.getValue()==null||datefin.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Form non valide !");
            alert.setContentText("verifie les champs !");
        alert.showAndWait();
        }else {
            rv.setDateDebut(java.sql.Date.valueOf(datedebut.getValue()));
            rv.setDatefin(java.sql.Date.valueOf(datefin.getValue()));
            String Chauffeur = cbChauffeur.getValue().substring(0, cbChauffeur.getValue().indexOf(" "));
            int idChauffeur = Integer.parseInt(Chauffeur);
            String voiture = cbVoiture.getValue().substring(0, cbVoiture.getValue().indexOf(" "));
            int idvoiture = Integer.parseInt(voiture);
            
            rv.setId_voiture(idvoiture);
            rv.setId_chauffeur(idChauffeur);
            rv.setId_client(idUser);
            System.out.println(idvoiture);
            System.out.println("-----------------");
            System.out.println(idChauffeur);
            new Reservation_Voiture_Service().ajouterReservation(rv);
            JOptionPane.showMessageDialog(null, "Reservation effectué !");
        }
    }
    
    
    
}
