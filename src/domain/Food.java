package domain;

import Util.TypeOfConsumption;

import java.time.LocalDate;

public class Food extends Consumption {
    private int id;
    private double weight;
    private String typeFood;
    Consumption consumption;

    public Food(LocalDate startDate, LocalDate endDate, double carbon, TypeOfConsumption typeOfConsumption, double weight, String typeFood) {
        super(startDate,endDate,carbon,typeOfConsumption);
        this.weight = weight;
        this.typeFood = typeFood;
    }

    public Consumption getConsumption() {
        return consumption;
    }

    public void setConsumption(Consumption consumption) {
        this.consumption = consumption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getTypeFood() {
        return typeFood;
    }

    public void setTypeFood(String typeFood) {
        this.typeFood = typeFood;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", weight=" + weight +
                ", typeFood='" + typeFood + '\'' +
                ", consumptionId=" + consumption.getId() +
                '}';
    }
}
