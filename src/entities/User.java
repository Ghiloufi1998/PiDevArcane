/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author bensa
 */
public class User {
    private int ID_user;
    private String nom;

    public User() {
    }

    public User(String nom) {
        this.nom = nom;
    }

    public User(int ID_user, String nom) {
        this.ID_user = ID_user;
        this.nom = nom;
    }
    

    public int getID_user() {
        return ID_user;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "User{" + "ID_user=" + ID_user + ", nom=" + nom + '}';
    }
    
}
