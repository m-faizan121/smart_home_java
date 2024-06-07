
public abstract class Device {
    // Attributes
    private String deviceId;
    private String deviceName;
    private boolean status;

    // Constructor
    public Device(String deviceId, String deviceName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.status = false;
    }

    // Methods
    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public boolean getStatus() {
        return status;
    }

    protected void setStatus(boolean status) {
        this.status = status;
    }

    // Abstract methods
    public abstract void turnOn();

    public abstract void turnOff();
}