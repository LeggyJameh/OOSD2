package uwe.as;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jamie
 */
public class DB_Controller {
    public static Data_Cache data_cache;
    private static String connectionString;
    private static Connection connection;
    private static Statement statement;
    
    public static boolean OpenConnection() throws SQLException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connectionString="jdbc:mysql://localhost:3306/uwe-as";
            /*connectionString =
                "Database=" + UWEAS.getProperty("jdbcDatabase") +
                ";Server=" + UWEAS.getProperty("jdbcHost") +
                ":" + UWEAS.getProperty("jdbcPort") +
                ";";*/
        }
        catch (ClassNotFoundException ex)
        {
               System.out.println(ex);
        }
        
        try
        {
            //connection = DriverManager.getConnection(connectionString, UWEAS.getProperty("jdbcUser"), UWEAS.getProperty("jdbcPass"));
            connection = DriverManager.getConnection(connectionString, "root", "");
            return true;
        }
        catch (SQLException e)
        {
            System.out.println(e);       
        }
        return false;
    }
    
    public static void getUsers()
    {
        String query = "SELECT * FROM `users`;";
        
        try
        {
            statement = connection.createStatement();
            if (!statement.isClosed())
            {
                ResultSet result = statement.executeQuery(query);
                while (result.next())
                {
                    // Creating the instance adds it to cache, no need to assign
                    new User(
                    result.getInt("UID"),
                    result.getString("Name"),
                    result.getString("RealName"),
                    result.getString("EmailAddress"),
                    result.getString("PasswordHash"),
                    result.getInt("AccountLevel"),
                    result.getString("StudentNumber"));
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    
    public static void getHalls()
    {
        List<Hall> halls = new ArrayList<Hall>();
        String query = "SELECT * FROM `halls`;";
        
        try
        {
            statement = connection.createStatement();
            if (!statement.isClosed())
            {
                ResultSet result = statement.executeQuery(query);
                while (result.next())
                {
                    Hall currentHall = new Hall(
                    result.getInt("UID"),
                    result.getInt("WardenUID"),
                    result.getString("Name"),
                    result.getString("Number"),
                    result.getString("Address"),
                    result.getString("TelephoneNumber"));
                    halls.add(currentHall);
                }
                
                for (Hall h : halls)
                {
                    query = "SELECT * FROM `hallrooms` WHERE `HallUID`='" + h.getUID() + "';";
                    
                    result = statement.executeQuery(query);
                    while (result.next())
                    {
                        h.addRoom(result.getInt("RoomUID"));
                    }
                    
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }  
    }
    
    public static void getRooms()
    {
        List<Room> rooms = new ArrayList<Room>();
        String query = "SELECT * FROM `rooms`;";
        
        try
        {
            statement = connection.createStatement();
            if (!statement.isClosed())
            {
                ResultSet result = statement.executeQuery(query);
                while (result.next())
                {
                    CleanState cleanState;
                    int cleanInt = result.getInt("Cleanliness");
                    switch (cleanInt)
                    {
                        case 1:
                            cleanState = CleanState.DIRTY;
                            break;
                        case 2:
                            cleanState = CleanState.OFFLINE;
                            break;
                        default:
                            cleanState = CleanState.CLEAN;
                            break;
                    }
                    Room currentRoom = new Room(
                    result.getInt("UID"),
                    result.getString("Number"),
                    result.getInt("RentRate"),
                    cleanState);
                    
                    rooms.add(currentRoom);
                }
                
                for (Room r : rooms)
                {
                    query = "SELECT * FROM `roomleases` WHERE `RoomUID`='" + r.getUID() + "';";
                    
                    result = statement.executeQuery(query);
                    while (result.next())
                    {
                        r.addLease(result.getInt("LeaseUID"));
                    }
                    
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    
    public static void getLeases()
    {
        String query = "SELECT * FROM `leases`;";
        
        try
        {
            statement = connection.createStatement();
            if (!statement.isClosed())
            {
                ResultSet result = statement.executeQuery(query);
                while (result.next())
                {
                    // Creating the instance adds it to cache, no need to assign
                    new Lease(
                    result.getInt("UID"),
                    result.getInt("LeaseNumber"),
                    result.getInt("Duration"),
                    result.getInt("StudentUID"),
                    result.getDate("StartDate")
                    );
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    
    public static void getApplications()
    {
        String query = "SELECT * FROM `applications`;";
        
        try
        {
            statement = connection.createStatement();
            if (statement != null)
            {
                if (!statement.isClosed())
                {
                    ResultSet result = statement.executeQuery(query);
                    while (result.next())
                    {
                        // Creating the instance adds it to cache, no need to assign
                        new RoomApplication(
                        result.getInt("UID"),
                        result.getInt("RoomUID"),
                        result.getDate("Date"),
                        result.getInt("Duration"),
                        result.getInt("StudentUID")
                        );
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    
    public static void updateUser(User user)
    {
        String query = "UPDATE `users` SET (`Name`='" + user.getName() +
                "', `RealName`='" + user.getRealName() +
                "', `EmailAddress`='" + user.getEmailAddress() +
                "', `StudentNumber`='" + user.getStudentNumber() +
                "', `AccountLevel`='" + Integer.toString(user.getAccountLevel()) +
                "') WHERE `UID`='" + Integer.toString(user.getUID()) + "';";
        executeNonQuery(query);
    }
    
    public static void updateHall(Hall hall)
    {
        String query =
                "UPDATE `halls` SET (`WardenUID`='" + Integer.toString(hall.getWardenUID())+
                "', `Name`='" + hall.getName() +
                "', `Number`='" + hall.getNumber() +
                "', `Address`='" + hall.getAddress() +
                "', `TelephoneNumber`='" + hall.getTelephoneNumber()+
                "') WHERE `UID`='" + Integer.toString(hall.getUID()) + "';";
        executeNonQuery(query);
    }
    
    public static void updateRoom(Room room)
    {
        int cleanliness;
        switch (room.getCleaniness())
        {
            default:
                cleanliness = 0;
                break;
            case DIRTY:
                cleanliness = 1;
                break;
            case OFFLINE:
                cleanliness = 2;
                break;
        }
        String query = "UPDATE `rooms` SET (`Number`='" + room.getNumber() +
                "', `RentRate`='" + Integer.toString(room.getRate()) +
                "', `Cleaniness`='" + Integer.toString(cleanliness) +
                "') WHERE `UID`='" + Integer.toString(room.getUID()) + "';";
        executeNonQuery(query);
    }
    
    public static void updateLease(Lease lease)
    {
        DateFormat df = new SimpleDateFormat();
        String date = df.format(lease.getStartDate());
        String query = "UPDATE `leases` SET (`LeaseNumber`='" + Integer.toString(lease.getLeaseNumber()) +
                "', `Duration`='" + Integer.toString(lease.getDuration()) +
                "', `StudentUID`='" + Integer.toString(lease.getStudentUID()) +
                "', `StartDate`='" + date +
                "') WHERE `UID`='" + Integer.toString(lease.getUID()) + "';";
        executeNonQuery(query);
    }
    
    public static void updateApplication(RoomApplication application)
    {
        DateFormat df = new SimpleDateFormat();
        String date = df.format(application.getDate());
        String query = "UPDATE `applications` SET (`RoomUID`='" + Integer.toString(application.getRoomUID()) +
                "', `Date`='" + date +
                "', `Duration`='" + Integer.toString(application.getDuration()) +
                "', `StudentUID`='" + Integer.toString(application.getStudentUID()) +
                "') WHERE `UID`='" + Integer.toString(application.getUID()) + "';";
        executeNonQuery(query);
    }
    
    public static int createUser(User user)
    {
        String query = "INSERT INTO `users`" +
                "(`Name`, `PasswordHash`, `RealName`," +
                "`EmailAddress`, `StudentNumber`" +
                "`AccountLevel`) VALUES ('" +
                user.getName() + "', '" +
                user.getPasswordHash() + "', '" +
                user.getRealName() + "', '" +
                user.getEmailAddress() + "', '" +
                user.getStudentNumber() + "', '" +
                "0')";
        executeNonQuery(query);
        
        return getLastInsertID();
    }
    
    public static int createHall(Hall hall)
    {
       String query = "INSERT INTO `halls`" +
                "(`Name`, `Number`, VALUES ('" +
                hall.getName() + "', '" +
                hall.getNumber() + "', ')";
       
        executeNonQuery(query);
        
        return getLastInsertID(); 
    }
    
    public static int createRoom(Room room)
    {
        String query = "INSERT INTO `rooms`" +
                "(`Number`, `RentRate`) VALUES ('" +
                room.getNumber() + "', '" +
                room.getRate() + "')";
        executeNonQuery(query);
        
        return getLastInsertID();
    }
    
    public static int createLease(Lease lease)
    {
        String query = "INSERT INTO `users`" +
                "(`LeaseNumber`, `StudentUID`) VALUES ('" +
                lease.getLeaseNumber() + "', '" +
                lease.getStudentUID() + "')";
        executeNonQuery(query);
        
        return getLastInsertID();
    }
    
    public static int createApplication(RoomApplication application)
    {
        DateFormat df = new SimpleDateFormat();
        String date = df.format(application.getDate());
        String query = "INSERT INTO `users`" +
                "(`RoomUID`, `Date`, `Duration`," +
                "`StudentUID` VALUES ('" +
                application.getRoomUID() + "', '" +
                date + "', '" +
                application.getDuration() + "', '" +
                application.getStudentUID() + "')";
        executeNonQuery(query);
        
        return getLastInsertID();
    }
    
    public static void removeUser(User user)
    {
        
    }
    
    public static void removeHall(Hall hall)
    {
        
    }
    
    public static void removeRoom(Room room)
    {
        
    }
    
    public static void removeLease(Lease lease)
    {
        
    }
    
    public static void removeApplication(RoomApplication application)
    {
        
    }
    
    private static void executeNonQuery(String query)
    {
        try
        {
            statement = connection.createStatement();
            if (!statement.isClosed())
            {  
                statement.executeUpdate(query);
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
    }

    private static int getLastInsertID()
    {
        String query = "SELECT last_insert_id()";
        
        try
        {
            statement = connection.createStatement();
            if (!statement.isClosed())
            {
                ResultSet result = statement.executeQuery(query);
                
                result.first();
                return result.getInt(0);
            }
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
        return -1;
    }
}
