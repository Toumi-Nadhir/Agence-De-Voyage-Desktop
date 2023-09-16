/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.VoitureService;
import entites.Voiture;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sahar
 */
public class AjouterVoitureController implements Initializable {

    @FXML
    private TextField tfMat;
    @FXML
    private TextField tfMarque;
    @FXML
    private TextField tfCouleur;
    @FXML
    private Button btnajouter;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    @FXML
    private void ajouterVoiture(MouseEvent event) {
         if (tfMat.getText().trim().isEmpty()||tfCouleur.getText().trim().isEmpty()||tfMarque.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Form non valide !");
            alert.setContentText("verifie les champs !");
        alert.showAndWait();
        }else {
        String matricule = tfMat.getText();
        
        String marque = tfMarque.getText();
        String couleur = tfCouleur.getText();
       
        Voiture v = new Voiture(matricule,marque,couleur);
        v.setMatricule(matricule);
        v.setMarque(marque);
        v.setCouleur(couleur);
        System.out.println(v.getMatricule());
        VoitureService vs = new VoitureService();
        vs.ajouterVoiture(v);
    }
    }
   
    
}
