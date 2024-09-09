package service;
import Util.DateValidator;
import domain.Consumption;
import domain.User;
import repository.ConsumptionRepository;
import repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class ConsumptionService {
    private ConsumptionRepository consumptionRepository = new ConsumptionRepository();
    private UserRepository userRepository = new UserRepository();
    private Scanner scanner = new Scanner(System.in);
    String tempCin;
    public Optional<Consumption> save(){
        Optional<Consumption> consumption = Optional.empty();
        System.out.println("Give me Cin : ");
        tempCin = scanner.nextLine();
        userRepository.findById(tempCin).ifPresentOrElse(user -> {
            LocalDate tempStartDate;
            LocalDate tempEndDate;
            boolean isExist;
            do{
                List<LocalDate> dateListRange = new ArrayList<>();
                System.out.print("Give me new start date : ");
                tempStartDate = LocalDate.parse(scanner.nextLine());
                System.out.print("Give me new end date : ");
                tempEndDate = LocalDate.parse(scanner.nextLine());
                dateListRange = dateListRange(getAllConsumption(user));
                isExist = DateValidator.isThisDateValid(dateListRange,tempStartDate,tempEndDate);
                if(isExist) System.out.println("This Date Already Exist Try Another Date !");
            }while (isExist);
            System.out.print("Give me Carbon : ");
            double tempCarVal = scanner.nextDouble();
            consumptionRepository.save(new Consumption(tempStartDate,tempEndDate,tempCarVal),user.getCin());

        },()->{
            System.out.println("user not found");
        });
        return consumption;
    }
    public static List<LocalDate> dateListRange(List<Consumption> listDate){
        List<LocalDate> dateListRange = new ArrayList<>();
        for(Consumption consumption : listDate){
            for(LocalDate dateTest = consumption.getStartDate(); !dateTest.isAfter(consumption.getEndDate()); dateTest=dateTest.plusDays(1)){
                dateListRange.add(dateTest);
            }
        }
        return dateListRange;
    }
    public List<Consumption> getAllConsumption(User user){
        return consumptionRepository.getAllConsumption(user);
    }
}
