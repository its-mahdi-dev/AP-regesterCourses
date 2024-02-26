package Cli;

import Controllers.CollegeController;
import Controllers.CourseController;
import Controllers.StudentController;

public class AdminCli extends Cli {
    CourseController courseController;
    CollegeController collegeController;
    StudentController studentController;

    private int college_id;

    public AdminCli() {
        courseController = new CourseController();
        collegeController = new CollegeController();
        studentController = new StudentController();
    }

    @Override
    public void processCommand(String command) {
        String[] parts = command.split(" ");
        switch (parts[0]) {
            case "colleges":
                college_id = 0;
                collegeController.getColleges();
                break;
            case "get":
                collegeController.getCourses(Integer.parseInt(parts[1]));
                college_id = Integer.parseInt(parts[1]);
                break;
            case "courses":
                courseController.getCourses();
                break;
            case "add":
                courseController.addCourse(college_id);
                break;
            case "addCapacity":
                courseController.addCapacity(Integer.parseInt(parts[1]), college_id);
                break;
            case "0":
                college_id = 0;
                collegeController.getColleges();
                break;
            case "remove":
                courseController.removeCourse(Integer.parseInt(parts[1]), college_id);
                break;
            case "exit":
                System.exit(0);
                break;
            case "addStudent":
                courseController.addStudent(Integer.parseInt(parts[1]));
                break;
            case "removeStudent":
                courseController.removeStudent(Integer.parseInt(parts[1]));
                break;
            default:
                System.out.println("Invalid command");
                break;
        }
    }

}
