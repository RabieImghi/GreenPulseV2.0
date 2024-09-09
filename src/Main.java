import service.MainService;
import service.UserService;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean functionReturn = false;
    public static boolean optionsEnd = false;
    public static UserService userService = new UserService();

    public static void main(String[] args){

        do{
            MainService.displayMenuUser();
            String option = scanner.nextLine();
            switch (option){
                case "1": {
                    functionReturn = userService.addUser();
                    if (!functionReturn) break;
                } break;
                case "7": break;
                default : System.out.println("Invalid option, please try again.");
            }
        }while (!optionsEnd);

        //UserService userService = new UserService();
        //User user = new User("ub","rabie",21);
        //userService.addUser(user);
        //userService.updateUser("ub");
        //userService.deleteUser("bbb");

    }
}