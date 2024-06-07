
public class DeviceMonitor implements Runnable {
    // Attributes
    private Device device;

    // Constructor
    public DeviceMonitor(Device device) {
        this.device = device;
    }

    // Method to run the thread
    public void run() {
        while (true) {
            synchronized (device) {
                System.out.println("Device Monitor: " + device.getDeviceName() + " - Status: "
                        + (device.getStatus() ? "On" : "Off"));
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}