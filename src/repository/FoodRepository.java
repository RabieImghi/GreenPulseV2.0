package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
        String stmSave = "INSERT INTO foods (food_type, weight, consumption_id) VALUES (?,?,?)";
        Optional<Food> foodOptional = Optional.empty();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(stmSave);
            preparedStatement.setString(1, food.getTypeFood());
            preparedStatement.setDouble(2, food.getWeight());
            preparedStatement.setInt(3, food.getConsumptionId());
            int resultOfInsert = preparedStatement.executeUpdate();
            if(resultOfInsert > 0) {
                return Optional.of(food);
            }else return foodOptional;
        } catch (Exception e) {
            System.out.println("Error on save food : " + e.getMessage());
            return foodOptional;
        }
    }
}
