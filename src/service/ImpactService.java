package service;

import Util.TypeOfConsumption;
import domain.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

public class ImpactService {
    public void saveImpact(LocalDate tempStartDate, LocalDate tempEndDate, Double tempCarVal, User user, TypeOfConsumption impactTypeOfConsumption, HashMap<Integer,String> typeImpactDetail) {
        switch (impactTypeOfConsumption) {
            case FOOD:{
                Food food = new Food(tempStartDate,tempEndDate,tempCarVal,impactTypeOfConsumption,Double.parseDouble(typeImpactDetail.get(1)),typeImpactDetail.get(0));
                food.setUser(user);
                FoodService foodService = new FoodService();
                Optional<Food> optionalFood = foodService.saveFood(food);
                optionalFood.ifPresentOrElse(housing1 -> System.out.println("Consumption of food added"),
                        ()-> System.out.println("Consumption not added"));
            }
                break;
            case HOUSING: {
                Housing housing = new Housing(tempStartDate,tempEndDate,tempCarVal,impactTypeOfConsumption,Double.parseDouble(typeImpactDetail.get(1)),typeImpactDetail.get(0));
                housing.setUser(user);
                HousingService housingService = new HousingService();
                Optional<Housing> optionalHousing = housingService.saveHousing(housing);
                optionalHousing.ifPresentOrElse(housing1 -> System.out.println("Consumption of housing added"),
                        ()-> System.out.println("Consumption not added"));
            }
                break;
            case TRANSPORT: {
                Transport transport = new Transport(tempStartDate,tempEndDate,tempCarVal,impactTypeOfConsumption,Double.parseDouble(typeImpactDetail.get(1)),typeImpactDetail.get(0));
                transport.setUser(user);
                TransportService transportService = new TransportService();
                Optional<Transport> transportOptional = transportService.saveTransport(transport);
                transportOptional.ifPresentOrElse(transport1 -> System.out.println("Consumption of transport added"),
                        ()-> System.out.println("Consumption not added"));
            }
                break;
            default:
                System.out.println("Error on save impact");
        }
    }


}
