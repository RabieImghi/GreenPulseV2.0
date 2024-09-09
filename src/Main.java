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
                    functionReturn = userService.save();
                    if (!functionReturn) break;
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
}