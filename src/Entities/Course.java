package Entities;

import java.util.List;

/*
- Course
---Id -> int, Code -> string, name -> string , rating -> double
- prerequisites
--- course_id -> int , pre_course_id -> int
- Review
--- user_id -> int , course_id -> int, comment -> string
- Rate
--- user_id -> int,  course_id -> int, rate -> double
-
* */
public class Course {
    String code = "", name = "",Review = "";
    double rating = 0;
    List<Course> prerequisites;
    List<String> reviews;
    public Course(){
    }
    public Course(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Course(String code, String name, double rating, List<Course> prerequisites, List<String> reviews) {
        this.code = code;
        this.name = name;
        this.rating = rating;
        this.prerequisites = prerequisites;
        this.reviews = reviews;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    // Print Course
    public void printCourse() {
        System.out.println("----------");
        System.out.println("Course Code: " + this.code);
        System.out.println("Course Name: " + this.name);
        System.out.println("Course Rating: " + this.rating);
        System.out.println("Course Prerequisites: ");
        for (Course course : this.prerequisites) {
            System.out.println(course.getName());
        }
        System.out.println("Course Reviews: ");
        for (String review : this.reviews) {
            System.out.println(review);
        }
        System.out.println("----------");
    }

}
