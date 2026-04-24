package eTicaretProjesi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Reviews {

    public boolean addReview(int customerId, int productId, int rating, String comment) {
        if (rating < 1 || rating > 5) {
            System.out.println("Error: Rating must be between 1 and 5!");
            return false;
        }

        String sql = "INSERT INTO Reviews(CustomerID, ProductID, Rating, Comment, ReviewDate) VALUES(?,?,?,?, datetime('now'))";
        
        try (Connection conn = databaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, rating);
            pstmt.setString(4, comment);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}