package model;

import Entities.Course;
import Entities.Asset;
import Entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseModel extends DatabaseConnector {

    public static Course getCourseByCode(String code) throws SQLException {
        Course course = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            String sql = "SELECT * FROM course WHERE code = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, code);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                course = new Course(
                        resultSet.getString("code"),
                        resultSet.getString("name"),
                        getAverageRating(code),
                        getPrerequisets(code),
                        getReviews(code)
                );
            }
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        return course;
    }

    // Get All Existing Courses
    public static List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            String sql = "SELECT * FROM course";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setCode(resultSet.getString("code"));
                course.setName(resultSet.getString("name"));
                course.setRating(getAverageRating(course.getCode()));
                course.setPrerequisites(getPrerequisets(course.getCode()));
                course.setReviews(getReviews(course.getCode()));
                courses.add(course);
            }
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        return courses;
    }

    // Get All Existing Courses By Name
    public static List<Course> getCourseByName(String name) throws SQLException {
        List<Course> courses = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            String sql = "SELECT * FROM course WHERE name LIKE ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setCode(resultSet.getString("code"));
                course.setName(resultSet.getString("name"));
                course.setRating(getAverageRating(course.getCode()));
                course.setPrerequisites(getPrerequisets(course.getCode()));
                course.setReviews(getReviews(course.getCode()));
                courses.add(course);
            }
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        return courses;
    }

    // Get My Courses
    public static List<Course> getMyCourses(User user) throws SQLException {
        List<Course> courses = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            String sql = "SELECT * FROM course WHERE code IN (SELECT course_code FROM course_enroll WHERE user_id = ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setCode(resultSet.getString("code"));
                course.setName(resultSet.getString("name"));
                course.setRating(getAverageRating(course.getCode()));
                course.setPrerequisites(getPrerequisets(course.getCode()));
                course.setReviews(getReviews(course.getCode()));
                courses.add(course);
            }
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        return courses;

    }

    // Get Course by rating
    public static List<Course> getCoursesByRating(double rating) throws SQLException {
        List<Course> courses = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
//            String sql = "SELECT * FROM course WHERE rate >= ?";
            String sql = "SELECT * FROM course WHERE code in(SELECT course_code FROM rate group by course_code having AVG(value) >= ?)";
            statement = connection.prepareStatement(sql);
            statement.setDouble(1, rating);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setCode(resultSet.getString("code"));
                course.setName(resultSet.getString("name"));
                course.setRating(getAverageRating(course.getCode()));
                course.setPrerequisites(getPrerequisets(course.getCode()));
                course.setReviews(getReviews(course.getCode()));
                courses.add(course);
            }
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        return courses;
    }

    // Get Prequisets of a course by joining table course with course_prereq on course.code = course_prereq.code
    public static List<Course> getPrerequisets(String code) throws SQLException {
        List<Course> courses = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            String sql =
                    "SELECT * FROM course where code in (SELECT code_to FROM course_prereq WHERE code_from = ?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, code);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setCode(resultSet.getString("code"));
                course.setName(resultSet.getString("name"));
                course.setRating(getAverageRating(course.getCode()));
                course.setReviews(getReviews(course.getCode()));
                courses.add(course);
            }
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        return courses;
    }

    // Update course Prerequisites
    public static boolean updatePrerequisites(String code, List<Course> courses) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        if (connection != null) {
            String sql = "DELETE FROM course_prereq WHERE code_from = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, code);
            statement.executeUpdate();
            for (Course course : courses) {
                sql = "INSERT INTO course_prereq (code_from, code_to) VALUES (?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, code);
                statement.setString(2, course.getCode());
                statement.executeUpdate();
            }
            return true;
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        return false;
    }

    // Get Reviews of a course
    public static List<String> getReviews(String code) throws SQLException {
        List<String> reviews = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            String sql =
                    "SELECT * FROM review where course_code = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, code);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                reviews.add(resultSet.getString("comment"));
            }
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        return reviews;
    }
    // Add Review

    public static boolean addReview(String code, String comment, int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        if (connection != null) {
            String sql = "INSERT INTO review (user_id, course_code, comment) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, code);
            statement.setString(3, comment);
            statement.executeUpdate();
            return true;
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        return false;
    }

    // Add Rate
    public static boolean addRate(String code, double rate, int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        if (connection != null) {
            String sql = "Insert into rate (user_id, course_code, value) values (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, code);
            statement.setDouble(3, rate);
            statement.executeUpdate();
            return true;
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        return false;
    }

    // Enroll In Course With course code for user with id
    public static boolean enrollCourse(String code, int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        if (connection != null) {
            String sql = "INSERT INTO course_enroll (user_id, course_code) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, code);
            statement.executeUpdate();
            return true;
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        return false;
    }

    // Withdraw Course With course code for user with id
    public static boolean withdrawCourse(String code, int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        if (connection != null) {
            String sql = "DELETE FROM course_enroll WHERE user_id = ? AND course_code = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, code);
//            statement.executeUpdate();
            if(statement.executeUpdate() == 0) {
                return false;
            }
            return true;
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        return false;
    }

    //  Calculate the average rating of a course
    public static double getAverageRating(String code) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            String sql = "SELECT AVG(value) FROM rate WHERE course_code = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, code);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        return 0;
    }

    // Get All Test For Course
    public static List<Asset> getTestByCourseCode(String code) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        List<Asset> assets = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT * FROM assets WHERE course_code = ? and role in (1, 0)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, code);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
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

    // Get Material By Course Code

    public static List<Asset> getMaterialsByCorseCode(String code) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        List<Asset> assets = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT * FROM assets WHERE course_code = ? and role = 2";
            statement = connection.prepareStatement(sql);
            statement.setString(1, code);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Asset asset = new Asset(
                        resultSet.getString("content"),
                        resultSet.getString("course_code"),
                        resultSet.getInt("role")
                );
                assets.add(asset);
            }
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        return assets;
    }

    // Add Asset
    public static boolean addAsset(String code, String content, int role) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        if (connection != null) {
            String sql = "INSERT INTO assets (course_code, content, role) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, code);
            statement.setString(2, content);
            statement.setInt(3, role);
            statement.executeUpdate();
            return true;
        } else {
            System.out.println("Failed to establish a database connection.");
        }
        return false;
    }
}
