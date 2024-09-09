package repository;

import dao.UserDAO;
import model.User;

import java.sql.SQLException;

public class UserRepository {
    private final UserDAO userDAO = new UserDAO();
    public UserRepository() {}

    public void addUser(User user){
        userDAO.addUser(user);
    }
    public void updateUser(User user, String cin){
        userDAO.updateUser(user,cin);
    }
    public boolean userExist(String cin){
        return userDAO.userExist(cin);
    }


}
