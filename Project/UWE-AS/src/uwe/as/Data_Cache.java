package uwe.as;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Jamie Mills (16004255)
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

    /**
     * Adds the specific single reference of an instantiated data_cache to all
     * data and UI classes.
     */
    private void setupReferences() {
        Hall.data_cache = this;
        Lease.data_cache = this;
        Room.data_cache = this;
        RoomApplication.data_cache = this;
        User.data_cache = this;
        uwe.asGUI.Login.data_cache = this;
        uwe.asGUI.MainScreen.data_cache = this;
        uwe.asGUI.CreateLease.data_cache = this;
        uwe.asGUI.CreateRoom.data_cache = this;
        uwe.asGUI.CreateApplication.data_cache = this;
        uwe.asGUI.ViewApplications.data_cache = this;
    }

    /**
     * <b>To only be used by DB_Controller</b>
     * Adds a hall to the data_cache list.
     */
    public void addHall(Hall hall) {
        if (halls.contains(hall) == false) {
            halls.add(hall);
        }
    }

    /**
     * <b>Use with caution, will remove data permanently.</b>
     * Removes a hall from the data_cache and database.
     */
    public void removeHall(Hall hall) {
        List<Room> roomsToRemove = new ArrayList<>();
        
        if (halls.contains(hall)) {
            for (Room r : rooms) {
                if (r.getHallUID() == hall.getUID()) {
                    roomsToRemove.add(r);
                }
            }
            
            for (Room r : roomsToRemove)
            {
                removeRoom(r);
            }
            
            // Mark for garbage collection
            roomsToRemove.clear();
            roomsToRemove = null;
            
            halls.remove(hall);
            DB_Controller.removeHall(hall);
        }
    }

    /**
     * Creates a new hall. This will add it to the database as well as the
     * data_cache. Do not use the existing reference after calling. Re-pull hall
     * class from data_cache.
     */
    public void createHall(Hall hall) {
        int id = DB_Controller.createHall(hall);
        DB_Controller.getHall(id);
    }

    /**
     * <b>To only be used by DB_Controller</b>
     * Adds a lease to the data_cache list.
     */
    public void addLease(Lease lease) {
        if (leases.contains(lease) == false) {
            leases.add(lease);
        }
    }

    /**
     * <b>Use with caution, will remove data permanently.</b>
     * Removes a lease from the data_cache and database.
     */
    public void removeLease(Lease lease) {
        if (leases.contains(lease)) {
            leases.remove(lease);
            DB_Controller.removeLease(lease);
        }
    }

    /**
     * Creates a new lease. This will add it to the database as well as the
     * data_cache. Do not use the existing reference after calling. Re-pull
     * lease class from data_cache.
     */
    public void createLease(Lease lease) {
        int id = DB_Controller.createLease(lease);
        DB_Controller.getLease(id);
    }

    /**
     * <b>To only be used by DB_Controller</b>
     * Adds a room to the data_cache list.
     */
    public void addRoom(Room room) {
        if (rooms.contains(room) == false) {
            rooms.add(room);
        }
    }

    /**
     * <b>Use with caution, will remove data permanently.</b>
     * Removes a room from the data_cache and database.
     */
    public void removeRoom(Room room) {
        List<Lease> leasesToRemove = new ArrayList<>();
        List<RoomApplication> applicationsToRemove = new ArrayList<>();
        
        if (rooms.contains(room)) {
            for (Lease l : leases) {
                if (l.getRoomUID() == room.getUID()) {
                    leasesToRemove.add(l);
                }
            }
            for (RoomApplication ra : applications) {
                if (ra.getRoomUID() == room.getUID()) {
                    applicationsToRemove.add(ra);
                }
            }
            
            for (Lease l : leasesToRemove)
            {
                removeLease(l);
            }
            
            for (RoomApplication ra : applicationsToRemove) {
                removeApplication(ra);
            }
            
            // Mark for garbage collection
            leasesToRemove.clear();
            leasesToRemove = null;
            applicationsToRemove.clear();
            applicationsToRemove = null;
            
            rooms.remove(room);
            DB_Controller.removeRoom(room);
        }
    }

    /**
     * Creates a new room. This will add it to the database as well as the
     * data_cache. Do not use the existing reference after calling. Re-pull room
     * class from data_cache.
     */
    public void createRoom(Room room) {
        int id = DB_Controller.createRoom(room);
        DB_Controller.getRoom(id);
    }

    /**
     * <b>To only be used by DB_Controller</b>
     * Adds a user to the data_cache list.
     */
    public void addUser(User user) {
        if (users.contains(user) == false) {
            users.add(user);
        }
    }

    /**
     * <b>Use with caution, will remove data permanently.</b>
     * Removes a user from the data_cache and database.
     */
    public void removeUser(User user) {
        if (users.contains(user)) {
            users.remove(user);
            DB_Controller.removeUser(user);
        }
    }

    /**
     * Creates a new user. This will add it to the database as well as the
     * data_cache. Do not use the existing reference after calling. Re-pull user
     * class from data_cache.
     */
    public void createUser(User user) {
        int id = DB_Controller.createUser(user);
        DB_Controller.getUser(id);
    }

    /**
     * <b>To only be used by DB_Controller</b>
     * Adds an application to the data_cache list.
     */
    public void addApplication(RoomApplication application) {
        if (applications.contains(application) == false) {
            applications.add(application);
        }
    }

    /**
     * <b>Use with caution, will remove data permanently.</b>
     * Removes an application from the data_cache and database.
     */
    public void removeApplication(RoomApplication application) {
        if (applications.contains(application)) {
            applications.remove(application);
            DB_Controller.removeApplication(application);
        }
    }

    /**
     * Creates a new application. This will add it to the database as well as
     * the data_cache. Do not use the existing reference after calling. Re-pull
     * application class from data_cache.
     */
    public void createApplication(RoomApplication application) {
        int id = DB_Controller.createApplication(application);
        DB_Controller.getApplication(id);
    }

    /**
     * Returns a full list of all halls.
     */
    public List<Hall> getHalls() {
        return halls;
    }

    /**
     * Find a hall by it's UID.
     */
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

    /**
     * Returns a full list of all users.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Find a user by it's UID.
     */
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

    /**
     * Find a user by it's username.
     *
     * @param username The user's name, <b>in uppercase</b>.
     */
    public User getUser(String username) {
        if (users != null) {
            List<User> filteredUsers = users.stream().filter(u -> u.getNameUpper().equals(username)).collect(Collectors.toList());
            if (filteredUsers != null) {
                if (!filteredUsers.isEmpty()) {
                    return filteredUsers.get(0);
                }
            }
        }
        return null;
    }

    /**
     * Returns a full list of all applications.
     */
    public List<RoomApplication> getApplications() {
        return applications;
    }

    /**
     * Find an application by it's UID.
     */
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

    /**
     * Returns a full list of all leases.
     */
    public List<Lease> getLeases() {
        return leases;
    }

    /**
     * Find a lease by it's UID.
     */
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

    /**
     * Returns a full list of all rooms.
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Find a room by it's UID.
     */
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

    /**
     * <b>To only be used by the user class.</b>
     * Tell the cache to update the user in the database.
     */
    public void updateUser(User user) {
        DB_Controller.updateUser(user);
    }

    /**
     * <b>To only be used by the hall class.</b>
     * Tell the cache to update the hall in the database.
     */
    public void updateHall(Hall hall) {
        DB_Controller.updateHall(hall);
    }

    /**
     * <b>To only be used by the lease class.</b>
     * Tell the cache to update the lease in the database.
     */
    public void updateLease(Lease lease) {
        DB_Controller.updateLease(lease);
    }

    /**
     * <b>To only be used by the room class.</b>
     * Tell the cache to update the room in the database.
     */
    public void updateRoom(Room room) {
        DB_Controller.updateRoom(room);
    }

    /**
     * <b>To only be used by the roomApplication class.</b>
     * Tell the cache to update the application in the database.
     */
    public void updateRoomApplication(RoomApplication application) {
        DB_Controller.updateApplication(application);
    }

    /**
     * Authenticate the user.
     *
     * @param user The user that they are trying to authenticate for, can be
     * null.
     * @param passwordAttempt The password that they have entered.
     * @return Returns true if password correct for given user. Will return
     * false if user is null or password incorrect.
     */
    public boolean authenticate(User user, char[] passwordAttempt) {
        try {
            if (user != null) {
                if (PasswordStorage.verifyPassword(passwordAttempt, user.getPasswordHash())) {
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println("Data_Cache.authenticate() produced the following error");
            System.out.println(ex);
        }

        return false;
    }
}
