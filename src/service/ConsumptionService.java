package service;

import domain.Consumption;
import domain.User;
import repository.ConsumptionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ConsumptionService {
    private final ConsumptionRepository consumptionRepository = new ConsumptionRepository();

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
