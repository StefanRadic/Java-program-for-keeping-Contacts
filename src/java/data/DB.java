package data;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Stefan
 * 
 * Java DB class that will make a connection for the database
 */
public class DB {
    
    private static DB instance;
    private String konekcija = "jdbc:derby://localhost:1527/Kontakti";
    
    private Connection con;
    
    private DB() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(konekcija, "administrator","admin123");
        } 
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
     public static DB getInstance() {
        if(instance == null) {
            instance = new DB();
        }
        return instance;
    }
     
     public synchronized Connection getConnection() {
         return con;
     }
    
    
}
