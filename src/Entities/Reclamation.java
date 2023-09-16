/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuyilisateur.com.esprit.Entite;

/**
 *
 * @author Ranim Ben Aafia
 */
public class Reclamation {
int id;
int id_user;
String subject;
String message;

    public Reclamation(int id, int id_user, String subject, String message) {
        this.id = id;
        this.id_user = id_user;
        this.subject = subject;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", id_user=" + id_user + ", subject=" + subject + ", message=" + message + '}';
    }


}
