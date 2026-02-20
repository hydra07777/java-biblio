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
import models.Abonne;
import utils.BDConnection;

public class AbonneDao {

    public void ajouterAbonne(Abonne abonne) {
        String sql = "INSERT INTO abonne(nom, prenom, email, telephone) VALUES (?, ?, ?, ?)";

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, abonne.getNom());
            ps.setString(2, abonne.getPrenom());
            ps.setString(3, abonne.getEmail());
            ps.setString(4, abonne.getTelephone());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Abonne trouverParId(int id) {
        String sql = "SELECT * FROM abonne WHERE id = ?";
        Abonne abonne = null;

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                abonne = new Abonne(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return abonne;
    }

    public List<Abonne> listerTous() {
        String sql = "SELECT * FROM abonne";
        List<Abonne> abonnes = new ArrayList<>();

        try (Connection conn = BDConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Abonne abonne = new Abonne(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"));
                abonnes.add(abonne);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return abonnes;
    }

    public void modifierAbonne(Abonne abonne) {
        String sql = "UPDATE abonne SET nom = ?, prenom = ?, email = ?, telephone = ? WHERE id = ?";

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, abonne.getNom());
            ps.setString(2, abonne.getPrenom());
            ps.setString(3, abonne.getEmail());
            ps.setString(4, abonne.getTelephone());
            ps.setInt(5, abonne.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerAbonne(int id) {
        String sql = "DELETE FROM abonne WHERE id = ?";

        try (Connection conn = BDConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
