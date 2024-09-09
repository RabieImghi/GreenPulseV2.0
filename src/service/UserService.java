package service;

import domain.User;
import repository.UserRepository;

import java.sql.SQLException;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public UserService() throws SQLException {}

    public void addUser(User user){
            if(userRepository.userExist(("aa"))) System.out.println("User CIN Already Exist");
            else userRepository.addUser(user);
    }
    public void updateUser(String cin){
        User user = new User("bbb","bbbbb",3333);
        if(userRepository.userExist("ub")) System.out.println("User CIN Already Exist");
        else  userRepository.updateUser(user,cin);
    }
    public void deleteUser(String cin){
        if(userRepository.userExist(cin))
            userRepository.deleteUser(cin);
        else System.out.println("User Not Found");
    }

}
