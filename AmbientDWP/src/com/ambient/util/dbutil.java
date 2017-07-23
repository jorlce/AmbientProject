
package com.ambient.util;
 
import com.ambient.util.Provider;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
 
//Connection with the MySql Database Server using Provider values
public class dbutil implements Provider {

    private static Connection conn = null;
    
    public static Connection getConnection() {
        if ( conn != null )
            return conn;
        else {
            try {
               Class.forName( driver ).newInstance();
               // conn = DriverManager.getConnection( connURL, username, pwd );
            	 conn = DriverManager.getConnection(connURL);
            } catch (Exception ex) {
                System.out.println("Error connection" + ex.getMessage());
            }
            return conn;
        }
    }
    
    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
