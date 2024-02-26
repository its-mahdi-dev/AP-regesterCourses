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
            case "1":
                studentController.getCourses();
                break;
            case "courses":
                courseController.getCourses();
                break;
            case "add":
                courseController.addCourse(college_id);
                break;
            case "remove":
                courseController.removeCourse(Integer.parseInt(parts[1]), college_id);

                break;

            default:
                break;
        }
    }

}
