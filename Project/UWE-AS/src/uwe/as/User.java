package uwe.as;

/**
 *
 * @author Jamie Mills (16004255)
 * (Completed 31/01/2018)
 */
public class User {
    private String name;
    private int UID;
    private String passwordHash;
    private String realName;
    private String emailAddress;
    private String studentNumber;
    private int accountLevel;
    private static Data_Cache data_cache;
    
    // General use constructor
    public void User(String name, String password, String realName, String emailAddress, Data_Cache data_cache)
    {
        this.name = name;
        this.passwordHash = password;
        this.realName = realName;
        this.emailAddress = emailAddress;
        this.data_cache = data_cache;
        data_cache.addUser(this);
    }
    
    // DB Controller constructor
    public void User(int UID, String name, String realName, String emailAddress, String passwordHash, int accountLevel, String studentNumber, Data_Cache data_cache)
    {
        this.UID = UID;
        this.name = name;
        this.realName = realName;
        this.emailAddress = emailAddress;
        this.passwordHash = passwordHash;
        this.accountLevel = accountLevel;
        this.studentNumber = studentNumber;
        this.data_cache = data_cache;
        data_cache.addUser(this);
    }
    
    public void modifyName(String newName)
    {
        this.name = newName;
        data_cache.updateUser(this);
    }
    
    public void modifyPasswordHash(String newHash)
    {
        this.passwordHash = newHash;
        data_cache.updateUser(this);
    }
    
    public void modifyRealName(String newName)
    {
        this.realName = newName;
        data_cache.updateUser(this);
    }
    
    public void modifyEmailAddress(String newAddress)
    {
        this.emailAddress = newAddress;
        data_cache.updateUser(this);
    }
    
    public void modifyStudentNumber(String newNumber)
    {
        this.studentNumber = newNumber;
        data_cache.updateUser(this);
    }
    
    public void modifyAccountLevel(int newLevel)
    {
        this.accountLevel = newLevel;
        data_cache.updateUser(this);
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
