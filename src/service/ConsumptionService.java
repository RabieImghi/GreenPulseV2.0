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
   /* public Optional<Consumption> save(LocalDate tempStartDate,LocalDate tempEndDate,Double tempCarVal,String cin){
        Optional<Consumption> consumption = Optional.empty();
        consumption = consumptionRepository.save(new Consumption(tempStartDate,tempEndDate,tempCarVal));
        return consumption;
    }*/
    public List<LocalDate> dateListRange(List<Consumption> listDate){
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
