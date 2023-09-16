/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entites.Reservation_voiture;
import entites.Voiture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

/**
 *
 * @author ahmed
 */
public class Reservation_Voiture_Service {
     Connection mc;
    PreparedStatement ste;

    public Reservation_Voiture_Service() {
        mc = MaConnexion.getInstance().getCnx();
    }
    
     public void ajouterReservation(Reservation_voiture V) {
        String sql = "INSERT INTO `reservation_voiture`(`id_voiture`, `id_chauffeur`, `id_client`, `DateDebut`, `DateFin`) Values(?,?,?,?,?)";
        try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, V.getId_voiture());
            ste.setInt(2, V.getId_chauffeur());
            ste.setInt(3, V.getId_client());
            ste.setDate(4, V.getDateDebut());
            ste.setDate(5, V.getDatefin());
            //System.out.println(V.getMatricule());

            ste.executeUpdate();
            System.out.println("reservvation ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
     
     public List<Reservation_voiture> getReservationByClient(int id){
         List<Reservation_voiture> list = new ArrayList<>();
        try {
            String sql = "select * from reservation_voiture where id_client=" + id;
            ste = mc.prepareStatement(sql);
            //ste.setInt(1, c.getId_chauffeur());
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                Reservation_voiture c = new Reservation_voiture();
                c.setId_voiture(rs.getInt("id_voiture"));
                c.setId_chauffeur(rs.getInt("id_chauffeur"));
                c.setId_rv(rs.getInt("id_rv"));
                c.setId_rv(rs.getInt(id));
                c.setDateDebut(rs.getDate("DateDebut"));
                c.setDatefin(rs.getDate("DateFin"));
                list.add(c);

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
     }
}
