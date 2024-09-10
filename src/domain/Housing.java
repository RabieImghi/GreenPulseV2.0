package domain;

public class Housing {
    private int id;
    private double consEnergy;
    private String typeEnergy;
    private int consumptionId;

    public Housing(double consEnergy, String typeEnergy, int consumptionId) {
        this.consEnergy = consEnergy;
        this.typeEnergy = typeEnergy;
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
}
