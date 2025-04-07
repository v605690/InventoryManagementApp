package inventory_app;

import java.sql.*;

public class JDBCConnector {
    Connection connection = null;
//    Statement statement = null;
//    ResultSet resultSet = null;

    public JDBCConnector(String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionString = "jdbc:mysql://localhost/inventory_db?"
                    + "user=" + user + "&password=" + password + "&useSSL=false";

            connection = DriverManager.getConnection(connectionString);
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM inventory_db.product");
//
//            while (resultSet.next()) {
//                int productId = resultSet.getInt("product_id");
//                String description = resultSet.getString("description");
//                String unit = resultSet.getString("unit");
//                int unitQuantity = resultSet.getInt("unit_quantity");
//                double price = resultSet.getDouble("price");
//                String category = resultSet.getString("category");
//                java.sql.Date date = resultSet.getDate("date");
//                int lastQuantity = resultSet.getInt("last_quantity");
//                System.out.println("Product ID: " + productId + " is " + description + " price " + price);
//            }
        } catch (SQLException exc) {
            System.out.println("SQL Exception occurred"
                    + " - unable to establish connection");
            exc.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.out.println("Exception occurred"
                    + " - driver not found");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
