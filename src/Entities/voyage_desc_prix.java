/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Acer
 */
public class voyage_desc_prix {
   private String Description;
   
   private int prix;

    public voyage_desc_prix(String Description, int prix) {
        this.Description = Description;
        this.prix = prix;
    }

    public voyage_desc_prix() {
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "voyage_desc_prix{" + "Description=" + Description + ", prix=" + prix + '}';
    }
    
}
