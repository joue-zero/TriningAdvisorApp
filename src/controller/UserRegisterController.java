package controller;

import Entities.User;
import model.AddNewUser;
import model.FetchAllUsers;
import view.UserRegistration;

import java.util.List;
import java.util.Objects;

public class UserRegisterController {
    UserRegistration userRegistration;
    User user;
    public UserRegisterController() {
        // Show Register Form
        userRegistration = new UserRegistration();
        // Get User Info
        user = userRegistration.getUser();
        // Register User
        register();
    }

    public void register() {
        // Register User
        while(!registerUser()){
            user = userRegistration.getUser();
        }
        // Insert User into Database
        if(addUserToDatabase()) {
            UserRegistration.activated();
        }
    }

    private boolean registerUser() {
        // Register User
        // Fetch All Users
        FetchAllUsers fetchAllUsers = new FetchAllUsers();
        // Get All Users
        List<User> users = fetchAllUsers.retrieveDataFromDatabase();
        for(User USER: users) {
            // Check if the User is Registered
            if(Objects.equals(USER.getEmail(), user.getEmail())) {
                return false;
            }
        }
        return true;
    }

    boolean addUserToDatabase() {
        // Insert User into Database
        return AddNewUser.insert(user);
    }

    public User getUser() { return user; }
}
