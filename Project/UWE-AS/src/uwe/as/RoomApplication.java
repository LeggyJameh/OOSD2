package uwe.as;

import java.util.Date;

/**
 *
 * @author
 * (WIP 31/01/2018)
 */
public class RoomApplication {
    private int UID;
    private int roomUID;
    private Date date;
    private int duration;
    private int studentUID;
    
    public void RoomApplication(int roomUID, Date date, int duration, int studentUID)
    {
        this.roomUID = roomUID;
        this.date = date;
        this.duration = duration;
        this.studentUID = studentUID;
    }
    
    public void approveApplication()
    {
        
    }
    
    public void refuseApplication()
    {
        
    }
    
    public Date getDate()
    {
        return this.date;
    }
    
    public int getDuration()
    {
        return this.duration;
    }
    
    public Date getEndDate()
    {
        // TODO
        return this.date;
    }
    
    public String toString()
    {
        // TODO
        return "";
    }
}
