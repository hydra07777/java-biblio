 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnection {

    private static final String url = 
        "jdbc:mysql://localhost:3306/bibliothequejava?useSSL=false&serverTimezone=UTC";

    private static final String user = "root";
    private static final String mdp = "";

    private static Connection conn = null;

    public static Connection getConnection() {
        try {

            // Charger explicitement le driver (important)
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, mdp);

            System.out.println("Connexion réussie !");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL non trouvé !");
        } catch (SQLException e) {
            System.out.println("Erreur connexion : " + e.getMessage());
        }

        return conn;
    }
}