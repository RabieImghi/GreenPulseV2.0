package repository;

import config.DatabaseConnection;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private final Connection connection;

    public UserRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public Optional<User> save(User user){
        String smt = "INSERT INTO users (cin,name,age) VALUES (?,?,?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = this.connection.prepareStatement(smt);
            preparedStatement.setString(1,user.getCin());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setInt(3, user.getAge());
            int rowsInserted = preparedStatement.executeUpdate();
            if(rowsInserted > 0) return  Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  Optional.of(user);
    }
    public Optional<User> findById(String cin){
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
    public Optional<User> update(User user){
        String smt = "UPDATE users SET name = ?, age = ? WHERE cin = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = this.connection.prepareStatement(smt);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setInt(2,user.getAge());
            preparedStatement.setString(3,user.getCin());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) System.out.println("user updated with success");
        }catch (SQLException e){
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  Optional.of(user);
    }
    public Optional<User> delete(String cin){
        Optional<User> user = findById(cin);
        String stmDelete = "DELETE FROM users WHERE cin = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = this.connection.prepareStatement(stmDelete);
            preparedStatement.setString(1,cin);
            preparedStatement.executeUpdate();
            System.out.println("User deleted with success");
        }catch (Exception e){
            System.out.println("Error In Delete User : "+ e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }   finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    public List<User> findAll(){
        List<User> userList = new ArrayList<>();
        String stmt = "SELECT * FROM users";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(stmt);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                User user = new User(result.getString("cin"),result.getString("name"),result.getInt("age"));
                userList.add(user);
            }
            return userList;
        }catch (Exception e){
            System.out.println("Error In Find All Users : "+ e.getMessage());
            return userList;
        }
    }


}
