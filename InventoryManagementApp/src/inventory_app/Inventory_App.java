package inventory_app;

import java.util.List;
import java.util.Scanner;

public class Inventory_App {
    public static void main(String[] args) {
        JDBCConnector jdbcConnector = new JDBCConnector();
        LifeCycleInventoryManagement lifeCycleInventoryManagement = new LifeCycleInventoryManagement(jdbcConnector);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Welcome to Inventory Management System");
                System.out.println("Please enter your choice");
                System.out.println("---------------------------------------");
                System.out.println("1. Add Product");
                System.out.println("2. Update Product");
                System.out.println("3. Delete Product");
                System.out.println("4. View All Products");
                System.out.println("5. Exit");

                int choice = safeInputInt(scanner, "Enter your choice: ");

                switch (choice) {
                    case 1:
                        int productId = safeInputInt(scanner, "Enter product ID: ");
                        String description = safeInputString(scanner, "Enter product description: ");
                        scanner.nextLine();
                        String unit = safeInputString(scanner, "Enter product unit: ");
                        int unitQuantity = safeInputInt(scanner, "Enter product unit quantity: ");
                        double price = safeInputDouble(scanner, "Enter product price: ");
                        String category = safeInputString(scanner, "Enter product category: ");
                        java.sql.Date date = safeInputDate(scanner, "Enter product date: ");
                        int lastQuantity = safeInputInt(scanner, "Enter product last quantity: ");
                        Product product = new Product(productId, description, unit, unitQuantity, price, category, date, lastQuantity);
                        lifeCycleInventoryManagement.addProduct(product);
                        System.out.println("Product added successfully");
                        break;
                    case 2:
                        int productIdToUpdate = safeInputInt(scanner, "Enter product ID: ");
                        String descriptionToUpdate = safeInputString(scanner, "Enter product description: ");
                        String unitToUpdate = safeInputString(scanner, "Enter product unit: ");
                        int unitQuantityToUpdate = safeInputInt(scanner, "Enter product unit quantity: ");
                        double priceToUpdate = safeInputDouble(scanner, "Enter product price: ");
                        String categoryToUpdate = safeInputString(scanner, "Enter product category: ");
                        java.sql.Date dateToUpdate = java.sql.Date.valueOf(scanner.next());
                        int lastQuantityToUpdate = safeInputInt(scanner, "Enter product last quantity: ");
                        Product productToUpdate = new Product(productIdToUpdate, descriptionToUpdate, unitToUpdate, unitQuantityToUpdate, priceToUpdate, categoryToUpdate, dateToUpdate, lastQuantityToUpdate);
                        lifeCycleInventoryManagement.updateProduct(productToUpdate);
                        System.out.println("Product updated successfully");
                        break;
                    case 3:
                        int productIdToDelete = safeInputInt(scanner, "Enter product ID: ");
                        lifeCycleInventoryManagement.deleteProduct(productIdToDelete);
                        System.out.println("Product deleted successfully");
                        break;
                    case 4:
                        List<Product> inventories = new LifeCycleInventoryManagement(jdbcConnector).getAllInventories();
                        if (inventories.isEmpty()) {
                            System.out.println("No inventories found");
                        } else {
                            System.out.println("Product ID\tDescription\tUnit\tUnit Quantity\tPrice\tCategory\tDate\tLast Quantity");
                            for (Product inventory : inventories) {
                                System.out.println(inventory.toString());
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Exiting Inventory Management System");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static int safeInputInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static double safeInputDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Invalid input.  Please enter a valid decmial number.");
                scanner.nextLine();
            }
        }
    }

    private static String safeInputString(Scanner scanner, String prompt) {
        System.out.println(prompt);
        return scanner.next();
    }

    private static java.sql.Date safeInputDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return java.sql.Date.valueOf(scanner.next());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format.  Please enter the date in yyyy-mm-dd format.");
            }
        }
    }
}