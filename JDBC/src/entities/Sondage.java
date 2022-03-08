/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author user
 */
public class Sondage {
    private int sondage_id ;
    private String sujet;
    private String catégorie;

    public Sondage() {
    }

    public Sondage(int sondage_id, String sujet, String catégorie) {
        this.sondage_id = sondage_id;
        this.sujet = sujet;
        this.catégorie = catégorie;
    }

    public Sondage(String sujet, String catégorie) {
        this.sujet = sujet;
        this.catégorie = catégorie;
    }

    public int getSondage_id() {
        return sondage_id;
    }

    public String getSujet() {
        return sujet;
    }

    public String getCatégorie() {
        return catégorie;
    }

    public void setSondage_id(int sondage_id) {
        this.sondage_id = sondage_id;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setCatégorie(String catégorie) {
        this.catégorie = catégorie;
    }

    @Override
    public String toString() {
        return "Sondage{" + "sondage_id=" + sondage_id + ", sujet=" + sujet + ", cat\u00e9gorie=" + catégorie + '}';
    }
    
    
    
}
