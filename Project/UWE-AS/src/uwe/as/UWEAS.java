
package uwe.as;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


/**
 *
 * @author
 * Adwait Chhetri (STUDENT NUMBER),
 * George Jones (STUDENT NUMBER),
 * Jamie Mills (16004255)
 */
public class UWEAS {

    private static Data_Cache data_cache;
    private static Properties properties;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        data_cache = new Data_Cache();
        DB_Controller.data_cache = data_cache;
        
        try
        {
            DB_Controller.OpenConnection();
            DB_Controller.getApplications();
            DB_Controller.getHalls();
            DB_Controller.getLeases();
            DB_Controller.getRooms();
            DB_Controller.getUsers();
        }
        catch (SQLException e)
        {
                    
        }
        
        List<User> users = data_cache.getUsers();
        System.out.println(users.get(0).getRealName());
        // TODO code application logic here
     
    }
    
    public UWEAS()
    {
        loadPropertiesFile();
    }
    
    public void loadPropertiesFile()
    {
        properties = new Properties();
        try
        {
            
            InputStream input = getClass().getResourceAsStream("config.properties");
            properties.load(input);
        }
        catch (IOException ex)
        {
            
        }
    }
    
    public static String getProperty(String propertyName)
    {
        return properties.getProperty(propertyName);
    }
}
