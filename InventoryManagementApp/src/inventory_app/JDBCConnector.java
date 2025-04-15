package inventory_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// NIKITA: changes to enable instantiation with parameters and initialize only once
public class JDBCConnector {
    // Create private instance variables
    //    private Connection connection = null;

    public static final String DB_URL = "jdbc:mysql://localhost:3306/inventory_db";
    public static final String USER = "root";
    public static final String PASS = "root-pass";

    // NIKITA: replace with a constructor with parameters
    // Setup Constructor with the initializeConnection method
    //    public JDBCConnector() {
    //        initializeConnection();
    //    }

    public static Connection initializeConnection(String dbUrl, String dbUser, String dbPass) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found  - driver not found");
            e.printStackTrace();

            throw new RuntimeException("JDBC Driver not found - driver not found");
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred  - unable to establish connection");
            e.printStackTrace();

            throw new RuntimeException("SQL Exception occurred - unable to establish connection");
        }
    }

    // Set up initializeConnection method
    //    private void initializeConnection() {
    //
    //        try {
    //            // Loads the MySQL driver
    //            Class.forName("com.mysql.cj.jdbc.Driver");
    //            // Setup the connection with the DB
    //            connection = DriverManager.getConnection(DB_URL, USER, PASS);
    //            System.out.println("Database connection established");
    //
    //        } catch (ClassNotFoundException e) {
    //            System.out.println("JDBC Driver not found"
    //                    + " - driver not found");
    //            e.printStackTrace();
    //
    //        } catch (SQLException e) {
    //            System.out.println("SQL Exception occurred"
    //                    + " - unable to establish connection");
    //            e.printStackTrace();
    //
    //        }
    //    }

    // Set up getConnection method
    //    public Connection getconnection() throws SQLException {
    //
    //        if (this.connection == null || connection.isClosed()) {
    //            System.err.println("Database connection is unavailable");
    //            initializeConnection();
    //        }
    //
    //        if (connection == null || connection.isClosed()) {
    //            throw new SQLException("Database connection could not be established");
    //        }
    //        return this.connection;
    //    }

    // Set up the closeConnection
    //    public void closeConnection() {
    //
    //        try {
    //            if (this.connection != null && !this.connection.isClosed()) {
    //                this.connection.close();
    //                System.out.println("Database connection closed");
    //            }
    //        } catch (SQLException e) {
    //            System.err.println("Error closing database connection");
    //            e.printStackTrace();
    //        }
    //    }
}
