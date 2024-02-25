package Controllers;

import Lists.CollegesList;
import Models.Student;
import views.StudentView;

public class CollegeController extends Controller {
    StudentView studentView;
    CollegesList collegesList;

    public CollegeController(Student student) {
        super(student);
        studentView = new StudentView();
        collegesList = new CollegesList();
    }

    public void getColleges() {
        studentView.showColleges(collegesList.getColleges());
    }

    public void getCourses(int id) {
        if (collegesList.findOne(id) == null) {
            studentView.showMessage("no such college exists");
            return;
        }
        studentView.showCourses(collegesList.findOne(id).getCourses());
    }
}
