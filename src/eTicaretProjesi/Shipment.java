package eTicaretProjesi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Shipment {

    private String generateFancyTrackingId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder("TR-");
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public String createShipment(int orderId) {
        String trackingId = generateFancyTrackingId();
        String sql = "INSERT INTO Shipment(orderId, shipmentDate, deliveryDate, trackingNumber, shipmentStatus) VALUES(?, datetime('now'), datetime('now', '+3 days'), ?, 'Processing')";
        
        try (Connection conn = databaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            pstmt.setString(2, trackingId);
            pstmt.executeUpdate();
            return trackingId; 
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void trackShipment(String trackingNo) {
        String sql = "SELECT shipmentStatus, shipmentDate, deliveryDate FROM Shipment WHERE trackingNumber = ?";

        try (Connection conn = databaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, trackingNo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- Shipment Status ---");
                System.out.println("📍 Status: " + rs.getString("shipmentStatus"));
                System.out.println("📅 Shipped On: " + rs.getString("shipmentDate"));
                System.out.println("🚚 Est. Delivery: " + rs.getString("deliveryDate"));
                System.out.println("------------------------");
            } else {
                System.out.println("❌ Error: Tracking ID not found!");
            }
        } catch (SQLException e) {
            System.out.println("Query Error: " + e.getMessage());
        }
    }
}