/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Classe abstraite de base pour tous les modèles
 * Fournit des méthodes communes et utilitaires
 * 
 * @author CAESAR
 */
public abstract class BaseModel {
    
    /**
     * Constructeur par défaut
     */
    public BaseModel() {
    }
    
    /**
     * Valide les données du modèle
     * @return true si le modèle est valide, false sinon
     */
    public abstract boolean isValid();
    
    /**
     * Retourne une représentation textuelle du modèle
     * @return String représentant le modèle
     */
    @Override
    public abstract String toString();
    
    /**
     * Vérifie si une chaîne est vide ou null
     * @param str la chaîne à vérifier
     * @return true si la chaîne est vide ou null
     */
    protected boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    /**
     * Vérifie si une chaîne n'est pas vide
     * @param str la chaîne à vérifier
     * @return true si la chaîne n'est pas vide
     */
    protected boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    /**
     * Vérifie si un nombre est positif
     * @param value le nombre à vérifier
     * @return true si le nombre est positif
     */
    protected boolean isPositive(int value) {
        return value > 0;
    }
    
    /**
     * Vérifie si un nombre est positif ou nul
     * @param value le nombre à vérifier
     * @return true si le nombre est positif ou nul
     */
    protected boolean isPositiveOrZero(int value) {
        return value >= 0;
    }
}
