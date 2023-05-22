package controller;

import Entities.User;
import model.FetchUser;

public class Validators {

    static public boolean isEmailValid(String email) {
        if (email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            User user = FetchUser.getUserByEmail(email);
            return user == null;
        }else {
            return false;
        }
    }
}
