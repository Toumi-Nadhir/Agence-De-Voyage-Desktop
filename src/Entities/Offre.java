/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


public class Offre {
     int id_offre ,id,dure;
     
      public Offre() {
    }

    public Offre(int id_offre, int id, int dure) {
        this.id_offre = id_offre;
        this.id = id;
        this.dure = dure;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDure() {
        return dure;
    }

    public void setDure(int dure) {
        this.dure = dure;
    }

    @Override
    public String toString() {
        return "Offre{" + "id_offre=" + id_offre + ", id=" + id + ", dure=" + dure + '}';
    }

   
       
     
     
}
