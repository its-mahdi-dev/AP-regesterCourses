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
            studentsList.updateStudent(super.getStudent());
        } else {
            studentView.showMessage("no such course exists");
        }
    }
}
