package repository;

import config.DatabaseConnection;
import domain.Consumption;
import domain.Housing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class HousingRepository {
    private final Connection connection;
    public HousingRepository() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

   public Optional<Housing> save(Housing housing){
       try{
           this.connection.setAutoCommit(false);

           ConsumptionRepository consumptionRepository=new ConsumptionRepository();
           int idConsumption = consumptionRepository.save(housing.getStartDate(),housing.getEndDate(),housing.getCarbon(),housing.getUser().getCin(),String.valueOf(housing.getTypeOfConsumption()));

           String saveHousing = "INSERT INTO housings (energy_consumption,energy_type, consumption_id) VALUES (?,?,?)";
           PreparedStatement preparedStatementSaveHousing = this.connection.prepareStatement(saveHousing);
           preparedStatementSaveHousing.setDouble(1, housing.getConsEnergy());
           preparedStatementSaveHousing.setString(2, housing.getTypeEnergy());
           preparedStatementSaveHousing.setInt(3, idConsumption);
           int resultOfInsert2 = preparedStatementSaveHousing.executeUpdate();
           if(resultOfInsert2 > 0) {
               return Optional.of(housing);
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

    public Optional<Housing> getHousingByIdConsumption(Consumption consumption){
        Optional<Housing> housingOptional = Optional.empty();
        try {
            String stm = "SELECT * FROM housings WHERE consumption_id = ?";
            PreparedStatement pr = this.connection.prepareStatement(stm);
            pr.setInt(1,consumption.getId());
            ResultSet resultSet = pr.executeQuery();
            while(resultSet.next()){
                housingOptional= Optional.of(new Housing(resultSet.getInt("id"),
                        resultSet.getDouble("energy_consumption"),
                        resultSet.getString("energy_type"),
                        consumption));
            }
        }catch (Exception e){
            System.out.println("Error on get Housing : "+e.getMessage());
            return housingOptional;
        }
        return housingOptional;
    }
}
