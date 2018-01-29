/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwe.as;

/**
 *
 * @author 
 */
public abstract class User {
    private String name;
    private int UID;
    private String passwordHash;
    private String realName;
    private String emailAddress;
    
    public void User(String name, String password, String realName, String emailAddress)
    {
        this.name = name;
        this.passwordHash = password;
        this.realName = realName;
        this.emailAddress = emailAddress;
    }
    
    public void modifyName(String newName)
    {
        this.name = newName;
    }
    
    public void modifyPasswordHash(String newHash)
    {
        this.passwordHash = newHash;
    }
    
    public void modifyRealName(String newName)
    {
        this.realName = newName;
    }
    
    public void modifyEmailAddress(String newAddress)
    {
        this.emailAddress = newAddress;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getUID()
    {
        return UID;
    }
    
    public String getRealName()
    {
        return realName;
    }
    
    public String getEmailAddress()
    {
        return emailAddress;
    }
    
    public boolean authenticate(String hash)
    {
        // TODO
        return false;
    }
}
