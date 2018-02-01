
package uwe.as;


/**
 *
 * @author
 * Adwait Chhetri (STUDENT NUMBER),
 * George Jones (STUDENT NUMBER),
 * Jamie Mills (16004255)
 */
public class UWEAS {

    private static Data_Cache data_cache;
    private static DB_Controller db_controller;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        data_cache = new Data_Cache();
        db_controller = new DB_Controller();
        // TODO code application logic here
        
    }
}
