package service;

import domain.*;
import repository.FoodRepository;
import repository.HousingRepository;
import repository.TransportRepository;
import repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    private final ConsumptionService consumptionService = new ConsumptionService();

    public UserService(){}

    public boolean save(User user){
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean userExist(String cin){
        return userRepository.userExist(cin);
    }
    public Optional<User> findById(String cin){
        return userRepository.findById(cin);
    }
    public Optional<User> update(User user){
        return userRepository.update(user);
    }
    public Optional<User> delete(String cin){
        Optional<User> userDeleted = Optional.empty();
        if(userRepository.findById(cin).isPresent()){
            userDeleted = userRepository.delete(cin);
        }
        return userDeleted;
    }

    public HashMap<User,List<Consumption>> findAll(){
        List<User> listUsers= userRepository.findAll();
        HashMap<User,List<Consumption>> listUserConsumption= new HashMap<>();
        listUsers.forEach(user-> listUserConsumption.put(user,consumptionService.getAllConsumption(user)));
        return listUserConsumption;
    }
    public double impactCal(Consumption consumption){
        switch (consumption.getTypeOfConsumption()){
            case HOUSING:{
                Optional<Housing> optionalHousing = new HousingRepository().getHousingByIdConsumption(consumption);
                return optionalHousing.map(housing -> housing.impactCal(consumption.getCarbon())).orElse(0.);
            }
            case FOOD:{
                Optional<Food> optionalFood = new FoodRepository().getFoodByIdConsumption(consumption);
                return optionalFood.map(food -> food.impactCal(consumption.getCarbon())).orElse(0.);
            }
            case TRANSPORT:{
                Optional<Transport> optionalTransport = new TransportRepository().getTransportByIdConsumption(consumption);
                return optionalTransport.map(transport -> transport.impactCal(consumption.getCarbon())).orElse(0.);
            }
            default: return 0.;
        }
    }

}
