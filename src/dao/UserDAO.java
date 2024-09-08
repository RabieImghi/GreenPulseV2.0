package dao;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO {
    private final Connection connection;

    public UserDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }
    public void addUser(User user){
        String smt = "INSERT INTO \"user\" (id,name, email,age) VALUES (?,?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(smt);
            preparedStatement.setInt(1,2);
            preparedStatement.setString(2,"jhhh");
            preparedStatement.setString(3,"
            preparedStatement.setInt(4,23);
            int rowsInserted = preparedStatement.executeUpdate();
            if(rowsInserted > 0)   System.out.println("user added avec succes");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
