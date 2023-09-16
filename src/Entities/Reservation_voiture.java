/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;

/**
 *
 * @author sahar
 */
public class Reservation_voiture {
    
    private int id_rv,id_voiture,id_chauffeur,id_client;
    private Date dateDebut,Datefin;

    public Reservation_voiture(int id_voiture, int id_chauffeur, int id_client, Date dateDebut, Date Datefin) {
        this.id_voiture = id_voiture;
        this.id_chauffeur = id_chauffeur;
        this.id_client = id_client;
        this.dateDebut = dateDebut;
        this.Datefin = Datefin;
    }

    public Reservation_voiture() {
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDatefin() {
        return Datefin;
    }

    public void setDatefin(Date Datefin) {
        this.Datefin = Datefin;
    }

    

    public int getId_rv() {
        return id_rv;
    }

    public int getId_voiture() {
        return id_voiture;
    }

    public int getId_chauffeur() {
        return id_chauffeur;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_rv(int id_rv) {
        this.id_rv = id_rv;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public void setId_chauffeur(int id_chauffeur) {
        this.id_chauffeur = id_chauffeur;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Reservation_voiture{" + "id_rv=" + id_rv + ", id_voiture=" + id_voiture + ", id_chauffeur=" + id_chauffeur + ", id_client=" + id_client + '}';
    }
    
    
}
