/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Souha saffar
 */
public class Offre {
    private int id_offre;
    private String description;
    private String destination;
    private int pourcentage_red;
    private int nb_point_req;

    public Offre(int id_offre, String description, String destination, int pourcentage_red, int nb_point_req) {
        this.id_offre = id_offre;
        this.description = description;
        this.destination = destination;
        this.pourcentage_red = pourcentage_red;
        this.nb_point_req = nb_point_req;
    }

    public Offre() {
    }

    public Offre(String description, String destination, int pourcentage_red, int nb_point_req) {
        this.description = description;
        this.destination = destination;
        this.pourcentage_red = pourcentage_red;
        this.nb_point_req = nb_point_req;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPourcentage_red() {
        return pourcentage_red;
    }

    public void setPourcentage_red(int pourcentage_red) {
        this.pourcentage_red = pourcentage_red;
    }

    public int getNb_point_req() {
        return nb_point_req;
    }

    public void setNb_point_req(int nb_point_req) {
        this.nb_point_req = nb_point_req;
    }

    @Override
    public String toString() {
        return "Offre{" + "id_offre=" + id_offre + ", description=" + description + ", destination=" + destination + ", pourcentage_red=" + pourcentage_red + ", nb_point_req=" + nb_point_req + '}';
    }
    
    
}
