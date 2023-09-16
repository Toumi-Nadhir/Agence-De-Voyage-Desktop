/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entites.Voiture;
import tools.MaConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoitureService {

    Connection mc;
    PreparedStatement ste;

    public VoitureService() {
        mc = MaConnexion.getInstance().getCnx();
    }

    public void ajouterVoiture(Voiture V) {
        String sql = "insert into voiture(matricule,marque,couleur) Values(?,?,?)";
        try {
            ste = mc.prepareStatement(sql);
            ste.setString(1, V.getMatricule());
            ste.setString(2, V.getMarque());
            ste.setString(3, V.getCouleur());
            //System.out.println(V.getMatricule());

            ste.executeUpdate();
            System.out.println("voiture ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<Voiture> afficherVoiture() {
        List<Voiture> voitures = new ArrayList<>();
        String sql = "select * from voiture";
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Voiture v = new Voiture();
                v.setId_voiture(rs.getInt("id_voiture"));
                v.setMarque(rs.getString("marque"));
                v.setMatricule(rs.getString("matricule"));
                v.setCouleur(rs.getString("couleur"));

                voitures.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return voitures;
    }

    public Voiture chercherVoiture(int id) {

        Voiture c = new Voiture();
        try {
            String sql = "select * from voiture where id_voiture=" + id;
            ste = mc.prepareStatement(sql);
            //ste.setInt(1, c.getId_chauffeur());
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                c.setId_voiture(id);
                c.setMarque(rs.getString("marque"));
                c.setMatricule(rs.getString("matricule"));
                c.setCouleur(rs.getString("couleur"));

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public boolean modifierVoiture(Voiture v) {
        try {
            String sql = "UPDATE `voiture` SET `matricule`=?,`marque`=?,`couleur`=? WHERE id_voiture=? ";

            ste = mc.prepareStatement(sql);
            ste.setString(1, v.getMatricule());
            ste.setString(2, v.getMarque());
            ste.setString(3, v.getCouleur());
            ste.setInt(4, v.getId_voiture());

            ste.executeUpdate();
            System.out.println(ste);

            if (ste.executeUpdate() > 0) {
                System.out.println("Voiture modifié");
                return true;
            } else {
                System.out.println("voiture non modifier");
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public int supprimerVoiture(int id) {
        int nb = 0;

        try {
            String sql = "DELETE FROM voiture where id_voiture=" + id;
            ste = mc.prepareStatement(sql);
            nb = ste.executeUpdate();
            System.out.println("voiture supprimer");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }
}
