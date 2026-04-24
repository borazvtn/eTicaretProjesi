package eTicaretProjesi;

import java.util.Scanner;

public class SellerMenu {

    private Scanner scanner;
    private int loggedInSellerId = -1;
    private Products products = new Products();
    private Shipment shipments = new Shipment();

    public SellerMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void display() {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- SELLER OPERATIONS ---");
            System.out.println("Status: " + (loggedInSellerId != -1 ? "Logged In (Seller ID: " + loggedInSellerId + ")" : "Guest"));
            System.out.println("1. Seller Login");
            System.out.println("2. Add New Product");
            System.out.println("3. Ship Order (Manual)");
            System.out.println("0. Back to Main Menu");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    try {
                        System.out.print("Enter your Seller ID: ");
                        loggedInSellerId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Logged in as Seller ID: " + loggedInSellerId);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID format.");
                    }
                    break;

                case "2":
                    if (loggedInSellerId == -1) {
                        System.out.println("Please login first!");
                        break;
                    }
                    try {
                        System.out.print("Enter Category ID: ");
                        int categoryId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Product Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Description: ");
                        String desc = scanner.nextLine();
                        System.out.print("Enter Price: ");
                        double price = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter Stock Quantity: ");
                        int stock = Integer.parseInt(scanner.nextLine());

                        if (products.addProduct(loggedInSellerId, categoryId, name, desc, price, stock)) {
                            System.out.println("Product added successfully!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please use numbers for Category, Price, and Stock.");
                    }
                    break;

                case "3":
                    if (loggedInSellerId == -1) {
                        System.out.println("Please login first!");
                        break;
                    }
                    try {
                        System.out.print("Enter Order ID to ship: ");
                        int orderId = Integer.parseInt(scanner.nextLine());
                        
                        String trackingId = shipments.createShipment(orderId);
                        
                        if (trackingId != null) {
                            System.out.println("Shipment process started.");
                            System.out.println("Generated Tracking ID: " + trackingId);
                        } else {
                            System.out.println("Shipment could not be created.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Order ID.");
                    }
                    break;

                case "0":
                    back = true;
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}