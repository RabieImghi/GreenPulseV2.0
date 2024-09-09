package service;

import model.User;
import repository.UserRepository;

import java.sql.SQLException;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public UserService() throws SQLException {}

    public void addUser(){
            User user = new User("aa","aaaa",21);
            if(userRepository.userExist(("aa"))) System.out.println("User CIN Already Exist");
            else userRepository.addUser(user);
    }
    public void updateUser(String cin){
        User user = new User("bbb","bbbbb",3333);
        if(userRepository.userExist("bbb")) System.out.println("User CIN Already Exist");
        else  userRepository.updateUser(user,cin);
    }

}
