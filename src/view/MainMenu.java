package view;

import Entities.User;

import java.util.Scanner;

public class MainMenu {
    UserProfileManagement userProfileManagement;
    User user;

    public int getChoice() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    public void promptMessage() {
        System.out.println("Main Menu");
        System.out.println("1. My Profile");
    }

    public void userMessage() {
        System.out.println("2. My Progress");
        System.out.println("3. My Courses");
        System.out.println("4. Search Course");
        System.out.println("Otherwise Exit");
    }

    public void adminMessage() {
        System.out.println("2. Manage Courses");
        System.out.println("3. Search Course");
        System.out.println("Otherwise Exit");
    }
}

/*
Main Menu
* MyProfile
* My Progress
* My Courses
* Search Course(4)
 * */