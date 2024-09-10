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

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean functionReturn = false;
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
                    functionReturn.ifPresentOrElse(user -> {
                        System.out.println("User Added : " + user.toString());
                    }, () -> {
                        System.out.println("User Not add");
                    });
                }
                break;
                case "2": {
                    updateUser();
                }
                break;
                case "3": {
                    System.out.print("Give me CIN : ");
                    userService.delete(scanner.nextLine());
                }
                break;
                case "4": {
                    saveConsumption();
                }
                break;
                case "7":
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        } while (!optionsEnd);
    }

    public static Optional<User> saveUser() {
        String tempCin;
        String tempName;
        int tempAge;
        boolean userCinExist = false;
        boolean userAgeTest = false;
        do {
            System.out.print("Give me your Cin : ");
            tempCin = scanner.nextLine();
            userCinExist = userService.userExist(tempCin);
            if (userCinExist) System.out.println("User CIN Already Exist");
        } while (userCinExist);
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
            int tempAge;
            System.out.print("Give me your name : ");
            String tempName = scanner.nextLine();
            user1.setName(tempName);
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
            user1.setAge(tempAge);
            Optional<User> userUpdated = userService.update(user1);
            userUpdated.ifPresent((System.out::println));
        }, () -> {
            System.out.println("User Not Exist");
        });
    }


    // function to get the impact type and values
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
                    System.out.println("Give me distance traveled : ");
                    double distance = scanner.nextDouble();
                    impactDistanceAndType.put(0,"Car");
                    impactDistanceAndType.put(1,Double.toString(distance));
                    return impactDistanceAndType;
                case "2":
                    System.out.println("Give me distance traveled : ");
                    double distanceTrain = scanner.nextDouble();
                    impactDistanceAndType.put(0,"Train");
                    impactDistanceAndType.put(1,Double.toString(distanceTrain));
                    return impactDistanceAndType;
                default:{
                    System.out.println("Invalid option, please try again.");
                    optionTest = true;
                    return null;
                }
            }
        } while (optionTest);
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
                    System.out.println("Give me Gas : ");
                    double gas = scanner.nextDouble();
                    impactEnergyAndType.put(0,"Gas");
                    impactEnergyAndType.put(1,Double.toString(gas));
                    return impactEnergyAndType;
                case "2":
                    System.out.println("Give me Electricity : ");
                    double electricity = scanner.nextDouble();
                    impactEnergyAndType.put(0,"Electricity");
                    impactEnergyAndType.put(1,Double.toString(electricity));
                    return impactEnergyAndType;
                default:
                    System.out.println("Invalid option, please try again.");
                    optionTest = true;
                    return null;
            }
        } while (optionTest);
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
                    System.out.println("Give me Meat : ");
                    double meat = scanner.nextDouble();
                    impactFoodAndType.put(0,"Meat");
                    impactFoodAndType.put(1,Double.toString(meat));
                    return impactFoodAndType;
                case "2":
                    System.out.println("Give me Vegetables : ");
                    double vegetables = scanner.nextDouble();
                    impactFoodAndType.put(0,"Vegetables");
                    impactFoodAndType.put(1,Double.toString(vegetables));
                    return impactFoodAndType;
                default:
                    System.out.println("Invalid option, please try again.");
                    optionTest = true;
                    return null;
            }
        } while (optionTest);
    }

    //function to save consumption

    public static void saveConsumption(){
        System.out.println("Give me Cin : ");
        String tempCin = scanner.nextLine();
        Optional<User> userOptional = userService.findById(tempCin);
        userOptional.ifPresentOrElse(user -> {
            TypeOfConsumption impactTypeOfConsumption = getImpact();
            HashMap<Integer,String> typeImpactDetail = typeOfImpact(impactTypeOfConsumption);
            LocalDate tempStartDate;
            LocalDate tempEndDate;
            boolean isExist;
            do{
                String defaultEntre = scanner.nextLine();
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

            Optional<Consumption> consumption;
           impactService.saveImpact(tempStartDate,tempEndDate,tempCarVal,user,impactTypeOfConsumption,typeImpactDetail);


            //Optional<Consumption> consumption= consumptionService.save(,user.getCin());
            //consumption.ifPresent(consumption1 -> System.out.println("Consumption added with success"));
        },()->{
            System.out.println("user not found");
        });
    }
}