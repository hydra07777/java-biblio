-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bibliothequejava
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `abonne`
--

DROP TABLE IF EXISTS `abonne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `abonne` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abonne`
--

/*!40000 ALTER TABLE `abonne` DISABLE KEYS */;
/*!40000 ALTER TABLE `abonne` ENABLE KEYS */;

--
-- Table structure for table `emprunt`
--

DROP TABLE IF EXISTS `emprunt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emprunt`
--

/*!40000 ALTER TABLE `emprunt` DISABLE KEYS */;
/*!40000 ALTER TABLE `emprunt` ENABLE KEYS */;

--
-- Table structure for table `livre`
--

DROP TABLE IF EXISTS `livre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livre`
--

/*!40000 ALTER TABLE `livre` DISABLE KEYS */;
INSERT INTO `livre` VALUES (1,'978-2-07-041272-5','Le Petit Prince','Antoine de Saint-Exupéry',25),(2,'978-2-253-00001-8','Les Misérables','Victor Hugo',15),(3,'978-2-07-036002-4','L\'Étranger','Albert Camus',30),(4,'978-2-266-11156-2','Harry Potter à l\'école des sorciers','J.K. Rowling',20),(5,'978-2-07-042430-8','1985','George Orwell',18),(6,'978-2-221-11211-5','Da Vinci Code','Dan Brown',12),(7,'978-2-07-036341-4','Germinal','Émile Zola',10),(8,'978-2-07-045806-5','Le Comte de Monte-Cristo','Alexandre Dumas',8),(9,'978-2-290-12345-6','Clean Code','Robert C. Martin',5),(10,'978-2-123-45678-9','Apprendre Java','Jean Dupont',14),(14,'978-2-07-042430-9','libl','lb',18);
/*!40000 ALTER TABLE `livre` ENABLE KEYS */;

--
-- Dumping routines for database 'bibliothequejava'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-20 16:23:43
