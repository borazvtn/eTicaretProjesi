package eTicaretProjesi;

import java.util.Scanner;

public class AdminMenu {

    private Scanner scanner;

    public AdminMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void display() {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- ADMIN OPERATIONS ---");
            System.out.println("1. System Control (Placeholder)");
            System.out.println("0. Back to Main Menu");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Admin operations can be implemented here.");
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