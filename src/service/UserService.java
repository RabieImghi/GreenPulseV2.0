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

    public boolean addUser(){
        boolean userCinExist = false;
        do{
            System.out.print("Give me your Cin : ");
            tempCin=scanner.nextLine();
            userCinExist = userRepository.userExist((tempCin));
            if(userCinExist) System.out.println("User CIN Already Exist");
        }while (userCinExist);
        System.out.print("Give me your name : ");
        tempName=scanner.nextLine();
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
        User user = new User(tempCin,tempName,tempAge);
        Optional<User> optionalUser = userRepository.addUser(user);
        optionalUser.ifPresent(System.out::println);
        return true;
    }
    public Optional<User> updateUser(String cin){
        user = userRepository.finUserById(cin);
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
            user = userRepository.updateUser(user1);
        }));
        return user;
    }
    public void deleteUser(String cin){
        userRepository.finUserById(cin).ifPresentOrElse(user->{
            Optional<User> userDeleted = userRepository.deleteUser(cin);
            userDeleted.ifPresent(user1->{
                System.out.println("User Deleted Is : ");
                System.out.println(user1.toString());
            });
        },()->{
            System.out.println("User Not Found");
        });
    }

}
