package uwe.as;

import java.util.Date;

/**
 *
 * @author 
 * (WIP 31/01/2018)
 */
public class Lease {
    private int UID;
    private int leaseNumber;
    private int duration;
    private int studentUID;
    private Date startDate;
    public static Data_Cache data_cache;
    
    public Lease(int number, int studentUID)
    {
        this.leaseNumber = number;
        this.studentUID = studentUID;
        data_cache.createLease(this);
    }
    
    public Lease(int UID, int leaseNumber, int duration, int studentUID, Date startDate)
    {
        this.UID = UID;
        this.leaseNumber = leaseNumber;
        this.duration = duration;
        this.studentUID = studentUID;
        this.startDate = startDate;
        data_cache.addLease(this);
    }
    
    public int getUID()
    {
        return this.UID;
    }
    
    public int getLeaseNumber()
    {
        return this.leaseNumber;
    }
    
    public int getDuration()
    {
        return this.duration;
    }
    
    public int getStudentUID()
    {
        return this.studentUID;
    }
    
    public Date getStartDate()
    {
        return this.startDate;
    }
    
    public void modifyDuration(int newDuration)
    {
        this.duration = newDuration;
    }
    
    public void modifyStartDate(Date newDate)
    {
        this.startDate = newDate;
    }
}
