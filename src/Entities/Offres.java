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
public class Offres {

    private int ID;
    private String Description;
    private int Nb_points_req;
    private String Destination;
    private int Pourcentage_red;


    public Offres() {
    }

    public Offres(int ID, String Description, int Nb_points_req, String Destination, int Pourcentage_red) {
        this.ID = ID;
        this.Description = Description;
        this.Nb_points_req = Nb_points_req;
        this.Destination = Destination;
        this.Pourcentage_red = Pourcentage_red;
    }

    public Offres(String Description, int Nb_points_req, String Destination, int Pourcentage_red) {
        this.Description = Description;
        this.Nb_points_req = Nb_points_req;
        this.Destination = Destination;
        this.Pourcentage_red = Pourcentage_red;
    }

    public int getID() {
        return ID;
    }


    public String getDescription() {
        return Description;
    }

    public int getNb_points_req() {
        return Nb_points_req;
    }

    public String getDestination() {
        return Destination;
    }

    public int getPourcentage_red() {
        return Pourcentage_red;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setNb_points_req(int Nb_points_req) {
        this.Nb_points_req = Nb_points_req;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public void setPourcentage_red(int Pourcentage_red) {
        this.Pourcentage_red = Pourcentage_red;
    }

    @Override
    public String toString() {
        return "Offres{" + "ID=" + ID + ", Description=" + Description + ", Nb_points_req=" + Nb_points_req + ", Destination=" + Destination + ", Pourcentage_red=" + Pourcentage_red + '}';
    }

 
    

}
