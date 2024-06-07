import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private SmartHome smartHome;

    @Override
    public void start(Stage primaryStage) {
        smartHome = new SmartHome("1", "My Smart Home");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        VBox roomBox = new VBox(10);
        roomBox.setPadding(new Insets(10));
        roomBox.setAlignment(Pos.CENTER_LEFT);

        TextField roomNameField = new TextField();
        roomNameField.setPromptText("Enter Room Name");
        Button addRoomButton = new Button("Add Room");
        addRoomButton.setOnAction(e -> {
            String roomName = roomNameField.getText().trim();
            if (!roomName.isEmpty()) {
                Room room = new Room(Integer.toString(smartHome.getRooms().size() + 1), roomName);
                smartHome.addRoom(room);
                roomBox.getChildren().add(new Label(room.getRoomName()));
            }
            roomNameField.clear();
        });

        VBox deviceBox = new VBox(10);
        deviceBox.setPadding(new Insets(10));
        deviceBox.setAlignment(Pos.CENTER_LEFT);

        TextField deviceNameField = new TextField();
        deviceNameField.setPromptText("Enter Device Name");
        Button addDeviceButton = new Button("Add Device");
        addDeviceButton.setOnAction(e -> {
            String deviceName = deviceNameField.getText().trim();
            if (!deviceName.isEmpty()) {
                SmartLight device = new SmartLight(Integer.toString(deviceBox.getChildren().size() + 1), deviceName);
                roomBox.getChildren().forEach(roomLabel -> {
                    String roomName = ((Label) roomLabel).getText();
                    Room room = smartHome.findRoomByName(roomName);
                    if (room != null) {
                        room.addDevice(device);
                    }
                });
                CheckBox statusCheckBox = new CheckBox("Off");
                statusCheckBox.setOnAction(event -> {
                    device.setStatus(statusCheckBox.isSelected());
                    statusCheckBox.setText(device.getStatus() ? "On" : "Off");
                });
                deviceBox.getChildren().add(new HBox(10, new Label(device.getDeviceName()), statusCheckBox));
            }
            deviceNameField.clear();
        });

        root.setLeft(new VBox(10, roomNameField, addRoomButton, new Label("Rooms:"), roomBox));
        root.setCenter(new VBox(10, deviceNameField, addDeviceButton, new Label("Devices:"), deviceBox));

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setTitle("Smart Home Control");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

abstract class Device {
    private String deviceId;
    private String deviceName;
    private boolean status;

    public Device(String deviceId, String deviceName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.status = false;
    }

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

    public abstract void turnOn();
    public abstract void turnOff();
}

class SmartLight extends Device {
    public SmartLight(String deviceId, String deviceName) {
        super(deviceId, deviceName);
    }

    public void turnOn() {
        setStatus(true);
    }

    public void turnOff() {
        setStatus(false);
    }
}

class SmartThermostat extends Device {
    public SmartThermostat(String deviceId, String deviceName) {
        super(deviceId, deviceName);
    }

    public void turnOn() {
        setStatus(true);
    }

    public void turnOff() {
        setStatus(false);
    }
}

class User {
    private String userId;
    private String userName;
    private List<Room> managedRooms;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.managedRooms = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void addRoom(Room room) {
        managedRooms.add(room);
    }

    public void removeRoom(Room room) {
        managedRooms.remove(room);
    }

    public void controlDevice(Device device, boolean turnOn) {
        if (turnOn) {
            device.turnOn();
        } else {
            device.turnOff();
        }
    }
}

class Room {
    private String roomId;
    private String roomName;
    private List<Device> devices;

    public Room(String roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.devices = new ArrayList<>();
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

    public String getRoomName() {
        return roomName;
    }

    public List<Device> getDevices() {
        return devices;
    }
}


class SmartHome {
    private String homeId;
    private String homeName;
    private List<Room> rooms;
    private List<User> users;

    public SmartHome(String homeId, String homeName) {
        this.homeId = homeId;
        this.homeName = homeName;
        this.rooms = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public Room findRoomByName(String roomName) {
        for (Room room : rooms) {
            if (room.getRoomName().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getHomeDetails() {
        String str = "Home ID: " + homeId + ", Home Name: " + homeName + "\nRooms:\n";
        for (Room room : rooms) {
            str += room.getRoomDetails() + "\n";
        }
        str += "Users:\n";
        for (User user : users) {
            str += user.getUserId() + " - " + user.getUserName() + "\n";
        }
        return str;

    }

}