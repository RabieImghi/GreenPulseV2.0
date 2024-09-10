package domain;

public class Transport {
    private int id;
    private double distance;
    private String typeVehicle;
    private int consumptionId;

    public Transport(double distance, String typeVehicle, int consumptionId) {
        this.distance = distance;
        this.typeVehicle = typeVehicle;
        this.consumptionId = consumptionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(int consumptionId) {
        this.consumptionId = consumptionId;
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

}
