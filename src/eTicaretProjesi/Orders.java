package eTicaretProjesi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Orders {

    public boolean createOrder(int customerId, int addressId, int productId, int quantity) {
        String getPriceSQL = "SELECT Price FROM Products WHERE ProductID = ?";
        String insertOrderSQL = "INSERT INTO Orders(CustomerID, AddressID, OrderDate, TotalAmount, OrderStatus) VALUES(?,?, datetime('now'), ?, 'Pending')";
        String insertOrderItemSQL = "INSERT INTO OrderItem(OrderID, ProductID, Quantity, UnitPrice) VALUES(?,?,?,?)";
        String updateStockSQL = "UPDATE Products SET StockQuantity = StockQuantity - ? WHERE ProductID = ?";
        
        Connection conn = null;
        try {
            conn = databaseConnector.connect();
            conn.setAutoCommit(false); 

            double unitPrice = 0;
            PreparedStatement pstmtPrice = conn.prepareStatement(getPriceSQL);
            pstmtPrice.setInt(1, productId);
            ResultSet rsPrice = pstmtPrice.executeQuery();
            if (rsPrice.next()) {
                unitPrice = rsPrice.getDouble("Price");
            } else {
                System.out.println("Error: Product not found!");
                return false;
            }
            
            double totalAmount = unitPrice * quantity;

            PreparedStatement pstmtOrder = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
            pstmtOrder.setInt(1, customerId);
            pstmtOrder.setInt(2, addressId);
            pstmtOrder.setDouble(3, totalAmount);
            pstmtOrder.executeUpdate();
            
            ResultSet rsKeys = pstmtOrder.getGeneratedKeys();
            int orderId = 0;
            if (rsKeys.next()) {
                orderId = rsKeys.getInt(1);
            }
            
            PreparedStatement pstmtItem = conn.prepareStatement(insertOrderItemSQL);
            pstmtItem.setInt(1, orderId);
            pstmtItem.setInt(2, productId);
            pstmtItem.setInt(3, quantity);
            pstmtItem.setDouble(4, unitPrice);
            pstmtItem.executeUpdate();

            PreparedStatement pstmtStock = conn.prepareStatement(updateStockSQL);
            pstmtStock.setInt(1, quantity);
            pstmtStock.setInt(2, productId);
            pstmtStock.executeUpdate();
            
            conn.commit();
            System.out.println("Order created! Total: " + totalAmount);
            return true;
            
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}