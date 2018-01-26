
package uwe.as;

import java.util.List;

/**
 *
 * @author Adwait Chhetri (STUDENT NUMBER), George Jones (STUDENT NUMBER), Jamie Mills (16004255)
 */
public class UWEAS {

    List<Hall> halls;
    List<User> users;
    List<Application> applications;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public void addHall(Hall hall)
    {
        if (halls.contains(hall) == false)
        {
            halls.add(hall);
        }
    }
    
    public void removeHall(Hall hall)
    {
        if (halls.contains(hall))
        {
            halls.remove(hall);
        }
    }
    
    public void addUser(User user)
    {
        if (users.contains(user) == false)
        {
            users.add(user);
        }
    }
    
    public void removeUser(User user)
    {
        if (users.contains(user))
        {
            users.remove(user);
        }
    }
    
    public void addApplication(Application application)
    {
        if (applications.contains(application) == false)
        {
            applications.add(application);
        }
    }
    
    public void removeApplication(Application application)
    {
        if (applications.contains(application))
        {
            applications.remove(application);
        }
    }
    
    public List<Hall> getHalls()
    {
        return halls;
    }
    
    public List<User> getUsers()
    {
        return users;
    }
    
    public List<Application> getApplications()
    {
        return applications;
    }
}
