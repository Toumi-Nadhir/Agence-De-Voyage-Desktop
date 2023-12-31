/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import static GUI.Start.Userconnected;
import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import static java.sql.JDBCType.NULL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hama Hagui
 */
public class LoginUserController implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    ServiceUser su = new ServiceUser();
    @FXML
    private Button btnAuth;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void Login(ActionEvent event) throws IOException, Exception {
        
        User u = new User();
        u = su.login(emailField.getText(), passwordField.getText());
        if(u.getIdUser()== 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Verifier vos donnés", ButtonType.CLOSE);
            alert.show();
            
        } else {
            
            User user1 = su.login(emailField.getText(), passwordField.getText());
        Userconnected.setIdUser(user1.getIdUser());
        Userconnected.setNom(user1.getNom());
        Userconnected.setPrenom(user1.getPrenom());
        Userconnected.setEmail(user1.getEmail());
        Userconnected.setPassword(user1.getPassword());
        Userconnected.setRole(user1.getRole());
        Userconnected.setImage(user1.getImage());
        
       Userconnected=user1;
        System.out.println("this is test");
        System.out.println(Userconnected);
        
        if(u.getRole().equals("Admin"))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Je vous souhaite la bienvenue Mr/Mme"+ u.getNom() +" "+ u.getPrenom(), ButtonType.OK);
                alert.show();
     
                FXMLLoader LOADER = new FXMLLoader();
                LOADER.setLocation(getClass().getResource("AdminInterface.fxml"));
                Parent parent = LOADER.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();
                AdminInterfaceController cntr = LOADER.getController();
                Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
            
        }
        
        
        if(u.getRole().equals("Client"))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Je vous souhaite la bienvenue Mr/Mme"+ u.getNom() +" "+ u.getPrenom(), ButtonType.OK);
            alert.show();
     
                FXMLLoader LOADER = new FXMLLoader(getClass().getResource("UserInterface.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      UserInterfaceController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
            
        }
         if(u.getRole().equals("Employee"))
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Je vous souhaite la bienvenue Mr/Mme"+ u.getNom() +" "+ u.getPrenom(), ButtonType.OK);
            alert.show();
     
                FXMLLoader LOADER = new FXMLLoader(getClass().getResource("UserInterface.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      UserInterfaceController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
            
        }
            
        }
        
    }
    
    @FXML
    public void GoToRegister(MouseEvent event) throws IOException, Exception {
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("RegisterUser.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                      RegisterUserController cntr = LOADER.getController();
                    Stage window =(Stage)((Node) event.getSource()).getScene().getWindow() ;
              
                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {
                  
    }
    }
    
}
