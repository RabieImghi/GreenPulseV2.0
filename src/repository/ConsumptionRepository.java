package repository;

import Util.TypeOfConsumption;
import config.DatabaseConnection;
import domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ConsumptionRepository {
    private final Connection connection;
    public ConsumptionRepository(){
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    public Integer save(LocalDate start, LocalDate end, double carbon,String cin,String typeImpact){
        int idConsumption=0;
        try {
            connection.setAutoCommit(false);
            String saveConsumption = "INSERT INTO consumptions (start_date, end_date,carbon_consumption,user_cin,type_impact) VALUES (?,?,?,?,?)";
            PreparedStatement preparedSaveConsumption = this.connection.prepareStatement(saveConsumption, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedSaveConsumption.setDate(1, java.sql.Date.valueOf(start));
            preparedSaveConsumption.setDate(2, java.sql.Date.valueOf(end));
            preparedSaveConsumption.setDouble(3, carbon);
            preparedSaveConsumption.setString(4, cin);
            preparedSaveConsumption.setString(5, typeImpact);
            int resultOfInsert = preparedSaveConsumption.executeUpdate();

            if (resultOfInsert > 0) {
                ResultSet generatedKeys = preparedSaveConsumption.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idConsumption = generatedKeys.getInt(1);
                } else {
                    System.out.println("No generated key returned.");
                }
            }
        }catch (SQLException e){
            System.err.println("add Consumption error : "+e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idConsumption;

    }
    public List<Consumption> getAllConsumption(User user){
        List<Consumption> consumptions = new ArrayList<>();
        try {
            String selectConsumption = "SELECT * FROM consumptions WHERE user_cin = ?";
            PreparedStatement preparedSelectConsumption = this.connection.prepareStatement(selectConsumption);
            preparedSelectConsumption.setString(1, user.getCin());
            ResultSet resultSelectConsumption = preparedSelectConsumption.executeQuery();
            while (resultSelectConsumption.next()) {
                int idConsumption = resultSelectConsumption.getInt("id");
                LocalDate startDate = resultSelectConsumption.getDate("start_date").toLocalDate();
                LocalDate endDate = resultSelectConsumption.getDate("end_date").toLocalDate();
                double carbon = resultSelectConsumption.getDouble("carbon_consumption");
                String typeImpact = resultSelectConsumption.getString("type_impact");
                Consumption consumption = new Consumption(startDate, endDate, carbon, TypeOfConsumption.valueOf(typeImpact));
                consumption.setUser(user);
                consumption.setId(idConsumption);
                consumptions.add(consumption);
            }
        } catch (SQLException e) {
            System.err.println("get all consumption error : " + e.getMessage());
        }
        return consumptions;
    }
}
