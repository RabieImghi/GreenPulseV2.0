package domain;

import Util.TypeOfConsumption;

import java.time.LocalDate;

public class Food extends Consumption {
    private int id;
    private final double weight;
    private final String typeFood;
    Consumption consumption;

    public Food(LocalDate startDate, LocalDate endDate, double carbon, TypeOfConsumption typeOfConsumption, double weight, String typeFood) {
        super(startDate,endDate,carbon,typeOfConsumption);
        this.weight = weight;
        this.typeFood = typeFood;
    }

    public Food(int id,double weight, String typeFood, Consumption consumption) {
        this.id = id;
        this.weight = weight;
        this.typeFood = typeFood;
        this.consumption = consumption;
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


    public String getTypeFood() {
        return typeFood;
    }


    @Override
    public double impactCal(Double consumption) {
        if(this.typeFood.equals("Meat")) return super.impactCal(consumption)*this.weight*5;
        else return super.impactCal(consumption)*this.weight*0.5;
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
