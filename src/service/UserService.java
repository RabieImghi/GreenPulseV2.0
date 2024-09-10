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

    public Optional<User> update(String cin){
        user = userRepository.findById(cin);
        user.ifPresent((user1 -> {
            System.out.print("Give me your name : ");
            tempName=scanner.nextLine();
            user1.setName(tempName);
            do {
                System.out.print("Give me your Age: ");
                String input = scanner.nextLine();
                try {
                    tempAge = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Age, please enter a valid integer.");
                    tempAge = 0;
                }
            } while (tempAge == 0);
            user1.setAge(tempAge);
            user = userRepository.update(user1);
        }));
        return user;
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
