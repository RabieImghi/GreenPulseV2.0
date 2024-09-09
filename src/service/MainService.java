package service;


import java.util.Scanner;

public class MainService {
    public static Scanner scanner = new Scanner(System.in);
    public static int tempAge = 0;
    public static String tempCin;
    public static String tempNom;
    public static String defaultEntre;

    public static void displayMenuUser(){
        System.out.print(
                "\n|===========================================|" +
                "\n| Select an option please :                 |" +
                "\n|===========================================|"+
                "\n| 1 : Add New User                          |" +
                "\n| 2 : Add New Carbon Consumption            |" +
                "\n| 3 : Display User Information (By CIN)     |" +
                "\n| 4 : Update User                           |" +
                "\n| 5 : Delete User                           |" +
                "\n| 6 : Consumption Analysis                  |" +
                "\n| 7 : Close                                 |" +
                "\n|===========================================|" +
                "\nEntre your option : ");
    }
    public static void displayMenuUserUpdate(){
        System.out.print(
                "\n|=======================================|" +
                "\n| Select an option please :             |" +
                "\n|=======================================|"+
                "\n| 1 : Update User Information           |" +
                "\n| 2 : Update Carbon Consumption         |" +
                "\n| 5 : Close                             |" +
                "\n|=======================================|" +
                "\nEntre your option : ");
    }
}
