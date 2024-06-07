import java.util.Date;

public class EnergyConsumption {
    // Attributes
    private String deviceId;
    private double energyUsed;
    private Date timestamp;

    // Constructor
    public EnergyConsumption(String deviceId, double energyUsed) {
        this.deviceId = deviceId;
        this.energyUsed = energyUsed;
        this.timestamp = new Date();
    }

    // Methods
    public void logEnergyUsage(double energy) {
        this.energyUsed += energy;
        this.timestamp = new Date();
    }

    public String getEnergyReport() {
        return "Device ID: " + deviceId + ", Energy Used: " + energyUsed + " kWh, Timestamp: " + timestamp.toString();
    }
}
