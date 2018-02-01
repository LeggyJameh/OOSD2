package uwe.as;

import java.util.Date;
import java.util.List;

/**
 *
 * @author 
 * (WIP 31/01/2018)
 */
public class Room {
    private int UID;
    private String number;
    private int rentRate;
    private CleanState cleanliness;
    private List<Integer> leases;
    
    public void Room(String number, int rate)
    {
        if (number != null)
        {
            this.number = number;
        }
        else
        {
            this.number = "";
        }
        this.rentRate = rate;
    }
    
    public void changeCleaninessState(CleanState newState)
    {
        this.cleanliness = newState;
    }
    
    public void modifyRate(int newRate)
    {
        this.rentRate = newRate;
    }
    
    public boolean addLease(int lease)
    {
       // TODO add calculation logic
        // Return false if cannot fit in lease due to another being in that time slot
        // return true if added successfully.
        return false;
    }
    
    public boolean removeLease(int lease)
    {
        // TODO
        return false;
    }
    
    public String getNumber()
    {
        return this.number;
    }
    
    public int getRate()
    {
        return this.rentRate;
    }
    
    public CleanState getCleaniness()
    {
        return this.cleanliness;
    }
    
    public List<Integer> getLeases()
    {
        return this.leases;
    }
    
    public Lease getLeaseForDate(Date date)
    {
        // TODO
        return null;
    }
    
    public Lease getLeaseForStudent(User student)
    {
        // TODO
        return null;
    }
    
    public String toString()
    {
        // TODO
        return "";
    }
}

