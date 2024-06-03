package ems_final;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionE {
    private static final String URL = "jdbc:mysql://localhost:3306/ EMS";
    private static final String USER = "root";
    private static final String PASSWORD = "Mike20601627";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
