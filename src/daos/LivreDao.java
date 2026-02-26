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
import java.util.List;
import models.Livre;
import utils.BDConnection;

public class LivreDao {

    public void ajouterLivre(Livre livre) {
        String sql = "INSERT INTO livre(isbn, titre, auteur, quantite) VALUES (?, ?, ?, ?)";

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, livre.getIsbn());
            ps.setString(2, livre.getTitre());
            ps.setString(3, livre.getAuteur());
            ps.setInt(4, livre.getQuantite());

            ps.executeUpdate();
            System.out.println("Livre ajouté avec succès !");

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du livre : " + e.getMessage());
        }
    }

    public Livre trouverParIsbn(String isbn) {
        String sql = "SELECT * FROM livre WHERE isbn = ?";
        Livre livre = null;

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                livre = new Livre(
                    rs.getString("isbn"),
                    rs.getString("titre"),
                    rs.getString("auteur"),
                    rs.getInt("quantite")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche : " + e.getMessage());
        }

        return livre;
    }

    public List<Livre> listerTous() {
        String sql = "SELECT * FROM livre ORDER BY titre";
        List<Livre> livres = new ArrayList<>();

        try (Connection conn = BDConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Livre livre = new Livre(
                    rs.getInt("id"),
                    rs.getString("isbn"),
                    rs.getString("titre"),
                    rs.getString("auteur"),
                    rs.getInt("quantite")
                );
                livres.add(livre);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la liste : " + e.getMessage());
        }

        return livres;
    }

    public void modifierLivre(Livre livre) {
        String sql = "UPDATE livre SET titre = ?, auteur = ?, quantite = ? WHERE isbn = ?";

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, livre.getTitre());
            ps.setString(2, livre.getAuteur());
            ps.setInt(3, livre.getQuantite());
            ps.setString(4, livre.getIsbn());

            ps.executeUpdate();
            System.out.println("Livre modifié avec succès !");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification : " + e.getMessage());
        }
    }

    public List<Livre> rechercher(String critere) {
        List<Livre> livres = new ArrayList<>();
        if (critere == null || critere.trim().isEmpty()) {
            return listerTous();
        }
        String sql = "SELECT * FROM livre WHERE UPPER(isbn) LIKE ? OR UPPER(titre) LIKE ? OR UPPER(auteur) LIKE ? ORDER BY titre";
        String pattern = "%" + critere.trim().toUpperCase() + "%";

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pattern);
            ps.setString(2, pattern);
            ps.setString(3, pattern);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Livre livre = new Livre(
                    rs.getString("isbn"),
                    rs.getString("titre"),
                    rs.getString("auteur"),
                    rs.getInt("quantite")
                );
                livres.add(livre);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche : " + e.getMessage());
        }

        return livres;
    }

    public void supprimerLivre(String isbn) {
        String sql = "DELETE FROM livre WHERE isbn = ?";

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, isbn);
            ps.executeUpdate();
            System.out.println("Livre supprimé avec succès !");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
        }
    }
}

