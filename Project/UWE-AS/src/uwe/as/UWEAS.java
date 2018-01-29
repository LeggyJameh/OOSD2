
package uwe.as;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author
 * Adwait Chhetri (STUDENT NUMBER),
 * George Jones (STUDENT NUMBER),
 * Jamie Mills (16004255)
 */
public class UWEAS {

    // Using list interface (cannot instantiate)
    private static List<Hall> halls;
    private static List<User> users;
    private static List<RoomApplication> applications;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Instantiate main lists
        halls = new ArrayList<Hall>();
        users = new ArrayList<User>();
        applications = new ArrayList<RoomApplication>();
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
}
