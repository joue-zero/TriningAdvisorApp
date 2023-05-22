package view;
import java.util.Scanner;
import Entities.User;

public class UserLogin {
    public User getUser() {
        User user = new User();
        String s;
        emailDisplay();
        s = emailInput();
        user.setEmail(s);
        passwordDisplay();
        s = passwordInput();
        user.setPassword(s);
        return user;
    }
    private void emailDisplay() {
        System.out.println("Enter Email:");
    }
    private String emailInput() {
        Scanner userName = new Scanner(System.in);
        return userName.nextLine();
    }
    private void passwordDisplay() {
        System.out.println("Enter Password: ");
    }
    private String  passwordInput() {
        Scanner password = new Scanner(System.in);
        return password.nextLine();
    }
    public void verifiedDisplay() {
        System.out.println("Account verified");
    }
    public void notVerifiedDisplay() {
        System.out.println("Please, check the User Name entered or Password!");
    }
}
