package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
    public static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/training_console_app";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    url, username, password
            );
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
}
