-- Table for Amendes
DROP TABLE IF EXISTS `amande`;
CREATE TABLE `amande` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_emprunt` int NOT NULL,
  `montant` decimal(10,2) NOT NULL,
  `date_amende` date NOT NULL,
  `statut` varchar(20) DEFAULT 'NON_PAYEE',
  PRIMARY KEY (`id`),
  KEY `fk_amende_emprunt` (`id_emprunt`),
  CONSTRAINT `fk_amende_emprunt` FOREIGN KEY (`id_emprunt`) REFERENCES `emprunt` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
