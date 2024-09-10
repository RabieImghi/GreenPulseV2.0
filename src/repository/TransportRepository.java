package repository;

import config.DatabaseConnection;
import domain.Consumption;
import domain.Transport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;

public class TransportRepository {
    private final Connection connection;
    public TransportRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    public Optional<Transport> save(Transport transport){
        String stmSave = "INSERT INTO transports (distance, typevehicule, consumption_id) VALUES (?,?,?)";
        Optional<Transport> transportOptional = Optional.empty();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(stmSave);
            preparedStatement.setDouble(1, transport.getDistance());
            preparedStatement.setString(2, transport.getTypeVehicle());
            preparedStatement.setInt(3, transport.getConsumptionId());
            int resultOfInsert = preparedStatement.executeUpdate();
            if(resultOfInsert > 0) {
                return transportOptional = Optional.of(transport);
            }
        } catch (Exception e) {
            System.out.println("Error on save transport : " + e.getMessage());
        }
        return transportOptional;
    }
}
