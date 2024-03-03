package Cli;

import Application.Application;
import Controllers.CollegeController;
import Controllers.CourseController;
import Controllers.StudentController;
import views.AdminView;

public class AdminCli extends Cli {
    CourseController courseController;
    CollegeController collegeController;
    StudentController studentController;
    private int college_id;
    private String backState;

    public AdminCli(Application application) {
        super(application);
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
                if (!isInteger(parts[1])) {
                    AdminView.showMessage("Invalid input");
                    break;
                }
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
                if (!isInteger(parts[1])) {
                    AdminView.showMessage("Invalid input");
                    break;
                }
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
            case "addStudents":
                if (!isInteger(parts[1])) {
                    AdminView.showMessage("Invalid input");
                    System.out.println("kkkk " + parts[1]);
                    break;
                }
                courseController.addStudent(Integer.parseInt(parts[1]));
                break;
            case "removeStudents":
                if (!isInteger(parts[1])) {
                    AdminView.showMessage("Invalid input");
                    break;
                }
                courseController.removeStudent(Integer.parseInt(parts[1]));
                break;
            case "getStudents":
                if (!isInteger(parts[1])) {
                    AdminView.showMessage("Invalid input");
                    break;
                }
                backState = "courses";
                courseController.getStudents(Integer.parseInt(parts[1]));
                break;
            case "back":
                if (backState.equals("courses")) {
                    collegeController.getCourses(college_id);
                    backState = "logout";
                } else if (backState.equals("logout")) {
                    getApplication().logout();
                }
                break;
            case "logout":
                getApplication().logout();
                break;
            default:
                AdminView.showMessage("Invalid command");
                break;
        }
    }

    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
