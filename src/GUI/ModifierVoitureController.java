/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.VoitureService;
import entites.Voiture;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author sahar
 */
public class ModifierVoitureController implements Initializable {

    @FXML
    private TextField tfChercher;
    @FXML
    private Button btnrecherchervoiture;
    @FXML
    private TextField tfMat;
    @FXML
    private TextField tfMarque;
    @FXML
    private TextField tfCouleur;
    @FXML
    private Button btnajoutervoiture;
    @FXML
    private Button btnsupprimervoiture;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void chercherVoiture(ActionEvent event) {

        String cher = tfChercher.getText();
        int id = Integer.parseInt(cher);

        VoitureService cs = new VoitureService();
        Voiture ch = cs.chercherVoiture(id);
        tfMat.setText(ch.getMatricule());
        tfMarque.setText(ch.getMarque());
        tfCouleur.setText(ch.getCouleur());

    }

    @FXML
    private void modifierVoiture(ActionEvent event) {
        String cher = tfChercher.getText();
        int id = Integer.parseInt(cher);
        String Mat = tfMat.getText();

        String Marque = tfMarque.getText();
        String Couleur = tfCouleur.getText();

        Voiture v = new Voiture(id, Mat, Marque, Couleur);
        new VoitureService().modifierVoiture(v);
                JOptionPane.showMessageDialog(null, "Voiture modifié !");

        Clear();

    }

    void Clear() {
        tfChercher.setText("");
        tfMat.setText("");
        tfMarque.setText("");
        tfCouleur.setText("");
    }

    @FXML
    private void supprimerVoiture(ActionEvent event) {
        String cher = tfChercher.getText();
        int id = Integer.parseInt(cher);
        
        new VoitureService().supprimerVoiture(id);
                JOptionPane.showMessageDialog(null, "voiture supprimé !");

    }

}
