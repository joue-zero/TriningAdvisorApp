package model;

import Entities.Asset;
import Entities.Course;
import Entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgressModel extends DatabaseConnector {

    // function to get all the courses that the user has completed
    public static List<Asset> getNotCompletedAssets(int user_id) throws SQLException {
            List<Asset> assessts = new ArrayList<>();
            Connection connection = getConnection();
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            if (connection != null) {
                String sql =
                        "SELECT *  FROM assets WHERE course_code " +
                                "in(SELECT course_code FROM course_enroll WHERE user_id = ?)" +
                                " and id not in (select asset_id from progress where user_id = ?) and role in(0,1)";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, user_id);
                statement.setInt(2, user_id);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Asset asset = new Asset(
                            resultSet.getString("content"),
                            resultSet.getString("course_code"),
                            resultSet.getInt("role")
                    );
                    asset.setId(resultSet.getInt("id"));
                    assessts.add(asset);
                }
            } else {
                System.out.println("Failed to establish a database connection.");
            }

        return assessts;
    }

    // Function given user ID and course code, return list of assets that are completed
    public static List<Asset> getCompletedAssets(int user_id) throws SQLException {
        List<Asset> assessts = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            String sql =
                    "SELECT *  FROM assets WHERE course_code " +
                            "in(SELECT course_code FROM course_enroll WHERE user_id = ?)" +
                            " and id in (select asset_id from progress where user_id = ?) and role in(0,1)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);
            statement.setInt(2, user_id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Asset asset = new Asset(
                        resultSet.getString("content"),
                        resultSet.getString("course_code"),
                        resultSet.getInt("role")
                );
                asset.setId(resultSet.getInt("id"));
                assessts.add(asset);
            }
        } else {
            System.out.println("Failed to establish a database connection.");
        }

        return assessts;
    }

    // fUNCTION Get completed Assest by course code and user id
    public static List<Asset> getCompletedAssets(int user_id, String course_code) throws SQLException {
        List<Asset> assessts = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            String sql =
                    "SELECT *  FROM assets WHERE course_code " +
                            "in(SELECT course_code FROM course_enroll WHERE user_id = ?)" +
                            " and id in (select asset_id from progress where user_id = ?) and role in(0,1) and course_code = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);
            statement.setInt(2, user_id);
            statement.setString(3, course_code);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Asset asset = new Asset(
                        resultSet.getString("content"),
                        resultSet.getString("course_code"),
                        resultSet.getInt("role")
                );
                asset.setId(resultSet.getInt("id"));
                assessts.add(asset);
            }
        } else {
            System.out.println("Failed to establish a database connection.");
        }

        return assessts;
    }

    // Function given user ID and course code, return list of assets that are not completed
    public static List<Asset> getNotCompletedAssets(int user_id, String course_code) throws SQLException {
        List<Asset> assets = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            String sql =
                    "SELECT *  FROM assets WHERE course_code " +
                            "in(SELECT course_code FROM course_enroll WHERE user_id = ?)" +
                            " and id not in (select asset_id from progress where user_id = ?) and role in(0,1) and course_code = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);
            statement.setInt(2, user_id);
            statement.setString(3, course_code);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Asset asset = new Asset(
                        resultSet.getString("content"),
                        resultSet.getString("course_code"),
                        resultSet.getInt("role")
                );
                asset.setId(resultSet.getInt("id"));
                assets.add(asset);
            }
        } else {
            System.out.println("Failed to establish a database connection.");
        }

        return assets;
    }

    // Get All Quizzes And Tests Which Is Not Taken By The User
    public static List<String> getCompletedCourses(int id) throws SQLException {
//        List<Course> courses = new ArrayList<>();
        List<String> courses = new ArrayList<>();
            Connection connection = getConnection();
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            if (connection != null) {
                String sql = // why?
                        "SELECT course_code FROM assets WHERE id " +
                                "IN (SELECT asset_id FROM progress WHERE user_id = ?)" +
                                "AND course_code IN (SELECT course_code FROM course_enroll WHERE user_id = ?) " +
                                "AND role IN (0, 1)" +
                                "GROUP BY course_code\n" +
                                "HAVING COUNT(*) = (SELECT COUNT(*) FROM assets AS a " +
                                   "WHERE a.course_code = assets.course_code and a.role in (0, 1))";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                statement.setInt(2, id);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    courses.add(resultSet.getString("course_code"));
                }
            } else {
                System.out.println("Failed to establish a database connection.");
            }

        return courses;
    }

    // Take Quiz Or Test
    public static void takeAsset(int user_id, int asset_id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            String sql = "INSERT INTO progress (user_id, asset_id) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);
            statement.setInt(2, asset_id);
            statement.executeUpdate();
        } else {
            System.out.println("Failed to establish a database connection.");
        }
    }
}
