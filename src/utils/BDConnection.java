 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author CAESAR
 */
public class BDConnection {

    private static String url = "jdbc:mysql://localhost:3306/bibliothequejava";
    private static String user = "root";
    private static String mdp = "root";
    private static Connection conn = null;

    public static Connection getConnection() {
        try {

            conn = DriverManager.getConnection(url, user, mdp);

        } catch (Exception e) {
            System.out.print("Erreur dan sla connection a la base de donnee : " + e);
        }

        return conn;
    }
}
