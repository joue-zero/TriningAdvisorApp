package view;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Entities.User;
import controller.Validators;

public class UserRegistration {

    public User getUser() {
        User user = new User();
        userNameDisplay();
        String name = userNameInput();
        String pass = password();
        String email = email();

        user.setUserName(name);
        user.setPassword(pass);
        user.setEmail(email);
        return user;
    }

    private static void userNameDisplay() {
        System.out.println("Enter a New Username:");
    }
    private static String userNameInput () {
        Scanner userName = new Scanner(System.in);
        return userName.nextLine();
    }

    private static String password() {
        Scanner passScanner = new Scanner(System.in);
        String pass1, pass2;
        while(true) {
            System.out.println("Enter a new Password:");
            pass1 = passScanner.nextLine();
            System.out.println("Renter the Password:");
            pass2 = passScanner.nextLine();
            if(Objects.equals(pass1, pass2)) return pass1;
            System.out.println("The Password You Entered Doesn't Match, Please Renter The password!");
        }
    }
    private static String email() {
        Scanner emailScanner = new Scanner(System.in);
        System.out.println("Enter a valid Email:");
        String email = emailScanner.nextLine();
        while(!Validators.isEmailValid(email)) {
            System.out.println("Please, Enter a valid Email:");
            email = emailScanner.nextLine();
        }
        return email;
    }
    public static void activated() {
        System.out.println("Your account is Activated Successfully!");
    }
}
