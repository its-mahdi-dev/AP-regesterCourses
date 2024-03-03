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
        adminView = new AdminView();
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
            adminView.showErrorMessage("college not found");
            return;
        }
        Map<String, String> map = getCourseInput();
        if (map == null) {
            AdminView.showMessage("add course cancled");
            return;
        }
        Course newCourse = new Course(coursesList.findNewId(), map.get("name"), Integer.parseInt(map.get("units")),
                college_id, Integer.parseInt(map.get("code")), map.get("type"), Integer.parseInt(map.get("group")),
                map.get("teacher"), Integer.parseInt(map.get("capacity")), new ArrayList<>(),
                collegesList.getTime(map.get("time")), collegesList.getTime(map.get("exam")));
        message = "course succesfully added";
        coursesList.addCourse(newCourse);
        college.addCourse(newCourse.getId());
        collegesList.updateList();
        coursesList.updateList();
        adminView.showSuccessMessage(message);
    }

    public Map<String, String> getCourseInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter course name: ");
        String name = sc.nextLine();
        if (name.equals("back")) {

            return null;
        }
        System.out.println("Enter course code: ");
        String code = sc.nextLine();
        if (code.equals("back")) {

            return null;
        }
        System.out.println("Enter course units: ");
        String units = sc.nextLine();
        if (units.equals("back")) {

            return null;
        }
        System.out.println("Enter course capacity: ");
        String capacity = sc.nextLine();
        if (capacity.equals("back")) {

            return null;
        }
        System.out.println("Enter course type (exclusive/public): ");
        String type = sc.nextLine();
        if (type.equals("back")) {

            return null;
        }
        System.out.println("Enter course group: ");
        String group = sc.nextLine();
        if (group.equals("back")) {

            return null;
        }
        System.out.println("Enter course teacher: ");
        String teacher = sc.nextLine();
        if (teacher.equals("back")) {

            return null;
        }
        System.out.println("Enter course exam time (27/02/1402:5~7): ");
        String exam = sc.nextLine();
        if (exam.equals("back")) {

            return null;
        }
        System.out.println("Enter course class time (sunday:5~7-monday:3~5): ");
        String time = sc.nextLine();
        if (time.equals("back")) {

            return null;
        }

        Map<String, String> map = Map.of("name", name, "code", code, "units", units, "capacity", capacity, "type", type,
                "group", group, "teacher", teacher, "exam", exam, "time", time);
        return map;
    }

    public void removeCourse(int id, int college_id) {
        Course course = coursesList.findOne(id);
        if (course == null) {
            studentView.showErrorMessage("course not found");
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
        studentView.showSuccessMessage("course removed successfully");
    }

    public void addCapacity(int id, int college_id) {
        Course course = coursesList.findOne(id);
        CollegesList collegesList = new CollegesList();
        College college = collegesList.findOne(college_id);
        if (college == null) {
            adminView.showErrorMessage("college not found");
            return;
        }
        if (course == null) {
            adminView.showErrorMessage("course not found");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter new capacity: ");
        String capacity = sc.nextLine();
        if (capacity.equals("back")) {
            return;
        }
        course.setCapacity(Integer.parseInt(capacity));
        coursesList.updateList();

        adminView.showSuccessMessage("capacity added successfully");
    }

    public void addStudent(int id) {
        Course course = coursesList.findOne(id);
        if (course == null) {
            studentView.showErrorMessage("course not found");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter students id: ");
        String students = sc.nextLine();
        if (students.equals("back")) {
            AdminView.showMessage("back to main menu");
            return;
        }

        String[] arr;
        if (students.contains(",")) {
            arr = students.split(",");
        } else {
            arr = new String[] { students };
        }
        StudentsList studentsList = new StudentsList();
        for (String student_id : arr) {
            if (!isInteger(student_id)) {
                studentView.showErrorMessage("invalid student id: " + student_id);
                return;
            }
            Student student = studentsList.findByStudentId(Integer.parseInt(student_id));
            if (student == null) {
                studentView.showErrorMessage("student " + student_id + " not found");
                return;
            }
            if (student.getCoursesId().contains(course.getId())) {
                studentView.showErrorMessage("student " + student_id + " already added in course");
                return;
            }
            student.addCourse(id);
            course.addStudent(student.getId());
        }

        coursesList.updateList();
        studentsList.updateList();
        studentView.showSuccessMessage("student added successfully");
    }

    public void removeStudent(int id) {
        Course course = coursesList.findOne(id);
        if (course == null) {
            studentView.showErrorMessage("course not found");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student id: ");
        String students = sc.nextLine();
        if (students.equals("back")) {
            return;
        }

        String[] arr;
        if (students.contains(",")) {
            arr = students.split(",");
        } else {
            arr = new String[] { students };
        }
        StudentsList studentsList = new StudentsList();
        for (String student_id : arr) {
            if (!isInteger(student_id)) {
                studentView.showErrorMessage("invalid student id: " + student_id);
                return;
            }
            Student student = studentsList.findByStudentId(Integer.parseInt(student_id));
            if (student == null) {
                studentView.showErrorMessage("student " + student_id + " not found");
                return;
            }
            if (!student.getCoursesId().contains(course.getId())) {
                studentView.showErrorMessage("student " + student_id + " not found in course");
                return;
            }
            student.removeCourse(id);
            course.removeStudent(student.getId());
        }

        coursesList.updateList();
        studentsList.updateList();
        studentView.showSuccessMessage("student added successfully");

    }

    public void getStudents(int id) {
        Course course = coursesList.findOne(id);
        if (course == null) {
            studentView.showErrorMessage("course not found");
            return;
        }
        List<Student> students = course.getStudents();
        adminView.showStudents(students);
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
