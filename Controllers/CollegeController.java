package Controllers;

import Lists.CollegesList;
import Models.Student;
import views.AdminView;
import views.StudentView;

public class CollegeController extends Controller {
    StudentView studentView;
    AdminView adminView;
    CollegesList collegesList;

    public CollegeController() {
        super(true);
        studentView = new StudentView();
        collegesList = new CollegesList();
        adminView = new AdminView();
    }

    public CollegeController(Student student) {
        super(student);
        studentView = new StudentView();
        collegesList = new CollegesList();
        adminView = new AdminView();
    }

    public void getColleges() {
        if (isAdmin())
            adminView.showColleges(new CollegesList().getColleges());
        else
            studentView.showColleges(new CollegesList().getColleges());
    }

    public void getCourses(int id) {
        if (new CollegesList().findOne(id) == null) {
            studentView.showErrorMessage("no such college exists");
            return;
        }
        if (isAdmin()) {
            adminView.showCourses(new CollegesList().findOne(id).getCourses());
        } else
            studentView.showCourses(new CollegesList().findOne(id).getCourses());
    }
}
