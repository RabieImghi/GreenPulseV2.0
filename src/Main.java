import Util.DateValidator;
import domain.Consumption;
import domain.User;
import service.ConsumptionService;
import service.MainService;
import service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
                    updateUser();
                }break;
                case "3": {
                    System.out.print("Give me CIN : ");
                    userService.delete(scanner.nextLine());
                } break;
                case "4": {
                    saveConsumption();
                }break;
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
    public static void updateUser(){
        System.out.print("Give me CIN : ");
        Optional<User> user = userService.findById(scanner.nextLine());
        user.ifPresentOrElse(user1 -> {
            int tempAge;
            System.out.print("Give me your name : ");
            String tempName = scanner.nextLine();
            user1.setName(tempName);
            do{
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
            Optional<User> userUpdated = userService.update(user1);
            userUpdated.ifPresent((System.out::println));
        },()->{
            System.out.println("User Not Exist");
        });
    }
    public static void saveConsumption(){
        System.out.println("Give me Cin : ");
        String tempCin = scanner.nextLine();
        Optional<User> userOptional = userService.findById(tempCin);
        userOptional.ifPresentOrElse(user -> {
            LocalDate tempStartDate;
            LocalDate tempEndDate;
            boolean isExist;
            do{
                List<LocalDate> dateListRange = new ArrayList<>();
                System.out.print("Give me new start date : ");
                tempStartDate = LocalDate.parse(scanner.nextLine());
                System.out.print("Give me new end date : ");
                tempEndDate = LocalDate.parse(scanner.nextLine());
                dateListRange = consumptionService.dateListRange(consumptionService.getAllConsumption(user));
                isExist = DateValidator.isThisDateValid(dateListRange,tempStartDate,tempEndDate);
                if(isExist) System.out.println("This Date Already Exist Try Another Date !");
            }while (isExist);
            System.out.print("Give me Carbon : ");
            double tempCarVal = scanner.nextDouble();
            Optional<Consumption> consumption= consumptionService.save(tempStartDate,tempEndDate,tempCarVal,user.getCin());
            consumption.ifPresent(consumption1 -> System.out.println("Consumption added with success"));
        },()->{
            System.out.println("user not found");
        });
    }

}