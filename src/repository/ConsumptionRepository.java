package repository;

import config.DatabaseConnection;
import domain.Consumption;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsumptionRepository {
    private final Connection connection;
    public ConsumptionRepository(){
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    public Optional<Consumption> save(Consumption consumption, String cin){
        String stmSave= "INSERT INTO consumptions (start_date, end_date,carbon_consumption,user_cin ) VALUES (?,?,?,?)";
        Optional<Consumption> consumptionOptional = Optional.empty();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(stmSave);
            preparedStatement.setDate(1, java.sql.Date.valueOf(consumption.getStartDate()));
            preparedStatement.setDate(2,java.sql.Date.valueOf(consumption.getEndDate()));
            preparedStatement.setDouble(3,consumption.getCarbon());
            preparedStatement.setString(4,cin);
            int resultOfInsert = preparedStatement.executeUpdate();
            if(resultOfInsert > 0) return consumptionOptional = Optional.of(consumption);

        }catch (Exception e){
            System.out.println("Error on save consumption : "+e.getMessage());
        }
        return consumptionOptional;
    }
    public List<Consumption> getAllConsumption(User user){
        List<Consumption> listConsumption = new ArrayList<>();
        String stmGetList = "SELECT * FROM consumptions WHERE user_cin = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(stmGetList);
            preparedStatement.setString(1,user.getCin());
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                listConsumption.add(new Consumption(result.getDate("start_date").toLocalDate(),result.getDate("end_date").toLocalDate(),result.getDouble("carbon_consumption")));
            }
        }catch (Exception e){
            System.out.println("Error on get all consumptions : "+e.getMessage());
        }
        return listConsumption;
    }
}
