package uwe.as;

import java.util.Date;

/**
 *
 * @author Jamie Mills (16004255)
 */
public class Lease {

    private int UID;
    private int roomUID;
    private int leaseNumber;
    private int duration;
    private int studentUID;
    private Date startDate;
    public static Data_Cache data_cache;

    public Lease(int number, int studentUID, int roomUID) {
        this.leaseNumber = number;
        this.studentUID = studentUID;
        this.roomUID = roomUID;
        data_cache.createLease(this);
    }

    public Lease(int UID, int roomUID, int leaseNumber, int duration, int studentUID, Date startDate) {
        this.UID = UID;
        this.roomUID = roomUID;
        this.leaseNumber = leaseNumber;
        this.duration = duration;
        this.studentUID = studentUID;
        this.startDate = startDate;
        data_cache.addLease(this);
    }

    public int getUID() {
        return this.UID;
    }

    public int getRoomUID() {
        return this.roomUID;
    }

    public int getLeaseNumber() {
        return this.leaseNumber;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getStudentUID() {
        return this.studentUID;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void modifyDuration(int newDuration) {
        this.duration = newDuration;
        data_cache.updateLease(this);
    }

    public void modifyStartDate(Date newDate) {
        this.startDate = newDate;
        data_cache.updateLease(this);
    }
}
