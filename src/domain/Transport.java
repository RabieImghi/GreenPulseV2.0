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

    public Transport(int id, double distance, String typeVehicle, Consumption consumption) {
        this.id = id;
        this.distance = distance;
        this.typeVehicle = typeVehicle;
        this.consumption = consumption;
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
    public double impactCal(Double consumption) {
        if (this.typeVehicle.equals("Car")) return super.impactCal(consumption)*this.distance*0.5;
        return super.impactCal(consumption)*this.distance*0.1;
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
