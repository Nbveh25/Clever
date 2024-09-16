package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBFunctions {
    public Connection connect_to_db(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);

            if (conn != null) {
                System.out.println("Connected to PostgreSQL database");
            } else {
                System.out.println("Failed to connect to PostgreSQL database");
            }

        } catch (Exception e) {
            System.out.print(e);
        }
        return conn;
    }

}
