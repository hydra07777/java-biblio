/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

/**
 *
 * @author CAESAR
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Emprunt;
import utils.BDConnection;

public class EmpruntDao {

    public boolean ajouterEmprunt(Emprunt emprunt) {
        String sql = "INSERT INTO emprunt(id_livre, id_abonne, date_emprunt, date_retour, statut) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = BDConnection.getConnection()) {
            // Vérifier si le livre existe
            if (!livreExiste(conn, emprunt.getIdLivre())) {
                System.err.println("Erreur: Le livre avec ID " + emprunt.getIdLivre() + " n'existe pas.");
                return false;
            }
            
            // Vérifier si l'abonné existe
            if (!abonneExiste(conn, emprunt.getIdAbonne())) {
                System.err.println("Erreur: L'abonné avec ID " + emprunt.getIdAbonne() + " n'existe pas.");
                return false;
            }
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, emprunt.getIdLivre());
                ps.setInt(2, emprunt.getIdAbonne());
                ps.setDate(3, new java.sql.Date(emprunt.getDateEmprunt().getTime()));
                ps.setDate(4,
                        emprunt.getDateRetour() != null ? new java.sql.Date(emprunt.getDateRetour().getTime()) : null);
                ps.setString(5, emprunt.getStatut());

                ps.executeUpdate();
                System.out.println("Emprunt ajouté avec succès!");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Erreur SQL lors de l'ajout de l'emprunt: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean livreExiste(Connection conn, int idLivre) {
        String sql = "SELECT COUNT(*) FROM livre WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idLivre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification du livre: " + e.getMessage());
        }
        return false;
    }
    
    private boolean abonneExiste(Connection conn, int idAbonne) {
        String sql = "SELECT COUNT(*) FROM abonne WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAbonne);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de l'abonné: " + e.getMessage());
        }
        return false;
    }

    // Méthode pour mettre à jour automatiquement les statuts RETARDE
    public void mettreAJourStatuts() {
        String sql = "UPDATE emprunt SET statut = 'RETARDE' WHERE " +
                     "statut = 'EN_COURS' AND " +
                     "date_retour IS NOT NULL AND " +
                     "date_retour < CURDATE()";
        
        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            
            int nbUpdates = ps.executeUpdate();
            if (nbUpdates > 0) {
                System.out.println(nbUpdates + " emprunt(s) marqué(s) comme RETARDE");
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour des statuts: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Emprunt trouverParId(int id) {
        String sql = "SELECT e.*, a.nom as nom_abonne, a.prenom as prenom_abonne, l.titre as titre_livre " +
                     "FROM emprunt e " +
                     "LEFT JOIN abonne a ON e.id_abonne = a.id " +
                     "LEFT JOIN livre l ON e.id_livre = l.id " +
                     "WHERE e.id = ?";
        Emprunt emprunt = null;

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                java.util.Date dateEmprunt = rs.getDate("date_emprunt");
                java.util.Date dateRetour = rs.getDate("date_retour");
                String statut = rs.getString("statut");
                if (statut == null) statut = "EN_COURS";
                
                emprunt = new Emprunt(
                        rs.getInt("id"),
                        rs.getInt("id_livre"),
                        rs.getInt("id_abonne"),
                        dateEmprunt,
                        dateRetour,
                        statut);
                
                // Ajouter les noms pour l'affichage
                emprunt.setNomAbonne(rs.getString("nom_abonne"));
                emprunt.setPrenomAbonne(rs.getString("prenom_abonne"));
                emprunt.setTitreLivre(rs.getString("titre_livre"));
            }

        } catch (SQLException e) {
            System.err.println("Erreur SQL dans trouverParId: " + e.getMessage());
            e.printStackTrace();
        }

        return emprunt;
    }

    public List<Emprunt> listerTous() {
        // Mettre à jour les statuts automatiquement
        mettreAJourStatuts();
        
        // Query avec JOIN pour récupérer les noms
        String sql = "SELECT e.id, e.id_livre, e.id_abonne, e.date_emprunt, e.date_retour, e.statut, " +
                     "a.nom as nom_abonne, a.prenom as prenom_abonne, l.titre as titre_livre " +
                     "FROM emprunt e " +
                     "LEFT JOIN abonne a ON e.id_abonne = a.id " +
                     "LEFT JOIN livre l ON e.id_livre = l.id " +
                     "ORDER BY e.id DESC";
        List<Emprunt> emprunts = new ArrayList<>();

        try (Connection conn = BDConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                java.util.Date dateEmprunt = rs.getDate("date_emprunt");
                java.util.Date dateRetour = rs.getDate("date_retour");
                String statut = rs.getString("statut");
                
                // Gérer les valeurs nulles
                if (statut == null) {
                    statut = "EN_COURS";
                }
                
                // Vérifier si l'emprunt est en retard (si pas déjà retourné)
                if (statut.equals("EN_COURS") && dateRetour != null) {
                    java.util.Date today = new java.util.Date();
                    if (dateRetour.before(today)) {
                        statut = "RETARDE";
                    }
                }
                
                Emprunt emprunt = new Emprunt(
                        rs.getInt("id"),
                        rs.getInt("id_livre"),
                        rs.getInt("id_abonne"),
                        dateEmprunt,
                        dateRetour,
                        statut);
                
                // Ajouter les noms pour l'affichage
                emprunt.setNomAbonne(rs.getString("nom_abonne"));
                emprunt.setPrenomAbonne(rs.getString("prenom_abonne"));
                emprunt.setTitreLivre(rs.getString("titre_livre"));
                
                emprunts.add(emprunt);
            }

        } catch (SQLException e) {
            System.err.println("Erreur SQL dans listerTous: " + e.getMessage());
            e.printStackTrace();
        }

        return emprunts;
    }

    public List<Emprunt> trouverParAbonne(int idAbonne) {
        String sql = "SELECT e.*, a.nom as nom_abonne, a.prenom as prenom_abonne, l.titre as titre_livre " +
                     "FROM emprunt e " +
                     "LEFT JOIN abonne a ON e.id_abonne = a.id " +
                     "LEFT JOIN livre l ON e.id_livre = l.id " +
                     "WHERE e.id_abonne = ?";
        List<Emprunt> emprunts = new ArrayList<>();

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAbonne);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                java.util.Date dateEmprunt = rs.getDate("date_emprunt");
                java.util.Date dateRetour = rs.getDate("date_retour");
                String statut = rs.getString("statut");
                if (statut == null) statut = "EN_COURS";
                
                Emprunt emprunt = new Emprunt(
                        rs.getInt("id"),
                        rs.getInt("id_livre"),
                        rs.getInt("id_abonne"),
                        dateEmprunt,
                        dateRetour,
                        statut);
                
                // Ajouter les noms pour l'affichage
                emprunt.setNomAbonne(rs.getString("nom_abonne"));
                emprunt.setPrenomAbonne(rs.getString("prenom_abonne"));
                emprunt.setTitreLivre(rs.getString("titre_livre"));
                
                emprunts.add(emprunt);
            }

        } catch (SQLException e) {
            System.err.println("Erreur SQL dans trouverParAbonne: " + e.getMessage());
            e.printStackTrace();
        }

        return emprunts;
    }

    public List<Emprunt> trouverParLivre(int idLivre) {
        String sql = "SELECT e.*, a.nom as nom_abonne, a.prenom as prenom_abonne, l.titre as titre_livre " +
                     "FROM emprunt e " +
                     "LEFT JOIN abonne a ON e.id_abonne = a.id " +
                     "LEFT JOIN livre l ON e.id_livre = l.id " +
                     "WHERE e.id_livre = ?";
        List<Emprunt> emprunts = new ArrayList<>();

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idLivre);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                java.util.Date dateEmprunt = rs.getDate("date_emprunt");
                java.util.Date dateRetour = rs.getDate("date_retour");
                String statut = rs.getString("statut");
                if (statut == null) statut = "EN_COURS";
                
                Emprunt emprunt = new Emprunt(
                        rs.getInt("id"),
                        rs.getInt("id_livre"),
                        rs.getInt("id_abonne"),
                        dateEmprunt,
                        dateRetour,
                        statut);
                
                // Ajouter les noms pour l'affichage
                emprunt.setNomAbonne(rs.getString("nom_abonne"));
                emprunt.setPrenomAbonne(rs.getString("prenom_abonne"));
                emprunt.setTitreLivre(rs.getString("titre_livre"));
                
                emprunts.add(emprunt);
            }

        } catch (SQLException e) {
            System.err.println("Erreur SQL dans trouverParLivre: " + e.getMessage());
            e.printStackTrace();
        }

        return emprunts;
    }

    public void modifierEmprunt(Emprunt emprunt) {
        String sql = "UPDATE emprunt SET id_livre = ?, id_abonne = ?, date_emprunt = ?, date_retour = ?, statut = ? WHERE id = ?";

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, emprunt.getIdLivre());
            ps.setInt(2, emprunt.getIdAbonne());
            ps.setDate(3, new java.sql.Date(emprunt.getDateEmprunt().getTime()));
            ps.setDate(4,
                    emprunt.getDateRetour() != null ? new java.sql.Date(emprunt.getDateRetour().getTime()) : null);
            ps.setString(5, emprunt.getStatut());
            ps.setInt(6, emprunt.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erreur SQL dans modifierEmprunt: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void retournerEmprunt(int id, Date dateRetour) {
        String sql = "UPDATE emprunt SET date_retour = ?, statut = 'RETOURNE' WHERE id = ?";

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(dateRetour.getTime()));
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erreur SQL dans retournerEmprunt: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void supprimerEmprunt(int id) {
        String sql = "DELETE FROM emprunt WHERE id = ?";

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erreur SQL dans supprimerEmprunt: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
