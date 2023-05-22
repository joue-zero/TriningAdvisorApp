package view;
import Entities.*;
import controller.Validators;

import java.util.Objects;
import java.util.Scanner;
import java.util.List;


public class UserProfileManagement {
    User user;

    public UserProfileManagement(User user) {
        this.user = user;
    }

    public int gettingStarted() {
        Scanner in = new Scanner(System.in);
        System.out.println("1. View Profile");
        System.out.println("2. Edit Profile");
        return in.nextInt();
    }

    public User edit() {
        while(true){
            promptMessage();
            Scanner in = new Scanner(System.in);
            int option = in.nextInt();
            if(option == 1){
                user.setUserName(editName());
            }else if(option == 2){
                user.setEmail(editEmail());
            }else if(option == 3) {
                user.setPassword(editPassword());
            } else {
                break;
            }
        }
        return user;
    }
    private String editName()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a new Name:");
        String name = in.nextLine();
        return name;
    }
    private String editEmail()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a new Email:");
        String email = in.nextLine();
        while(!Validators.isEmailValid(email)){
            System.out.println("Please, Enter a valid Email:");
            email = in.nextLine();
        }
        return email;
    }
    private String editPassword() {
        Scanner passScanner = new Scanner(System.in);
        String pass1, pass2;
        while (true) {
            System.out.println("Enter a new Password:");
            pass1 = passScanner.nextLine();
            System.out.println("Renter the Password:");
            pass2 = passScanner.nextLine();
            if (Objects.equals(pass1, pass2)) return pass1;
            System.out.println("The Password You Entered Doesn't Match, Please Renter The password!");
        }
    }
    void promptMessage(){
        System.out.println("1. Edit Name");
        System.out.println("2. Edit Email");
        System.out.println("3. Change Password");
        System.out.println("Otherwise Exit");
    }

    public void displayProfile(){
        System.out.println("User Name: " + user.getUserName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());
    }

    public static int chooseCourseProgress() {
        System.out.println("Enter course number to show its progress");
        System.out.println("Otherwise Exit");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public static int completedAssets(List<Asset> assets) {
        if(assets.size() == 0) {
            System.out.println("No completed coursework...");
            return 0;
        }
        System.out.println("Completed coursework");
        for(int i = 0; i < assets.size(); i++) {
            System.out.println("Coursework number " + (i+1));
            assets.get(i).printTest();
        }
        return assets.size();
    }

    public static int notCompletedAssets(List<Asset> assets) {
        if(assets.size() == 0) {
            return 0;
        }
        System.out.println("Not Completed coursework");
        for(int i = 0; i < assets.size(); i++) {
            System.out.println("Coursework number " + (i+1));
            assets.get(i).printTest();
        }
        return assets.size();
    }
}

/*
- Course
---Id -> int, Code -> string, name -> string , rating -> double
- Prequisets
--- course_id -> int , pre_course_id -> int
- Review
--- user_id -> int , course_id -> int, comment -> string
- Rate
--- user_id -> int,  course_id -> int, rate -> double
-
* */