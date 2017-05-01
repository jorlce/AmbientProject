
package com.ambient.util;
 
import com.ambient.util.Provider;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
 

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
                System.out.println("3234234234234" + ex.getMessage());
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
/*public class dbutil {
 
    private static Connection conn;
 
    public static Connection getConnection() {
        if( conn != null )  return conn;

        else {
            try {
                Class.forName( driver );
                conn = DriverManager.getConnection( connURL, username, pwd );
            } catch (Exception ex) {
                System.out.println("3234234234234" + ex.getMessage());
            }
            return conn;
        }
 */
        /*InputStream inputStream = dbutil.class.getClassLoader().getResourceAsStream( "/db.properties" );
        Properties properties = new Properties();
        try {
            properties.load( inputStream );
            String driver = properties.getProperty( "driver" );
            String url = properties.getProperty( "url" );
            String user = properties.getProperty( "user" );
            String password = properties.getProperty( "password" );
            Class.forName( driver );
            conn = DriverManager.getConnection( url, user, password );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
 
   /*     return conn;
    }
 
    public static void closeConnection( Connection toBeClosed ) {
        if( toBeClosed == null )
            return;
        try {
            toBeClosed.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}*/