package service;

import model.User;
import repository.UserRepository;

import java.sql.SQLException;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public UserService() throws SQLException {}

    public void addUser(){
            User user = new User("aa","aaaa",21);
            if(userRepository.userExist(("aa"))) System.out.println("user exist");
            else
            userRepository.addUser(user);
    }

}
