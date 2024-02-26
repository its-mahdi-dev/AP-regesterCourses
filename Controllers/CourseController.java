package Controllers;

import java.util.List;

import Lists.CoursesList;
import Models.Course;
import Models.Student;
import views.StudentView;

public class CourseController extends Controller {
    CoursesList coursesList;
    StudentView studentView;

    public CourseController() {
        super(true);
        coursesList = new CoursesList();
        studentView = new StudentView();
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

    public void addCourse(int id) {
        String message = null;
        Course course = coursesList.findOne(id);
        if (course != null) {
            coursesList.addCourse(course);
            message = "course succesfully added";
        }

        studentView.showMessage(message);
    }
}
