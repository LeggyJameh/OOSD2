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
public class Lease {
    
    private int leaseNumber;
    private int duration;
    private Student student;
    private Date startDate;
    
    public void Lease(int number, Student student)
    {
        this.leaseNumber = number;
        this.student = student;
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
