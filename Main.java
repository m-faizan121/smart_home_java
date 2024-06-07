public class Main {
    public static void main(String[] args) {
        try {
            // A new SmartHome object named mySmartHome is created
            SmartHome mySmartHome = new SmartHome("1", "My Smart Home");
            // Room objects for the living room and kitchen are created and added to the
            // smart home
            Room livingRoom = new Room("1", "Living Room");
            Room kitchen = new Room("2", "Kitchen");
            mySmartHome.addRoom(livingRoom);
            mySmartHome.addRoom(kitchen);

            // SmartLight and SmartThermostat objects are created.
            // These devices are added to the appropriate rooms
            SmartLight livingRoomLight = new SmartLight("1", "Living Room Light");
            SmartThermostat livingRoomThermostat = new SmartThermostat("2", "Living Room Thermostat");
            livingRoom.addDevice(livingRoomLight);
            livingRoom.addDevice(livingRoomThermostat);

            // User object is created and assigned to manage the created rooms
            User user = new User("1", "John");
            user.addRoom(livingRoom);
            user.addRoom(kitchen);

            // Perform multi-threading
            Thread deviceMonitorThread = new Thread(new DeviceMonitor(livingRoomLight));
            Thread userCommandThread = new Thread(new UserCommandProcessor(user, livingRoomThermostat, true));
            deviceMonitorThread.start();
            userCommandThread.start();

            // Join the threads
            try {
                deviceMonitorThread.join();
                userCommandThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /*
             * The main thread waits for the monitoring and command processing threads to
             * complete using join().
             * The final state of the smart home is printed to the console
             */
            System.out.println(mySmartHome.getHomeDetails());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
