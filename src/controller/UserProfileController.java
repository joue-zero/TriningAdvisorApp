package controller;
import Entities.Course;
import Entities.User;
import model.CourseModel;
import model.FetchUser;
import model.ProgressModel;
import model.UpdateUser;
import view.UserProfileManagement;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class UserProfileController {
    User user;
    public UserProfileController(User USER) {
        this.user = USER;
    }
    public void showProfile() throws SQLException {
        UserProfileManagement userProfile = new UserProfileManagement(user);
        int option = userProfile.gettingStarted();
        if(option == 1){
            userProfile.displayProfile();
        }else{
            user = userProfile.edit();
            if(UpdateUser.Update(user)){
                System.out.println("User Updated Successfully");
            }else{
                System.out.println("User Update Failed");
            }
        }
        MainMenuController mainMenuController = new MainMenuController(user);
    }

//    Show user progress
    public void showProgress() throws SQLException {
        List<Course> courses = CourseModel.getMyCourses(user);
        if(courses.size() == 0) {
            System.out.println("You are not enrolled in any course");
            MainMenuController mainMenuController = new MainMenuController(user);
            return;
        }
        for(int i = 0; i < courses.size(); i++) {
            System.out.println("Course number " + (i+1));
            courses.get(i).printCourse();
        }
        int index = UserProfileManagement.chooseCourseProgress(); index--;
        if(index >= 0 && index < courses.size()) {
            int completed = UserProfileManagement.completedAssets(ProgressModel.getCompletedAssets(user.getId(), courses.get(index).getCode()));
            int notCompleted = UserProfileManagement.notCompletedAssets(ProgressModel.getNotCompletedAssets(user.getId(), courses.get(index).getCode()));
            if(completed + notCompleted != 0)
                System.out.println("Course completion = " + (((double) completed / ((double) completed + (double) notCompleted)) * 100) + "%");
            else System.out.println("No Course Work");
        }
        MainMenuController mainMenuController = new MainMenuController(user);
    }

    public User getUser() {
        return user;
    }
}