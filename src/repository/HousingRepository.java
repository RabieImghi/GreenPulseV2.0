package repository;

import config.DatabaseConnection;
import domain.Consumption;
import domain.Housing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;

public class HousingRepository {
    private final Connection connection;
    public HousingRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public Optional<Housing> save(Housing housing){
        String stmSave = "INSERT INTO housings (energy_consumption,energy_type, consumption_id) VALUES (?,?,?)";
        Optional<Housing> housingOptional = Optional.empty();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(stmSave);
            preparedStatement.setDouble(1, housing.getConsEnergy());
            preparedStatement.setString(2, housing.getTypeEnergy());
            preparedStatement.setInt(3, housing.getConsumptionId());
            int resultOfInsert = preparedStatement.executeUpdate();
            if(resultOfInsert > 0) {
                return housingOptional = Optional.of(housing);
            }
        } catch (Exception e) {
            System.out.println("Error on save housing : " + e.getMessage());
        }
        return housingOptional;
    }
}
