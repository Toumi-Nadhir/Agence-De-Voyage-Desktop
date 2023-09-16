/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import IService.IServiceUser;
import connexion.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MaConnexion;

/**
 *
 * 
 */
public class ServiceUser implements IServiceUser<User> {

    private Connection con;
    private Statement ste;

    public ServiceUser() {
             con = MaConnexion.getInstance().getCnx();
    }

    public void register(User t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `skycraft`.`user` (`idUser` , `nom`, `prenom`, `email` , `password` , `role` ) VALUES (NULL, '" + t.getNom() + "' , '" + t.getPrenom() + "' , '" + t.getEmail() + "', '" + t.getPassword() + "', '" + t.getRole() + "');";
        ste.executeUpdate(requeteInsert);

    }

    public User login(String email, String password) throws SQLException {
        User u = new User();
        try {
            String sql = "SELECT * from user WHERE email ='" + email + "' AND password='" + password + "'";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            if (rs.next() == true) {
                System.out.println("------------------------");
                 System.out.println(rs);
                int idUser = rs.getInt(1);
                String username = rs.getString(2);
                String Email = rs.getString(3);
                String Password = rs.getString(4);
                String role = rs.getString(5);
                String nom = rs.getString(6);
                String prenom = rs.getString(7);
                
                u = new User(idUser,nom,prenom, Email, Password, role);
                System.out.println(" |||  user  authentifié  |||");
                System.out.println(u);
              
            } else {
                System.out.println("non trouvé");
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(IServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    
    public boolean update(User t, int id) throws SQLException {
    
    String sql = "UPDATE user SET nom=?, prenom=?, email=?,role=?, imageUser=? WHERE idUser=?";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, t.getNom());
        statement.setString(2, t.getPrenom());
        statement.setString(3, t.getEmail());
        
        statement.setString(4, "User");
        statement.setString(5, t.getImage());
        statement.setInt(6, id);
       

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing User was updated successfully!");
        }
        return true;
    }

    public boolean delete(User t) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `skycraft`.`user` where idUser =? AND nom =?");
        pre.setInt(1, t.getIdUser());
        pre.setString(2, t.getNom());
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A User was deleted successfully!");
        }
        return true;
    }

    public User FindById(int id) throws SQLException {
        String req = "select * from user where idUser = ?";
            User u = null;
            try {
                PreparedStatement ps = con.prepareStatement(req);
                 ps.setInt(1, id);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
               
                
                    u = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return u;
    }

    public boolean ResetPassword(String pass, int id) throws SQLException {
         String sql = "UPDATE user SET password=? WHERE idUser=?";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, pass);
        statement.setInt(2, id);
        
       

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing User has reseted his password !");
        }
        return true;
    }

   

}
