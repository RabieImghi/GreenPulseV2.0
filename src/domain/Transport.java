package domain;

import Util.TypeOfConsumption;

import java.time.LocalDate;

public class Transport extends Consumption {
    private int id;
    private double distance;
    private String typeVehicle;
    Consumption consumption;

    public Transport(LocalDate startDate, LocalDate endDate, double carbon, TypeOfConsumption typeOfConsumption, double distance, String typeVehicle) {
        super(startDate,endDate,carbon,typeOfConsumption);
        this.distance = distance;
        this.typeVehicle = typeVehicle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Consumption getConsumption() {
        return consumption;
    }

    public void setConsumption(Consumption consumption) {
        this.consumption = consumption;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", distance=" + distance +
                ", typeVehicle='" + typeVehicle + '\'' +
                ", consumptionId=" + consumption.getId() +
                '}';
    }
}
