package uwe.as;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Jamie
 */
public class Data_Cache {

    private static List<Hall> halls;
    private static List<Lease> leases;
    private static List<Room> rooms;
    private static List<RoomApplication> applications;
    private static List<User> users;

    public Data_Cache() {
        halls = new ArrayList<>();
        leases = new ArrayList<>();
        rooms = new ArrayList<>();
        applications = new ArrayList<>();
        users = new ArrayList<>();

        setupReferences();
    }

    private void setupReferences() {
        Hall.data_cache = this;
        Lease.data_cache = this;
        Room.data_cache = this;
        RoomApplication.data_cache = this;
        User.data_cache = this;
        uwe.asGUI.Login.data_cache = this;
        uwe.asGUI.MainScreen.data_cache = this;
    }

    // Adds an unique hall to the hall list
    public void addHall(Hall hall) {
        if (halls.contains(hall) == false) {
            halls.add(hall);
        }
    }

    // Removes an existing hall from the hall list
    public void removeHall(Hall hall) {
        if (halls.contains(hall)) {
            halls.remove(hall);
        }
    }

    // Creates this entry in the database and updates with correct info from the db.
    public void createHall(Hall hall) {
        int id = DB_Controller.createHall(hall);
        DB_Controller.getHall(id);
    }

    public void addLease(Lease lease) {
        if (leases.contains(lease) == false) {
            leases.add(lease);
        }
    }

    public void removeLease(Lease lease) {
        if (leases.contains(lease)) {
            leases.remove(lease);
        }
    }

    public void createLease(Lease lease) {
        int id = DB_Controller.createLease(lease);
        DB_Controller.getLease(id);
    }

    public void addRoom(Room room) {
        if (rooms.contains(room) == false) {
            rooms.add(room);
        }
    }

    public void removeRoom(Room room) {
        if (rooms.contains(room)) {
            rooms.remove(room);
        }
    }

    public void createRoom(Room room) {
        int id = DB_Controller.createRoom(room);
        DB_Controller.getRoom(id);
    }

    // Adds an unique user to the user list
    public void addUser(User user) {
        if (users.contains(user) == false) {
            users.add(user);
        }
    }

    // Removes an existing user from the user list
    public void removeUser(User user) {
        if (users.contains(user)) {
            users.remove(user);
        }
    }

    public void createUser(User user) {
        int id = DB_Controller.createUser(user);
        DB_Controller.getUser(id);
    }

    // Adds an unique application to the application list
    public void addApplication(RoomApplication application) {
        if (applications.contains(application) == false) {
            applications.add(application);
        }
    }

    // Removes an existing application from the application list
    public void removeApplication(RoomApplication application) {
        if (applications.contains(application)) {
            applications.remove(application);
        }
    }

    public void createApplication(RoomApplication application) {
        int id = DB_Controller.createApplication(application);
        DB_Controller.getApplication(id);
    }

    // Returns byref the hall list
    public List<Hall> getHalls() {
        return halls;
    }

    public Hall getHall(int UID) {
        if (halls != null) {
            List<Hall> filteredHalls = halls.stream().filter(h -> h.getUID() == UID).collect(Collectors.toList());
            if (filteredHalls != null) {
                if (!filteredHalls.isEmpty()) {
                    return filteredHalls.get(0);
                }
            }
        }
        return null;
    }

    // Returns byref the user list
    public List<User> getUsers() {
        return users;
    }

    public User getUser(int UID) {
        if (users != null) {
            List<User> filteredUsers = users.stream().filter(u -> u.getUID() == UID).collect(Collectors.toList());
            if (filteredUsers != null) {
                if (!filteredUsers.isEmpty()) {
                    return filteredUsers.get(0);
                }
            }
        }
        return null;
    }

    public User getUser(String username) {
        if (users != null) {
            List<User> filteredUsers = users.stream().filter(u -> u.getName() == username).collect(Collectors.toList());
            if (filteredUsers != null) {
                if (!filteredUsers.isEmpty()) {
                    return filteredUsers.get(0);
                }
            }
        }
        return null;
    }

    // Returns byref the application list
    public List<RoomApplication> getApplications() {
        return applications;
    }

    public RoomApplication getApplication(int UID) {
        if (applications != null) {
            List<RoomApplication> filteredApplications = applications.stream().filter(a -> a.getUID() == UID).collect(Collectors.toList());
            if (filteredApplications != null) {
                if (!filteredApplications.isEmpty()) {
                    return filteredApplications.get(0);
                }
            }
        }
        return null;
    }

    public List<Lease> getLeases() {
        return leases;
    }

    public Lease getLease(int UID) {
        if (leases != null) {
            List<Lease> filteredLeases = leases.stream().filter(l -> l.getUID() == UID).collect(Collectors.toList());
            if (filteredLeases != null) {
                if (!filteredLeases.isEmpty()) {
                    return filteredLeases.get(0);
                }
            }
        }
        return null;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Room getRoom(int UID) {
        if (rooms != null) {
            List<Room> filteredRooms = rooms.stream().filter(r -> r.getUID() == UID).collect(Collectors.toList());
            if (filteredRooms != null) {
                if (!filteredRooms.isEmpty()) {
                    return filteredRooms.get(0);
                }
            }
        }
        return null;
    }

    public void updateUser(User user) {
        DB_Controller.updateUser(user);
    }

    public void updateHall(Hall hall) {
        DB_Controller.updateHall(hall);
    }

    public void updateLease(Lease lease) {
        DB_Controller.updateLease(lease);
    }

    public void updateRoom(Room room) {
        DB_Controller.updateRoom(room);
    }

    public void updateRoomApplication(RoomApplication application) {
        DB_Controller.updateApplication(application);
    }

<<<<<<< HEAD
//    public boolean authenticate(String username, String passwordAttempt) {
//        User currentUser = this.getUser(username);
//
//        if (currentUser != null) {
//            if (PasswordStorage.ValidatePassword(passwordAttempt, currentUser.getPasswordHash())) {
//                return true;
//            }
//        }
//        return false;
//    }
=======
    public boolean authenticate(User user, char[] passwordAttempt) {
        try {
        if (user != null) {
            if (PasswordStorage.verifyPassword(passwordAttempt, user.getPasswordHash())) {
                return true;
            }
        }
        }
        catch (Exception ex) {
            System.out.println("Data_Cache.authenticate() produced the following error");
            System.out.println(ex);
        }
            
        return false;
    }
>>>>>>> da55c11afb108c33b84b602fcba5040848188e75
}
