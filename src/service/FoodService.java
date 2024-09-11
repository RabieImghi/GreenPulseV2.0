package service;

import domain.Food;
import repository.FoodRepository;

import java.util.Optional;

public class FoodService {
    private FoodRepository foodRepository = new FoodRepository();


    public Optional<Food> saveFood(Food food){
        return new FoodRepository().save(food);
    }
}
