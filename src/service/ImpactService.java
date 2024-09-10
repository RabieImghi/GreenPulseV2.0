package service;

import Util.TypeOfConsumption;
import domain.*;
import repository.ConsumptionRepository;
import repository.FoodRepository;
import repository.HousingRepository;
import repository.TransportRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

public class ImpactService {
    private final   ConsumptionRepository consumptionRepository = new ConsumptionRepository();
    public void saveImpact(LocalDate tempStartDate, LocalDate tempEndDate, Double tempCarVal, User user, TypeOfConsumption impactTypeOfConsumption, HashMap<Integer,String> typeImpactDetail) {
        switch (impactTypeOfConsumption) {
            case FOOD:{
                Consumption consumption = new Consumption(tempStartDate,tempEndDate,tempCarVal,impactTypeOfConsumption);
                consumption.setUser(user);
                consumptionRepository.save(consumption).ifPresentOrElse(consumption1 -> {
                    Food food = new Food(Double.parseDouble(typeImpactDetail.get(1)),typeImpactDetail.get(0),consumption1.getId());
                    saveFood(food).ifPresent(food1 -> {
                        System.out.println("Food impact saved");
                    });
                },()->{
                    System.out.println("Error on save food impact");
                });
            }
                break;
            case HOUSING: {
                Consumption consumption = new Consumption(tempStartDate,tempEndDate,tempCarVal,impactTypeOfConsumption);
                consumption.setUser(user);
                consumptionRepository.save(consumption).ifPresentOrElse(consumption1 -> {
                    Housing housing = new Housing(Double.parseDouble(typeImpactDetail.get(1)),typeImpactDetail.get(0),consumption1.getId());
                    saveHousing(housing).ifPresent(housing1 -> {
                        System.out.println("Housing impact saved");
                    });
                },()->{
                    System.out.println("Error on save housing impact");
                });
            }
                break;
            case TRANSPORT: {
                Consumption consumption = new Consumption(tempStartDate,tempEndDate,tempCarVal,impactTypeOfConsumption);
                consumption.setUser(user);
                consumptionRepository.save(consumption).ifPresentOrElse(consumption1 -> {
                    Transport transport = new Transport(Double.parseDouble(typeImpactDetail.get(1)),typeImpactDetail.get(0),consumption1.getId());
                    saveTransport(transport).ifPresent(transport1 -> {
                        System.out.println("Transport impact saved");
                    });
                },()->{
                    System.out.println("Error on save transport impact");
                });
            }
                break;
            default:
                System.out.println("Error on save impact");
        }
    }
    public Optional<Food> saveFood(Food food){
        return new FoodRepository().save(food);
    }
    public Optional<Housing> saveHousing(Housing housing){
        return new HousingRepository().save(housing);
    }
    public Optional<Transport> saveTransport(Transport transport){
        return new TransportRepository().save(transport);
    }
}
