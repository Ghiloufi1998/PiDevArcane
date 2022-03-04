/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Acer
 */
public class Transport {
    private int Transport_id;
    private String Type;
    private String Description;
    private int Disponibilité;
    private int Prix;
    private String Image;
    private int Hebergement_id;
    private Hebergement Hebergement;

    public Transport() {
    }

    public Transport(int Transport_id, String Type, String Description, int Disponibilité, int Prix, String Image, Hebergement Hebergement) {
        this.Transport_id = Transport_id;
        this.Type = Type;
        this.Description = Description;
        this.Disponibilité = Disponibilité;
        this.Prix = Prix;
        this.Image = Image;
        this.Hebergement = Hebergement;
    }

    public Transport(String Type, String Description, int Disponibilité, int Prix, String Image, Hebergement Hebergement ) {
        this.Type = Type;
        this.Description = Description;
        this.Disponibilité = Disponibilité;
        this.Prix = Prix;
        this.Image = Image;
        this.Hebergement_id = Hebergement_id;
    }

    public Transport(Hebergement Hebergement) {
        this.Hebergement = Hebergement;
    }

    public int getTransport_id() {
        return Transport_id;
    }

    public String getType() {
        return Type;
    }

    public String getDescription() {
        return Description;
    }

    public int getDisponibilité() {
        return Disponibilité;
    }

    public int getPrix() {
        return Prix;
    }

    public String getImage() {
        return Image;
    }

    
    public void setTransport_id(int Transport_id) {
        this.Transport_id = Transport_id;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setDisponibilité(int Disponibilité) {
        this.Disponibilité = Disponibilité;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public Hebergement getHebergement() {
        return Hebergement;
    }

    public void setHebergement(Hebergement Hebergement) {
        this.Hebergement = Hebergement;
    }

    @Override
    public String toString() {
        return "Transport{" + "Transport_id=" + Transport_id + ", Type=" + Type + ", Description=" + Description + ", Disponibilit\u00e9=" + Disponibilité + ", Prix=" + Prix + ", Image=" + Image + ", Hebergement=" + Hebergement + '}';
    }

    

    
    
    
}
