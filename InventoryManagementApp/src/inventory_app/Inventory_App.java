package inventory_app;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Inventory_App {
    public static void main(String[] args) {
        // NIKITA: The way environment variables could be used to configure application
        String dbUrl = System.getenv("DB_URL") == null ? JDBCConnector.DB_URL : System.getenv("DB_URL");
        String user = System.getenv("USER") == null ? JDBCConnector.USER : System.getenv("USER");
        String pass = System.getenv("PASS") == null ? JDBCConnector.PASS : System.getenv("PASS");

        // NIKITA: An example of how arguments could be used to configure application
        // java Inventory_App -d inventory_db -u root -p root-pass
        //        if (args.length != 0) {
        //            int shift = 0;
        //            while (shift < args.length) {
        //                switch (args[shift]) {
        //                    case "-d": {
        //                        dbUrl = args[shift + 1];
        //                        break;
        //                    }
        //                }
        //                shift++;
        //            }
        //        }

        // NIKITA: moved try with connection resource, so it is opened only once and closed only when quit application
        try (Connection connection = JDBCConnector.initializeConnection(dbUrl, user, pass)) {

            // Create new jbdcConnector  and lifeCycleInventoryManagement objects
            LifeCycleInventoryManagement lifeCycleInventoryManagement = new LifeCycleInventoryManagement(connection);
            // Set up a scanner class to parse and retrieving input from various sources, such as the console, files and strings
            Scanner scanner = new Scanner(System.in);

            while (true) {
                try {
                    System.out.println("---------------------------------------");
                    System.out.println("Welcome To HK Inventory Management System");
                    System.out.println("Please enter your choice");
                    System.out.println("---------------------------------------");
                    System.out.println("1. Add Product");
                    System.out.println("2. Update Product");
                    System.out.println("3. Delete Product");
                    System.out.println("4. View All Products");
                    System.out.println("5. Import from CSV");
                    System.out.println("6. Exit");

                    int choice = safeInputInt(scanner, "Enter your choice: ");
                    // Set up a switch statement to rotate though the cases
                    switch (choice) {
                        case 1:
                            int productId = safeInputInt(scanner, "Enter product ID: ");
                            String description = safeInputString(scanner, "Enter product description: ");
                            scanner.nextLine();
                            String unit = safeInputString(scanner, "Enter product unit: ");
                            String unitQuantity = safeInputString(scanner, "Enter product unit quantity: ");
                            double price = safeInputDouble(scanner, "Enter product price: ");
                            String date = safeInputString(scanner, "Enter product date: ");
                            int lastQuantity = safeInputInt(scanner, "Enter product last quantity: ");
                            Product product = new Product(productId, description, unit, unitQuantity, price, date, lastQuantity);
                            lifeCycleInventoryManagement.addProduct(product);
                            System.out.println("Product added successfully");
                            break;
                        case 2:
                            int productIdToUpdate = safeInputInt(scanner, "Enter product ID: ");
                            String descriptionToUpdate = safeInputString(scanner, "Enter product description: ");
                            String unitToUpdate = safeInputString(scanner, "Enter product unit: ");
                            String unitQuantityToUpdate = safeInputString(scanner, "Enter product unit quantity: ");
                            double priceToUpdate = safeInputDouble(scanner, "Enter product price: ");
                            String dateToUpdate = safeInputString(scanner, "Enter last date: ");
                            int lastQuantityToUpdate = safeInputInt(scanner, "Enter product last quantity: ");
                            Product productToUpdate = new Product(productIdToUpdate, descriptionToUpdate, unitToUpdate, unitQuantityToUpdate, priceToUpdate, dateToUpdate, lastQuantityToUpdate);
                            lifeCycleInventoryManagement.updateProduct(productToUpdate);
                            System.out.println("Product updated successfully");
                            break;
                        case 3:
                            int productIdToDelete = safeInputInt(scanner, "Enter product ID: ");
                            lifeCycleInventoryManagement.deleteProduct(productIdToDelete);
                            System.out.println("Product deleted successfully");
                            break;
                        case 4:
                            List<Product> inventories = lifeCycleInventoryManagement.getAllInventories();
                            if (inventories.isEmpty()) {
                                System.out.println("No inventories found");
                            } else {
                                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
                                System.out.println("PRODUCT ID              \tDESCRIPTION                         \tUNIT      \tUNIT QUANTITY     \tPRICE      \tDATE    \tLAST QUANTITY");
                                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
                                for (Product inventory : inventories) {
                                    System.out.println(inventory.toString());
                                }
                            }
                            break;
                        case 5:
                            // NIKITA: An example of incorporating load from CSV
                            String filePath = safeInputString(scanner, "Enter file path: ");

                            CSVController csvController = new CSVController();
                            List<Product> products = csvController.readCSV(filePath);
                            for (Product p : products) {
                                lifeCycleInventoryManagement.addProduct(p);
                            }
                            System.out.println("Imported product successfully");

                            break;
                        case 6:
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Set up four scanner methods
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
                System.out.println("Invalid input.  Please enter a valid decimal number.");
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