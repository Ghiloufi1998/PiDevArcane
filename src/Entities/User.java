/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Ghiloufi
 */
public class User {
    private int id;
    private String name;
    private String fname;
    private String gender;
    private Date birthday;
    private String adresse;
    private String email;
    private String password;
    private String image;
    private String role;
    private int Nb_point;
    private int id_offre;
    private Offres offre;
   


  
    

    public User() {
}

    public User(int id, String name, String fname, String gender, Date birthday, String adresse, String email, String password, String image, String role, int Nb_point, int id_offre, Offres offre) {
        this.id = id;
        this.name = name;
        this.fname = fname;
        this.gender = gender;
        this.birthday = birthday;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
        this.Nb_point = Nb_point;
        this.id_offre = id_offre;
        this.offre = offre;
    }

    public User(String name, String fname, String gender, Date birthday, String adresse, String email, String password, String image, String role, int Nb_point, int id_offre, Offres offre) {
        this.name = name;
        this.fname = fname;
        this.gender = gender;
        this.birthday = birthday;
        this.adresse = adresse;
        this.email = email;
        this.password = password;
        this.image = image;
        this.role = role;
        this.Nb_point = Nb_point;
        this.id_offre = id_offre;
        this.offre = offre;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getNb_point() {
        return Nb_point;
    }

    public void setNb_point(int Nb_point) {
        this.Nb_point = Nb_point;
    }

    public User(Offres offre) {
        this.offre = offre;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public Offres getOffre() {
        return offre;
    }

    public void setOffre(Offres offre) {
        this.offre = offre;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", fname=" + fname + ", gender=" + gender + ", birthday=" + birthday + ", adresse=" + adresse + ", email=" + email + ", password=" + password + ", image=" + image + ", role=" + role + ", Nb_point=" + Nb_point + ", id_offre=" + id_offre + '}';
    }


    

   

   

    
    
   
    
}