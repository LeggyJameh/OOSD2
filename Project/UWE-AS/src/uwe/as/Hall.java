/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwe.as;

import java.util.List;

/**
 *
 * @author 
 */
public class Hall {
    private Warden warden;
    private String name;
    private String number;
    private String address;
    private String telephoneNumber;
    private List<Room> rooms;
    
    public void Hall(String name, String number)
    {
        if (name != null)
        {
            this.name = name;
        }
        else
        {
            this.name = "";
        }
        if (number != null)
        {
            this.number = number;
        }
        else
        {
            this.number = "";
        }
    }
    
    public void addRoom(Room room)
    {
        if (rooms.contains(room) == false)
        {
            rooms.add(room);
        }
    }
    
    public void removeRoom(Room room)
    {
        if (rooms.contains(room))
        {
            rooms.remove(room);
        }
    }
    
    public void modifyWarden(Warden warden)
    {
        if (warden != null)
        {
            this.warden = warden;
        }
    }
    
    public void modifyAddress(String address)
    {
        if (address != null)
        {
            if (!address.equals(""))
            {
                this.address = address;
            }
        }
    }
    
    public void modifyTelephone(String newNumber)
    {
        if (telephoneNumber != null)
        {
            if (!telephoneNumber.equals(""))
            {
                this.telephoneNumber = newNumber;
            }
        }
    }
    
    public void modifyName(String newName)
    {
        if (newName != null)
        {
            if (!newName.equals(""))
            {
                this.name = newName;
            }
        }
    }
    
    public void modifyNumber(String newNumber)
    {
        if (newNumber != null)
        {
            if (!newNumber.equals(""))
            {
                this.number = newNumber;
            }
        }
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getNumber()
    {
        return this.number;
    }
    
    public String getAddress()
    {
        return this.address;
    }
    
    public String getTelephoneNumber()
    {
        return this.telephoneNumber;
    }
    
    public List<Room> getRooms()
    {
        return rooms;
    }
}

