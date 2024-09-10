package domain;

public class Food {
    private int id;
    private double weight;
    private String typeFood;
    private int consumptionId;

    public Food(double weight, String typeFood, int consumptionId) {
        this.weight = weight;
        this.typeFood = typeFood;
        this.consumptionId = consumptionId;
    }

    public int getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(int consumptionId) {
        this.consumptionId = consumptionId;
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
}
