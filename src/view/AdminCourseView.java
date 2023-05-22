package view;

import Entities.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminCourseView {
    Course course = new Course();
    public int options() {
        Scanner in = new Scanner(System.in);
        System.out.println("1. Add Course");
        System.out.println("2. Edit Course");
        System.out.println("Otherwise Exit");
        return in.nextInt();
    }

    public Course add(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the course code");
        course.setCode(in.nextLine());
        System.out.println("Please enter the course name");
        course.setName(in.nextLine());
        return course;
    }
    public String getCode(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the course code");
        return in.nextLine();
    }
    public int edit(){
        Scanner in = new Scanner(System.in);
        System.out.println("1. Change Prerequisites");
        System.out.println("2. Add Assets");
        System.out.println("Otherwise Exit");
        return in.nextInt();
    }
    public int getRole() {
        System.out.println("1. Quiz");
        System.out.println("2. Exam");
        System.out.println("3. Material");
        Scanner in = new Scanner(System.in);
        int role = in.nextInt();
        while(role < 1 || role > 3) {
            System.out.println("Please enter a valid role");
            role = in.nextInt();
        }
        return role;
    }
    public String material(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the Material Content:");
        return in.nextLine();
    }
    public List<String> prerequisites(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the number of prerequisites");
        int nCourses = in.nextInt();
        List<String> prerequisites = new ArrayList<>();
        System.out.println("Enter Course Codes");
        for(int i = 1; i <= nCourses; i++) {
            System.out.println("Prerequisites number: " + i);
            Scanner in2 = new Scanner(System.in);
            String code = in2.nextLine();
            prerequisites.add(code);
        }
        System.out.println("Only Valid Course Codes are added as Prerequisites");
        return prerequisites;
    }
}
