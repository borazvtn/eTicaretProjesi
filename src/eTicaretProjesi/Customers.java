package eTicaretProjesi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customers {

    public boolean registerCustomer(String firstName, String lastName, String email, String phone, String password) {
        String sql = "INSERT INTO Customers(FirstName, LastName, Email, phoneNumber, Password) VALUES(?,?,?,?,?)";
        
        try (Connection conn = databaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setString(5, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean loginCustomer(String email, String password) {
        String sql = "SELECT CustomerID FROM Customers WHERE Email = ? AND Password = ?";
        
        try (Connection conn = databaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}