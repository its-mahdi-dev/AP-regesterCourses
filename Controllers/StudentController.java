package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        studentsList = new StudentsList();
        studentView = new StudentView();
    }

    public void addCourse(int id) {
        Course course = new CoursesList().findOne(id);
        Student student = studentsList.findOne(super.getStudent().getId());
        if (course != null) {
            if (student.getCoursesId().contains(id)) {
                studentView.showMessage("course already exists");
                return;
            }
            Map<String, Integer[]> times = course.getTimes();
            List<String> days = new ArrayList<>(times.keySet());
            for (Course c : student.getCourses()) {
                for (Map.Entry<String, Integer[]> entry : c.getTimes().entrySet()) {
                    if (days.contains(entry.getKey())) {
                        Integer[] time1 = times.get(entry.getKey());
                        Integer[] time2 = entry.getValue();
                        System.out.println(time1[0] + " " + time1[1] + " -- " + time2[0] + " " + time2[1]);
                        if ((time1[0] >= time2[0] && time1[0] < time2[1])
                                || (time1[1] > time2[0] && time1[1] <= time2[1]) || (time1[0] <= time2[0] && time1[1] >= time2[1])) {
                            studentView.showMessage("course conflict");
                            return;
                        }
                    }
                }
            }
            studentsList.addCourse(student, id);
            studentView.showMessage("course added successfully");
        } else {
            studentView.showMessage("no such course exists");
        }
    }

    public void removeCourse(int id) {
        Course course = new CoursesList().findOne(id);
        if (course != null) {
            if (!studentsList.findOne(super.getStudent().getId()).getCoursesId().contains(id)) {
                studentView.showMessage("course not found");
            } else {
                studentsList.removeCourse(super.getStudent(), id);
                studentsList.updateStudent(getStudent());
                studentView.showMessage("course removed successfully");
            }
        } else {
            studentView.showMessage("no such course exists");
        }
    }

    public void getCourses() {
        studentView.showMyCourses(studentsList.findOne(super.getStudent().getId()).getCourses());
    }
}
