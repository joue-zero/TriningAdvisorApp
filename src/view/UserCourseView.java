package view;
import Entities.*;

import java.util.Scanner;

public class    UserCourseView {

    public int search() {
        Scanner in = new Scanner(System.in);
        System.out.println("Search by:");
        System.out.println("1. Course Code");
        System.out.println("2. Name");
        System.out.println("3. Minimum Ratings (greater than or equal)");
        System.out.println("4. Show All Courses");
        System.out.println("Otherwise Exit");
        return in.nextInt();
    }
    public String searchByCode() {
        System.out.println("Enter Course Code");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    public String searchByName() {
        System.out.println("Enter Course Name");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    public double searchByRating() {
        System.out.println("Enter Minimum Rating");
        Scanner in = new Scanner(System.in);
        return in.nextDouble();
    }
    public int enroll() {
        System.out.println("Enroll => Course number as shown above");
        System.out.println("Exit   => Any number that is not mentioned above (0)");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    public void enrolled(String code) {
        System.out.println("You have enrolled in course code: " + code);
    }

//    Withdraw or track progress at a course
    public int myCoursesAction() {
        System.out.println("1. Withdraw");
        System.out.println("2. Interact");
        System.out.println("3. Request Certificate");
        System.out.println("Otherwise Exit");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

//    Interact with a course
    public int interact() {
        System.out.println("Interact with Course:");
        System.out.println("1. Take a Quiz | Exam | Assignment");
        System.out.println("2. View Materials");
        System.out.println("3. Watch Videos");
        System.out.println("4. Rate");
        System.out.println("5. Add Review");
        System.out.println("Otherwise Exit");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public void watchVideos(String courseCode) {
        System.out.println("Watching Videos For Course: " + courseCode + "...");
    }

    public void viewMaterials(String courseCode) {
        System.out.println("Viewing Materials For Course: " + courseCode + "...");
    }

    public int takeClasswork() {
        System.out.println("Enter Quiz | Exam number to solve");
        System.out.println("Otherwise Exit");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public void takenClasswork(String type, int num) {
        System.out.println(type + " number " + num);
        System.out.println("Is taken successfully!");
    }

    public double addRate() {
        System.out.println("Enter a rate between 0 and 5:");
        Scanner in = new Scanner(System.in);
        double rate = in.nextDouble();
        if(rate < 0 || rate > 5) rate = addRate();
        return rate;
    }

    public String writeReview(String courseCode) {
        System.out.println("Write your review:");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void certified(Course course, String name) {
        System.out.println("Certificate for course completion");
        System.out.println("By Training Advisor");
        System.out.println("To " + name);
        System.out.println("For his appreciated work in course: " + course.getName() + "with Code: " + course.getCode());
    }

    public static void notCertified() {
        System.out.println("The course is not completed!");
    }
}