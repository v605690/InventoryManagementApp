package inventory_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {

    private Connection connection = null;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/inventory_db";
    private static final String USER = "root";
    private static final String PASS = "K3yst0n3!!";

    public JDBCConnector() {
        initializeConnection();
    }

      private void initializeConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Database connection established");

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found"
                    + " - driver not found");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("SQL Exception occurred"
                    + " - unable to establish connection");
            e.printStackTrace();

        }
    }

    public Connection getconnection() throws SQLException {

        if (this.connection == null || connection.isClosed()) {
            System.err.println("Database connection is unavailable");
            initializeConnection();
        }

        if (connection == null || connection.isClosed()) {
            throw new SQLException("Database connection could not be established");
        }
        return this.connection;
        }

    public void closeConnection() {

    try {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
            System.out.println("Database connection closed");
        }
    } catch (SQLException e) {
        System.err.println("Error closing database connection");
        e.printStackTrace();
        }
    }
}
