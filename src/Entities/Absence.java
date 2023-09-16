/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author 21696
 */
public class Absence {
    private int id_ab,id;
    private String etat_ab,date_ab;

    public Absence(int id_ab, int id, String etat_ab, String date_ab) {
        this.id_ab = id_ab;
        this.id = id;
        this.etat_ab = etat_ab;
        this.date_ab = date_ab;
    }

    public Absence() {
    }

    public int getId_ab() {
        return id_ab;
    }

    public void setId_ab(int id_ab) {
        this.id_ab = id_ab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat_ab() {
        return etat_ab;
    }

    public void setEtat_ab(String etat_ab) {
        this.etat_ab = etat_ab;
    }

    public String getDate_ab() {
        return date_ab;
    }

    public void setDate_ab(String date_ab) {
        this.date_ab = date_ab;
    }

    @Override
    public String toString() {
        return "Absence{" + "id_ab=" + id_ab + ", id=" + id + ", etat_ab=" + etat_ab + ", date_ab=" + date_ab + '}';
    }
    
}
