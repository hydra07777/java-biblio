/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 * Modèle représentant un emprunt de livre
 * Hérite de BaseEntity pour la gestion de l'ID
 * 
 * @author CAESAR
 */
public class Emprunt extends BaseEntity {
    private int idLivre;
    private int idAbonne;
    private Date dateEmprunt;
    private Date dateRetour;
    private String statut; // "EN_COURS", "RETOURNE", "RETARDE"

    public Emprunt() {
        super();
    }

    public Emprunt(int idLivre, int idAbonne, Date dateEmprunt, Date dateRetour, String statut) {
        super();
        this.idLivre = idLivre;
        this.idAbonne = idAbonne;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.statut = statut;
    }

    public Emprunt(int id, int idLivre, int idAbonne, Date dateEmprunt, Date dateRetour, String statut) {
        super(id);
        this.idLivre = idLivre;
        this.idAbonne = idAbonne;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.statut = statut;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public int getIdAbonne() {
        return idAbonne;
    }

    public void setIdAbonne(int idAbonne) {
        this.idAbonne = idAbonne;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public boolean isValid() {
        return super.isValid() && 
               isPositive(idLivre) && 
               isPositive(idAbonne) && 
               dateEmprunt != null &&
               (statut != null && (statut.equals("EN_COURS") || statut.equals("RETOURNE") || statut.equals("RETARDE")));
    }

    @Override
    public String toString() {
        return "Emprunt{" +
               "id=" + id +
               ", idLivre=" + idLivre +
               ", idAbonne=" + idAbonne +
               ", dateEmprunt=" + dateEmprunt +
               ", dateRetour=" + dateRetour +
               ", statut='" + statut + '\'' +
               '}';
    }
}

