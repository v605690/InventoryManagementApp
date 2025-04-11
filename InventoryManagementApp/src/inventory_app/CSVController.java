package inventory_app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVController {
    public static void main(String[] args) {

        CSVController csvController = new CSVController();
        csvController.readCSV();
    }

    // Set up a new management object from LifeCycleInventoryManagement class
    LifeCycleInventoryManagement management = new LifeCycleInventoryManagement(new JDBCConnector());
    // readCSV method created
    public void readCSV() {

        ArrayList<Product> products = new ArrayList<>();
        // Set up file path for the csv file
        String filePath = "/Users/v605690/Java-Projects/InventoryManagementApp/inventory.csv";
        // Create the BufferedReader using try-with-resources
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            // br.read() returns the next character, or
            // -1 if there are no more characters, so
            // the following while loop will loop until
            // there are no more characters in the file

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                products.add(mapper(data));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Set up a for loop to iterate over the product array calling the addProduct method
        for(Product product : products) {
           management.addProduct(product);
        }
    }
    // Create mapper method to retrieve the index from array
    private Product mapper(String[] data) {
        Product product = new Product();

        product.setProduct(Integer.parseInt(data[0]));
        product.setDescription(data[1]);
        product.setUnit(data[2]);
        product.setUnitQty(data[3]);
        product.setPrice(Double.parseDouble(data[4]));
        product.setDate(data[5]);
        product.setLastQty(Integer.parseInt(data[6]));

        return product;
    }
}
