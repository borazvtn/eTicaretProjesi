package eTicaretProjesi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Products {

    public boolean addProduct(int sellerId, int categoryId, String productName, String description, double price, int stockQuantity) {
        String sql = "INSERT INTO Products(SellerID, CategoryID, ProductName, Description, Price, StockQuantity) VALUES(?,?,?,?,?,?)";
        
        try (Connection conn = databaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, sellerId);
            pstmt.setInt(2, categoryId);
            pstmt.setString(3, productName);
            pstmt.setString(4, description);
            pstmt.setDouble(5, price);
            pstmt.setInt(6, stockQuantity);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public void getProductsByCategory(int categoryId) {
        String sql = "SELECT ProductID, ProductName, Price, StockQuantity FROM Products WHERE CategoryID = ?";
        
        try (Connection conn = databaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, categoryId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ProductID") + 
                                   " | Product: " + rs.getString("ProductName") + 
                                   " | Price: " + rs.getDouble("Price") + 
                                   " | Stock: " + rs.getInt("StockQuantity"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}