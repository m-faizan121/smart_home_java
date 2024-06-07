
public class UserCommandProcessor implements Runnable {
    private User user;
    private Device device;
    private boolean turnOn;

    // Constructor
    public UserCommandProcessor(User user, Device device, boolean turnOn) {
        this.user = user;
        this.device = device;
        this.turnOn = turnOn;
    }

    // Method to run the thread
    public void run() {
        synchronized (device) {
            user.controlDevice(device, turnOn);
            System.out.println("User " + user.getUserName() + " has turned " + (turnOn ? "on" : "off") + " the device " + device.getDeviceName());
        }
    }
}