/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entites.Chauffeur;
import tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ChauffeurService {

   
    Connection mc;
    PreparedStatement ste;

    public ChauffeurService() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    public void ajouterChauffeur(Chauffeur C){
        String sql ="insert into chauffeur(nom_chauffeur,prenom_chauffeur,cin_chauffeur,num_tel_chauffeur,email_chauffeur,num_compte_bancaire) Values(?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, C.getNom_chauffeur());
            ste.setString(2, C.getPrenom_chauffeur());
            ste.setString(3, C.getCin_chauffeur());
            ste.setInt(4, C.getNum_tel_chauffeur());
            ste.setString(5, C.getEmail_chauffeur());
            ste.setString(6, C.getNum_compte_bancaire());
            
            ste.executeUpdate();
            System.out.println("Chauffeur ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
     public boolean ModifierChauffeur(Chauffeur C){
        try {
            String sql = "Update chauffeur set nom_chauffeur=?,prenom_chauffeur=?,cin_chauffeur=?,num_tel_chauffeur=?,email_chauffeur=?,num_compte_bancaire=? where id_chauffeur=? ";

            ste=mc.prepareStatement(sql);
            ste.setString(1, C.getNom_chauffeur());
            ste.setString(2, C.getPrenom_chauffeur());
            ste.setString(3, C.getCin_chauffeur());
            ste.setInt(4, C.getNum_tel_chauffeur());
            ste.setString(5, C.getEmail_chauffeur());
            ste.setString(6, C.getNum_compte_bancaire());
            ste.setInt(7, C.getId_chauffeur());
            
            ste.executeUpdate();
            System.out.println(ste);
            
            if (ste.executeUpdate()>0){
                System.out.println("Chauffeur modifié");
                 return true;
            }else{
                System.out.println("Chauffeur non modifier");
                return false;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

        
        
    }
    public List<Chauffeur> afficherChauffeur(){
        List<Chauffeur> chauffeurs = new ArrayList<>();
        String sql="select * from chauffeur";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Chauffeur c = new Chauffeur();
                c.setId_chauffeur(rs.getInt("id_chauffeur"));
                c.setNom_chauffeur(rs.getString("nom_chauffeur"));
                c.setPrenom_chauffeur(rs.getString("prenom_chauffeur"));
                c.setCin_chauffeur(rs.getString("cin_chauffeur"));
                c.setNum_tel_chauffeur(rs.getInt("num_tel_chauffeur"));
                c.setEmail_chauffeur(rs.getString("email_chauffeur"));
                c.setNum_compte_bancaire(rs.getString("num_compte_bancaire"));

                chauffeurs.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return chauffeurs;
    }

    public  Chauffeur chercherChauffeur(int id) {
    
       Chauffeur c = new Chauffeur();
       try{
           String sql ="select * from chauffeur where id_chauffeur="+id;
           ste=mc.prepareStatement(sql);
           //ste.setInt(1, c.getId_chauffeur());
           ResultSet rs = ste.executeQuery();
           
           while (rs.next()){
               c.setCin_chauffeur(rs.getString("cin_chauffeur"));
               c.setEmail_chauffeur(rs.getString("email_chauffeur"));
               c.setId_chauffeur(rs.getInt("id_chauffeur"));
               c.setNom_chauffeur(rs.getString("nom_chauffeur"));
               c.setPrenom_chauffeur(rs.getString("prenom_chauffeur"));
               c.setNum_tel_chauffeur(rs.getInt("num_tel_chauffeur"));
               c.setNum_compte_bancaire(rs.getString("num_compte_bancaire"));
               
            }
           rs.close();
       }catch (SQLException e){
            e.printStackTrace();       
       }
       return c;
    }
    
     public int supprimerCHauffeur(int Id) {
        int nb = 0;
        try {
            String sql = "DELETE FROM chauffeur where id_chauffeur="+Id;
            ste = mc.prepareStatement(sql);
            nb = ste.executeUpdate();
            System.out.println("chauffeur supprimer");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;

    }

    
}
