/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwe.as;

import java.util.Date;
import java.util.List;

/**
 *
 * @author 
 */
public class Room {
    private String number;
    private int rentRate;
    private CleanState cleanliness;
    private List<Lease> leases;
    
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
    
    public boolean addLease(Lease lease)
    {
       // TODO add calculation logic
        // Return false if cannot fit in lease due to another being in that time slot
        // return true if added successfully.
        return false;
    }
    
    public boolean removeLease(Lease lease)
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
    
    public List<Lease> getLeases()
    {
        return this.leases;
    }
    
    public Lease getLeaseForDate(Date date)
    {
        // TODO
        return null;
    }
    
    public Lease getLeaseForStudent(Student student)
    {
        // TODO
        return null;
    }
}

