package net.johnnyconsole.cp630.project.client.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=America/Toronto",
                                USER = "root",
                                PASSWORD = "";

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch(ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }

}
