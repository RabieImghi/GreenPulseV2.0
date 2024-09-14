package service;
public class MainService {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";


    public static void displayMenuUser(){
        System.out.println(BLUE + "\n|====================================================|" + RESET);
        System.out.println(GREEN + "|                   User Management                  |" + RESET);
        System.out.println(BLUE + "|====================================================|" + RESET);
        System.out.println(YELLOW + "| [1]  Add New User          [2]  Update User        |" + RESET);
        System.out.println(YELLOW + "| [3]  Delete User           [4]  Display All Users  |" + RESET);
        System.out.println(BLUE + "|====================================================|" + RESET);
        System.out.println(GREEN + "|                Consumption Management              |" + RESET);
        System.out.println(BLUE + "|====================================================|" + RESET);
        System.out.println(YELLOW + "| [5]  Add Consumption       [6]  Display Users >3000|" + RESET);
        System.out.println(YELLOW + "| [7]  Sort Users by Consumption                     |" + RESET);
        System.out.println(BLUE + "|====================================================|" + RESET);
        System.out.println(GREEN + "|                  Impact / Analytics                |" + RESET);
        System.out.println(BLUE + "|====================================================|" + RESET);
        System.out.println(YELLOW + "| [8]  Inactive User Detection [9]  Avg Consumption  |" + RESET);
        System.out.println(YELLOW + "| [10] Close                                         |" + RESET);
        System.out.println(BLUE + "|====================================================|" + RESET);
        System.out.print(PURPLE + "Enter your option: " + RESET);
    }
    public static void displayMenuImpact(){
        System.out.println(BLUE + "\n|===========================================|" + RESET);
        System.out.println(GREEN + "| Select an option of impact :              |" + RESET);
        System.out.println(BLUE + "|===========================================|" + RESET);
        System.out.println(YELLOW + "| [1]  Transport                            |" + RESET);
        System.out.println(YELLOW + "| [2]  Housing                              |" + RESET);
        System.out.println(YELLOW + "| [3]  Food                                 |" + RESET);
        System.out.println(BLUE + "|===========================================|" + RESET);
        System.out.print(PURPLE + "Enter your option: " + RESET);
    }
    public static void displayMenuImpactTransport(){
        System.out.println(BLUE + "\n|===========================================|" + RESET);
        System.out.println(GREEN + "| Select an option of impact :              |" + RESET);
        System.out.println(BLUE + "|===========================================|" + RESET);
        System.out.println(YELLOW + "| [1] : Car                                 |" + RESET);
        System.out.println(YELLOW + "| [2] : Train                               |" + RESET);
        System.out.println(BLUE + "|===========================================|" + RESET);
        System.out.print(GREEN + "Enter your option: " + RESET);
    }
    public static void displayMenuImpactFood(){
        System.out.println(BLUE + "\n|===========================================|" + RESET);
        System.out.println(GREEN + "| Select an option of impact :              |" + RESET);
        System.out.println(BLUE + "|===========================================|" + RESET);
        System.out.println(YELLOW + "| [1] : Meat                                |" + RESET);
        System.out.println(YELLOW + "| [2] : Vegetables                          |" + RESET);
        System.out.println(BLUE + "|===========================================|" + RESET);
        System.out.print(GREEN + "Enter your option: " + RESET);
    }
    public static void displayMenuImpactHousing(){
        System.out.println(BLUE + "\n|===========================================|" + RESET);
        System.out.println(GREEN + "| Select an option of impact :              |" + RESET);
        System.out.println(BLUE + "|===========================================|" + RESET);
        System.out.println(YELLOW + "| [1] : Gas                                 |" + RESET);
        System.out.println(YELLOW + "| [2] : Electricity                         |" + RESET);
        System.out.println(BLUE + "|===========================================|" + RESET);
        System.out.print(GREEN + "Enter your option: " + RESET);
    }

}
