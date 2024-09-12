import Util.DateValidator;
import domain.Consumption;
import domain.Food;
import domain.Transport;
import domain.User;
import service.ConsumptionService;
import service.ImpactService;
import service.MainService;
import service.UserService;
import Util.TypeOfConsumption;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean optionsEnd = false;
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";
    public static UserService userService = new UserService();
    public static ImpactService impactService = new ImpactService();
    public static ConsumptionService consumptionService = new ConsumptionService();

    public static void main(String[] args) {
        do {
            MainService.displayMenuUser();
            String option = scanner.nextLine();
            switch (option) {
                case "1": {
                    Optional<User> functionReturn = saveUser();
                    functionReturn.ifPresent(System.out::println);
                }
                break;
                case "2": {
                    displayUserCin();
                    updateUser();
                }
                break;
                case "3": {
                    displayUserCin();
                    Optional<User> user ;
                    String cin;
                    do {
                        System.out.print("Give me CIN: ");
                        cin = scanner.nextLine();
                        user = userService.delete(cin);
                        user.ifPresentOrElse(
                                System.out::println,
                                () -> System.out.println("User CIN Not Exist! (Back? Type 'quite')")
                        );
                    } while (user.isEmpty() && !cin.equals("quite"));
                }
                break;
                case "4": {
                    displayUserCin();
                    saveConsumption();
                }break;
                case "5": displayUserInfo(); break;
                case "6": displayUserFilteredByImpact(50000); break;
                case "7": displayInactiveUser(); break;
                case "8": SortingUsersByConsumption(); break;
                case "9": CalculationAverageConsumption(); break;
                case "10": optionsEnd = true; break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        } while (!optionsEnd);
    }

    //=================================================== User Functions
    public static Optional<User> saveUser() {
        String tempCin;
        String tempName;
        boolean userCinExist;

        do {
            System.out.print(CYAN + "Give me your Cin : " + RESET);
            tempCin = scanner.nextLine();
            userCinExist = userService.userExist(tempCin);
            if (userCinExist) System.out.println(RED + "User CIN Already Exists" + RESET);
        } while (userCinExist);

        System.out.print(CYAN + "Give Me Your Name : " + RESET);
        tempName = scanner.nextLine();

        int tempAge = getAgeUser();
        User user = new User(tempCin, tempName, tempAge);

        boolean userSaved = userService.save(user);
        Optional<User> optionalUser;

        if (userSaved) {
            optionalUser = Optional.of(user);
            System.out.println(GREEN + "User successfully saved!" + RESET);
        } else {
            optionalUser = Optional.empty();
            System.out.println(RED + "Failed to save user." + RESET);
        }

        return optionalUser;
    }
    public static void updateUser() {
        System.out.print(CYAN + "Give me CIN : " + RESET);
        Optional<User> user = userService.findById(scanner.nextLine());

        user.ifPresentOrElse(user1 -> {
            System.out.print(CYAN + "Give me your name : " + RESET);
            String tempName = scanner.nextLine();
            user1.setName(tempName);

            int tempAge = getAgeUser();
            user1.setAge(tempAge);

            Optional<User> userUpdated = userService.update(user1);
            userUpdated.ifPresent(updatedUser -> System.out.println(GREEN + "User updated: " + updatedUser + RESET));
        }, () -> System.out.println(RED + "User Not Exist" + RESET));
    }
    public static int getAgeUser() {
        int tempAge;
        do {
            System.out.print(CYAN + "Give me your Age: " + RESET);
            String input = scanner.nextLine();
            try {
                tempAge = Integer.parseInt(input);
                if (tempAge <= 0) {
                    System.out.println(RED + "Age must be a positive integer. Please try again." + RESET);
                    tempAge = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid Age, please enter a valid integer." + RESET);
                tempAge = 0;
            }
        } while (tempAge <= 0);
        return tempAge;
    }
    public static void displayUserCin(){
        HashMap<User, List<Consumption>> userList = userService.findAll();
        System.out.println("\n");
        System.out.printf(BLUE + "%-15s | %-20s | %-10s | %-20s%n" + RESET, "User CIN", "User Name", "User Age", "Total Consumption");
        System.out.println(BLUE + "---------------------------------------------------------------------------" + RESET);
        userList.forEach((user,consumption)->{
            System.out.printf(YELLOW +"%-15s | %-20s | %-10d | %-2d%n", user.getCin(), user.getName(), user.getAge(), consumption.size());
        });
        System.out.println(BLUE + "---------------------------------------------------------------------------" + RESET);
    }
    public static void SortingUsersByConsumption(){
        HashMap<User, List<Consumption>> userList = userService.findAll();
        userList.entrySet().stream()
                .sorted((user, user2) -> {
                    double totalImpact1 = user.getValue().stream()
                            .mapToDouble(consumption -> userService.impactCal(consumption))
                            .sum();

                    double totalImpact2 = user2.getValue().stream()
                            .mapToDouble(consumption -> userService.impactCal(consumption))
                            .sum();

                    return Double.compare(totalImpact2, totalImpact1);
                })
                .forEach(entry -> {
                    User user = entry.getKey();
                    List<Consumption> consumptionList = entry.getValue();
                    System.out.println(user.getCin() + "/" + user.getName() + "\n");
                    double totalImpact = 0.;
                    for (Consumption c : consumptionList){
                        totalImpact+=userService.impactCal(c);
                    }
                    System.out.println("total : "+totalImpact);
                });
    }
    public static void displayUserFilteredByImpact(int number){
        HashMap<User, List<Consumption>> userList = userService.findAll();
        userList.forEach((user, consumptionList) -> {
            System.out.println(user.getCin() + "/" + user.getName() + "\n");
            List<Consumption> filteredConsumptions = consumptionList.stream()
                    .filter(consumption -> userService.impactCal(consumption) > number)
                    .collect(Collectors.toList());
            displayConsumptionList(filteredConsumptions);
        });
    }
    public static void displayUserInfo(){
        HashMap<User, List<Consumption>> userList = userService.findAll();
        userList.forEach((user,coumptionList)->{
            System.out.println(user.getCin()+"/"+user.getName()+"\n");
            displayConsumptionList(coumptionList);
        });
    }

    //===================================================  function to get Inactive User
    public static void displayInactiveUser(){
        HashMap<User, List<Consumption>> userList = userService.findAll();
        LocalDate startDate;
        LocalDate endDate;
        System.out.println("Give me period : ");
        System.out.print("Start Date (YYYY-MM-DD) : ");
        startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("End Date (YYYY-MM-DD) : ");
        endDate = LocalDate.parse(scanner.nextLine());
        LocalDate finalStartDate = startDate;
        LocalDate finalEndDate1 = endDate;
        userList.forEach((user, consumptionList)->{
            List<LocalDate> dateListRange = consumptionService.dateListRange(consumptionList);
            List<Consumption> newConsumationList = consumptionList.stream()
                    .filter(consumption -> DateValidator.isThisDateValid(dateListRange, finalStartDate, finalEndDate1)).collect(Collectors.toList());
            if(newConsumationList.isEmpty()){
                System.out.println(user.getCin()+"/"+user.getName());
            }
        });
    }

    //=================================================== Consumption Save Function
    public static void saveConsumption(){
        System.out.print("Give me Cin : ");
        String tempCin = scanner.nextLine();
        Optional<User> userOptional = userService.findById(tempCin);
        userOptional.ifPresentOrElse(user -> {
            TypeOfConsumption impactTypeOfConsumption = getImpact();
            HashMap<Integer,String> typeImpactDetail = typeOfImpact(impactTypeOfConsumption);
            LocalDate tempStartDate;
            LocalDate tempEndDate;
            boolean isExist;
            do{
                List<LocalDate> dateListRange;
                System.out.print("Give me new start date : ");
                tempStartDate = LocalDate.parse(scanner.nextLine());
                System.out.print("Give me new end date : ");
                tempEndDate = LocalDate.parse(scanner.nextLine());
                List<Consumption> listConsumptionFilterByImpact;
                listConsumptionFilterByImpact=consumptionService.getAllConsumption(user).stream()
                        .filter(consumption -> String.valueOf(consumption.getTypeOfConsumption())
                                .equals(String.valueOf(impactTypeOfConsumption)))
                        .collect(Collectors.toList());
                dateListRange = consumptionService.dateListRange(listConsumptionFilterByImpact);
                isExist = DateValidator.isThisDateValid(dateListRange,tempStartDate,tempEndDate);
                if(isExist) System.out.println("This Date Already Exist Try Another Date !");
            }while (isExist);
            System.out.print("Give me Carbon : ");
            double tempCarVal = scanner.nextDouble();
            impactService.saveImpact(tempStartDate,tempEndDate,tempCarVal,user,impactTypeOfConsumption,typeImpactDetail);
        },()-> System.out.println("user not found"));
    }


    //=================================================== function to get the impact type and values
    public static TypeOfConsumption getImpact() {
        MainService.displayMenuImpact();
        String typeImpact;
        TypeOfConsumption typeOfConsumption = null;
        boolean optionTest;
        do {
            optionTest = false;
            typeImpact = scanner.nextLine();
            switch (typeImpact) {
                case "1":
                    typeOfConsumption = TypeOfConsumption.TRANSPORT;
                    break;
                case "2":
                    typeOfConsumption = TypeOfConsumption.HOUSING;
                    break;
                case "3":
                    typeOfConsumption = TypeOfConsumption.FOOD;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    optionTest = true;
            }
        } while (optionTest);
        return typeOfConsumption;
    }
    public static HashMap<Integer,String> typeOfImpact(TypeOfConsumption typeOfConsumption) {
        switch (typeOfConsumption) {
            case TRANSPORT: return typeOfVehicle();
            case HOUSING: return typeEnergy();
            case FOOD: return typeFood();
            default: return null;
        }
    }
    public static HashMap<Integer,String> typeOfVehicle(){
        MainService.displayMenuImpactTransport();
        HashMap<Integer,String> impactDistanceAndType = new HashMap<>();
        String typeImpact;
        boolean optionTest=false;
        do {
            typeImpact = scanner.nextLine();
            switch (typeImpact) {
                case "1":
                    System.out.print("Give Me Distance Traveled : ");
                    double distance = scanner.nextDouble();
                    scanner.nextLine();
                    impactDistanceAndType.put(0, "Car");
                    impactDistanceAndType.put(1, Double.toString(distance));
                    optionTest = true;
                    break;
                case "2":
                    System.out.print("Give Me Distance Traveled: ");
                    double distanceTrain = scanner.nextDouble();
                    scanner.nextLine();
                    impactDistanceAndType.put(0, "Train");
                    impactDistanceAndType.put(1, Double.toString(distanceTrain));
                    optionTest = true;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    System.out.print("Give me Distance Traveled : ");

            }
        } while (!optionTest);
        return impactDistanceAndType;
    }
    public static HashMap<Integer,String> typeEnergy(){
        MainService.displayMenuImpactHousing();
        HashMap<Integer,String> impactEnergyAndType = new HashMap<>();
        String typeImpact;
        boolean optionTest=false;
        do {
            typeImpact = scanner.nextLine();
            switch (typeImpact) {
                case "1":
                    System.out.print("Give me Gas Energy consumption : ");
                    double gas = scanner.nextDouble();
                    impactEnergyAndType.put(0,"Gas");
                    impactEnergyAndType.put(1,Double.toString(gas));
                    optionTest = true;
                    break;
                case "2":
                    System.out.print("Give me Electricity Energy consumption : ");
                    double electricity = scanner.nextDouble();
                    impactEnergyAndType.put(0,"Electricity");
                    impactEnergyAndType.put(1,Double.toString(electricity));
                    optionTest = true;

                default:
                    System.out.println("Invalid option, please try again");
                    System.out.print("Give me Energy Consumption : ");
            }
        } while (!optionTest);
        return impactEnergyAndType;
    }
    public static HashMap<Integer,String> typeFood(){
        MainService.displayMenuImpactFood();
        HashMap<Integer,String> impactFoodAndType = new HashMap<>();
        String typeImpact;
        boolean optionTest=false;
        do {
            typeImpact = scanner.nextLine();
            switch (typeImpact) {
                case "1":
                    System.out.print("Give Me Meat Weight : ");
                    double meat = scanner.nextDouble();
                    impactFoodAndType.put(0,"Meat");
                    impactFoodAndType.put(1,Double.toString(meat));
                case "2":
                    System.out.print("Give me Vegetables Weight : ");
                    double vegetables = scanner.nextDouble();
                    impactFoodAndType.put(0,"Vegetables");
                    impactFoodAndType.put(1,Double.toString(vegetables));
                    optionTest = true;
                default:
                    System.out.println("Invalid option, please try again.");
                    System.out.println("Give me Weight : ");
            }
        } while (!optionTest);
        return impactFoodAndType;
    }

    //display users information of consumption
    public static void displayConsumptionList(List<Consumption> coumptionList){
        coumptionList.forEach(consumption -> {
            System.out.println("stat / end : "+consumption.getStartDate()+"/"+consumption.getEndDate());
            System.out.println("impact type : "+consumption.getTypeOfConsumption());
            System.out.println("impact Consumption = "+userService.impactCal(consumption));
        });
    }

    public static void CalculationAverageConsumption(){
        HashMap<User, List<Consumption>> userList = userService.findAll();
        LocalDate startDate;
        LocalDate endDate;
          System.out.println("Give me period : ");
        System.out.print("Start Date (YYYY-MM-DD) : ");
        startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("End Date (YYYY-MM-DD) : ");
        endDate = LocalDate.parse(scanner.nextLine());
        userList.forEach((user,consumptionList)->{
            System.out.println("=========================================");
            System.out.println(user.getCin()+"/"+user.getName());
            System.out.println("Average Consumption : "+averageByPeriod(consumptionList,startDate,endDate));
        });
    }
    public static  Double averageByPeriod(List<Consumption> consumptions, LocalDate start , LocalDate endDate) {
        if (!start.isAfter(endDate)) {
            List<LocalDate> dates = DateValidator.dateListRange(start, endDate);
            return (consumptions
                    .stream()
                    .filter(e -> DateValidator.isThisDateValid(dates,e.getStartDate(), e.getEndDate()))
                    .mapToDouble(e-> userService.impactCal(e)).sum()) / dates.size();
        }else return 0.;
    }

}