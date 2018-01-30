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
    private String studentNumber;
    private int accountLevel;
    
    public void User(String name, String password, String realName, String emailAddress)
    {
        this.name = name;
        this.passwordHash = password;
        this.realName = realName;
        this.emailAddress = emailAddress;
    }
    
    public void User(int UID, String name, String realName, String emailAddress, String passwordHash, int accountLevel, String studentNumber)
    {
        this.UID = UID;
        this.name = name;
        this.realName = realName;
        this.emailAddress = emailAddress;
        this.passwordHash = passwordHash;
        this.accountLevel = accountLevel;
        this.studentNumber = studentNumber;
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
    
    public void modifyStudentNumber(String newNumber)
    {
        this.studentNumber = newNumber;
    }
    
    public void modifyAccountLevel(int newLevel)
    {
        this.accountLevel = newLevel;
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
    
    public String getStudentNumber()
    {
        return this.studentNumber;
    }
    
    public int getAccountLevel()
    {
        return this.accountLevel;
    }
    
    public boolean authenticate(String hash)
    {
        // TODO
        return false;
    }
    
    public String toString()
    {
        return "User [UID='" + this.UID +
                "', name='"+ this.name +
                "', realName='" + this.realName +
                "', emailAddress='" + this.emailAddress +
                "', passwordHash='" + this.passwordHash +
                "', accountLevel='" + this.accountLevel +
                "', studentNumber='" + this.studentNumber +
                "']";
    }
}
