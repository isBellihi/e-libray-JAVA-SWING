package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static String url = "jdbc:mysql://localhost/biblio";    
    private static String driver = "com.mysql.jdbc.Driver";   
    private static String username = "root";   
    private static String password = "";
    private static Connection con = null;
    private static String urlstring;

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            try {
            	if(con==null){
            		con = DriverManager.getConnection(url, username, password);
            		System.out.println("Database appeler");
            	}
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Erreur de connection."); 
        }
        return con;
    }
}
