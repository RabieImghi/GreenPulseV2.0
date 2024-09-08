package repository;

import dao.UserDAO;

import java.sql.SQLException;

public class UserRepository {
    private final UserDAO userDAO = new UserDAO();
    public UserRepository() throws SQLException {}


}
