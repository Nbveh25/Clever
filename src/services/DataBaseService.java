package services;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseService {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
    private static final String DATABASE_DRIVER = "org.postgresql.Driver";

    private static final String DATABASE_NAME = "cleverdb";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "12345";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL + DATABASE_NAME, USER_NAME, PASSWORD);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

}
