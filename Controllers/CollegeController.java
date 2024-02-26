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
    }

    public void getColleges() {
        if (isAdmin())
            studentView.showColleges(collegesList.getColleges());
        else
            adminView.showColleges(collegesList.getColleges());
    }

    public void getCourses(int id) {
        if (collegesList.findOne(id) == null) {
            studentView.showMessage("no such college exists");
            return;
        }
        if (isAdmin()) {
            adminView.showCourses(collegesList.findOne(id).getCourses());
        } else
            studentView.showCourses(collegesList.findOne(id).getCourses());
    }
}
