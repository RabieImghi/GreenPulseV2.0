import Util.DateValidator;
import domain.Consumption;
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
            System.out.print("Give me your Cin : ");
            tempCin = scanner.nextLine();
            userCinExist = userService.userExist(tempCin);
            if (userCinExist) System.out.println("User CIN Already Exist");
        } while (userCinExist);
        System.out.print("Give Me Your Name : ");
        tempName = scanner.nextLine();
        int tempAge = getAgeUser();
        User user = new User(tempCin, tempName, tempAge);
        boolean userSaved = userService.save(user);
        Optional<User> optionalUser;
        if (userSaved) optionalUser = Optional.of(user);
        else optionalUser = Optional.empty();
        return optionalUser;
    }
    public static void updateUser() {
        System.out.print("Give me CIN : ");
        Optional<User> user = userService.findById(scanner.nextLine());
        user.ifPresentOrElse(user1 -> {

            System.out.print("Give me your name : ");
            String tempName = scanner.nextLine();
            user1.setName(tempName);
            int tempAge = getAgeUser();
            user1.setAge(tempAge);
            Optional<User> userUpdated = userService.update(user1);
            userUpdated.ifPresent((System.out::println));
        }, () -> System.out.println("User Not Exist"));
    }
    public static int getAgeUser(){
        int tempAge;
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
        return tempAge;
    }
    public static void displayUserCin(){
        HashMap<User, List<Consumption>> userList = userService.findAll();
        System.out.println("=========================================");
        userList.forEach((user,consumption)->{
            System.out.println("User Cin : "+user.getCin()+"\tUser Name : "+user.getName());
            System.out.println("-----------------------------------------");
        });
        System.out.println("=========================================");
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

    // function to get Inactive User
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




}