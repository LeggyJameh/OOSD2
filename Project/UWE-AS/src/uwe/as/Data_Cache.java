/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwe.as;

import java.util.ArrayList;
import java.util.List;

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
    
    public void Data_Cache()
    {
        halls = new ArrayList<Hall>();
        leases = new ArrayList<Lease>();
        rooms = new ArrayList<Room>();
        applications = new ArrayList<RoomApplication>();
        users = new ArrayList<User>();
    }
    
    // Adds an unique hall to the hall list
    public void addHall(Hall hall)
    {
        if (halls.contains(hall) == false)
        {
            halls.add(hall);
        }
    }
    
    // Removes an existing hall from the hall list
    public void removeHall(Hall hall)
    {
        if (halls.contains(hall))
        {
            halls.remove(hall);
        }
    }
    
    public void addLease(Lease lease)
    {
        if (leases.contains(lease) == false)
        {
            leases.add(lease);
        }
    }
    
    public void removeLease(Lease lease)
    {
        if (leases.contains(lease))
        {
            leases.remove(lease);
        }
    }
    
    public void addRoom(Room room)
    {
        if (rooms.contains(room) == false)
        {
            rooms.add(room);
        }
    }
    
    public void removeRoom (Room room)
    {
        if (rooms.contains(room))
        {
            rooms.remove(room);
        }
    }
    
    // Adds an unique user to the user list
    public void addUser(User user)
    {
        if (users.contains(user) == false)
        {
            users.add(user);
        }
    }
    
    // Removes an existing user from the user list
    public void removeUser(User user)
    {
        if (users.contains(user))
        {
            users.remove(user);
        }
    }
    
    // Adds an unique application to the application list
    public void addApplication(RoomApplication application)
    {
        if (applications.contains(application) == false)
        {
            applications.add(application);
        }
    }
    
    // Removes an existing application from the application list
    public void removeApplication(RoomApplication application)
    {
        if (applications.contains(application))
        {
            applications.remove(application);
        }
    }
    
    // Returns byref the hall list
    public List<Hall> getHalls()
    {
        return halls;
    }
    
    // Returns byref the user list
    public List<User> getUsers()
    {
        return users;
    }
    
    // Returns byref the application list
    public List<RoomApplication> getApplications()
    {
        return applications;
    }
    
    public List<Lease> getLeases()
    {
        return leases;
    }
    
    public List<Room> getRooms()
    {
        return rooms;
    }
    
    public void updateUser(User user)
    {
        // TODO: Send updates to jpa controller
    }
    
    public void updateHall(Hall hall)
    {
        // TODO: Send updates to jpa controller
    }
    
    public void updateLease(Lease lease)
    {
        // TODO: Send updates to jpa controller
    }
    
    public void updateRoom(Room room)
    {
        // TODO: Send updates to jpa controller
    }
    
    public void updateRoomApplication(RoomApplication application)
    {
        // TODO: Send updates to jpa controller
    }
}
