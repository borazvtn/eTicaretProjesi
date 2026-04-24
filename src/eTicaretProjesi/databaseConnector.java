package eTicaretProjesi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnector {

	private static final String URL = "jdbc:sqlite:/Users/mustaa/eclipse-workspace/eTicaretProjesi/eTicaret.db";
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantı hatası: " + e.getMessage());
        }
        return conn;
    }
}