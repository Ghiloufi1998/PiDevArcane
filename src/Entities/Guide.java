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
public class Guide {
    private int ID_g;
    private String Pays;
    private int Level; 
    private Vol vol; 
    private int id_vol;
    
    public Guide() {
    }

    public Guide(Vol vol) {
        this.vol = vol;
    }

    public Guide(String Pays, int Level, Vol vol, int id_vol) {
        this.Pays = Pays;
        this.Level = Level;
        this.vol = vol;
        this.id_vol = id_vol;
    }

    public Guide(String Pays, int Level, Vol vol) {
        this.Pays = Pays;
        this.Level = Level;
        this.vol = vol;
    }

    public Guide(int ID_g, String Pays, int Level, Vol vol, int id_vol) {
        this.ID_g = ID_g;
        this.Pays = Pays;
        this.Level = Level;
        this.vol = vol;
        this.id_vol = id_vol;
    }

    public Guide(String Pays, int Level, int id_vol) {
        this.Pays = Pays;
        this.Level = Level;
        this.id_vol = id_vol;
    }
    

    public int getID_g() {
        return ID_g;
    }

    public String getPays() {
        return Pays;
    }

    public int getLevel() {
        return Level;
    }

    public Vol getVol() {
        return vol;
    }

    public int getId_vol() {
        return id_vol;
    }

    public void setID_g(int ID_g) {
        this.ID_g = ID_g;
    }

    public void setPays(String Pays) {
        this.Pays = Pays;
    }

    public void setLevel(int Level) {
        this.Level = Level;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }

    @Override
    public String toString() {
        return "Guide{" + "ID_g=" + ID_g + ", Pays=" + Pays + ", Level=" + Level + ", id_vol=" + id_vol + '}';
    }

    
    
}
