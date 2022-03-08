/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author bensa
 */
public class Réservation {

    private int ID_rev;
    private Date Date_deb;
    private Date Date_fin;
    private String Type;
    private int Nbr_adultes;
    private int Nbr_enfants;
    private int ID_heb;
    private int ID_Vol;
    private int ID_user;
    private String Destination;
    private User user;
    private Vol vol;
    private Hebergement hebergement;

    public Réservation() {
    }
    

    public Réservation(Date Date_deb, Date Date_fin, int Nbr_adultes, int Nbr_enfants, int ID_heb, int ID_Vol, int ID_user, String Destination) {
        this.Date_deb = Date_deb;
        this.Date_fin = Date_fin;
        this.Nbr_adultes = Nbr_adultes;
        this.Nbr_enfants = Nbr_enfants;
        this.ID_heb = ID_heb;
        this.ID_Vol = ID_Vol;
        this.ID_user = ID_user;
        this.Destination = Destination;
    }
    

    public Réservation(int ID_rev, Date Date_deb, Date Date_fin, String Type, int Nbr_adultes, int Nbr_enfants, int ID_heb, int ID_Vol, int ID_user, String Destination, User user, Vol vol, Hebergement hebergement) {
        this.ID_rev = ID_rev;
        this.Date_deb = Date_deb;
        this.Date_fin = Date_fin;
        this.Type = Type;
        this.Nbr_adultes = Nbr_adultes;
        this.Nbr_enfants = Nbr_enfants;
        this.ID_heb = ID_heb;
        this.ID_Vol = ID_Vol;
        this.ID_user = ID_user;
        this.Destination = Destination;
        this.user = user;
        this.vol = vol;
        this.hebergement = hebergement;
    }

    public Réservation(Date Date_deb, Date Date_fin, String Type, int Nbr_adultes, int Nbr_enfants, String Destination) {
        this.Date_deb = Date_deb;
        this.Date_fin = Date_fin;
        this.Type = Type;
        this.Nbr_adultes = Nbr_adultes;
        this.Nbr_enfants = Nbr_enfants;
        this.Destination = Destination;
    }

    public Réservation(Date Date_deb, Date Date_fin, String Destination) {
        this.Date_deb = Date_deb;
        this.Date_fin = Date_fin;
        this.Destination = Destination;
    }

    public Réservation(Date Date_deb, Date Date_fin, String Type, int Nbr_adultes, int Nbr_enfants, int ID_heb, int ID_Vol, int ID_user, String Destination, User user, Vol vol, Hebergement hebergement) {
        this.Date_deb = Date_deb;
        this.Date_fin = Date_fin;
        this.Type = Type;
        this.Nbr_adultes = Nbr_adultes;
        this.Nbr_enfants = Nbr_enfants;
        this.ID_heb = ID_heb;
        this.ID_Vol = ID_Vol;
        this.ID_user = ID_user;
        this.Destination = Destination;
        this.user = user;
        this.vol = vol;
        this.hebergement = hebergement;
    }

    public Réservation(Date Date_deb, Date Date_fin, String Type, int Nbr_adultes, int Nbr_enfants, int ID_heb, int ID_Vol, int ID_user, String Destination) {
        this.Date_deb = Date_deb;
        this.Date_fin = Date_fin;
        this.Type = Type;
        this.Nbr_adultes = Nbr_adultes;
        this.Nbr_enfants = Nbr_enfants;
        this.ID_heb = ID_heb;
        this.ID_Vol = ID_Vol;
        this.ID_user = ID_user;
        this.Destination = Destination;
    }

    public Réservation(User user, Vol vol, Hebergement hebergement) {
        this.user = user;
        this.vol = vol;
        this.hebergement = hebergement;
    }

    public Réservation(int ID_rev, Date Date_deb, Date Date_fin, String Type, int Nbr_adultes, int Nbr_enfants) {
        this.ID_rev = ID_rev;
        this.Date_deb = Date_deb;
        this.Date_fin = Date_fin;
        this.Type = Type;
        this.Nbr_adultes = Nbr_adultes;
        this.Nbr_enfants = Nbr_enfants;
    }

    public Réservation(int ID_rev , java.sql.Date Date_deb, java.sql.Date Date_fin, int Nbr_adultes, int Nbr_enfants , String Type,String Destination ) {
        this.ID_rev = ID_rev;
        this.Date_deb = Date_deb;
        this.Date_fin = Date_fin;
        this.Type = Type;
        this.Nbr_adultes = Nbr_adultes;
        this.Nbr_enfants = Nbr_enfants;
        this.Destination = Destination;
       
    }

    public Réservation(int id_rev, java.sql.Date d, java.sql.Date f, String x, String ty, String z) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Réservation(LocalDate d, LocalDate fin, String ty, int nb1, int nb2, String x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getID_rev() {
        return ID_rev;
    }

    public void setID_rev(int ID_rev) {
        this.ID_rev = ID_rev;
    }

    public Date getDate_deb() {
        return Date_deb;
    }

    public void setDate_deb(Date Date_deb) {
        this.Date_deb = Date_deb;
    }

    public Date getDate_fin() {
        return Date_fin;
    }

    public void setDate_fin(Date Date_fin) {
        this.Date_fin = Date_fin;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getNbr_adultes() {
        return Nbr_adultes;
    }

    public void setNbr_adultes(int Nbr_adultes) {
        this.Nbr_adultes = Nbr_adultes;
    }

    public int getNbr_enfants() {
        return Nbr_enfants;
    }

    public void setNbr_enfants(int Nbr_enfants) {
        this.Nbr_enfants = Nbr_enfants;
    }

    public int getID_heb() {
        return ID_heb;
    }

    public void setID_heb(int ID_heb) {
        this.ID_heb = ID_heb;
    }

    public int getID_Vol() {
        return ID_Vol;
    }

    public void setID_Vol(int ID_Vol) {
        this.ID_Vol = ID_Vol;
    }

    public int getID_user() {
        return ID_user;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public Hebergement getHebergement() {
        return hebergement;
    }

    public void setHebergement(Hebergement hebergement) {
        this.hebergement = hebergement;
    }

    @Override
    public String toString() {
        return "R\u00e9servation{" + "ID_rev=" + ID_rev + ", Date_deb=" + Date_deb + ", Date_fin=" + Date_fin + ", Type=" + Type + ", Nbr_adultes=" + Nbr_adultes + ", Nbr_enfants=" + Nbr_enfants + ", ID_heb=" + ID_heb + ", ID_Vol=" + ID_Vol + ", ID_user=" + ID_user + ", Destination=" + Destination + ", user=" + user + ", vol=" + vol + ", hebergement=" + hebergement + '}';
    }
    
}