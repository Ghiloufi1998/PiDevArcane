/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author souha saffar
 */
public class Reclamation {
     private int id;
    private int etat;
    private String description;
    private Date date;
    private String type;
    private int user_id;
    private User user;

    public Reclamation() {
    }

    public Reclamation(int etat, String description, Date date, String type, int user_id,User user) {
        this.etat = etat;
        this.description = description;
        this.date = date;
        this.type = type;
        this.user_id = user_id;
        this.user=user;
    }

    public Reclamation(int id, int etat, String description, Date date, String type, int user_id,User user) {
        this.id = id;
        this.etat = etat;
        this.description = description;
        this.date = date;
        this.type = type;
        this.user_id = user_id;
        this.user=user;
        
    }

    public Reclamation(User user) {
        this.user = user;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", etat=" + etat + ", description=" + description + ", date=" + date + ", type=" + type + ", user_id=" + user_id + ", user=" + user.getName() + '}';
    }

    

    
}
