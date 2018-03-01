package uwe.as;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jamie Mills (16004255)
 */
public class Room {

    private int UID;
    private int hallUID;
    private String number;
    private int rentRate;
    private CleanState cleanliness;
    private List<Integer> leases;
    private String description;
    public static Data_Cache data_cache;

    public Room(String number, int rate, int hallUID) {
        if (number != null) {
            this.number = number;
        } else {
            this.number = "";
        }
        this.rentRate = rate;
        this.hallUID = hallUID;
        data_cache.createRoom(this);
    }

    public Room(int UID, int hallUID, String number, int rentRate, CleanState cleaniness, String description) {
        this.UID = UID;
        this.hallUID = hallUID;
        this.number = number;
        this.rentRate = rentRate;
        this.cleanliness = cleaniness;
        this.leases = new ArrayList<Integer>();
        this.description = description;
        data_cache.addRoom(this);
    }

    public int getUID() {
        return this.UID;
    }
    
    public int getHallUID() {
        return this.hallUID;
    }
    
   
            
    public String getNumber() {
        return this.number;
    }

    public int getRate() {
        return this.rentRate;
    }

    public CleanState getCleanliness() {
        return this.cleanliness;
    }

    public List<Integer> getLeases() {
        return this.leases;
    }

    public String getDescription() {
        return this.description;
    }

    public void changeCleanlinessState(CleanState newState) {
        this.cleanliness = newState;
        data_cache.updateRoom(this);
    }

    public void modifyRate(int newRate) {
        this.rentRate = newRate;
        data_cache.updateRoom(this);
    }
    
    public void modifyDescription(String newDescription)
    {
        this.description = newDescription;
    }

    public void addLease(int lease) {
        leases.add(lease);
    }

    public void removeLease(int lease) {
       leases.remove(lease); 
    }

    public Lease getLeaseForDate(Date date) {
        for (int l : leases)
        {
            Lease currentLease = data_cache.getLease(l);
            if (currentLease != null)
            {
                if (currentLease.getStartDate().before(date))
                {
                    Calendar calCurrent = Calendar.getInstance();
                    Calendar calAfter = Calendar.getInstance();
                    calAfter.setTime(date);
                    calCurrent.setTime(date);
                    calAfter.add(Calendar.MONTH, currentLease.getDuration());
                    if (calAfter.after(calCurrent))
                    {
                        return currentLease;
                    }
                }
            }
        }
        return null;
    }

    public Lease getLeaseForStudent(User student) {
        // TODO
        return null;
    }
}
