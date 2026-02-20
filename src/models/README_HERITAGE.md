# Guide d'utilisation des classes de base pour les modèles

## Structure des classes créées

### 1. `BaseModel` (classe abstraite)
Classe de base pour tous les modèles. Fournit :
- Méthode abstraite `isValid()` pour la validation
- Méthode abstraite `toString()` pour l'affichage
- Méthodes utilitaires : `isEmpty()`, `isNotEmpty()`, `isPositive()`, `isPositiveOrZero()`

### 2. `BaseEntity` (classe abstraite)
Hérite de `BaseModel` pour les entités avec un ID numérique. Ajoute :
- Gestion de l'ID (`id`)
- Méthodes : `hasId()`, `isNew()`, `getId()`, `setId()`
- Implémentation de `equals()` et `hashCode()` basés sur l'ID
- Validation automatique de l'ID

### 3. `Identifiable` (interface)
Pour les entités avec différents types d'identifiants (ISBN, code, etc.). Définit :
- `getIdentifier()` : retourne l'identifiant sous forme de String
- `hasIdentifier()` : vérifie si l'identifiant est valide

## Exemples d'utilisation

### Exemple 1 : Modèle avec ID numérique (Abonne, Emprunt)

```java
public class Abonne extends BaseEntity {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    
    // Constructeurs...
    
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
               '}';
    }
    
    // Getters et setters...
}
```

### Exemple 2 : Modèle avec identifiant personnalisé (Livre)

```java
public class Livre extends BaseModel implements Identifiable {
    private String isbn;
    private String titre;
    private String auteur;
    private int quantite;
    
    // Constructeurs...
    
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
    
    // Getters et setters...
}
```

## Avantages

1. **Code réutilisable** : Méthodes communes centralisées
2. **Validation standardisée** : Méthode `isValid()` pour tous les modèles
3. **Affichage cohérent** : `toString()` personnalisé pour chaque modèle
4. **Gestion des IDs** : `BaseEntity` simplifie la gestion des identifiants numériques
5. **Flexibilité** : `Identifiable` permet différents types d'identifiants
