import java.util.ArrayList;
import java.util.List;

public class SmartHome {
    // Attributes
    private String homeId;
    private String homeName;
    private List<Room> rooms;
    private List<User> users;

    // Constructor
    public SmartHome(String homeId, String homeName) {
        this.homeId = homeId;
        this.homeName = homeName;
        this.rooms = new ArrayList<Room>();
        this.users = new ArrayList<User>();
    }

    // Methods
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
