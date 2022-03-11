/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author Acer
 */
public class Hebergement {
    private int Hebergement_id;
    private String Description;
    private String Type;
    private int Disponibilité;
    private String Adresse;
    private String Image;
    private int Prix;
    

    public Hebergement() {
    }

    public Hebergement(int Hebergement_id, String Description, String Type, int Disponibilité, String Adresse, String Image) {
        this.Hebergement_id = Hebergement_id;
        this.Description = Description;
        this.Type = Type;
        this.Disponibilité = Disponibilité;
        this.Adresse = Adresse;
        this.Image = Image;
    }
     public Hebergement(int Hebergement_id, String Description, String Type, int Disponibilité, String Adresse, String Image,int Prix) {
        this.Hebergement_id = Hebergement_id;
        this.Description = Description;
        this.Type = Type;
        this.Disponibilité = Disponibilité;
        this.Adresse = Adresse;
        this.Image = Image;
        this.Prix = Prix;
    }

    public Hebergement(String Description, String Type, int Disponibilité, String Adresse, String Image) {
        this.Description = Description;
        this.Type = Type;
        this.Disponibilité = Disponibilité;
        this.Adresse = Adresse;
        this.Image = Image;
    }
    public Hebergement(String Description, String Type, int Disponibilité, String Adresse, String Image,int Prix) {
        this.Description = Description;
        this.Type = Type;
        this.Disponibilité = Disponibilité;
        this.Adresse = Adresse;
        this.Image = Image;
        this.Prix = Prix;
    }

    public int getHebergement_id() {
        return Hebergement_id;
    }

    public String getDescription() {
        return Description;
    }

    public String getType() {
        return Type;
    }

    public int getDisponibilité() {
        return Disponibilité;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getImage() {
        return Image;
    }

    public void setHebergement_id(int Hebergement_id) {
        this.Hebergement_id = Hebergement_id;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setDisponibilité(int Disponibilité) {
        this.Disponibilité = Disponibilité;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
     public int getPrix() {
        return Prix;
    }
      public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    @Override
    public String toString() {
        return "Hebergement{" + "Hebergement_id=" + Hebergement_id + ", Description=" + Description + ", Type=" + Type + ", Disponibilit\u00e9=" + Disponibilité + ", Adresse=" + Adresse + ", Image=" + Image + ", Prix=" + Prix +  '}';
    }
    
    
     @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.Hebergement_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hebergement other = (Hebergement) obj;
        if (!Objects.equals(this.Type, other.Type)) {
            return false;
        }
        return true;
    }
}
