package service;

import Util.TypeOfConsumption;
import domain.*;
import repository.ConsumptionRepository;
import repository.FoodRepository;
import repository.HousingRepository;
import repository.TransportRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

public class ImpactService {
    private final   ConsumptionRepository consumptionRepository = new ConsumptionRepository();
    public void saveImpact(LocalDate tempStartDate, LocalDate tempEndDate, Double tempCarVal, User user, TypeOfConsumption impactTypeOfConsumption, HashMap<Integer,String> typeImpactDetail) {
        switch (impactTypeOfConsumption) {
            case FOOD:{
                Food food = new Food(tempStartDate,tempEndDate,tempCarVal,impactTypeOfConsumption,Double.parseDouble(typeImpactDetail.get(1)),typeImpactDetail.get(0));
                food.setUser(user);
                FoodService foodService = new FoodService();
                foodService.saveFood(food);
            }
                break;
            case HOUSING: {
                Housing housing = new Housing(tempStartDate,tempEndDate,tempCarVal,impactTypeOfConsumption,Double.parseDouble(typeImpactDetail.get(1)),typeImpactDetail.get(0));
                housing.setUser(user);
                HousingService housingService = new HousingService();
                housingService.saveHousing(housing);

            }
                break;
            case TRANSPORT: {
                Transport transport = new Transport(tempStartDate,tempEndDate,tempCarVal,impactTypeOfConsumption,Double.parseDouble(typeImpactDetail.get(1)),typeImpactDetail.get(0));
                transport.setUser(user);
                TransportService transportService = new TransportService();
                transportService.saveTransport(transport);
            }
                break;
            default:
                System.out.println("Error on save impact");
        }
    }


}
