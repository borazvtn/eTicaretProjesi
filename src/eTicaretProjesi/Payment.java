package eTicaretProjesi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Payment {

    public String processPayment(int orderId, String paymentMethod) {
        String getAmountSQL = "SELECT totalAmount FROM Orders WHERE orderId = ?";
        String insertPaymentSQL = "INSERT INTO Payment(orderId, paymentDate, paymentMethod, paymentStatus, amount) VALUES(?, datetime('now'), ?, 'Completed', ?)";
        String updateOrderSQL = "UPDATE Orders SET orderStatus = 'Paid' WHERE orderId = ?";
        
        Connection conn = null;
        try {
            conn = databaseConnector.connect();
            conn.setAutoCommit(false); 
            
            double amount = 0;
            try (PreparedStatement pstmtGet = conn.prepareStatement(getAmountSQL)) {
                pstmtGet.setInt(1, orderId);
                try (ResultSet rs = pstmtGet.executeQuery()) {
                    if (rs.next()) {
                        amount = rs.getDouble("totalAmount");
                    } else {
                        return null;
                    }
                }
            }
            
            try (PreparedStatement pstmtPayment = conn.prepareStatement(insertPaymentSQL)) {
                pstmtPayment.setInt(1, orderId);
                pstmtPayment.setString(2, paymentMethod);
                pstmtPayment.setDouble(3, amount);
                pstmtPayment.executeUpdate();
            }
            
            try (PreparedStatement pstmtOrder = conn.prepareStatement(updateOrderSQL)) {
                pstmtOrder.setInt(1, orderId);
                pstmtOrder.executeUpdate();
            }
            
            conn.commit();
            
            Shipment shipment = new Shipment();
            return shipment.createShipment(orderId);
            
        } catch (SQLException e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) {}
            return null;
        } finally {
            try { if (conn != null) { conn.setAutoCommit(true); conn.close(); } } catch (SQLException e) {}
        }
    }
}