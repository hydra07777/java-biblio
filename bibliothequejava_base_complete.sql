-- Base de données pour le projet Java Bibliothèque
-- Executez ce script dans phpMyAdmin pour créer la base de données

-- Créer la base de données
CREATE DATABASE IF NOT EXISTS bibliothequejava;
USE bibliothequejava;

-- Table des abonnés
DROP TABLE IF EXISTS `abonne`;
CREATE TABLE `abonne` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `abonne` VALUES 
(1, 'Kalaba Mwila', 'Monique Michael', 'monique.kalaba@gmail.com', '0990000001'),
(2, 'Ngoya Mpiana', 'Glodi', 'glodi.ngoya@gmail.com', '0990000002'),
(3, 'Bakila Luyeye', 'Mervedi', 'mervedi.bakila@gmail.com', '0990000003'),
(4, 'Musulumbila Kambi', 'Peter', 'peter.musulumbila@gmail.com', '0990000004'),
(5, 'Kanumbedi Ngandu', 'Isaac', 'isaac.kanumbedi@gmail.com', '0990000005'),
(6, 'Mubwaka Basala', 'Dorcas', 'dorcas.mubwaka@gmail.com', '0990000006'),
(7, 'Musafiri Maneno', 'Randy', 'randy.musafiri@gmail.com', '0990000007'),
(8, 'Lusukula Omokoko', 'Andy', 'andy.lusukula@gmail.com', '0990000008'),
(9, 'Paysayo Maliako', 'Cesar', 'cesar.paysayo@gmail.com', '0990000009'),
(10, 'Buana Kaninda', 'Samuel', 'samuel.buana@gmail.com', '0990000010'),
(11, 'Ngonde Shaidai', 'Bradley', 'bradley.ngonde@gmail.com', '0990000011');

-- Table des livres
DROP TABLE IF EXISTS `livre`;
CREATE TABLE `livre` (
  `id` int NOT NULL AUTO_INCREMENT,
  `isbn` varchar(20) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `auteur` varchar(255) DEFAULT NULL,
  `quantite` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn` (`isbn`),
  CONSTRAINT `chk_quantite` CHECK ((`quantite` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Insérer des livres de test
INSERT INTO `livre` VALUES 
(1, '978-2-207-041272-5', 'Le Petit Prince', 'Antoine de Saint-Exupéry', 25),
(2, '978-2-253-00001-8', 'Les Misérables', 'Victor Hugo', 15),
(3, '978-2-207-036002-4', 'L\'Étranger', 'Albert Camus', 30),
(4, '978-2-266-11156-2', 'Harry Potter à l\'école des sorciers', 'J.K. Rowling', 20),
(5, '978-2-207-042430-8', '1984', 'George Orwell', 18),
(6, '978-2-221-11211-5', 'Da Vinci Code', 'Dan Brown', 12),
(7, '978-2-207-036341-4', 'Germinal', 'Émile Zola', 10),
(8, '978-2-207-045806-5', 'Le Comte de Monte-Cristo', 'Alexandre Dumas', 8),
(9, '978-2-290-12345-6', 'Clean Code', 'Robert C. Martin', 5),
(10, '978-2-123-45678-9', 'Apprendre Java', 'Jean Dupont', 14);

-- Table des emprunts
DROP TABLE IF EXISTS `emprunt`;
CREATE TABLE `emprunt` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_livre` int NOT NULL,
  `id_abonne` int NOT NULL,
  `date_emprunt` date NOT NULL,
  `date_retour` date DEFAULT NULL,
  `statut` varchar(20) DEFAULT 'EN_COURS',
  PRIMARY KEY (`id`),
  KEY `fk_emprunt_livre` (`id_livre`),
  KEY `fk_emprunt_abonne` (`id_abonne`),
  CONSTRAINT `fk_emprunt_abonne` FOREIGN KEY (`id_abonne`) REFERENCES `abonne` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_emprunt_livre` FOREIGN KEY (`id_livre`) REFERENCES `livre` (`id`) ON DELETE CASCADE,
  CONSTRAINT `chk_statut` CHECK ((`statut` in (_utf8mb4'EN_COURS',_utf8mb4'RETOURNE',_utf8mb4'RETARDE')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Insérer un exemple d'emprunt (optionnel)
-- INSERT INTO `emprunt` VALUES (1, 1, 1, '2024-01-15', '2024-01-22', 'RETOURNE');

-- Table des amendes (si nécessaire)
DROP TABLE IF EXISTS `amende`;
CREATE TABLE `amende` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_emprunt` int NOT NULL,
  `montant` decimal(10,2) NOT NULL,
  `date_amende` date NOT NULL,
  `statut` varchar(20) DEFAULT 'NON_PAYEE',
  PRIMARY KEY (`id`),
  KEY `fk_amende_emprunt` (`id_emprunt`),
  CONSTRAINT `fk_amende_emprunt` FOREIGN KEY (`id_emprunt`) REFERENCES `emprunt` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Message de confirmation
SELECT 'Base de données créée avec succès !' AS message;
SELECT '5 abonnés et 10 livres ont été ajoutés.' AS donnees_ajoutees;
