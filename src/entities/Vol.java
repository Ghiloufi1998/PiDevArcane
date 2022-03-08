/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author Acer
 */
public class Vol {
  private int    Vol_id ;   
  private String Destination ;   
  private String Départ ;   
  private String Image ;   
  private int prix;
  private ImageView img;

    public Vol(String Destination, String Départ, String Image, int prix, ImageView img) {
        this.Destination = Destination;
        this.Départ = Départ;
        this.Image = Image;
        this.prix = prix;
        this.img = img;
    }

    public Vol(int Vol_id, String Destination, String Départ, String Image, int prix) {
        this.Vol_id = Vol_id;
        this.Destination = Destination;
        this.Départ = Départ;
        this.Image = Image;
        this.prix = prix;
    }

    public Vol(int Vol_id, String Destination, String Départ, String Image, int prix, ImageView img) {
        this.Vol_id = Vol_id;
        this.Destination = Destination;
        this.Départ = Départ;
        this.Image = Image;
        this.prix = prix;
        this.img = img;
    }

    public Vol(String Départ, String Image, int prix, ImageView img) {
        this.Départ = Départ;
        this.Image = Image;
        this.prix = prix;
        this.img = img;
    }

    public Vol() {
    }
    

    public Vol( String Image,String Départ, int prix) {
        this.Départ = Départ;
        this.Image = Image;
        this.prix = prix;
    }

    public int getVol_id() {
        return Vol_id;
    }

    public void setVol_id(int Vol_id) {
        this.Vol_id = Vol_id;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public String getDépart() {
        return Départ;
    }

    public void setDépart(String Départ) {
        this.Départ = Départ;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Vol{" + "Vol_id=" + Vol_id + ", Destination=" + Destination + ", D\u00e9part=" + Départ + ", Image=" + Image + ", prix=" + prix + ", img=" + img + '}';
    }
    
    

  
  
}
