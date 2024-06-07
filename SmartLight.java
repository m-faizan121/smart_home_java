
public class SmartLight extends Device {
    // Constructor
    public SmartLight(String deviceId, String deviceName) {
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