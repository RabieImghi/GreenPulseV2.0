package domain;

import Util.TypeOfConsumption;

import java.time.LocalDate;


public class Consumption {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double carbon;
    private TypeOfConsumption typeOfConsumption;
    private User user;

    public Consumption(LocalDate startDate, LocalDate endDate, double carbon,TypeOfConsumption typeOfConsumption) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.carbon = carbon;
        this.typeOfConsumption = typeOfConsumption;
    }

    public Consumption() {
    }

    public int getId() {
        return id;
    }

    public TypeOfConsumption getTypeOfConsumption() {
        return typeOfConsumption;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }


    public LocalDate getEndDate() {
        return endDate;
    }


    public double getCarbon() {
        return carbon;
    }

    public void setUser(User user){
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    public double impactCal(Double consumption){
        return consumption;
    }

    @Override
    public String toString() {
        return "Consumption{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", carbon=" + carbon +
                ", typeOfConsumption=" + typeOfConsumption +
                '}';
    }
}
