/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Interface pour les entités identifiables
 * Permet de gérer différents types d'identifiants (ID numérique, ISBN, etc.)
 * 
 * @author CAESAR
 */
public interface Identifiable {
    
    /**
     * Retourne l'identifiant de l'entité sous forme de String
     * @return l'identifiant de l'entité
     */
    String getIdentifier();
    
    /**
     * Vérifie si l'entité a un identifiant valide
     * @return true si l'entité a un identifiant valide
     */
    boolean hasIdentifier();
}
