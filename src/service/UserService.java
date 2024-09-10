package service;

import domain.User;
import repository.UserRepository;

import java.util.Optional;
import java.util.Scanner;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    private Scanner scanner = new Scanner(System.in);
    private int tempAge = 0;
    private String tempCin;
    private String tempName;
    private Optional<User> user;
    //public static String defaultEntre;

    public UserService(){}

    public boolean save(User user){
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());;
            return false;
        }
    }
    public boolean userExist(String cin){
        return userRepository.userExist(cin);
    }
    public Optional<User> findById(String cin){
        return userRepository.findById(cin);
    }
    public Optional<User> update(User user){
        return userRepository.update(user);
    }
    public void delete(String cin){
        userRepository.findById(cin).ifPresentOrElse(user->{
            Optional<User> userDeleted = userRepository.delete(cin);
            userDeleted.ifPresent(user1->{
                System.out.println("User Deleted Is : ");
                System.out.println(user1.toString());
            });
        },()->{
            System.out.println("User Not Found");
        });
    }

}
