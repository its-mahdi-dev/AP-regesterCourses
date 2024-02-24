package Controllers;

import java.util.List;

import Lists.CoursesList;
import Models.Course;
import views.StudentView;

public class CourseController extends Controller {
    CoursesList coursesList;
    StudentView studentView;

    public CourseController() {
        coursesList = new CoursesList();
        studentView = new StudentView();
    }

    public CoursesList getCoursesList() {
        return coursesList;
    }

    public void getCourses() {
        studentView.showCourses(coursesList.getAll());
    }
}
