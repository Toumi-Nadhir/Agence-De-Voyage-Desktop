/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sahar
 */
public class TransportController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void quitter(ActionEvent event) {
        
        System.exit(0);
        }
    @FXML
    private void lanceAjoutChauffeur(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Chauffeur.fxml"));
         Scene scene = new Scene(root);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.show();
         stage.setResizable(false);

    }

    @FXML
    private void lanceModifChauffeur(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ModifierChauffeur.fxml"));
         Scene scene = new Scene(root);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.show();
         stage.setResizable(false);
    }

    @FXML
    private void lanceAfficheChauffeur(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AfficherChauffeur.fxml"));
         Scene scene = new Scene(root);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.show();
         stage.setResizable(false);
    }

    @FXML
    private void lanceAjoutVoiture(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Voiture.fxml"));
         Scene scene = new Scene(root);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.show();
         stage.setResizable(false);
    }

    @FXML
    private void lanceAfficheVoiture(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AfficherVoiture.fxml"));
         Scene scene = new Scene(root);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.show();
         stage.setResizable(false);
    }

    @FXML
    private void lancecherchervoiture(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ModifierVoiture.fxml"));
         Scene scene = new Scene(root);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.show();
         stage.setResizable(false);
    }

    @FXML
    private void lanceAjoutReservation(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AjoutReservationVoiture.fxml"));
         Scene scene = new Scene(root);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.show();
         stage.setResizable(false);
    }

    @FXML
    private void lanceAfficheReservation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AfficheReservation.fxml"));
         Scene scene = new Scene(root);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.show();
    }
    
}
