package repository;

import config.DatabaseConnection;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {
    private final Connection connection;

    public UserRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public Optional<User> addUser(User user){
        String smt = "INSERT INTO users (cin,name,age) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(smt);
            preparedStatement.setString(1,user.getCin());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setInt(3, user.getAge());
            int rowsInserted = preparedStatement.executeUpdate();
            if(rowsInserted > 0) System.out.println("user added with success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  Optional.of(user);
    }
    public Optional<User> finUserById(String cin){
        String stm2 = "SELECT * FROM users WHERE cin = ?";
        Optional<User> user = Optional.empty();
        try {
            PreparedStatement preparedStatement2 = connection.prepareStatement(stm2);
            preparedStatement2.setString(1, cin);
            ResultSet result = preparedStatement2.executeQuery();
            while (result.next()){
                user = Optional.of(new User(result.getString("cin"),result.getString("name"),result.getInt("age")));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;


    }
    public Optional<User> updateUser(User user){
        String smt = "UPDATE users SET name = ?, age = ? WHERE cin = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(smt);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setInt(2,user.getAge());
            preparedStatement.setString(3,user.getCin());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) System.out.println("user updated with success");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  Optional.of(user);
    }

    public Optional<User> deleteUser(String cin){
        Optional<User> user = finUserById(cin);
        String stmDelete = "DELETE FROM users WHERE cin = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(stmDelete);
            preparedStatement.setString(1,cin);
            preparedStatement.executeUpdate();
            System.out.println("User deleted with success");
        }catch (Exception e){
            System.out.println("Error In Delete User : "+ e.getMessage());
        }
        return user;
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
