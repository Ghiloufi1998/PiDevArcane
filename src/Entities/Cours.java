/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Ghiloufi
 */
public class Cours {

    private int ID_crs;
    private String Type;
    private String Titre;
    private String Contenu;
    private int ID_g;
    private Guide guide;

    public Cours() {
    }
    

    public Cours(String Type, String Titre, String Contenu, Guide guide) {
        this.Type = Type;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.guide = guide;
    }

    public Cours(int ID_crs, String Type, String Titre, String Contenu, int ID_g, Guide guide) {
        this.ID_crs = ID_crs;
        this.Type = Type;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.ID_g = ID_g;
        this.guide = guide;
    }

    public Cours(String Type, String Titre, String Contenu, int ID_g, Guide guide) {
        this.Type = Type;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.ID_g = ID_g;
        this.guide = guide;
    }

    public Cours(int ID_crs, String Type, String Titre, String Contenu, Guide guide) {
        this.ID_crs = ID_crs;
        this.Type = Type;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.guide = guide;
    }

    public Cours(Guide guide) {
        this.guide = guide;
    }

    public int getID_crs() {
        return ID_crs;
    }

    public String getType() {
        return Type;
    }

    public String getTitre() {
        return Titre;
    }

    public String getContenu() {
        return Contenu;
    }

    public int getID_g() {
        return ID_g;
    }

    public Guide getGuide() {
        return guide;
    }

    public void setID_crs(int ID_crs) {
        this.ID_crs = ID_crs;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public void setID_g(int ID_g) {
        this.ID_g = ID_g;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }

    @Override
    public String toString() {
        return "Cours{" + "ID_crs=" + ID_crs + ", Type=" + Type + ", Titre=" + Titre + ", Contenu=" + Contenu + ", ID_g=" + ID_g + ", guide=" + guide + '}';
    }
    
    

   
}
