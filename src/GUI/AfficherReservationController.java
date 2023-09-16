/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ChauffeurService;
import Services.Reservation_Voiture_Service;
import Services.VoitureService;
import Tools.Create_QR;
import Tools.PdfGeneration;
import entites.Chauffeur;
import entites.Reservation_voiture;
import entites.Voiture;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AfficherReservationController implements Initializable {
// ajouter id selon la session

    int idUser = 1;

    List<Reservation_voiture> list = new Reservation_Voiture_Service().getReservationByClient(idUser);
    @FXML
    private ListView<String> tfListeR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        list.forEach((rv) -> {
            Voiture v = new VoitureService().chercherVoiture(rv.getId_voiture());
            Chauffeur ch = new ChauffeurService().chercherChauffeur(rv.getId_chauffeur());

            String retour = "voiture : " + v.getMarque()
                    + " - Chauffeur :" + ch.getNom_chauffeur() + " " + ch.getPrenom_chauffeur()
                    + " - Date de debut : " + rv.getDateDebut()
                    + " - Date fin : " + rv.getDatefin();
            tfListeR.getItems().add(retour);
        });

        tfListeR.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void ExportPdf(MouseEvent event) {
        PdfGeneration.ReservationPDF((ArrayList<Reservation_voiture>) list);
        JOptionPane.showMessageDialog(null, "PDF Génerer avec succés !");
    }

    @FXML
    private void GenererQRCode(MouseEvent event) {

        Create_QR q = new Create_QR();
        q.gene(String.valueOf(list.get(tfListeR.getSelectionModel().getSelectedIndex()).getId_rv()), tfListeR.getSelectionModel().getSelectedItem());
        JOptionPane.showMessageDialog(null, "QRCODE génerer avec succés!");
    }

}
