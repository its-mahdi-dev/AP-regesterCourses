package Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Lists.CollegesList;
import Lists.CoursesList;
import Lists.StudentsList;
import Models.College;
import Models.Course;
import Models.Student;
import views.AdminView;
import views.StudentView;

public class CourseController extends Controller {
    CoursesList coursesList;
    StudentView studentView;
    AdminView adminView;

    public CourseController() {
        super(true);
        coursesList = new CoursesList();
        studentView = new StudentView();
        adminView = new AdminView();
    }

    public CourseController(Student student) {
        super(student);
        coursesList = new CoursesList();
        studentView = new StudentView();
    }

    public CoursesList getCoursesList() {
        return coursesList;
    }

    public void getCourses() {
        studentView.showCourses(coursesList.getCourses());
    }

    public void addCourse(int college_id) {
        String message = null;
        CollegesList collegesList = new CollegesList();
        College college = collegesList.findOne(college_id);
        if (college == null) {
            adminView.showMessage("college not found");
            return;
        }
        Map<String, String> map = getCourseInput();
        Course newCourse = new Course(coursesList.findNewId(), map.get("name"), Integer.parseInt(map.get("units")),
                college_id, Integer.parseInt(map.get("code")), map.get("type"), Integer.parseInt(map.get("group")),
                map.get("teacher"), Integer.parseInt(map.get("capacity")), new ArrayList<>(),
                collegesList.getTime(map.get("time")), collegesList.getTime(map.get("exam")));
        message = "course succesfully added";
        coursesList.addCourse(newCourse);
        college.addCourse(newCourse.getId());
        collegesList.updateList();
        coursesList.updateList();
        studentView.showMessage(message);
    }

    public Map<String, String> getCourseInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter course name: ");
        String name = sc.nextLine();
        System.out.println("Enter course code: ");
        String code = sc.nextLine();
        System.out.println("Enter course units: ");
        String units = sc.nextLine();
        System.out.println("Enter course capacity: ");
        String capacity = sc.nextLine();
        System.out.println("Enter course type (exclusive/public): ");
        String type = sc.nextLine();
        System.out.println("Enter course group: ");
        String group = sc.nextLine();
        System.out.println("Enter course teacher: ");
        String teacher = sc.nextLine();
        System.out.println("Enter course exam time (27/02/1402:5~7): ");
        String exam = sc.nextLine();
        System.out.println("Enter course class time (sunday:5~7-monday:3~5): ");
        String time = sc.nextLine();

        Map<String, String> map = Map.of("name", name, "code", code, "units", units, "capacity", capacity, "type", type,
                "group", group, "teacher", teacher, "exam", exam, "time", time);
        return map;
    }

    public void removeCourse(int id, int college_id) {
        Course course = coursesList.findOne(id);
        if (course == null) {
            studentView.showMessage("course not found");
            return;
        }
        StudentsList studentsList = new StudentsList();
        for (Student student : studentsList.getStudents()) {
            if (student.getCoursesId().contains(id)) {
                student.removeCourse(id);
            }
        }
        coursesList.removeCourse(id);

        CollegesList collegesList = new CollegesList();
        collegesList.findOne(college_id).removeCourse(id);
        collegesList.updateList();
        coursesList.updateList();
        studentsList.updateList();
        studentView.showMessage("course removed successfully");
    }

    public void addCapacity(int id, int college_id) {
        Course course = coursesList.findOne(id);
        CollegesList collegesList = new CollegesList();
        College college = collegesList.findOne(college_id);
        if (college == null) {
            adminView.showMessage("college not found");
            return;
        }
        if (course == null) {
            adminView.showMessage("course not found");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter new capacity: ");
        int capacity = sc.nextInt();
        course.setCapacity(capacity);
        coursesList.updateList();

        adminView.showMessage("capacity added successfully");
    }
}
