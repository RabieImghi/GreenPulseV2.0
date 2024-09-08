package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private DatabaseConnection() {
        try {
            String password = "";
            String user = "GreenPulse";
            String url = "jdbc:postgresql://localhost:5432/postgres";
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
        }
    }
    public static DatabaseConnection getInstance(){
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
