package service;

import domain.Food;
import domain.Housing;
import repository.FoodRepository;
import repository.HousingRepository;

import java.util.Optional;

public class HousingService {
    public Optional<Housing> saveHousing(Housing housing){
        return new HousingRepository().save(housing);
    }
}
