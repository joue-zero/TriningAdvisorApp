package model;

import Entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FetchAllUsers extends DatabaseConnector {

    public List<User> retrieveDataFromDatabase() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

                // Process the result set
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");

                    String password = resultSet.getString("password");

                    String email = resultSet.getString("email");

                    int role = resultSet.getInt("role");

                    User user = new User(id, name, password, email, role);
                    users.add(user);
                }

                // close the resources
                resultSet.close();
                statement.close();
                connection.close();
            } else {
                System.out.println("Failed to establish a database connection.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return users;
    }

}
