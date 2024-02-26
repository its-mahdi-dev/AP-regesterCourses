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

    public StudentController() {
        super(true);
        studentsList = new StudentsList();
        studentView = new StudentView();
    }

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
            String examDay = course.getExam().keySet().iterator().next();
            System.out.println("eeee" + examDay);
            for (Course c : student.getCourses()) {
                for (Map.Entry<String, Integer[]> entry : c.getTimes().entrySet()) {
                    if (days.contains(entry.getKey())) {
                        Integer[] time1 = times.get(entry.getKey());
                        Integer[] time2 = entry.getValue();
                        if (!compareTime(time1, time2)) {
                            studentView.showMessage("course class time conflict with other courses");
                            return;
                        }
                    }
                }
                if (c.getExam().keySet().iterator().next().equals(examDay)) {
                    Integer[] time1 = course.getExam().get(examDay);
                    Integer[] time2 = c.getExam().get(examDay);
                    if (!compareTime(time1, time2)) {
                        studentView.showMessage("course exam conflict with other courses");
                        return;
                    }
                }
            }

            if (student.getUnitsCount() + course.getUnits() > course.getCapacity()) {
                studentView.showMessage("course capacity exceeded");
            }
            if (course.getType().equals("public")) {
                if (student.getPublicationsCount() + course.getUnits() > 5) {
                    studentView.showMessage("course publication capacity exceeded");
                }
            }
            studentsList.addCourse(student, id);
            CoursesList coursesList = new CoursesList();
            coursesList.findOne(course.getId()).addStudent(student.getId());
            coursesList.updateList();
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
                CoursesList coursesList = new CoursesList();
                coursesList.findOne(course.getId()).removeStudent(getStudent().getId());
                coursesList.updateList();
                studentsList.updateList();
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
