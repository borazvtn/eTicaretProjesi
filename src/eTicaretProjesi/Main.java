package eTicaretProjesi;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        CustomerMenu customerMenu = new CustomerMenu(scanner);
        SellerMenu sellerMenu = new SellerMenu(scanner);
        AdminMenu adminMenu = new AdminMenu(scanner);

        while (!exit) {
            System.out.println("\n=======================================");
            System.out.println("      WELCOME TO E-COMMERCE SYSTEM     ");
            System.out.println("=======================================");
            System.out.println("1. Customer Operations");
            System.out.println("2. Seller Operations");
            System.out.println("3. Admin Operations");
            System.out.println("0. Exit");
            System.out.print("Please select an operation: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    customerMenu.display();
                    break;
                case "2":
                    sellerMenu.display();
                    break;
                case "3":
                    adminMenu.display();
                    break;
                case "0":
                    exit = true;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}