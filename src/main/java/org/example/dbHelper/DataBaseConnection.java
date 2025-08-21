package org.example.dbHelper;

import java.sql.*;

public class DataBaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/banka_users";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASSWORD);

    }

}
