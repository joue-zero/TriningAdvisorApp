package controller;

import Entities.User;
import view.HomePage;
import controller.MainMenuController;

import java.sql.SQLException;

public class HomePageController {
    User user;
    public HomePageController() throws SQLException {
        //Show Home Page View
        HomePage homePage = new HomePage();
        //Get Option
        int option = homePage.getChoice();
        //Display Option
        Display(option);
        MainMenuController mainMenuController = new MainMenuController(user);
    }
    //Show 2 Option Login and Register
    public void Display(int option) throws SQLException {
        if(option == 1) {
            //Show Login Form
            UserLoginController userLoginController = new UserLoginController();
            userLoginController.login();
            user = userLoginController.getUser();
        } else if(option == 2) {
            //Show Register Form
            UserRegisterController userRegisterController = new UserRegisterController();
            user = userRegisterController.getUser();
        } else if(option == 3) {
            //Exit
            System.exit(0);
        } else {
            System.out.println("Please choose 1, 2 or 3");
            new HomePageController();
        }
    }

    public User getUser() {
        return user;
    }
}
