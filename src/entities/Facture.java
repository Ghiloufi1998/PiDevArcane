/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author bensa
 */
public class Facture {
    private int ID_fac;
    private Date date_ech;
    private double Montant;
    private String etat;
    private int ID_rev;
    private int ID_Pai;
    private Réservation r;
    private Paiement p;

    public Facture(Date date_ech, double Montant, String etat, int ID_rev, int ID_Pai, Réservation r, Paiement p) {
        this.date_ech = date_ech;
        this.Montant = Montant;
        this.etat = etat;
        this.ID_rev = ID_rev;
        this.ID_Pai = ID_Pai;
        this.r = r;
        this.p = p;
    }

    public Facture(Réservation r, Paiement p) {
        this.r = r;
        this.p = p;
    }

    public Facture(int ID_fac, Date date_ech, double Montant, String etat, int ID_rev, int ID_Pai, Réservation r, Paiement p) {
        this.ID_fac = ID_fac;
        this.date_ech = date_ech;
        this.Montant = Montant;
        this.etat = etat;
        this.ID_rev = ID_rev;
        this.ID_Pai = ID_Pai;
        this.r = r;
        this.p = p;
    }
    

    public int getID_fac() {
        return ID_fac;
    }

    public void setID_fac(int ID_fac) {
        this.ID_fac = ID_fac;
    }

    public Date getDate_ech() {
        return date_ech;
    }

    public void setDate_ech(Date date_ech) {
        this.date_ech = date_ech;
    }

    public double getMontant() {
        return Montant;
    }

    public void setMontant(double Montant) {
        this.Montant = Montant;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getID_rev() {
        return ID_rev;
    }

    public void setID_rev(int ID_rev) {
        this.ID_rev = ID_rev;
    }

    public int getID_Pai() {
        return ID_Pai;
    }

    public void setID_Pai(int ID_Pai) {
        this.ID_Pai = ID_Pai;
    }

    public Réservation getR() {
        return r;
    }

    public void setR(Réservation r) {
        this.r = r;
    }

    public Paiement getP() {
        return p;
    }

    public void setP(Paiement p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "Facture{" + "ID_fac=" + ID_fac + ", date_ech=" + date_ech + ", Montant=" + Montant + ", etat=" + etat + ", ID_rev=" + ID_rev + ", ID_Pai=" + ID_Pai + ", r=" + r + ", p=" + p + '}';
    }

    public Facture() {
    }
   
}
