package inventory_app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LifeCycleInventoryManagement {
    private JDBC_Connector jdbcConnector;

    public LifeCycleInventoryManagement(JDBC_Connector jdbcConnector) {
        this.jdbcConnector = jdbcConnector;
    }
    public void addProduct(Product product) {
        String sql = "INSERT INTO inventory_db.product (description, unit, unit_quantity, price, category, date, last_quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = jdbcConnector.connection;
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getUnit());
            preparedStatement.setInt(4, product.getUnitQuantity());
            preparedStatement.setDouble(5, product.getPrice());
            preparedStatement.setString(6, product.getCategory());
            preparedStatement.setDate(7, product.getDate());
            preparedStatement.setInt(8, product.getLastQuantity());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllInventories() {
           List<Product> inventories = new ArrayList<>();
           String sql = "SELECT * FROM inventory_db.product";

           try (Connection connection = jdbcConnector.connection;
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
               ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setProductId(resultSet.getInt("product_id"));
                    product.setDescription(resultSet.getString("description"));
                    product.setUnit(resultSet.getString("unit"));
                    product.setUnitQuantity(resultSet.getInt("unit_quantity"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setCategory(resultSet.getString("category"));
                    product.setDate(resultSet.getDate("date"));
                    product.setLastQuantity(resultSet.getInt("last_quantity"));
                    inventories.add(product);
                }
               } catch (SQLException e) {
               e.printStackTrace();
           }

        return inventories;
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE inventory_db.product SET description = ?, unit = ?, unit_quantity = ?, price = ?, category = ?, date = ?, last_quantity = ? WHERE product_id = ?";

        try (Connection connection = jdbcConnector.connection;
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setDouble(5, product.setPrice());
            preparedStatement.setInt(8, product.setLastQuantity());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Inventory updated successfully");;
            } else {
                System.out.println("No inventory found with the given product ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(Product product) {
        String sql = "DELETE FROM inventory_db.product WHERE product_id = ?";
        try (Connection connection = jdbcConnector.connection;
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
