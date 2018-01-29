/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwe.as;

import java.util.Date;

/**
 *
 * @author 
 */
public class RoomApplication {
    private Room room;
    private Date date;
    private int duration;
    private Student student;
    
    public void RoomApplication(Room room, Date date, int duration, Student student)
    {
        this.room = room;
        this.date = date;
        this.duration = duration;
        this.student = student;
    }
    
    public void approveApplication()
    {
        
    }
    
    public void refuseApplication()
    {
        
    }
    
    public Room getRoom()
    {
        return this.room;
    }
    
    public Date getDate()
    {
        return this.date;
    }
    
    public int getDuration()
    {
        return this.duration;
    }
    
    public Student getStudent()
    {
        return this.student;
    }
    
    public Date getEndDate()
    {
        // TODO
        return this.date;
    }
}
