/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Modèle représentant un livre de la bibliothèque
 * Hérite de BaseModel et implémente Identifiable (utilise ISBN comme identifiant)
 * 
 * @author CAESAR
 */
public class Livre extends BaseModel implements Identifiable {
    private String isbn;
    private String titre;
    private String auteur;
    private int quantite;

    public Livre() {
    }

    public Livre(String isbn, String titre, String auteur, int quantite) {
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.quantite = quantite;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public boolean isValid() {
        return isNotEmpty(isbn) && 
               isNotEmpty(titre) && 
               isNotEmpty(auteur) && 
               isPositiveOrZero(quantite);
    }

    @Override
    public String getIdentifier() {
        return isbn;
    }

    @Override
    public boolean hasIdentifier() {
        return isNotEmpty(isbn);
    }

    @Override
    public String toString() {
        return "Livre{" +
               "isbn='" + isbn + '\'' +
               ", titre='" + titre + '\'' +
               ", auteur='" + auteur + '\'' +
               ", quantite=" + quantite +
               '}';
    }
}

