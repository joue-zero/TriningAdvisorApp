import Entities.*;
import controller.*;
import model.*;
import view.*;

import java.sql.SQLException;
import java.util.List;

public class Main  {
    public static void main(String[] args) throws SQLException {
        // Show Home Page
        while(true){
            HomePageController homePageController = new HomePageController();
//            User user = FetchUser.getUserByEmail(homePageController.getUser().getEmail());
//            MainMenuController menuController = new MainMenuController(user);
        }






        // Test Progress
//        List<Asset> as = ProgreesModel.getNotCompletedAssets(10);
////        ProgreesModel.takeAsset(10, as.get(0).getId());
//        for(Asset a: as) {
//            a.printTest();
//        }
//        System.out.println("Completed Courses");
//        for(String s:ProgreesModel.getCompletedCourses(10)) {
//            System.out.println(s);
//        }
//        ProgreesModel.takeAsset(10, as.get(0).getId());
//        System.out.println("Completed Courses");
//        ProgreesModel.takeAsset(15, 3);
//        ProgreesModel.getCompletedCourses(15);
//        for(String s:ProgreesModel.getCompletedCourses(15)) {
//            System.out.println(s);
//        }
//        for(Asset a: ProgreesModel.getNotCompletedAssets(10)) {
//            a.printTest();
//        }
    }
}

// FetchPreqByCourse.getAll(course) -> List<Course>

// List<Course> courses = FetchPreqByCourse.getAll(course);