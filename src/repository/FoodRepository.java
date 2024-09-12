package repository;

import java.sql.*;
import java.util.Optional;

import config.DatabaseConnection;
import domain.Consumption;
import domain.Food;
import domain.Housing;

public class FoodRepository {
    private final Connection connection;

    public FoodRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    public Optional<Food> save(Food food){
        try{
            this.connection.setAutoCommit(false);

            ConsumptionRepository consumptionRepository=new ConsumptionRepository();
            int idConsumption = consumptionRepository.save(food.getStartDate(),food.getEndDate(),food.getCarbon(),food.getUser().getCin(),String.valueOf(food.getTypeOfConsumption()));
            String saveFood = "INSERT INTO foods (food_type, weight, consumption_id) VALUES (?,?,?)";
            PreparedStatement preparedStatementSaveFood = this.connection.prepareStatement(saveFood);
            preparedStatementSaveFood.setString(1, food.getTypeFood());
            preparedStatementSaveFood.setDouble(2, food.getWeight());
            preparedStatementSaveFood.setInt(3, idConsumption);
            int resultOfInsert2 = preparedStatementSaveFood.executeUpdate();
            if(resultOfInsert2 > 0) {
                return Optional.of(food);
            }else return Optional.empty();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            return Optional.empty();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Optional<Food> getFoodByIdConsumption(Consumption consumption){
        Optional<Food> foodOptional = Optional.empty();
        try {
            String stm = "SELECT * FROM foods WHERE consumption_id = ?";
            PreparedStatement pr = this.connection.prepareStatement(stm);
            pr.setInt(1,consumption.getId());
            ResultSet resultSet = pr.executeQuery();
            while(resultSet.next()){
                foodOptional= Optional.of(new Food(resultSet.getInt("id"),
                        resultSet.getDouble("weight"),
                        resultSet.getString("food_type"),
                        consumption));
            }
        }catch (Exception e){
            System.out.println("Error on get Housing : "+e.getMessage());
            return foodOptional;
        }
        return foodOptional;
    }

}
