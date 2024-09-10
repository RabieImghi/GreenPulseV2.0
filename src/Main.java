import domain.User;
import service.ConsumptionService;
import service.MainService;
import service.UserService;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean functionReturn = false;
    public static boolean optionsEnd = false;
    public static UserService userService = new UserService();
    public static ConsumptionService consumptionService = new ConsumptionService();

    public static void main(String[] args){

        do{
            MainService.displayMenuUser();
            String option = scanner.nextLine();
            switch (option){
                case "1": {
                    Optional<User> functionReturn = saveUser();
                    functionReturn.ifPresentOrElse(user -> {
                        System.out.println("User Added : "+user.toString());
                    },()->{
                        System.out.println("User Not add");
                    });
                } break;
                case "2": {
                    System.out.print("Give me CIN : ");
                    Optional<User> user = userService.update(scanner.nextLine());
                    user.ifPresent((System.out::println));

                }break;
                case "3": {
                    System.out.print("Give me CIN : ");
                    userService.delete(scanner.nextLine());
                } break;
                case "4": {
                    consumptionService.save();
                }
                case "7": break;
                default : System.out.println("Invalid option, please try again.");
            }
        }while (!optionsEnd);
    }
    public static Optional<User> saveUser(){
        String tempCin;
        String tempName;
        int tempAge;
        boolean userCinExist = false;
        boolean userAgeTest = false;
        do{
            System.out.print("Give me your Cin : ");
            tempCin=scanner.nextLine();
            userCinExist = userService.userExist(tempCin);
            if(userCinExist) System.out.println("User CIN Already Exist");
        }while (userCinExist);
        System.out.print("Give Me Your Name : ");
        tempName = scanner.nextLine();
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
        boolean userSaved = userService.save(user);
        Optional<User> optionalUser;

        if(userSaved) optionalUser = Optional.of(user);
        else optionalUser=Optional.empty();
        return optionalUser;
    }
}