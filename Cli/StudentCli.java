package Cli;

import Controllers.CollegeController;
import Controllers.CourseController;
import Controllers.StudentController;
import Lists.StudentsList;
import Models.Student;

public class StudentCli extends Cli {

    private String state;
    private int college_id;
    private Student student;
    CourseController courseController;
    CollegeController collegeController;
    StudentController studentController;

    public StudentCli(String studentId) {
        System.out.println(studentId);
        this.student = new StudentsList().findByStudentId(Integer.parseInt(studentId));
        courseController = new CourseController(this.student);
        collegeController = new CollegeController(this.student);
        studentController = new StudentController(this.student);
    }

    @Override
    public void processCommand(String command) {
        String[] parts = command.split(" ");
        switch (parts[0]) {
            case "colleges":
                collegeController.getColleges();
                break;
            case "get":
                collegeController.getCourses(Integer.parseInt(parts[1]));
                break;
            case "1":
                studentController.getCourses();
                break;
            case "courses":
                courseController.getCourses();
                break;
            case "add":
                studentController.addCourse(Integer.parseInt(parts[1]));
                break;
            case "remove":
                studentController.removeCourse(Integer.parseInt(parts[1]));
                break;
            case "0":
                collegeController.getColleges();
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                System.out.println("Unknown command");
                break;

        }

    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCollege_id(int college_id) {
        this.college_id = college_id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getState() {
        return state;
    }

    public int getCollege_id() {
        return college_id;
    }

    public Student getStudent() {
        return student;
    }

}
