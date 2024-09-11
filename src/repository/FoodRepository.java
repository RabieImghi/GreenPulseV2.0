package repository;

import java.sql.*;
import java.util.Optional;

import config.DatabaseConnection;
import domain.Consumption;
import domain.Food;

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

}
