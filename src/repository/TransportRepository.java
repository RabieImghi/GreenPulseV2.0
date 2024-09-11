package repository;

import config.DatabaseConnection;
import domain.Consumption;
import domain.Housing;
import domain.Transport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class TransportRepository {
    private final Connection connection;
    public TransportRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public Optional<Transport> save(Transport transport){
        try{
            this.connection.setAutoCommit(false);

            ConsumptionRepository consumptionRepository=new ConsumptionRepository();
            int idConsumption = consumptionRepository.save(transport.getStartDate(),transport.getEndDate(),transport.getCarbon(),transport.getUser().getCin(),String.valueOf(transport.getTypeOfConsumption()));

            String saveTransport = "INSERT INTO transports (distance, type_vehicule, consumption_id) VALUES (?,?,?)";
            PreparedStatement preparedStatementSaveTransport = this.connection.prepareStatement(saveTransport);
            preparedStatementSaveTransport.setDouble(1, transport.getDistance());
            preparedStatementSaveTransport.setString(2, transport.getTypeVehicle());
            preparedStatementSaveTransport.setInt(3, idConsumption);
            int resultOfInsert = preparedStatementSaveTransport.executeUpdate();
            if(resultOfInsert > 0) {
                return Optional.of(transport);
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
