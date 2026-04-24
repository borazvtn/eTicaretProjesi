package eTicaretProjesi;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSeeder {

    public static void seed() {
        String[] queries = {

            "DELETE FROM Reviews", "DELETE FROM Shipment", "DELETE FROM Payment", 
            "DELETE FROM OrderItem", "DELETE FROM Orders", "DELETE FROM Address", 
            "DELETE FROM Products", "DELETE FROM Customers", "DELETE FROM Sellers", "DELETE FROM Categories",


            "DELETE FROM sqlite_sequence", 


            "INSERT INTO Categories (CategoryName, Description) VALUES ('Electronics', 'Computers and Phones')",
            "INSERT INTO Categories (CategoryName, Description) VALUES ('Clothing', 'Men and Women Apparel')",
            "INSERT INTO Categories (CategoryName, Description) VALUES ('Home', 'Furniture and Decor')",
            "INSERT INTO Categories (CategoryName, Description) VALUES ('Books', 'Novels and Academic')",
            "INSERT INTO Categories (CategoryName, Description) VALUES ('Sports', 'Equipment and Gear')",

            "INSERT INTO Sellers (storeName, email, phoneNumber) VALUES ('TechStore', 'tech@store.com', '111222')",
            "INSERT INTO Sellers (storeName, email, phoneNumber) VALUES ('FashionHub', 'fashion@hub.com', '333444')",
            "INSERT INTO Sellers (storeName, email, phoneNumber) VALUES ('HomeStyle', 'home@style.com', '555666')",
            "INSERT INTO Sellers (storeName, email, phoneNumber) VALUES ('BookWorm', 'book@worm.com', '777888')",
            "INSERT INTO Sellers (storeName, email, phoneNumber) VALUES ('FitLife', 'fit@life.com', '999000')",

            "INSERT INTO Customers (FirstName, LastName, Email, phoneNumber, Password) VALUES ('Ali', 'Yilmaz', 'ali@mail.com', '555111', 'pass1')",
            "INSERT INTO Customers (FirstName, LastName, Email, phoneNumber, Password) VALUES ('Ayse', 'Kaya', 'ayse@mail.com', '555222', 'pass2')",
            "INSERT INTO Customers (FirstName, LastName, Email, phoneNumber, Password) VALUES ('Mehmet', 'Demir', 'mehmet@mail.com', '555333', 'pass3')",
            "INSERT INTO Customers (FirstName, LastName, Email, phoneNumber, Password) VALUES ('Fatma', 'Celik', 'fatma@mail.com', '555444', 'pass4')",
            "INSERT INTO Customers (FirstName, LastName, Email, phoneNumber, Password) VALUES ('Can', 'Ozturk', 'can@mail.com', '555555', 'pass5')",

            "INSERT INTO Products (SellerID, CategoryID, ProductName, Description, Price, StockQuantity) VALUES (1, 1, 'Gaming Laptop', '16GB RAM', 1500.0, 10)",
            "INSERT INTO Products (SellerID, CategoryID, ProductName, Description, Price, StockQuantity) VALUES (2, 2, 'Cotton T-Shirt', 'Black L', 25.0, 50)",
            "INSERT INTO Products (SellerID, CategoryID, ProductName, Description, Price, StockQuantity) VALUES (3, 3, 'Sofa', '3 Seater', 300.0, 5)",
            "INSERT INTO Products (SellerID, CategoryID, ProductName, Description, Price, StockQuantity) VALUES (4, 4, 'Science Fiction Novel', 'Hardcover', 15.0, 100)",
            "INSERT INTO Products (SellerID, CategoryID, ProductName, Description, Price, StockQuantity) VALUES (5, 5, 'Dumbbell Set', '20kg', 50.0, 20)",

            "INSERT INTO Address (CustomerID, City, District, FullAddress, PostalCode) VALUES (1, 'Izmir', 'Balcova', 'Street 1, Apt 2', '35330')",
            "INSERT INTO Address (CustomerID, City, District, FullAddress, PostalCode) VALUES (2, 'Istanbul', 'Kadikoy', 'Street 5, Apt 10', '34710')",
            "INSERT INTO Address (CustomerID, City, District, FullAddress, PostalCode) VALUES (3, 'Ankara', 'Cankaya', 'Avenue 3', '06690')",
            "INSERT INTO Address (CustomerID, City, District, FullAddress, PostalCode) VALUES (4, 'Bursa', 'Nilufer', 'Boulevard 9', '16120')",
            "INSERT INTO Address (CustomerID, City, District, FullAddress, PostalCode) VALUES (5, 'Antalya', 'Muratpasa', 'Street 12', '07100')",

            "INSERT INTO Orders (CustomerID, AddressID, OrderDate, TotalAmount, OrderStatus) VALUES (1, 1, datetime('now'), 1500.0, 'Paid')",
            "INSERT INTO Orders (CustomerID, AddressID, OrderDate, TotalAmount, OrderStatus) VALUES (2, 2, datetime('now'), 50.0, 'Pending')",
            "INSERT INTO Orders (CustomerID, AddressID, OrderDate, TotalAmount, OrderStatus) VALUES (3, 3, datetime('now'), 300.0, 'Shipped')",
            "INSERT INTO Orders (CustomerID, AddressID, OrderDate, TotalAmount, OrderStatus) VALUES (4, 4, datetime('now'), 15.0, 'Delivered')",
            "INSERT INTO Orders (CustomerID, AddressID, OrderDate, TotalAmount, OrderStatus) VALUES (5, 5, datetime('now'), 50.0, 'Paid')",

            "INSERT INTO OrderItem (OrderID, ProductID, Quantity, UnitPrice) VALUES (1, 1, 1, 1500.0)",
            "INSERT INTO OrderItem (OrderID, ProductID, Quantity, UnitPrice) VALUES (2, 2, 2, 25.0)",
            "INSERT INTO OrderItem (OrderID, ProductID, Quantity, UnitPrice) VALUES (3, 3, 1, 300.0)",
            "INSERT INTO OrderItem (OrderID, ProductID, Quantity, UnitPrice) VALUES (4, 4, 1, 15.0)",
            "INSERT INTO OrderItem (OrderID, ProductID, Quantity, UnitPrice) VALUES (5, 5, 1, 50.0)",

            "INSERT INTO Payment (OrderID, PaymentDate, PaymentMethod, PaymentStatus, Amount) VALUES (1, datetime('now'), 'Credit Card', 'Completed', 1500.0)",
            "INSERT INTO Payment (OrderID, PaymentDate, PaymentMethod, PaymentStatus, Amount) VALUES (2, datetime('now'), 'Bank Transfer', 'Pending', 50.0)",
            "INSERT INTO Payment (OrderID, PaymentDate, PaymentMethod, PaymentStatus, Amount) VALUES (3, datetime('now'), 'Credit Card', 'Completed', 300.0)",
            "INSERT INTO Payment (OrderID, PaymentDate, PaymentMethod, PaymentStatus, Amount) VALUES (4, datetime('now'), 'Debit Card', 'Completed', 15.0)",
            "INSERT INTO Payment (OrderID, PaymentDate, PaymentMethod, PaymentStatus, Amount) VALUES (5, datetime('now'), 'Credit Card', 'Completed', 50.0)",

            "INSERT INTO Shipment (OrderID, ShipmentDate, deliveryDate, TrackingNumber, ShipmentStatus) VALUES (1, datetime('now'), datetime('now', '+3 days'), 'TR-A1B2C3', 'Processing')",
            "INSERT INTO Shipment (OrderID, ShipmentDate, deliveryDate, TrackingNumber, ShipmentStatus) VALUES (2, datetime('now'), datetime('now', '+3 days'), 'TR-D4E5F6', 'Pending')",
            "INSERT INTO Shipment (OrderID, ShipmentDate, deliveryDate, TrackingNumber, ShipmentStatus) VALUES (3, datetime('now'), datetime('now', '+1 day'), 'TR-G7H8I9', 'Shipped')",
            "INSERT INTO Shipment (OrderID, ShipmentDate, deliveryDate, TrackingNumber, ShipmentStatus) VALUES (4, datetime('now'), datetime('now'), 'TR-J0K1L2', 'Delivered')",
            "INSERT INTO Shipment (OrderID, ShipmentDate, deliveryDate, TrackingNumber, ShipmentStatus) VALUES (5, datetime('now'), datetime('now', '+3 days'), 'TR-M3N4O5', 'Processing')",

            "INSERT INTO Reviews (CustomerID, ProductID, Rating, Comment, ReviewDate) VALUES (1, 1, 5, 'Perfect performance!', datetime('now'))",
            "INSERT INTO Reviews (CustomerID, ProductID, Rating, Comment, ReviewDate) VALUES (2, 2, 4, 'Good quality cotton.', datetime('now'))",
            "INSERT INTO Reviews (CustomerID, ProductID, Rating, Comment, ReviewDate) VALUES (3, 3, 5, 'Very comfortable.', datetime('now'))",
            "INSERT INTO Reviews (CustomerID, ProductID, Rating, Comment, ReviewDate) VALUES (4, 4, 3, 'Decent story.', datetime('now'))",
            "INSERT INTO Reviews (CustomerID, ProductID, Rating, Comment, ReviewDate) VALUES (5, 5, 5, 'Solid weights.', datetime('now'))"
        };

        try (Connection conn = databaseConnector.connect();
             Statement stmt = conn.createStatement()) {

            for (String query : queries) {
                stmt.addBatch(query);
            }
            stmt.executeBatch();
            System.out.println("Database reset, ID counters cleared and seeded successfully!");

        } catch (SQLException e) {
            System.out.println("Seeding error: " + e.getMessage());
        }
    }
}