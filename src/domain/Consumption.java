package domain;

import java.time.LocalDate;

public class Consumption {
    private LocalDate startDate;
    private LocalDate endDate;
    private double carbon;
    private User user;

    public Consumption(LocalDate startDate, LocalDate endDate, double carbon) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.carbon = carbon;
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
                '}';
    }
}
