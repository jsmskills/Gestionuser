/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etities;

import java.util.regex.Matcher;
import javafx.scene.input.KeyCode;
import Iservice.Iservice;
 
/**
 *
 * @author HP
 */
public class Personne {

 


  
    int idu;
    String nom;
String prenom;
String username;
String mail;
String pwd;
String  role;

    public Personne(String nom, String prenom, String username, String mail, String pwd, String  role) {

        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.mail = mail;
        this.pwd = pwd;
        this.role = role;
    }

    public Personne() {
       
    }

   
    

    

    public int getIdu() {
        return idu;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getPwd() {
        return pwd;
    }

    public String getRole() {
        return role;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setRole(String  role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "crudp{" + "idu=" + idu + ", nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", mail=" + mail + ", pwd=" + pwd + ", role=" + role + '}';
    }

    

    

    

    

}
