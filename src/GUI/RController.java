/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entities.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tools.MaConnexion;
/**
 *
 * @author 21696
 */
public class RController {
    Connection mc;
    PreparedStatement ste;
    
    
      public RController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
    public void ajouterReservation(Reservation r){
        String sql ="insert into reservation_ev(id_re,id_even,montant,dure) Values(?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
           
            ste.setInt(1,r.getId_re());
            ste.setInt(2, r.getId_even());
            ste.setInt(3, r.getMontant());
            ste.setInt(4,r.getDure());
            
      
            ste.executeUpdate();
            System.out.println("reservation Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
}
