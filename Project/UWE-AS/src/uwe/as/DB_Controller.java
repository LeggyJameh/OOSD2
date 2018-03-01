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
import java.sql.Date;

/**
 *
 * @author Jamie Mills (16004255)
 */

/* If this is generating errors, make sure you have started the database with
the StartDatabase.bat file in the project root folder. You can edit the contents
of the database using heidisql.
 */
public class DB_Controller {

    public static Data_Cache data_cache;
    private static String connectionString;
    private static Connection connection;
    private static Statement statement;

    /**
     * <b>To only be used by the UWEAS class.</b>
     * Open the database connection. Will throw exception if connection fails.
     */
    public static boolean OpenConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connectionString = "jdbc:mysql://localhost:3306/uwe-as";
            /*connectionString =
                "Database=" + UWEAS.getProperty("jdbcDatabase") +
                ";Server=" + UWEAS.getProperty("jdbcHost") +
                ":" + UWEAS.getProperty("jdbcPort") +
                ";";*/
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {
            //connection = DriverManager.getConnection(connectionString, UWEAS.getProperty("jdbcUser"), UWEAS.getProperty("jdbcPass"));
            connection = DriverManager.getConnection(connectionString, "root", "");
            statement = connection.createStatement();
            return true;
        } catch (SQLException e) {
            System.out.println("OpenConnection() produced the following error:");
            System.out.println(e);
        }
        return false;
    }

    /**
     * <b>To only be used by the UWEAS class.</b>
     * Pull all of the users from the database, into the cache.
     */
    public static void getUsers() {
        String query = "SELECT * FROM `users`;";

        try {
            if (!statement.isClosed()) {
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    // Creating the instance adds it to cache, no need to assign
                    getUserFromResult(result);
                }
            }
        } catch (SQLException e) {
            System.out.println("DB_Controller.getUsers() produced the following error:");
            System.out.println(e);
        }
    }

    /**
     * <b>To only be used by the UWEAS class.</b>
     * Pull all of the halls from the database, into the cache.
     */
    public static void getHalls() {
        String query = "SELECT * FROM `halls`;";
        List<Hall> halls = new ArrayList<Hall>();

        try {
            if (!statement.isClosed()) {
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    halls.add(getHallFromResult(result));
                }
            }

            for (Hall h : halls) {
                getHallRooms(h);
            }
        } catch (SQLException e) {
            System.out.println("DB_Controller.getHalls() produced the following error:");
            System.out.println(e);
        }
    }

    /**
     * <b>To only be used by the UWEAS class.</b>
     * Pull all of the rooms from the database, into the cache.
     */
    public static void getRooms() {
        String query = "SELECT * FROM `rooms`;";
        List<Room> rooms = new ArrayList<Room>();

        try {
            if (!statement.isClosed()) {
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    rooms.add(getRoomFromResult(result));
                }
            }

            for (Room r : rooms) {
                getRoomLeases(r);
            }
        } catch (SQLException e) {
            System.out.println("DB_Controller.getRooms() produced the following error:");
            System.out.println(e);
        }
    }

    /**
     * <b>To only be used by the UWEAS class.</b>
     * Pull all of the leases from the database, into the cache.
     */
    public static void getLeases() {
        String query = "SELECT * FROM `leases`;";

        try {
            if (!statement.isClosed()) {
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    // Creating the instance adds it to cache, no need to assign
                    getLeaseFromResult(result);
                }
            }
        } catch (SQLException e) {
            System.out.println("DB_Controller.getLeases() produced the following error:");
            System.out.println(e);
        }
    }

    /**
     * <b>To only be used by the UWEAS class.</b>
     * Pull all of the applications from the database, into the cache.
     */
    public static void getApplications() {
        String query = "SELECT * FROM `applications`;";

        try {
            if (statement != null) {
                if (!statement.isClosed()) {
                    ResultSet result = statement.executeQuery(query);
                    while (result.next()) {
                        // Creating the instance adds it to cache, no need to assign
                        getApplicationFromResult(result);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("DB_Controller.getApplications() produced the following error:");
            System.out.println(e);
        }
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Pull newly created user out of the database for DB-specific data.
     */
    public static void getUser(int UID) {
        String query = "SELECT * FROM `Users` WHERE `UID`='" + UID + "';";

        try {
            if (!statement.isClosed()) {
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    getUserFromResult(result);
                }
            }
        } catch (SQLException ex) {
            System.out.println("DB_Controller.getUser() produced the following error:");
            System.out.println(ex);
        }
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Pull newly created hall out of the database for DB-specific data.
     */
    public static void getHall(int UID) {
        String query = "SELECT * FROM `Halls` WHERE `UID`='" + UID + "';";
        Hall hall = null;

        try {
            if (!statement.isClosed()) {
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    hall = getHallFromResult(result);
                }
            }

            if (hall != null) {
                getHallRooms(hall);
            }
        } catch (SQLException ex) {
            System.out.println("DB_Controller.getHall() produced the following error:");
            System.out.println(ex);
        }
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Pull newly created room out of the database for DB-specific data.
     */
    public static void getRoom(int UID) {
        String query = "SELECT * FROM `Rooms` WHERE `UID`='" + UID + "';";
        Room room = null;

        try {
            if (!statement.isClosed()) {
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    getRoomFromResult(result);
                }
            }
            if (room != null) {
                getRoomLeases(room);
            }
        } catch (SQLException ex) {
            System.out.println("DB_Controller.getRoom() produced the following error:");
            System.out.println(ex);
        }
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Pull newly created lease out of the database for DB-specific data.
     */
    public static void getLease(int UID) {
        String query = "SELECT * FROM `Leases` WHERE `UID`='" + UID + "';";

        try {
            if (!statement.isClosed()) {
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    getLeaseFromResult(result);
                }
            }
        } catch (SQLException ex) {
            System.out.println("DB_Controller.getLease() produced the following error:");
            System.out.println(ex);
        }
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Pull newly created application out of the database for DB-specific data.
     */
    public static void getApplication(int UID) {
        String query = "SELECT * FROM `Applications` WHERE `UID`='" + UID + "';";

        try {
            if (!statement.isClosed()) {
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    getApplicationFromResult(result);
                }
            }
        } catch (SQLException ex) {
            System.out.println("DB_Controller.getApplication() produced the following error:");
            System.out.println(ex);
        }
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Update the details of the user in the database.
     */
    public static void updateUser(User user) {
        String query = " UPDATE `users` SET `Name`='" + user.getName()
                + "', `RealName`='" + user.getRealName()
                + "', `EmailAddress`='" + user.getEmailAddress()
                + "', `StudentNumber`='" + user.getStudentNumber()
                + "', `AccountLevel`='" + Integer.toString(user.getAccountLevel())
                + "' WHERE `UID`='" + Integer.toString(user.getUID()) + "';";
        
        executeNonQuery(query);
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Update the details of the hall in the database.
     */
    public static void updateHall(Hall hall) {
        String query
                = "UPDATE `halls` SET (`WardenUID`='" + Integer.toString(hall.getWardenUID())
                + "', `Name`='" + hall.getName()
                + "', `Number`='" + hall.getNumber()
                + "', `Address`='" + hall.getAddress()
                + "', `TelephoneNumber`='" + hall.getTelephoneNumber()
                + "') WHERE `UID`='" + Integer.toString(hall.getUID()) + "';";
        
        executeNonQuery(query);
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Update the details of the room in the database.
     */
    public static void updateRoom(Room room) {
        int cleanliness;
        switch (room.getCleanliness()) {
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
        String query = "UPDATE `rooms` SET (`Number`='" + room.getNumber()
                + "', `RentRate`='" + Integer.toString(room.getRate())
                + "', `Cleaniness`='" + Integer.toString(cleanliness)
                + "') WHERE `UID`='" + Integer.toString(room.getUID()) + "';";
        
        executeNonQuery(query);
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Update the details of the lease in the database.
     */
    public static void updateLease(Lease lease) {
        DateFormat df = new SimpleDateFormat();
        String date = df.format(lease.getStartDate());
        String query = "UPDATE `leases` SET `LeaseNumber`='" + Integer.toString(lease.getLeaseNumber())
                + "', `Duration`='" + Integer.toString(lease.getDuration())
                + "', `StudentUID`='" + Integer.toString(lease.getStudentUID())
                + "', `RoomUID`='" + Integer.toString(lease.getRoomUID())
                + "', `StartDate`='" + date
                + "' WHERE `UID`='" + Integer.toString(lease.getUID()) + "';";
        
        executeNonQuery(query);
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Update the details of the application in the database.
     */
    public static void updateApplication(RoomApplication application) {
        DateFormat df = new SimpleDateFormat();
        String date = df.format(application.getDate());
        String query = "UPDATE `applications` SET (`RoomUID`='" + Integer.toString(application.getRoomUID())
                + "', `Date`='" + date
                + "', `Duration`='" + Integer.toString(application.getDuration())
                + "', `StudentUID`='" + Integer.toString(application.getStudentUID())
                + "') WHERE `UID`='" + Integer.toString(application.getUID()) + "';";
        
        executeNonQuery(query);
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Create new user in the database.
     */
    public static int createUser(User user) {
        String query = "INSERT INTO `users`"
                + "(`Name`, `PasswordHash`, `RealName`,"
                + "`EmailAddress`, `StudentNumber`"
                + "`AccountLevel`) VALUES ('"
                + user.getName() + "', '"
                + user.getPasswordHash() + "', '"
                + user.getRealName() + "', '"
                + user.getEmailAddress() + "', '"
                + user.getStudentNumber() + "', '"
                + "0')";
        
        executeNonQuery(query);

        return getLastInsertID();
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Create new hall in the database.
     */
    public static int createHall(Hall hall) {
        String query = "INSERT INTO `halls`"
                + "(`Name`, `Number`, VALUES ('"
                + hall.getName() + "', '"
                + hall.getNumber() + "', ')";

        executeNonQuery(query);

        return getLastInsertID();
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Create new room in the database.
     */
    public static int createRoom(Room room) {
        String query = "INSERT INTO `rooms`"
                + "(`Number`, `RentRate`) VALUES ('"
                + room.getNumber() + "', '"
                + room.getRate() + "')";
        
        executeNonQuery(query);

        return getLastInsertID();
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Create new lease in the database.
     */
    public static int createLease(Lease lease) {
        Date date = new Date(lease.getStartDate().getTime());
        String query = "INSERT INTO `leases`"
                + "(`LeaseNumber`, `StudentUID`, `RoomUID`, `Duration`, `StartDate`) VALUES ('"
                + lease.getLeaseNumber() + "', '"
                + lease.getStudentUID() + "', '"
                + lease.getRoomUID() + "', '"
                + lease.getDuration() + "', '"
                + date.toString() + "')";
        
        executeNonQuery(query);

        return getLastInsertID();
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Create new application in the database.
     */
    public static int createApplication(RoomApplication application) {
        DateFormat df = new SimpleDateFormat();
        String date = df.format(application.getDate());
        String query = "INSERT INTO `users`"
                + "(`RoomUID`, `Date`, `Duration`,"
                + "`StudentUID` VALUES ('"
                + application.getRoomUID() + "', '"
                + date + "', '"
                + application.getDuration() + "', '"
                + application.getStudentUID() + "')";
        
        executeNonQuery(query);

        return getLastInsertID();
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Remove existing user from the database. Note that this is permanent and
     * <b> will result in data loss</b>
     */
    public static void removeUser(User user) {
        String query = "DELETE FROM `users` WHERE `UID`='" + user.getUID() + "';";
        
        executeNonQuery(query);
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Remove existing hall from the database. Note that this is permanent and
     * <b> will result in data loss</b>
     */
    public static void removeHall(Hall hall) {
        String query = "DELETE FROM `halls` WHERE `UID`='" + hall.getUID() + "';";
        
        executeNonQuery(query);
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Remove existing room from the database. Note that this is permanent and
     * <b> will result in data loss</b>
     */
    public static void removeRoom(Room room) {
        String query = "DELETE FROM `rooms` WHERE `UID`='" + room.getUID() + "';";
        
        executeNonQuery(query);
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Remove existing lease from the database. Note that this is permanent and
     * <b> will result in data loss</b>
     */
    public static void removeLease(Lease lease) {
        String query = "DELETE FROM `leases` WHERE `UID`='" + lease.getUID() + "';";
        
        executeNonQuery(query);
    }

    /**
     * <b>To only be used by the cache class.</b>
     * Remove existing application from the database. Note that this is permanent and
     * <b> will result in data loss</b>
     */
    public static void removeApplication(RoomApplication application) {
        String query = "DELETE FROM `applications` WHERE `UID`='" + application.getUID() + "';";
        
        executeNonQuery(query);
    }

    /**
     * Used to execute queries that require no return data.
     */
    private static void executeNonQuery(String query) {
        try {
            if (!statement.isClosed()) {
                statement.executeUpdate(query);
            }
        } catch (SQLException ex) {
            System.out.println("DB_Controller.executeNonQuery() produced the following error:");
            System.out.println(ex);
        }
    }

    /**
     * Used to retrieve the last UID after creating a record.
     */
    private static int getLastInsertID() {
        String query = "SELECT last_insert_id() AS 'UID'";

        try {
            statement = connection.createStatement();
            if (!statement.isClosed()) {
                ResultSet result = statement.executeQuery(query);

                result.first();
                return result.getInt("UID");
            }
        } catch (SQLException ex) {
            System.out.println("DB_Controller.getLastInsertID() produced the following error:");
            System.out.println(ex);
        }
        return -1;
    }

    /**
     * Creates user class from known user resultset.
     */
    private static User getUserFromResult(ResultSet result) {
        try {
            return new User(
                    result.getInt("UID"),
                    result.getString("Name"),
                    result.getString("RealName"),
                    result.getString("EmailAddress"),
                    result.getString("PasswordHash"),
                    result.getInt("AccountLevel"),
                    result.getString("StudentNumber"));
        } catch (SQLException ex) {
            System.out.println("DB_Controller.getUserFromResult() produced the following error:");
            System.out.println(ex);
        }
        return null;
    }

    /**
     * Creates hall class from known hall resultset.
     */
    private static Hall getHallFromResult(ResultSet result) {
        try {
            return new Hall(
                    result.getInt("UID"),
                    result.getInt("WardenUID"),
                    result.getString("Name"),
                    result.getString("Number"),
                    result.getString("Address"),
                    result.getString("TelephoneNumber"));

        } catch (SQLException ex) {
            System.out.println("DB_Controller.getHallFromResult() produced the following error:");
            System.out.println(ex);
        }
        return null;
    }

    /**
     * Get the list of UID's of rooms that are in the specified hall.
     */
    private static void getHallRooms(Hall hall) {
        try {
            if (hall != null) {
                String query = "SELECT * FROM `rooms` WHERE `HallUID`='" + hall.getUID() + "';";

                if (!statement.isClosed()) {
                    ResultSet result = statement.executeQuery(query);
                    while (result.next()) {
                        hall.addRoom(result.getInt("UID"));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("DB_Controller.getHallRooms() produced the following error:");
            System.out.println(ex);
        }
    }

    /**
     * Creates room class from known room resultset.
     */
    private static Room getRoomFromResult(ResultSet result) {
        try {
            CleanState cleanState;
            int cleanInt = result.getInt("Cleanliness");
            switch (cleanInt) {
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
            return new Room(
                    result.getInt("UID"),
                    result.getInt("HallUID"),
                    result.getString("Number"),
                    result.getInt("RentRate"),
                    cleanState,
                    result.getString("Description"));

        } catch (SQLException ex) {
            System.out.println("DB_Controller.getRoomFromResult() produced the following error:");
            System.out.println(ex);
        }
        return null;
    }

    /**
     * Get the list of UID's of leases that are in the specified room.
     */
    private static void getRoomLeases(Room room) {
        try {
            if (room != null) {
                String query = "SELECT * FROM `leases` WHERE `RoomUID`='" + room.getUID() + "';";

                if (!statement.isClosed()) {
                    ResultSet result = statement.executeQuery(query);
                    while (result.next()) {
                        room.addLease(result.getInt("UID"));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("DB_Controller.getRoomLeases() produced the following error:");
            System.out.println(ex);
        }
    }

    /**
     * Creates lease class from known lease resultset.
     */
    private static Lease getLeaseFromResult(ResultSet result) {
        try {
            return new Lease(
                    result.getInt("UID"),
                    result.getInt("RoomUID"),
                    result.getInt("LeaseNumber"),
                    result.getInt("Duration"),
                    result.getInt("StudentUID"),
                    result.getDate("StartDate")
            );
        } catch (SQLException ex) {
            System.out.println("DB_Controller.getLeaseFromResult() produced the following error:");
            System.out.println(ex);
        }
        return null;
    }

    /**
     * Creates an application class from known application resultset.
     */
    private static RoomApplication getApplicationFromResult(ResultSet result) {
        try {
            return new RoomApplication(
                    result.getInt("UID"),
                    result.getInt("RoomUID"),
                    result.getDate("Date"),
                    result.getInt("Duration"),
                    result.getInt("StudentUID")
            );
        } catch (SQLException ex) {
            System.out.println("DB_Controller.getApplicationFromResult() produced the following error:");
            System.out.println(ex);
        }
        return null;
    }
}
