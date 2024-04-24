package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {

    // Object is defined as static
    private static Db instance = null;
    private Connection connection = null;
    private final String DB_URL = "jdbc:postgresql://localhost:5432/tourismagency";
    private final String DB_USERNAME = "postgres";
    private final String DB_PASS = "1234";

    // Constructor is set to private to prevent external access
    private Db() {
        try {
            // Establishing a connection to the database
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASS);
        } catch (SQLException e) {
            // Printing error message if connection fails
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    // Accessing the method without creating an object, hence using static
    // Singleton design pattern: Ensures only one instance of the class is created
    public static Connection getInstance() {

        try {
            // Creating a new instance if it's null or closed
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Db();
            }
        } catch (SQLException e) {
            // Handling SQL exceptions
            throw new RuntimeException(e);
        }
        // Returning the connection
        return instance.getConnection();
    }
}
