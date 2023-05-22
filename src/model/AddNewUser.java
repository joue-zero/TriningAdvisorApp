package model;

import Entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddNewUser extends DatabaseConnector {


    public static boolean insert(User user) {

        try {
            Connection connection = getConnection();
            PreparedStatement statement = null;
            if (connection != null) {
                String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    return true;
                }
            } else {
                System.out.println("Failed to establish a database connection.");
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
