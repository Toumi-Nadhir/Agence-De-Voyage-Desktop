/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.sql.Date; 
/**
 *
 * @author 21696
 */
public class Reservation {
    int id_re,id_even,montant,dure;

    public Reservation(int id_re, int id_even, int montant, int dure) {
        this.id_re = id_re;
        this.id_even = id_even;
        this.montant = montant;
        this.dure = dure;
    }
    
    

    public Reservation() {
    }

    public int getId_re() {
        return id_re;
    }

    public void setId_re(int id_re) {
        this.id_re = id_re;
    }

    public int getId_even() {
        return id_even;
    }

    public void setId_even(int id_even) {
        this.id_even = id_even;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getDure() {
        return dure;
    }

    public void setDure(int dure) {
        this.dure = dure;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_re=" + id_re + ", id_even=" + id_even + ", montant=" + montant + ", dure=" + dure + '}';
    }
    
    
    
    
}
