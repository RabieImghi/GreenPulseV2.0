package repository;

import dao.UserDAO;
import model.User;

import java.sql.SQLException;

public class UserRepository {
    private final UserDAO userDAO = new UserDAO();
    public UserRepository() throws SQLException {}

    public void addUser(User user){
        userDAO.addUser(user);
    }
    public boolean userExist(String cin){
        return userDAO.userExist(cin);
    }


}
