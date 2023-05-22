package controller;

import Entities.Course;
import Entities.User;
import model.AddCourse;
import model.CourseModel;
import view.UserCourseView;
import view.AdminCourseView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminCourseController {
    AdminCourseView adminCourseView = new AdminCourseView();
    User user;
    Course course = new Course();

    UserCourseView userCourseView;
    public AdminCourseController(User user) throws SQLException {
        this.user = user;
        // If I'm an admin, I can add/edit courses
        int option = adminCourseView.options();
        if(option == 1){
            addCourse();
        }else if(option == 2){
            editCourse();
        }
//        Return to Main Menu
        MainMenuController mainMenuController = new MainMenuController(user);
    }
    // Add course
    public void addCourse() throws SQLException {
        course = adminCourseView.add();
        AddCourse.insert(course);
    }
    // Edit course
    public void editCourse() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String courseCode = adminCourseView.getCode();
        course = CourseModel.getCourseByCode(courseCode);
        if(course == null) {
            System.out.println("No course found with the given code.");
            AdminCourseController adminCourseController = new AdminCourseController(user);
            return;
        }
        int option = adminCourseView.edit();
        if(option == 1){
            addPrerequisites(courses);
        }
        else if(option == 2)  {
            addAssets();
        }
    }
//    To add Material to the course
    public void addAssets() throws SQLException {
        int role = adminCourseView.getRole();
        String material = adminCourseView.material();
        // Update the course in the database
        CourseModel.addAsset(course.getCode(), material, role);
    }
    public void addPrerequisites(List<Course> courses) throws SQLException {
        List<String> prerequisites = adminCourseView.prerequisites();
        int success = 0, fail = 0;
        for (String code : prerequisites) {
            Course COURSE = CourseModel.getCourseByCode(code);
            if (COURSE != null) {
                ++success;
                courses.add(COURSE);
                continue;
            }
            ++fail;
        }
        System.out.println("Success: " + success + " Fail: " + fail);
        course.setPrerequisites(courses);
        // Update the course in the database
        CourseModel.updatePrerequisites(course.getCode(), course.getPrerequisites());
    }
}
