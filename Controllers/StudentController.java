package Controllers;

import Lists.CoursesList;
import Lists.StudentsList;
import Models.Course;
import Models.Student;
import views.StudentView;

public class StudentController extends Controller {
    StudentsList studentsList;
    StudentView studentView;

    public StudentController(Student student) {
        super(student);
        System.out.println(student.getName());
        studentsList = new StudentsList();
        studentView = new StudentView();
    }

    public void addCourse(int id) {
        Course course = new CoursesList().findOne(id);
        if (course != null) {
            if (super.getStudent().getCoursesId().contains(id)) {
                studentView.showMessage("course already exists");
                return;
            }
            studentsList.addCourse(super.getStudent(), id);
            studentView.showMessage("course added successfully");
        } else {
            studentView.showMessage("no such course exists");
        }
    }

    public void removeCourse(int id) {
        Course course = new CoursesList().findOne(id);
        if (course != null) {
            if (!super.getStudent().getCoursesId().contains(id)) {
                studentView.showMessage("course not found");
            } else {
                studentsList.removeCourse(super.getStudent(), id);
                studentView.showMessage("course removed successfully");
            }
        } else {
            studentView.showMessage("no such course exists");
        }
    }
}
