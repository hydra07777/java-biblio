# Plan de Correction - Enregistrement des emprunts

## Problème identifié
L'enregistrement des emprunts échoue silencieusement car :
1. Les erreurs SQL sont capturées mais non affichées à l'utilisateur
2. Aucune vérification de l'existence des clés étrangères (livre, abonné)
3. La table abonne semble vide dans la base de données

## Fichiers à modifier

### 1. src/daos/EmpruntDao.java
- Modifier `ajouterEmprunt()` pour :
  - Ajouter un message d'erreur explicite en cas d'échec
  - Vérifier si l'ID du livre existe
  - Vérifier si l'ID de l'abonné existe
  - Retourner un boolean ou lancer une exception avec message clair

### 2. src/GestionEmpruntFrame.java  
- Modifier `ajouterEmprunt()` pour :
  - Afficher un message d'erreur si l'emprunt n'a pas pu être ajouté
  - Vérifier la validité des IDs saisis

### 3. Vérification de la base de données
- S'assurer qu'il y a des abonnés dans la table `abonne`
- Les IDs utilisés doivent correspondre à des enregistrements existants

## Étapes de correction
1. Modifier EmpruntDao pour gérer correctement les erreurs
2. Modifier GestionEmpruntFrame pour afficher les erreurs
3. Tester avec des IDs valides (livre et abonné existants)
