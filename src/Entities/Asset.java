package Entities;

public class Asset {
    private int id;
    private String content;
    private String course_code;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Asset(String content, String course_code, int role) {
        this.id = 0;
        this.content = content;
        this.course_code = course_code;
        if(role == 0) {
            this.type = "Quiz";
        } else if (role == 1) {
            this.type = "Exam";
        }else if (role == 2) {
            this.type = "Material";
        }
    }

    public String getContent() {
        return content;
    }
    public String getCourseCode() {
        return course_code;
    }

    public String getType() {
        return type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void printTest() {
        System.out.println("----------");
        System.out.println("Content: " + content);
        System.out.println("Course Code: " + course_code);
        System.out.println("Type: " + type);
        System.out.println("----------");
    }
}
