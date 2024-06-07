
public class SmartThermostat extends Device {
    // Constructor
    public SmartThermostat(String deviceId, String deviceName) {
        super(deviceId, deviceName);
    }

    // Overriden abstract methods
    public void turnOn() {
        setStatus(true);
    }

    public void turnOff() {
        setStatus(false);
    }
}