/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ChauffeurService;
import entites.Chauffeur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author sahar
 */
public class ModifierChauffeurController implements Initializable {

    Connection mc;
    PreparedStatement ste;
    @FXML
    private Button btnrechercher;
    @FXML
    private Button btnajouterchauff;
    @FXML
    private Button btnsupprimerchauffeur;

    public ModifierChauffeurController() {
        mc = MaConnexion.getInstance().getCnx();
    }

    @FXML
    private TextField tfChercher;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfCin;
    @FXML
    private TextField tfTel;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfBan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void chercherChauffeur(ActionEvent event) {
        String cher = tfChercher.getText();
        int id = Integer.parseInt(cher);

        ChauffeurService cs = new ChauffeurService();
        Chauffeur ch = cs.chercherChauffeur(id);
        System.out.println(ch.getNom_chauffeur());
        tfNom.setText(ch.getNom_chauffeur());
        tfPrenom.setText(ch.getPrenom_chauffeur());
        tfCin.setText(ch.getCin_chauffeur());
        tfTel.setText(String.valueOf(ch.getNum_tel_chauffeur()));
        tfEmail.setText(ch.getEmail_chauffeur());
        tfBan.setText(ch.getNum_compte_bancaire());

    }

    void Clear() {
        tfChercher.setText("");
        tfNom.setText("");
        tfPrenom.setText("");
        tfCin.setText("");
        tfTel.setText("");
        tfEmail.setText("");
        tfBan.setText("");
    }

    @FXML
    private void modifierChauffeur(ActionEvent event) {

        String cher = tfChercher.getText();
        int id = Integer.parseInt(cher);
        String nom_chauffeur = tfNom.getText();

        String prenom_chauffeur = tfPrenom.getText();
        String cin_chauffeur = tfCin.getText();
        String num_tel_chauffeur = tfTel.getText();

        String email_chauffeur = tfEmail.getText();
        String num_compte_bancaire = tfBan.getText();

        Chauffeur c = new Chauffeur(nom_chauffeur, prenom_chauffeur, cin_chauffeur, num_tel_chauffeur, email_chauffeur, num_compte_bancaire);
        c.setNom_chauffeur(nom_chauffeur);
        c.setPrenom_chauffeur(prenom_chauffeur);
        c.setCin_chauffeur(cin_chauffeur);
        c.setNum_tel_chauffeur(Integer.parseInt(num_tel_chauffeur));
        c.setEmail_chauffeur(email_chauffeur);
        c.setNum_compte_bancaire(num_compte_bancaire);
        c.setId_chauffeur(id);
        System.out.println(c.getNom_chauffeur());
        ChauffeurService cs = new ChauffeurService();
        cs.ModifierChauffeur(c);

        JOptionPane.showMessageDialog(null, "Chauffeur modifié !");
        Clear();

    }

    @FXML
    private void supprimerChauffeur(ActionEvent event) {
        String cher = tfChercher.getText();
        int id = Integer.parseInt(cher);
        new ChauffeurService().supprimerCHauffeur(id);
        JOptionPane.showMessageDialog(null, "Chauffeur supprimé !");
        Clear();

    }

}
