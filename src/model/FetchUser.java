package model;

import Entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FetchUser extends DatabaseConnector {

    public User getUserById(int id) {
        User user = new User();
        try {
            Connection connection = getConnection();
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            if (connection != null) {
                String sql = "SELECT * FROM users WHERE id = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setUserName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getInt("role"));
                } else {
                    System.out.println("No user found with the given ID.");
                }
            } else {
                System.out.println("Failed to establish a database connection.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    public static User getUserByEmail(String email) {
        User user = null;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            if (connection != null) {
                String sql = "SELECT * FROM users WHERE email = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, email);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUserName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getInt("role"));
                }
            } else {
                System.out.println("Failed to establish a database connection.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }
}
