/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Absence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tools.MaConnexion;

/**
 *
 * @author 21696
 */
public class AbController {
     Connection mc;
    PreparedStatement ste;
    
    
      public AbController() {
        mc=MaConnexion.getInstance().getCnx();
        
    }
    public void ajouterAbsence(Absence a){
        String sql ="insert into absence(id,etat_ab,date_ab) Values(?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, a.getId());
            ste.setString(2, a.getEtat_ab());
             
             ste.setString(3,a.getDate_ab());
            ste.executeUpdate();
            System.out.println("Absence Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
