package service;
public class MainService {

    public static void displayMenuUser(){
        System.out.print(
                "\n|===========================================|" +
                "\n| Select an option please :                 |" +
                "\n|===========================================|"+
                "\n| 1 : Add New User                          |" +
                "\n| 2 : Update User                           |" +
                "\n| 3 : Delete User                           |" +
                "\n| 4 : Add Consumption User                  |" +
                "\n| 5 : Display All User                      |" +
                "\n| 6 : Display All User >3000                |" +
                "\n| 7 : InactiveUserDetection                 |" +
                "\n| 8 : Sorting Users by Consumption          |" +
                "\n| 7 : Close                                 |" +
                "\n| 7 : Close                                 |" +
                "\n|===========================================|" +
                "\nEntre your option : ");
    }
    public static void displayMenuImpact(){
        System.out.print(
                        "\n|===========================================|" +
                        "\n| Select an option of impact :              |" +
                        "\n|===========================================|"+
                        "\n| 1 : Transport                             |" +
                        "\n| 2 : Housing                               |" +
                        "\n| 3 : Food                                  |" +
                        "\n|===========================================|" +
                        "\nEntre your option : ");
    }
    public static void displayMenuImpactTransport(){
        System.out.print(
                "\n|===========================================|" +
                "\n| Select an option of impact :              |" +
                "\n|===========================================|"+
                "\n| 1 : Car                                   |" +
                "\n| 2 : Train                                 |" +
                "\n|===========================================|" +
                "\nEntre your option : ");
    }
    public static void displayMenuImpactFood(){
        System.out.print(
                "\n|===========================================|" +
                "\n| Select an option of impact :              |" +
                "\n|===========================================|"+
                "\n| 1 : Meat                                  |" +
                "\n| 2 : Vegetables                            |" +
                "\n|===========================================|" +
                "\nEntre your option : ");
    }
    public static void displayMenuImpactHousing(){
        System.out.print(
                "\n|===========================================|" +
                "\n| Select an option of impact :              |" +
                "\n|===========================================|"+
                "\n| 1 : Gas                                   |" +
                "\n| 2 : Electricity                           |" +
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
                "\n| 3 : Close                             |" +
                "\n|=======================================|" +
                "\nEntre your option : ");
    }
}
