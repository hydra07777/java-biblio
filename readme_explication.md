# Documentation Technique - Application de Gestion de Bibliothèque Java

## Table des Matières
1. [Présentation Générale](#présentation-générale)
2. [Fonctionnalités](#fonctionnalités)
3. [Architecture du Projet](#architecture-du-projet)
4. [Héritage et Polymorphisme](#héritage-et-polymorphisme)
5. [Encapsulation](#encapsulation)
6. [Modèle de Données](#modèle-de-données)
7. [Fonctionnement des Classes](#fonctionnement-des-classes)

---

## 1. Présentation Générale

Cette application de gestion de bibliothèque est développée en **Java** avec une interface graphique **Swing**. Elle permet de gérer les livres, les abonnés, les emprunts et les amendes d'une bibliothèque.

### Caractéristiques techniques :
- **Langage** : Java
- **Interface** : Swing (Java GUI)
- **Base de données** : MySQL
- **Architecture** : Pattern DAO (Data Access Object)
- **Connexion** : JDBC

---

## 2. Fonctionnalités

### 2.1 Gestion des Livres
- **Ajouter** un nouveau livre (ISBN, titre, auteur, quantité)
- **Modifier** les informations d'un livre existant
- **Supprimer** un livre
- **Rechercher** des livres par ISBN, titre ou auteur
- **Lister** tous les livres disponibles

### 2.2 Gestion des Abonnés
- **Ajouter** un nouvel abonné (nom, prénom, email, téléphone)
- **Modifier** les informations d'un abonné
- **Supprimer** un abonné
- **Lister** tous les abonnés

### 2.3 Gestion des Emprunts
- **Enregistrer** un nouvel emprunt (livre + abonné + dates)
- **Retourner** un livre emprunté
- **Lister** les emprunts
- **Filtrer** les emprunts par abonné ou par livre
- **Suivre** le statut des emprunts (EN_COURS, RETOURNE, RETARDE)

### 2.4 Gestion des Amendes
- Visualisation des amendes et retards

---

## 3. Architecture du Projet

```
src/
├── models/              # Classes métier (entités)
│   ├── BaseModel.java
│   ├── BaseEntity.java
│   ├── Identifiable.java
│   ├── Abonne.java
│   ├── Livre.java
│   └── Emprunt.java
├── daos/                # Data Access Objects (persistance)
│   ├── AbonneDao.java
│   ├── LivreDao.java
│   └── EmpruntDao.java
├── utils/               # Utilitaires
│   └── BDConnection.java
├── MenuPrincipale.java  # Menu principal
├── GestionLivresFrame.java
├── GestionAbonneFrame.java
├── GestionEmpruntFrame.java
└── GestionAmendesFrame.java
```

### Schéma de l'architecture :

```
┌─────────────────────────────────────────────────────────┐
│                    INTERFACE GUI                        │
│  (MenuPrincipale, GestionLivresFrame, etc.)             │
└─────────────────────────┬───────────────────────────────┘
                          │
┌─────────────────────────▼───────────────────────────────┐
│                      DAOs                               │
│  (AbonneDao, LivreDao, EmpruntDao)                      │
└─────────────────────────┬───────────────────────────────┘
                          │
┌─────────────────────────▼───────────────────────────────┐
│                   MODÈLES (Entities)                    │
│  (Abonne, Livre, Emprunt)                               │
└─────────────────────────┬───────────────────────────────┘
                          │
┌─────────────────────────▼───────────────────────────────┐
│              BASE DE DONNÉES MySQL                      │
│  (bibliothequejava)                                     │
└─────────────────────────────────────────────────────────┘
```

---

## 4. Héritage et Polymorphisme

### 4.1 Hiérarchie des Classes Modèles

```
                    ┌─────────────────────┐
                    │   <<abstract>>      │
                    │     BaseModel       │
                    │─────────────────────│
                    │ + isValid(): boolean│
                    │ + toString(): String│
                    │ # isEmpty(String)   │
                    │ # isNotEmpty(String)│
                    │ # isPositive(int)   │
                    └──────────┬──────────┘
                               │
                               │ extends
                               │
          ┌────────────────────┴────────────────────┐
          │                                         │
          ▼                                         ▼
┌─────────────────────┐               ┌─────────────────────────┐
│   <<abstract>>      │               │      <<interface>>      │
│   BaseEntity        │               │     Identifiable        │
│─────────────────────│               │─────────────────────────│
│ - id: int           │               │+ getIdentifier(): String│
│─────────────────────│               │+ hasIdentifier():boolean│
│ + getId(): int      │               └───────────┬─────────────┘
│ + setId(int)        │                           │
│ + hasId(): boolean  │                           │ implements
│ + isNew(): boolean  │                           │
│ + isValid(): boolean│                           │
│ + equals(Object)    │                           │
│ + hashCode(): int   │                           ▼
└─────────┬───────────┘               ┌─────────────────────┐
          │                           │       Livre         │
          │ extends                   │─────────────────────│
          │                           │ - isbn: String      │
          ▼                           │ - titre: String     │
┌─────────────────────┐               │ - auteur: String    │
│      Abonne         │               │ - quantite: int     │
│─────────────────────│               └─────────────────────┘
│ - nom: String       │
│ - prenom: String    │
│ - email: String     │
│ - telephone: String │
└─────────────────────┘

┌─────────────────────┐
│      Emprunt        │
│─────────────────────│
│ - idLivre: int      │
│ - idAbonne: int     │
│ - dateEmprunt: Date │
│ - dateRetour: Date  │
│ - statut: String    │
└─────────────────────┘
```

### 4.2 Explications Détaillées

#### BaseModel (Classe de base abstraite)
C'est la classe racine de tous les modèles. Elle fournit :
- Des méthodes utilitaires pour la validation (`isEmpty`, `isNotEmpty`, `isPositive`, etc.)
- Des méthodes abstraites que les sous-classes doivent implémenter (`isValid()`, `toString()`)

```
java
public abstract class BaseModel {
    public abstract boolean isValid();  // Chaque modèle doit définir sa validation
    public abstract String toString();  // Chaque modèle doit définir sa représentation
}
```

#### BaseEntity (Hérite de BaseModel)
Ajoute la gestion d'un identifiant numérique (ID) :
- Tous les objets persistés en base de données ont un ID
- Méthodes utilitaires : `hasId()`, `isNew()`, `equals()`, `hashCode()`

#### Identifiable (Interface)
Permet d'identifier les entités par différents types d'identifiants :
- Pour `Livre` : l'ISBN est l'identifiant unique
- Contrairement à `BaseEntity` qui utilise un ID numérique

#### Abonne et Emprunt (Héritent de BaseEntity)
Ces classes représentent des entités avec un ID numérique auto-incrémenté en base de données.

#### Livre (Hérite de BaseModel + Implémente Identifiable)
Utilise l'ISBN comme identifiant unique (au lieu d'un ID numérique).

---

## 5. Encapsulation

L'encapsulation est strictement appliquée dans toutes les classes du projet. Voici comment elle est mise en œuvre :

### 5.1 Principes Appliqués

| Principe               | Application dans le Code |
|------------------------|--------------------------|
| **Attributs privés**   | Tous les attributs sont en `private` |
| **Getters/Setters**    | Méthodes publiques pour accéder/modifier les attributs |
| **Validation**         | La méthode `isValid()` vérifie les données avant persistance |
| **Méthodes protégées** | Les utilitaires de validation sont `protected` (accessibles aux sous-classes) |

### 5.2 Exemple avec la classe Abonne

```
java
public class Abonne extends BaseEntity {
    // ✓ Attributs PRIVÉS - encapsulation stricte
    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    // ✓ Getters et Setters publics pour accéder aux données
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    // ... autres getters/setters

    // ✓ Validation des données dans isValid()
    @Override
    public boolean isValid() {
        return super.isValid() && 
               isNotEmpty(nom) &&           // Utilise la méthode héritée
               isNotEmpty(prenom) && 
               isNotEmpty(email);
    }
}
```

### 5.3 Accès aux Membres (Modificateurs d'accès)

| Modificateur | Classe | Package | Sous-classe | Partout |
|--------------|--------|---------|-------------|---------|
| `private`    | ✓      |         |             |         |
| `protected`  | ✓      | ✓       | ✓           |         |
| `public`     | ✓      | ✓       | ✓           | ✓       |
| (défaut)     | ✓      | ✓       |             |         |

**Utilisation dans le projet :**
- `private` : Tous les attributs des modèles
- `protected` : Méthodes utilitaires de BaseModel (`isEmpty`, `isNotEmpty`, etc.)
- `public` : Getters/setters, méthodes DAO, interfaces graphiques

---

## 6. Modèle de Données

### 6.1 Schéma de la Base de Données

```
┌──────────────────┐       ┌──────────────────┐
│     abonne       │       │      livre       │
├──────────────────┤       ├──────────────────┤
│ id (PK)          │       │ id (PK)          │
│ nom              │       │ isbn (UNIQUE)    │
│ prenom           │       │ titre            │
│ email            │       │ auteur           │
│ telephone        │       │ quantite         │
└────────┬─────────┘       └──────────────────┘
         │
         │ 1,N
         │
         ▼
┌──────────────────────────────────────────┐
│               emprunt                    │
├──────────────────────────────────────────┤
│ id (PK)                                  │
│ id_livre (FK) ───────► livre(id)         │
│ id_abonne (FK) ──────► abonne(id)        │
│ date_emprunt                             │
│ date_retour                              │
│ statut (EN_COURS, RETOURNE, RETARDE)     │
└──────────────────────────────────────────┘
```

### 6.2 Descriptions des Tables

#### Table `abonne`
| Colonne   | Type         | Contrainte |
|-----------|--------------|------------|
| id        | INT          | PRIMARY KEY, AUTO_INCREMENT |
| nom       | VARCHAR(100) | NOT NULL |
| prenom    | VARCHAR(100) | NOT NULL |
| email     | VARCHAR(255) | |
| telephone | VARCHAR(50)  | |

#### Table `livre`
| Colonne | Type | Contrainte |
|---------|------|------------|
| id | INT | PRIMARY KEY, AUTO_INCREMENT |
| isbn | VARCHAR(20) | NOT NULL, UNIQUE |
| titre | VARCHAR(255) | NOT NULL |
| auteur | VARCHAR(255) | |
| quantite | INT | DEFAULT 0, CHECK >= 0 |

#### Table `emprunt`
| Colonne | Type | Contrainte |
|---------|------|------------|
| id | INT | PRIMARY KEY, AUTO_INCREMENT |
| id_livre | INT | FK → livre(id), CASCADE |
| id_abonne | INT | FK → abonne(id), CASCADE |
| date_emprunt | DATE | NOT NULL |
| date_retour | DATE | |
| statut | VARCHAR(20) | CHECK (EN_COURS, RETOURNE, RETARDE) |

---

## 7. Fonctionnement des Classes

### 7.1 Couche Modèle (Models)

#### **BaseModel.java**
Classe abstraite de base fournissant :
- Méthodes de validation des chaînes (`isEmpty`, `isNotEmpty`)
- Méthodes de validation des nombres (`isPositive`, `isPositiveOrZero`)
- Méthodes abstraites `isValid()` et `toString()`

```
java
// Méthodes utilitaires protégées (accessibles aux sous-classes)
protected boolean isEmpty(String str) {
    return str == null || str.trim().isEmpty();
}

protected boolean isNotEmpty(String str) {
    return !isEmpty(str);
}
```

#### **BaseEntity.java**
Étend BaseModel pour les entités avec ID numérique :
- Gestion de l'ID (getter/setter)
- Méthodes de vérification : `hasId()`, `isNew()`
- Redéfinition de `equals()` et `hashCode()` basés sur l'ID

#### **Abonne.java**
Représente un abonné de la bibliothèque.
- **Attributs** : nom, prénom, email, téléphone
- **Héritage** : extends BaseEntity (gestion automatique de l'ID)

#### **Livre.java**
Représente un livre de la bibliothèque.
- **Attributs** : isbn, titre, auteur, quantité
- **Interface** : implements Identifiable (utilise ISBN comme identifiant)
- **Particularité** : N'hérite PAS de BaseEntity (utilise ISBN au lieu d'ID numérique)

#### **Emprunt.java**
Représente un emprunt de livre par un abonné.
- **Attributs** : idLivre, idAbonne, dateEmprunt, dateRetour, statut
- **Statuts possibles** : "EN_COURS", "RETOURNE", "RETARDE"
- **Héritage** : extends BaseEntity

### 7.2 Couche d'Accès aux Données (DAOs)

Les DAOs implémentent le **Pattern DAO** (Data Access Object) pour séparerr la logique métier de l'accès aux données.

#### **AbonneDao.java**
| Méthode | Description |
|---------|-------------|
| `ajouterAbonne(Abonne)` | Insère un nouvel abonné |
| `trouverParId(int id)` | Recherche un abonné par son ID |
| `listerTous()` | Retourne tous les abonnés |
| `modifierAbonne(Abonne)` | Met à jour un abonné |
| `supprimerAbonne(int id)` | Supprime un abonné par son ID |

#### **LivreDao.java**
| Méthode | Description |
|---------|-------------|
| `ajouterLivre(Livre)` | Insère un nouveau livre |
| `trouverParIsbn(String isbn)` | Recherche un livre par ISBN |
| `listerTous()` | Retourne tous les livres |
| `modifierLivre(Livre)` | Met à jour un livre |
| `rechercher(String critere)` | Recherche par ISBN, titre ou auteur |
| `supprimerLivre(String isbn)` | Supprime un livre par ISBN |

#### **EmpruntDao.java**
| Méthode | Description |
|---------|-------------|
| `ajouterEmprunt(Emprunt)` | Enregistre un nouvel emprunt |
| `trouverParId(int id)` | Recherche un emprunt par ID |
| `listerTous()` | Retourne tous les emprunts |
| `trouverParAbonne(int idAbonne)` | Filtre les emprunts par abonné |
| `trouverParLivre(int idLivre)` | Filtre les emprunts par livre |
| `modifierEmprunt(Emprunt)` | Met à jour un emprunt |
| `retournerEmprunt(int id, Date)` | Marque un emprunt comme retourné |
| `supprimerEmprunt(int id)` | Supprime un emprunt |

### 7.3 Connexion à la Base de Données

#### **BDConnection.java**
Gère la connexion à la base de données MySQL.

```
java
private static final String url = 
    "jdbc:mysql://localhost:3306/bibliothequejava?useSSL=false&serverTimezone=UTC";

private static final String user = "root";
private static final String mdp = "";

public static Connection getConnection() {
    Class.forName("com.mysql.cj.jdbc.Driver");  // Chargement du driver
    conn = DriverManager.getConnection(url, user, mdp);
    return conn;
}
```

**Configuration requise :**
- Driver MySQL : `com.mysql.cj.jdbc.Driver`
- Base : `bibliothequejava`
- Serveur : `localhost:3306`

### 7.4 Interface Graphique (GUI)

#### **MenuPrincipale.java**
- Menu principal de l'application
- Boutons de navigation vers les différentes fonctionnalités
- Thème visuel personnalisé (couleurs nuit)

#### **GestionLivresFrame.java**
Interface de gestion des livres avec :
- Formulaire d'ajout/modification
- Tableau d'affichage des livres
- Boutons : Ajouter, Modifier, Supprimer, Rechercher, Actualiser

#### **Flux de données dans la GUI**

```
┌─────────────────┐      ┌─────────────┐      ┌─────────────────┐
│  Interface      │────▶│   DAO       │────▶│  Base de données│
│  (Saisie/       │     │  (LivreDao)  │     │  MySQL          │
│   Affichage)    │◀────│             │◀────│                 │
└─────────────────┘      └─────────────┘      └─────────────────┘
```

### 7.5 Exemple de Flux d'Exécution

**Ajout d'un livre :**

1. L'utilisateur saisit les informations dans le formulaire
2. Clic sur le bouton "Ajouter"
3. La méthode `validerFormulaire()` vérifie les champs
4. Création d'un objet `Livre` avec les données
5. Appel de `livreDao.ajouterLivre(livre)`
6. Le DAO exécute une requête INSERT SQL
7. Affichage d'un message de confirmation
8. Actualisation du tableau avec `actualiserTable()`

---

## 8. Patterns et Principes Utilisés

### 8.1 Patterns de Conception

| Pattern | Application |
|---------|-------------|
| **DAO** | AbonneDao, LivreDao, EmpruntDao |
| **Abstract Factory** | BaseModel → BaseEntity → Abonne/Emprunt |
| **Template Method** | isValid() dans BaseModel redéfini dans chaque sous-classe |

### 8.2 Principes POO

- **Héritage** : BaseModel → BaseEntity → Abonne/Emprunt
- **Polymorphisme** : isValid() a un comportement différent selon la classe
- **Encapsulation** : Attributs privés avec getters/setters
- **Abstraction** : Classes et méthodes abstraites (BaseModel, BaseEntity)

---

## 9. Compilation et Exécution

### Prérequis
- JDK 8 ou supérieur
- MySQL 8.0+
- Driver MySQL JDBC (mysql-connector-java)

### Compilation
```
bash
# Via NetBeans (recommandé)
# Ou en ligne de commande :
javac -d build/classes -sourcepath src src/*.java src/**/*.java
```

### Exécution
```
bash
java -cp build/classes MenuPrincipale
```

### Configuration Base de Données
1. Créer une base de données `bibliothequejava`
2. Exécuter le script SQL `bibliothequejava_2026-02-20_162300.sql`
3. Modifier `BDConnection.java` si nécessaire (utilisateur/mot de passe)

---

## 10. Résumé

| Concept | Exemple dans le projet |
|---------|----------------------|
| **Héritage** | `Abonne extends BaseEntity` |
| **Polymorphisme** | `isValid()` redéfini dans chaque modèle |
| **Encapsulation** | Attributs `private` + getters/setters |
| **Interface** | `Livre implements Identifiable` |
| **Classe abstraite** | `BaseModel`, `BaseEntity` |
| **Pattern DAO** | `AbonneDao`, `LivreDao`, `EmpruntDao` |

Ce projet illustre les concepts fondamentaux de la programmation orientée objet en Java tout en utilisant des patterns couramment employés dans les applications professionnelles de gestion de données.
