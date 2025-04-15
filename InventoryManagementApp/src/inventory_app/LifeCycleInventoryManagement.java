package inventory_app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LifeCycleInventoryManagement {
    //    private final JDBCConnector jdbcConnector;
    private final Connection connection;

    //    public LifeCycleInventoryManagement(JDBCConnector jdbcConnector) {
    //        this.jdbcConnector = jdbcConnector;
    //    }

    public LifeCycleInventoryManagement(Connection connection) {
        this.connection = connection;
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO inventory_db.product (product, description, unit, unit_qty, current_price, last_date, last_qty) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, product.getProduct());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getUnit());
            preparedStatement.setString(4, product.getUnitQty());
            preparedStatement.setDouble(5, product.getPrice());
            preparedStatement.setString(6, product.getDate());
            preparedStatement.setInt(7, product.getLastQty());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // NIKITA: block it off since I don't have this logic
    //    public void getMetaData() {
    //        String sql = "SELECT * FROM inventory_db.product";
    //        try (Connection connection = jdbcConnector.getconnection();
    //        PreparedStatement preparedStatement = connection.prepareStatement(sql);
    //        ResultSet resultSet = preparedStatement.executeQuery()) {
    //            while (resultSet.next()) {
    //                var metaData = resultSet.getMetaData();
    //                for (int i = 1; i<=metaData.getColumnCount(); i++) {
    //                    System.out.print(resultSet.getObject(i) + "  ");
    //                }
    //                System.out.println();
    //            }
    //
    //        } catch (SQLException e) {
    //            e.printStackTrace();
    //        }
    //    }

    public List<Product> getAllInventories() {
        List<Product> inventories = new ArrayList<>();
        String sql = "SELECT * FROM inventory_db.product";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setProduct(resultSet.getInt("product"));
                product.setDescription(resultSet.getString("description"));
                product.setUnit(resultSet.getString("unit"));
                product.setUnitQty(resultSet.getString("unit_qty"));
                product.setPrice(resultSet.getDouble("current_price"));
                product.setDate(resultSet.getString("last_date"));
                product.setLastQty(resultSet.getInt("last_qty"));
                inventories.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventories;
    }

    //    var metaData = resultSet.getMetaData();
    //                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
    //        System.out.println(resultSet.getObject(i) + " ");
    //    }
    //                    System.out.println();

    public void updateProduct(Product product) {
        String sql = "UPDATE inventory_db.product SET description = ?, unit = ?, unit_qty = ?, current_price = ?, last_date = ?, last_qty = ? WHERE product_id = ?";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, product.getDescription());
            preparedStatement.setString(2, product.getUnit());
            preparedStatement.setString(3, product.getUnitQty());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setString(6, product.getDate());
            preparedStatement.setInt(7, product.getLastQty());
            preparedStatement.setInt(8, product.getProduct());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Inventory updated successfully");
                ;
            } else {
                System.out.println("No inventory found with the given product ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(int productId) {
        String sql = "DELETE FROM inventory_db.product WHERE product_id = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
