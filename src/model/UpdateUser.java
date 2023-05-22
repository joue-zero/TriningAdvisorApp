package model;

import Entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UpdateUser extends DatabaseConnector {


    public static boolean Update(User user) {

        try {
            Connection connection = getConnection();
            PreparedStatement statement = null;
            String sql = "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
