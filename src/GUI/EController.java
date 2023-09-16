/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Evennement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tools.MaConnexion;

/**
 *
 * @author mdhah
 */
public class EController {
    
    
      Connection mc;
    PreparedStatement ste;
    
    
      public EController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
    public void ajouterEvent(Evennement e){
        String sql ="insert into evenement(detail,date_debut,duree_ev, commantaire) Values(?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, e.getDetail());
            ste.setString(2, e.getDate_debut());
             ste.setInt(3, e.getDuree_ev());
             ste.setString(4, e.getCommantaire());
            ste.executeUpdate();
            System.out.println("Evennement Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
    
}
