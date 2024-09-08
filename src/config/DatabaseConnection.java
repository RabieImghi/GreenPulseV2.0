package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;
    public DatabaseConnection() throws SQLException {
        try {
            String password = "";
            String user = "GreenPulse";
            String url = "jdbc:postgresql://localhost:5432/postgres";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new SQLException("Failed to create a database connection.");
        }
    }

    public static Connection getConnection()throws SQLException  {
        if(connection==null) {
            connection= (Connection) new DatabaseConnection();
        }
        return connection;
    }
    public void closeConnection(){
        closeConnection();
    }
}

