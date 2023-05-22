package controller;

import Entities.User;
import model.FetchAllUsers;
import model.FetchUser;
import view.MainMenu;
import view.UserLogin;

import java.util.List;

public class UserLoginController {
    private User user;
    private UserLogin userLogin;
    public void login() {
        // Show User Login Page
        userLogin = new UserLogin();
        // Get The Current USer
        user = userLogin.getUser();
        // Login and Redirect to User Profile
        check();
    }

    // Login and Redirect to User Profile
    private void check() {
        // Check if the User is Registered
        if (isRegistered()) {
            // Show User Profile
            user = FetchUser.getUserByEmail(user.getEmail());
            userLogin.verifiedDisplay();

        } else {
            // Show Error Message
            userLogin.notVerifiedDisplay();
            login();
        }
    }

    public boolean isRegistered() {
        // Fetch All Users
        FetchAllUsers fetchAllUsers = new FetchAllUsers();
        // Get All Users
        List<User> users = fetchAllUsers.retrieveDataFromDatabase();
        for(User USER: users) {
            // Check if the User is Registered
            if(USER.equals(user)) {
                return true;
            }
        }
        return false;
    }

    public User getUser() {
        return user;
    }
}
