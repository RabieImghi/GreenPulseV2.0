package domain;

import Util.TypeOfConsumption;

import java.time.LocalDate;

public class Housing extends Consumption {
    private int id;
    private double consEnergy;
    private String typeEnergy;
    private Consumption consumption;

    public Housing(LocalDate startDate, LocalDate endDate, double carbon, TypeOfConsumption typeOfConsumption,double consEnergy, String typeEnergy) {
        super(startDate,endDate,carbon,typeOfConsumption);
        this.consEnergy = consEnergy;
        this.typeEnergy = typeEnergy;
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

    public double getConsEnergy() {
        return consEnergy;
    }

    public void setConsEnergy(double consEnergy) {
        this.consEnergy = consEnergy;
    }

    public String getTypeEnergy() {
        return typeEnergy;
    }

    public void setTypeEnergy(String typeEnergy) {
        this.typeEnergy = typeEnergy;
    }

    @Override
    public String toString() {
        return "Housing{" +
                "id=" + id +
                ", consEnergy=" + consEnergy +
                ", typeEnergy='" + typeEnergy + '\'' +
                ", consumptionId=" + consumption.getId() +
                '}';
    }
}
