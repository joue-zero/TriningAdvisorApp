package view;
import java.util.Scanner;

public class HomePage {

    public HomePage() {
        displayHomePage();
    }

    public void displayHomePage() {
        System.out.println("Welcome to the Training Advisor System");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
    }
    public int getChoice() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
}
