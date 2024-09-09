package service;

import domain.User;
import repository.UserRepository;
import java.util.Scanner;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    public static Scanner scanner = new Scanner(System.in);
    public static int tempAge = 0;
    public static String tempCin;
    public static String tempName;
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
        userRepository.addUser(user);
        return true;
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
