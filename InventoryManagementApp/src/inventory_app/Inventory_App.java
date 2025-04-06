package inventory_app;

import java.util.List;
import java.util.Scanner;

public class Inventory_App {
    public static void main(String[] args) {
        JDBC_Connector jdbcConnector = new JDBC_Connector();
        Scanner scanner = new Scanner(System.in);

        while (true) {
        System.out.println("Welcome to Inventory Management System");
        System.out.println("Please enter your choice");
        System.out.println();
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. View All Products");
        System.out.println("5. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter product ID");
                int productId = scanner.nextInt();
                System.out.println("Enter product description");
                String description = scanner.next();
                System.out.println("Enter product unit");
                String unit = scanner.next();
                System.out.println("Enter product unit quantity");
                int unitQuantity = scanner.nextInt();
                System.out.println("Enter product price");
                double price = scanner.nextDouble();
                System.out.println("Enter product category");
                String category = scanner.next();
                System.out.println("Enter product date");
                java.sql.Date date = java.sql.Date.valueOf(scanner.next());
                System.out.println("Enter product last quantity");
                int lastQuantity = scanner.nextInt();
                Product product = new Product(productId, description, unit, unitQuantity, price, category, date, lastQuantity);
                LifeCycleInventoryManagement lifeCycleInventoryManagement = new LifeCycleInventoryManagement(jdbcConnector);
                lifeCycleInventoryManagement.addProduct(product);
                System.out.println("Product added successfully");
                break;
            case 2:
                System.out.println("Enter product ID");
                int productIdToUpdate = scanner.nextInt();
                System.out.println("Enter product description");
                String descriptionToUpdate = scanner.next();
                System.out.println("Enter product unit");
                String unitToUpdate = scanner.next();
                System.out.println("Enter product unit quantity");
                int unitQuantityToUpdate = scanner.nextInt();
                System.out.println("Enter product price");
                double priceToUpdate = scanner.nextDouble();
                System.out.println("Enter product category");
                String categoryToUpdate = scanner.next();
                System.out.println("Enter product date");
                java.sql.Date dateToUpdate = java.sql.Date.valueOf(scanner.next());
                System.out.println("Enter product last quantity");
                int lastQuantityToUpdate = scanner.nextInt();
                Product productToUpdate = new Product(productIdToUpdate, descriptionToUpdate, unitToUpdate, unitQuantityToUpdate, priceToUpdate, categoryToUpdate, dateToUpdate, lastQuantityToUpdate);
                LifeCycleInventoryManagement lifeCycleInventoryManagementToUpdate = new LifeCycleInventoryManagement(jdbcConnector);
                lifeCycleInventoryManagementToUpdate.updateProduct(productToUpdate);
                System.out.println("Product updated successfully");
                break;
            case 3:
                System.out.println("Enter product ID");
                int productIdToDelete = scanner.nextInt();
                Product productToDelete = new Product(productIdToDelete, null, null, 0, 0, null, null, 0);
                LifeCycleInventoryManagement lifeCycleInventoryManagementToDelete = new LifeCycleInventoryManagement(jdbcConnector);
                lifeCycleInventoryManagementToDelete.deleteProduct(productToDelete);
                System.out.println("Product deleted successfully");
                break;
            case 4:
                List<Product> inventories = new LifeCycleInventoryManagement(jdbcConnector).getAllInventories();
                if (inventories.isEmpty()) {
                    System.out.println("No inventories found");
                } else {
                    System.out.println("Product ID\tDescription\tUnit\tUnit Quantity\tPrice\tCategory\tDate\tLast Quantity");
                    for (Product inventory : inventories) {
                        System.out.println(inventory);
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
        }
    }
}