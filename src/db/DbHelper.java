package db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Theonlysmartboy <Theonlysmartboy@github.com>
 */
public class DbHelper {

    static Connection conn = null;
    static Statement stmt = null;

    public static void createNewDatabase(String fileName) {
        try {
            String url = "jdbc:sqlite:src/resources/database/" + fileName;
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                createTables();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private static void createTables() {
        try {
            stmt = conn.createStatement();
            String qu = "CREATE TABLE IF NOT EXISTS keys( key varchar(64) PRIMARY KEY, user text NOT NULL, organization text NOT NULL, email text NOT NULL UNIQUE, Timestamp DATETIME DEFAULT CURRENT_TIMESTAMP);";
            stmt.executeUpdate(qu);
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }

}
