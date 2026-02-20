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

    public void ajouterEmprunt(Emprunt emprunt) {
        String sql = "INSERT INTO emprunt(id_livre, id_abonne, date_emprunt, date_retour, statut) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, emprunt.getIdLivre());
            ps.setInt(2, emprunt.getIdAbonne());
            ps.setDate(3, new java.sql.Date(emprunt.getDateEmprunt().getTime()));
            ps.setDate(4,
                    emprunt.getDateRetour() != null ? new java.sql.Date(emprunt.getDateRetour().getTime()) : null);
            ps.setString(5, emprunt.getStatut());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Emprunt trouverParId(int id) {
        String sql = "SELECT * FROM emprunt WHERE id = ?";
        Emprunt emprunt = null;

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                emprunt = new Emprunt(
                        rs.getInt("id"),
                        rs.getInt("id_livre"),
                        rs.getInt("id_abonne"),
                        rs.getDate("date_emprunt"),
                        rs.getDate("date_retour"),
                        rs.getString("statut"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprunt;
    }

    public List<Emprunt> listerTous() {
        String sql = "SELECT * FROM emprunt";
        List<Emprunt> emprunts = new ArrayList<>();

        try (Connection conn = BDConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Emprunt emprunt = new Emprunt(
                        rs.getInt("id"),
                        rs.getInt("id_livre"),
                        rs.getInt("id_abonne"),
                        rs.getDate("date_emprunt"),
                        rs.getDate("date_retour"),
                        rs.getString("statut"));
                emprunts.add(emprunt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprunts;
    }

    public List<Emprunt> trouverParAbonne(int idAbonne) {
        String sql = "SELECT * FROM emprunt WHERE id_abonne = ?";
        List<Emprunt> emprunts = new ArrayList<>();

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAbonne);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Emprunt emprunt = new Emprunt(
                        rs.getInt("id"),
                        rs.getInt("id_livre"),
                        rs.getInt("id_abonne"),
                        rs.getDate("date_emprunt"),
                        rs.getDate("date_retour"),
                        rs.getString("statut"));
                emprunts.add(emprunt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprunts;
    }

    public List<Emprunt> trouverParLivre(int idLivre) {
        String sql = "SELECT * FROM emprunt WHERE id_livre = ?";
        List<Emprunt> emprunts = new ArrayList<>();

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idLivre);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Emprunt emprunt = new Emprunt(
                        rs.getInt("id"),
                        rs.getInt("id_livre"),
                        rs.getInt("id_abonne"),
                        rs.getDate("date_emprunt"),
                        rs.getDate("date_retour"),
                        rs.getString("statut"));
                emprunts.add(emprunt);
            }

        } catch (SQLException e) {
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
            e.printStackTrace();
        }
    }
}
