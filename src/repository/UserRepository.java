package repository;

import config.DatabaseConnection;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {
    private final Connection connection;

    public UserRepository() {
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
            if(rowsInserted > 0)   System.out.println("user added with success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUser(User user,String cin){
        String smt = "UPDATE users SET cin = ?, name = ?, age = ? WHERE cin = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(smt);
            preparedStatement.setString(1,user.getCin());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setInt(3,user.getAge());
            preparedStatement.setString(4,cin);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) System.out.println("user updated with success");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteUser(String cin){
        String stmDelete = "DELETE FROM users WHERE cin = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(stmDelete);
            preparedStatement.setString(1,cin);
            preparedStatement.executeUpdate();
            System.out.println("User deleted with success");
        }catch (Exception e){
            System.out.println("Error In Delete User : "+ e.getMessage());
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
