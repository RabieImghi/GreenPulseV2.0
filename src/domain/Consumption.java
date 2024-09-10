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

    public int getId() {
        return id;
    }

    public TypeOfConsumption getTypeOfConsumption() {
        return typeOfConsumption;
    }

    public void setTypeOfConsumption(TypeOfConsumption typeOfConsumption) {
        this.typeOfConsumption = typeOfConsumption;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getCarbon() {
        return carbon;
    }

    public void setCarbon(double carbon) {
        this.carbon = carbon;
    }
    public void setUser(User user){
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Consumption{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", carbon=" + carbon +
                ", user=" + user +
                ", typeOfConsumption=" + typeOfConsumption +
                '}';
    }
}
