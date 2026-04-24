package eTicaretProjesi;

import java.util.Scanner;

public class CustomerMenu {
    
    private Scanner scanner;
    private int loggedInCustomerId = -1;
    private Customers customers = new Customers();
    private Products products = new Products();
    private Orders orders = new Orders();
    private Payment payments = new Payment();
    private Shipment shipments = new Shipment();
    private Reviews reviews = new Reviews();

    public CustomerMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void display() {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- CUSTOMER OPERATIONS ---");
            System.out.println("Status: " + (loggedInCustomerId != -1 ? "Logged In (ID: " + loggedInCustomerId + ")" : "Guest"));
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. View and Filter Products");
            System.out.println("4. Place Order");
            System.out.println("5. Make Payment");
            System.out.println("6. Track Shipment");
            System.out.println("7. Review Product");
            System.out.println("0. Back to Main Menu");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("First Name: ");
                    String fName = scanner.nextLine();
                    System.out.print("Last Name: ");
                    String lName = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Password: ");
                    String pass = scanner.nextLine();
                    
                    if (customers.registerCustomer(fName, lName, email, phone, pass)) {
                        System.out.println("Registration successful!");
                    }
                    break;

                case "2":
                    System.out.print("Email: ");
                    String loginEmail = scanner.nextLine();
                    System.out.print("Password: ");
                    String loginPass = scanner.nextLine();
                    
                    if (customers.loginCustomer(loginEmail, loginPass)) {
                        System.out.println("Login successful!");
                        System.out.print("Please enter your Customer ID to verify session: ");
                        try {
                            loggedInCustomerId = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid ID format.");
                        }
                    } else {
                        System.out.println("Login failed. Check credentials.");
                    }
                    break;

                case "3":
                    System.out.print("Enter Category ID to view products: ");
                    try {
                        int categoryId = Integer.parseInt(scanner.nextLine());
                        products.getProductsByCategory(categoryId);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID.");
                    }
                    break;

                case "4":
                    if (loggedInCustomerId == -1) {
                        System.out.println("Please login first!");
                        break;
                    }
                    try {
                        System.out.print("Enter Address ID: ");
                        int addressId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Product ID: ");
                        int productId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Quantity: ");
                        int quantity = Integer.parseInt(scanner.nextLine());

                        
                        if (orders.createOrder(loggedInCustomerId, addressId, productId, quantity)) {
                            System.out.println("Order created successfully!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter numeric values for IDs and Quantity.");
                    }
                    break;

                case "5":
                    try {
                        System.out.print("Enter Order ID to Pay: ");
                        int orderId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Payment Method: ");
                        String method = scanner.nextLine();

                        String trackingCode = payments.processPayment(orderId, method);

                        if (trackingCode != null) {
                            System.out.println("Payment Successful!");
                            System.out.println("Shipment Created!");
                            System.out.println("TRACKING ID: " + trackingCode);
                        } else {
                            System.out.println("Payment failed.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Order ID.");
                    }
                    break;

                case "6":
                    System.out.print("Enter your tracking number: ");
                    String trackingNo = scanner.nextLine();
                    shipments.trackShipment(trackingNo);
                    break;

                case "7":
                    if (loggedInCustomerId == -1) {
                        System.out.println("Please login first!");
                        break;
                    }
                    try {
                        System.out.print("Enter Product ID: ");
                        int revProductId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Rating (1-5): ");
                        int rating = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Comment: ");
                        String comment = scanner.nextLine();

                        if (reviews.addReview(loggedInCustomerId, revProductId, rating, comment)) {
                            System.out.println("Review added successfully!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input format.");
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