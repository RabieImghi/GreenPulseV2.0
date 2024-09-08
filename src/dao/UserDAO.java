package dao;

import config.DatabaseConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private final Connection connection;

    public UserDAO(){
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    public void addUser(User user){
        String smt = "INSERT INTO users (id,cin,name,age) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(smt);
            preparedStatement.setInt(1,3);
            preparedStatement.setString(2,user.getCin());
            preparedStatement.setString(3,user.getName());
            preparedStatement.setInt(4, user.getAge());
            int rowsInserted = preparedStatement.executeUpdate();
            if(rowsInserted > 0)   System.out.println("user added avec succes");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean userExist(String cin) {
        String stm2 = "SELECT * FROM users WHERE cin = ?";
        try {
            PreparedStatement preparedStatement2 = connection.prepareStatement(stm2);
            preparedStatement2.setString(1, cin);
            return preparedStatement2.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
