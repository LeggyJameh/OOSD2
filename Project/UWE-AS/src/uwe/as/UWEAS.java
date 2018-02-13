package uwe.as;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Adwait Chhetri (15021047), George Jones (STUDENT NUMBER), Jamie
 * Mills (16004255)
 */
public class UWEAS {

    private static Data_Cache data_cache;
    private static Properties properties;
    public static User currentUser;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        data_cache = new Data_Cache();      
        DB_Controller.data_cache = data_cache;
        currentUser = null;

        try {
            DB_Controller.OpenConnection();
            DB_Controller.getApplications();
            DB_Controller.getHalls();
            DB_Controller.getLeases();
            DB_Controller.getRooms();
            DB_Controller.getUsers();
        } catch (SQLException ex) {
            System.out.print("UWEAS.main() produced the following error:");
            System.out.print(ex);
        }

        // Examples for gathering data
        List<Room> rooms = data_cache.getRooms();
        List<Lease> leases = data_cache.getLeases();
        if (rooms != null && leases != null) {
            if (!rooms.isEmpty() && !leases.isEmpty()) {
                Room currentRoom = rooms.get(0);

                for (Lease l : leases) {
                    if (currentRoom.getLeases().contains(l.getUID())) {
                        User currentUser = data_cache.getUser(l.getStudentUID());

                        if (currentUser != null) {
                            DateFormat df = new SimpleDateFormat();
                            System.out.println("Room " + currentRoom.getNumber()
                                    + " has lease " + l.getLeaseNumber()
                                    + " for " + l.getDuration()
                                    + " months starting: " + df.format(l.getStartDate())
                                    + " with student: " + currentUser.getName()
                                    + " (" + currentUser.getStudentNumber() + ")");
                        }
                    }
                }
            }
        }
        // TODO code application logic here
    }

    public UWEAS() {
        loadPropertiesFile();
    }

    public void loadPropertiesFile() {
        properties = new Properties();
        try {

            InputStream input = getClass().getResourceAsStream("config.properties");
            properties.load(input);
        } catch (IOException ex) {

        }
    }

    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
