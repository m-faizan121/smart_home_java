
import java.util.ArrayList;
import java.util.List;

public class Room {
    private String roomId;
    private String roomName;
    private List<Device> devices;

    // Constructor
    public Room(String roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.devices = new ArrayList<Device>();
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void removeDevice(Device device) {
        devices.remove(device);
    }

    public String getRoomDetails() {
        String str = "Room ID: " + roomId + ", Room Name: " + roomName + "\nDevices:\n";
        for (Device device : devices) {
            str += device.getDeviceId() + " - " + device.getDeviceName() + " - " + (device.getStatus() ? "On" : "Off")
                    + "\n";
        }
        return str;
    }
}
