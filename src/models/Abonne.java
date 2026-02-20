/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Modèle représentant un abonné de la bibliothèque
 * Hérite de BaseEntity pour la gestion de l'ID
 * 
 * @author CAESAR
 */
public class Abonne extends BaseEntity {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    public Abonne() {
        super();
    }

    public Abonne(String nom, String prenom, String email, String telephone) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
    }

    public Abonne(int id, String nom, String prenom, String email, String telephone) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean isValid() {
        return super.isValid() && 
               isNotEmpty(nom) && 
               isNotEmpty(prenom) && 
               isNotEmpty(email);
    }

    @Override
    public String toString() {
        return "Abonne{" +
               "id=" + id +
               ", nom='" + nom + '\'' +
               ", prenom='" + prenom + '\'' +
               ", email='" + email + '\'' +
               ", telephone='" + telephone + '\'' +
               '}';
    }
}

