package model;

import Entities.Course;
import Entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddCourse extends DatabaseConnector {


    public static boolean insert(Course course) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        if (connection != null) {
            String sql = "INSERT INTO course (name, code) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, course.getName());
            statement.setString(2, course.getCode());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        connection.close();
        return false;
    }

}
