/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author souha saffar
 */
public class Reclamation {
     private int id;
    private int etat;
    private String description;

    private int user_id;

    private String user;
    public Reclamation() {
    }


    public Reclamation(int id, int etat, String description, int user_id) {
        this.id = id;
        this.etat = etat;
        this.description = description;
        this.user_id = user_id;
    }


    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser() {
        return user;
    }


    public void setUser(String user) {
        this.user = user;
    }


    public Reclamation(int id, int etat, String description, int user_id, String user) {
        this.id = id;
        this.etat = etat;
        this.description = description;
        this.user_id = user_id;
        this.user = user;
    }



    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", etat=" + etat + ", description=" + description + ", user_id=" + user_id + ", user=" + user + '}';
    }
   
   
}
