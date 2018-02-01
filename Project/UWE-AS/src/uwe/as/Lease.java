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
    
    public void Lease(int number, int studentUID)
    {
        this.leaseNumber = number;
        this.studentUID = studentUID;
    }
    
    public void modifyDuration(int newDuration)
    {
        this.duration = newDuration;
    }
    
    public void modifyStartDate(Date newDate)
    {
        this.startDate = newDate;
    }
    
    public String toString()
    {
        // TODO
        return "";
    }
}
