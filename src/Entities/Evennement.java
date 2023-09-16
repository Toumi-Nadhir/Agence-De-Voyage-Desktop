/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;


public class Evennement {
    
    int id_even, duree_ev;
    String detail, commantaire;
    String date_debut;

    public Evennement(){
        
    }
    
    
    
    public Evennement(int id_even, int duree_ev, String detail, String commantaire, String date_debut) {
        this.id_even = id_even;
        this.duree_ev = duree_ev;
        this.detail = detail;
        this.commantaire = commantaire;
        this.date_debut = date_debut;
    }

    public int getId_even() {
        return id_even;
    }

    public void setId_even(int id_even) {
        this.id_even = id_even;
    }

    public int getDuree_ev() {
        return duree_ev;
    }

    public void setDuree_ev(int duree_ev) {
        this.duree_ev = duree_ev;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCommantaire() {
        return commantaire;
    }

    public void setCommantaire(String commantaire) {
        this.commantaire = commantaire;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

   

    @Override
    public String toString() {
        return "Evennement{" + "id_even=" + id_even + ", duree_ev=" + duree_ev + ", detail=" + detail + ", commantaire=" + commantaire + ", date_debut=" + date_debut + '}';
    }
    
    
    
    
}
