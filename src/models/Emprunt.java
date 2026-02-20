/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author CAESAR
 */
import java.util.Date;

public class Emprunt {
    private int id;
    private int idLivre;
    private int idAbonne;
    private Date dateEmprunt;
    private Date dateRetour;
    private String statut; // "EN_COURS", "RETOURNE", "RETARDE"

    public Emprunt() {
    }

    public Emprunt(int idLivre, int idAbonne, Date dateEmprunt, Date dateRetour, String statut) {
        this.idLivre = idLivre;
        this.idAbonne = idAbonne;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.statut = statut;
    }

    public Emprunt(int id, int idLivre, int idAbonne, Date dateEmprunt, Date dateRetour, String statut) {
        this.id = id;
        this.idLivre = idLivre;
        this.idAbonne = idAbonne;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}

