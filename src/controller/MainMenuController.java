package controller;
import Entities.User;
import view.MainMenu;

import java.sql.SQLException;

public class MainMenuController {
    User user = new User();
    public MainMenuController(User user) throws SQLException {
        this.user = user;
        MainMenu menuView = new MainMenu();
        menuView.promptMessage();
        if(user.getRole() == 0) menuView.userMessage();
        else menuView.adminMessage();
        int choice = menuView.getChoice();
        UserProfileController userProfileController = new UserProfileController(user);
        if(choice == 1) {
            userProfileController.showProfile();
        } else if(choice == 2 && user.getRole() == 0) {
//            My progress
            userProfileController.showProgress();
        } else if(choice == 2 && user.getRole() == 1) {
//            Manage Courses
            AdminCourseController adminCourseController = new AdminCourseController(user);
        } else if(choice == 3 && user.getRole() == 0) {
//            My Courses
            UserCourseController userCourseController = new UserCourseController(user);
            userCourseController.myCourses();
            MainMenuController mainMenuController = new MainMenuController(user);
        } else if(choice == 4 && user.getRole() == 0 || choice == 3 && user.getRole() == 1) {
//            Search Course
                UserCourseController userCourseController = new UserCourseController(user);
                userCourseController.searchCourse();
        } else {
//            Return the user to the home page
            HomePageController homePageController = new HomePageController();
        }
    }

}
