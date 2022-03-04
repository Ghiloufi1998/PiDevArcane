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
public class Voyageorganise {
   private int Voyage_id;
   private String Description;
   private String Image;
   private int prix;
   private int Nbre_Places;
   private int Vol_id;
   private int Transport_id;
   //private int Hebergement_id;
   private Vol vol;
   //private Hebergement Hebergement;
   private Transport Transport;
   public Voyageorganise() {
    }

    public Voyageorganise(int Voyage_id, String Description, String Image, int prix, int Nbre_Places, Vol vol,  Transport Transport) {
        this.Voyage_id = Voyage_id;
        this.Description = Description;
        this.Image = Image;
        this.prix = prix;
        this.Nbre_Places = Nbre_Places;
        this.vol = vol;
     //   this.Hebergement = Hebergement;
        this.Transport = Transport;
    }

    public Voyageorganise(Vol vol,  Transport Transport) {
        this.vol = vol;
        //this.Hebergement = Hebergement;
        this.Transport = Transport;
    }

  

    public Voyageorganise( String Description, String Image, int prix, int Nbre_Places, Vol vol,  Transport Transport) {
        
        this.Description = Description;
        this.Image = Image;
        this.prix = prix;
        this.Nbre_Places = Nbre_Places;
        this.vol = vol;
        //this.Hebergement = Hebergement;
        this.Transport = Transport;
    }

    public Voyageorganise(int Voyage_id, String Description, String Image, int prix, int Nbre_Places) {
        this.Voyage_id = Voyage_id;
        this.Description = Description;
        this.Image = Image;
        this.prix = prix;
        this.Nbre_Places = Nbre_Places;
    }
    

    public int getVoyage_id() {
        return Voyage_id;
    }

    public void setVol_id(int Vol_id) {
        this.Vol_id = Vol_id;
    }

    public void setTransport_id(int Transport_id) {
        this.Transport_id = Transport_id;
    }

    

    public int getVol_id() {
        return Vol_id;
    }

    public int getTransport_id() {
        return Transport_id;
    }

    

    

    public String getDescription() {
        return Description;
    }

    public String getImage() {
        return Image;
    }

    public int getPrix() {
        return prix;
    }

    public int getNbre_Places() {
        return Nbre_Places;
    }

    public Vol getVol() {
        return vol;
    }

  

    public Transport getTransport() {
        return Transport;
    }

    

    public void setVoyage_id(int Voyage_id) {
        this.Voyage_id = Voyage_id;
    }


    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setNbre_Places(int Nbre_Places) {
        this.Nbre_Places = Nbre_Places;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

   
    public void setTransport(Transport Transport) {
        this.Transport = Transport;
    }

    @Override
    public String toString() {
        return "Voyageorganise{" + "Voyage_id=" + Voyage_id + ", Description=" + Description + ", Image=" + Image + ", prix=" + prix + ", Nbre_Places=" + Nbre_Places + ", Vol_id=" + Vol_id + ", Transport_id=" + Transport_id + ", vol=" + vol + ", Transport=" + Transport + '}';
    }

    

    

}
