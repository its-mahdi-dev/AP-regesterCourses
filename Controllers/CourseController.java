package Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Lists.CollegesList;
import Lists.CoursesList;
import Lists.StudentsList;
import Models.College;
import Models.Course;
import Models.General;
import Models.Specialized;
import Models.Student;
import views.AdminView;
import views.StudentView;

public class CourseController extends Controller {
    StudentView studentView;
    AdminView adminView = new AdminView();

    public CourseController() {
        super(true);
        studentView = new StudentView();
    }

    public CourseController(Student student) {
        super(student);
        studentView = new StudentView();
    }

    public CoursesList getCoursesList() {
        return new CoursesList();
    }

    public void getCourses() {
        studentView.showCourses(new CoursesList().getCourses());
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
        try {
            Course newCourse;
            if (map.get("type").equals("general")) {
                newCourse = new General(new CoursesList().findNewId(), map.get("name"),
                        Integer.parseInt(map.get("units")),
                        college_id, Integer.parseInt(map.get("code")), Integer.parseInt(map.get("group")),
                        map.get("teacher"), Integer.parseInt(map.get("capacity")), new ArrayList<>(),
                        collegesList.getTime(map.get("time")), collegesList.getTime(map.get("exam")));
            } else {
                newCourse = new Specialized(new CoursesList().findNewId(), map.get("name"),
                        Integer.parseInt(map.get("units")),
                        college_id, Integer.parseInt(map.get("code")), Integer.parseInt(map.get("group")),
                        map.get("teacher"), Integer.parseInt(map.get("capacity")), new ArrayList<>(),
                        collegesList.getTime(map.get("time")), collegesList.getTime(map.get("exam")));
            }
            message = "course succesfully added";
            CoursesList coursesList = new CoursesList();
            coursesList.addCourse(newCourse);
            college.addCourse(newCourse.getId());
            collegesList.updateList();
            coursesList.updateList();
            adminView.showSuccessMessage(message);
        } catch (Exception e) {
            adminView.showErrorMessage("invalid input");

        }

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
        while (!isInteger(code)) {
            if (code.equals("back")) {
                return null;
            }
            System.out.println("Enter course code: ");
            code = sc.nextLine();
        }

        System.out.println("Enter course units: ");
        String units = sc.nextLine();
        while (!isInteger(units)) {
            if (units.equals("back")) {
                return null;
            }
            System.out.println("Enter course units: ");
            units = sc.nextLine();
        }

        System.out.println("Enter course capacity: ");
        String capacity = sc.nextLine();
        while (!isInteger(capacity)) {
            if (capacity.equals("back")) {
                return null;
            }
            System.out.println("Enter course capacity: ");
            capacity = sc.nextLine();
        }

        System.out.println("Enter course type (specialized/general): ");
        String type = sc.nextLine();
        while (!type.equals("specialized") && !type.equals("general")) {
            if (type.equals("back")) {
                return null;
            }
            System.out.println("Enter course type (specialized/general): ");
            type = sc.nextLine();
        }
        System.out.println("Enter course group: ");
        String group = sc.nextLine();
        while (!isInteger(group)) {
            if (group.equals("back")) {
                return null;
            }
            System.out.println("Enter course group: ");
            group = sc.nextLine();
        }

        System.out.println("Enter course teacher: ");
        String teacher = sc.nextLine();
        if (teacher.equals("back")) {

            return null;
        }
        System.out.println("Enter course exam time (27/02/1402:5~7): ");
        String exam = sc.nextLine();
        String examRegex = "^\\d{2}/\\d{2}/\\d{4}:\\d{1,2}~\\d{1,2}$";
        Matcher exMatcher = Pattern.compile(examRegex).matcher(exam);
        while (!exMatcher.matches()) {
            if (exam.equals("back")) {
                return null;
            }
            System.out.println("Enter course exam time (27/02/1402:5~7): ");
            exam = sc.nextLine();
            exMatcher = Pattern.compile(examRegex).matcher(exam);
        }

        System.out.println("Enter course class time (sunday:5~7-monday:3~5): ");
        String time = sc.nextLine();
        String timeRegex = "^(?:[a-zA-Z]+:\\d{1,2}~\\d{1,2}(?:-|$))+";
        Matcher timeMatcher = Pattern.compile(timeRegex).matcher(time);
        while (!timeMatcher.matches()) {
            if (exam.equals("back")) {
                return null;
            }
            System.out.println("Enter course class time (sunday:5~7-monday:3~5): ");
            time = sc.nextLine();
            timeMatcher = Pattern.compile(timeRegex).matcher(time);
        }
        if (time.equals("back")) {

            return null;
        }

        Map<String, String> map = Map.of("name", name, "code", code, "units", units, "capacity", capacity, "type", type,
                "group", group, "teacher", teacher, "exam", exam, "time", time);
        return map;
    }

    public void removeCourse(int id, int college_id) {
        Course course = new CoursesList().findOne(id);
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
        new CoursesList().removeCourse(id);

        CollegesList collegesList = new CollegesList();
        collegesList.findOne(college_id).removeCourse(id);
        collegesList.updateList();
        new CoursesList().updateList();
        studentsList.updateList();
        studentView.showSuccessMessage("course removed successfully");
    }

    public void addCapacity(int id, int college_id) {
        Course course = new CoursesList().findOne(id);
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
        while (!isInteger(capacity)) {
            if (capacity.equals("back")) {
                return;
            }
            capacity = sc.nextLine();
        }

        course.setCapacity(Integer.parseInt(capacity));
        new CoursesList().updateList();

        adminView.showSuccessMessage("capacity added successfully");
    }

    public void addStudent(int id) {
        Course course = new CoursesList().findOne(id);
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
        CoursesList coursesList = new CoursesList();
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
            Course newCourse = coursesList.findOne(id);
            newCourse.addStudent(student.getId());
        }

        coursesList.updateList();
        studentsList.updateList();
        adminView.showSuccessMessage("student added successfully");
    }

    public void removeStudent(int id) {
        Course course = new CoursesList().findOne(id);
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
        CoursesList coursesList = new CoursesList();
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
        adminView.showSuccessMessage("student added successfully");

    }

    public void getStudents(int id) {
        Course course = new CoursesList().findOne(id);
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
