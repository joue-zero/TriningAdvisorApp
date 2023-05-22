package controller;

import Entities.*;

import model.CourseModel;
import model.ProgressModel;
import view.UserCourseView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static model.CourseModel.*;

public class UserCourseController {
    User user;
    UserCourseView courseView = new UserCourseView();
    public UserCourseController(User user) {
        this.user = user;
    }
    // Search for a course
    public void searchCourse() throws SQLException {
        int option = courseView.search();
        if(option == 1) {
            searchByCode();
        } else if (option == 2) {
            searchByName();
        } else if (option == 3) {
            searchByRating();
        }
        else if(option == 4){
            searchAll();
        }
        else {
            MainMenuController mainMenuController = new MainMenuController(user);
        }
    }
    private void searchAll() throws SQLException{
        System.out.println("All Courses:");
        List<Course> courses = CourseModel.getAllCourses();
        for (int i = 0; i < courses.size(); i++) {
            System.out.println("Course number: " + (i+1));
            courses.get(i).printCourse();
        }
        if(user.getRole() == 1) {
            MainMenuController mainMenuController = new MainMenuController(user);
            return;
        }
        int index = courseView.enroll();
        if(index >= 1 && index <= courses.size()) {
            enrollCourse(courses.get(index-1).getCode());
        }

        MainMenuController mainMenuController = new MainMenuController(user);
    }
    private void searchByCode() throws SQLException {
        String code = courseView.searchByCode();
        Course course = CourseModel.getCourseByCode(code);
        System.out.println("Course number " + 1);
        if(course == null) {
            System.out.println("Course not found");
            MainMenuController mainMenuController = new MainMenuController(user);
            return;
        }
        course.printCourse();
        if(user.getRole() == 1) {
            MainMenuController mainMenuController = new MainMenuController(user);
            return;
        }
        int index = courseView.enroll();
        if(index == 1) {
            enrollCourse(course.getCode());
        }
        MainMenuController mainMenuController = new MainMenuController(user);
    }
    private void searchByName() throws SQLException {
        String name = courseView.searchByName();
        List<Course> courses = CourseModel.getCourseByName(name);
        for(int i = 0; i < courses.size(); i++) {
            System.out.println("Course number: " + (i+1));
            courses.get(i).printCourse();
        }
        if(user.getRole() == 1) {
            MainMenuController mainMenuController = new MainMenuController(user);
            return;
        }
        int index = courseView.enroll();
        if(index >= 1 && index <= courses.size()) {
            enrollCourse(courses.get(index-1).getCode());
        }
        MainMenuController mainMenuController = new MainMenuController(user);
    }
    private void searchByRating() throws SQLException {
        double rating = courseView.searchByRating();
        List<Course> courses = CourseModel.getCoursesByRating(rating);
        for(int i = 0; i < courses.size(); i++) {
            System.out.println("Course number " + (i+1));
            courses.get(i).printCourse();
        }
        if(user.getRole() == 1) {
            MainMenuController mainMenuController = new MainMenuController(user);
            return;
        }
        int index = courseView.enroll();
        if(index >= 1 && index <= courses.size()) {
            enrollCourse(courses.get(index-1).getCode());
        }
        MainMenuController mainMenuController = new MainMenuController(user);
    }


    // View All Courses
    public void myCourses() throws SQLException {
        List<Course> courses = CourseModel.getMyCourses(user);
        if(courses.size() == 0) {
            System.out.println("You are not enrolled in any course");
            MainMenuController mainMenuController = new MainMenuController(user);
            return;
        }
        for (int i = 0; i < courses.size(); i++) {
            System.out.println("Course number " + (i+1));
            courses.get(i).printCourse();
        }
        int option = courseView.myCoursesAction();
        Scanner in = new Scanner(System.in);
        if(option == 1 || option == 2 || option == 3) {
            System.out.println("Enter Course number as mentioned above");
            System.out.println("Otherwise Exit:");
            int index = in.nextInt();
            if (index >= 1 && index <= courses.size()) {
                if(option == 1) {
                    withdrawCourse(courses.get(index - 1).getCode());
                } else if (option == 2) {
                    courseInteractions(courses.get(index - 1).getCode());
                } else {
                    requestCertificate(courses.get(index - 1).getCode());
                }
            }
        }
        MainMenuController mainMenuController = new MainMenuController(user);
    }

    public void requestCertificate(String courseCode) throws SQLException {
        List<String> completedCourses = ProgressModel.getCompletedCourses(user.getId());
        boolean flag = false;
        for(String completedCourse : completedCourses) {
            if(Objects.equals(completedCourse, courseCode)) {
                flag = true;
                UserCourseView.certified(CourseModel.getCourseByCode(courseCode), user.getUserName());
            }
        }
        if(!flag) {
            UserCourseView.notCertified();
        }
    }

    public void courseInteractions(String courseCode) throws SQLException {
        int option = courseView.interact();
        if(option == 1) {
            takeClasswork(courseCode);
        } else if (option == 2) {
            courseView.viewMaterials(courseCode);
            List<Asset> assets = getMaterialsByCorseCode(courseCode);
            boolean flag = false;
            for(int i = 0; i < assets.size(); i++, flag = true) {
                System.out.println("Material number " + (i+1));
                assets.get(i).printTest();
            }
            if(!flag) {
                System.out.println("No Materials Available");
            }
            courseInteractions(courseCode);
        } else if (option == 3) {
            courseView.watchVideos(courseCode);
            courseInteractions(courseCode);
        } else if(option == 4) {
            rateCourse(courseCode, courseView.addRate());
            courseInteractions(courseCode);
        } else if(option == 5) {
            addReview(courseCode, courseView.writeReview(courseCode));
            courseInteractions(courseCode);
        }
    }

    // Withdraw from a course
    private void withdrawCourse(String courseCode) throws SQLException {
        if(CourseModel.withdrawCourse(courseCode, user.getId())){
            System.out.println("Withdrawn Successfully");
        } else {
            System.out.println("Withdrawn Failed");
        }
    }

    private void takeClasswork(String courseCode) throws SQLException {
        List<Asset> assets = getTestByCourseCode(courseCode);
        List<Asset> classwork = new ArrayList<>();
        int counter = 1;
        if(assets.size() == 0) {
            System.out.println("No classwork to solve");
            return;
        }
        for(Asset asset : assets) {
            if(Objects.equals(asset.getType(), "Quiz") || Objects.equals(asset.getType(), "Exam") || Objects.equals(asset.getType(), "Assignment")) {
                System.out.println("Classwork number: " + (counter++));
                asset.printTest();
                classwork.add(asset);
            }
        }

        int index = courseView.takeClasswork(); index--;
        if(index < 0 || index >= classwork.size()) {
            MainMenuController mainMenuController = new MainMenuController(user);
        } else {
//            System.out.println(user.getId() + " " + classwork.get(index).getId());
            ProgressModel.takeAsset(user.getId(), classwork.get(index).getId());
            courseView.takenClasswork(classwork.get(index).getType(), index+1);
        }

    }
    // Enroll in a course
    private void enrollCourse(String courseCode) throws SQLException {
        CourseModel.enrollCourse(courseCode, user.getId());
        courseView.enrolled(courseCode);
    }
    // Rate a course
    private void rateCourse(String courseCode, double rate) throws SQLException {
        CourseModel.addRate(courseCode, rate, user.getId());
    }
    //  Add View to a course
    private void addReview(String courseCode, String review) throws SQLException {
        CourseModel.addReview(courseCode, review, user.getId());
    }
}
