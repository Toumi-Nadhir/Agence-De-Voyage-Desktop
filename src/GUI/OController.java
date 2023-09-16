/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entity.Offre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tools.MaConnexion;
/**
 *
 * @author 21696
 */
public class OController {
    Connection mc;
    PreparedStatement ste;
    
    
      public OController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
    public void ajouterOffre(Offre o){
        String sql ="insert into offre(id,dure) Values(?,?)";
        try {
            ste=mc.prepareStatement(sql);
           
            ste.setInt(1,o.getId());
            ste.setInt(2, o.getDure());
      
            ste.executeUpdate();
            System.out.println("offre Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
    
    
}
