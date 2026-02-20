/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 * Classe abstraite pour les entités avec un identifiant numérique (ID)
 * Hérite de BaseModel et ajoute la gestion de l'ID
 * 
 * @author CAESAR
 */
public abstract class BaseEntity extends BaseModel {
    
    protected int id;
    
    /**
     * Constructeur par défaut
     */
    public BaseEntity() {
        this.id = 0;
    }
    
    /**
     * Constructeur avec ID
     * @param id l'identifiant de l'entité
     */
    public BaseEntity(int id) {
        this.id = id;
    }
    
    /**
     * Retourne l'identifiant de l'entité
     * @return l'ID de l'entité
     */
    public int getId() {
        return id;
    }
    
    /**
     * Définit l'identifiant de l'entité
     * @param id l'ID à définir
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Vérifie si l'entité a un ID valide (ID > 0)
     * @return true si l'entité a un ID valide
     */
    public boolean hasId() {
        return id > 0;
    }
    
    /**
     * Vérifie si l'entité est nouvelle (pas encore persistée)
     * @return true si l'entité n'a pas d'ID
     */
    public boolean isNew() {
        return id <= 0;
    }
    
    /**
     * Valide que l'ID est valide si l'entité n'est pas nouvelle
     * @return true si l'entité est nouvelle ou a un ID valide
     */
    @Override
    public boolean isValid() {
        return isNew() || hasId();
    }
    
    /**
     * Compare deux entités par leur ID
     * @param obj l'objet à comparer
     * @return true si les deux entités ont le même ID et sont de la même classe
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) obj;
        return id == that.id;
    }
    
    /**
     * Retourne le hash code basé sur l'ID
     * @return le hash code
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
