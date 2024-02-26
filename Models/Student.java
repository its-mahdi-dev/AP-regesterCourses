package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Lists.CoursesList;
import Lists.StudentsList;

public class Student extends Model {
    private int id;
    private String name;
    private List<Integer> courses;
    private int studentId;
    private int collegeId;

    public Student(int id, String name, int studentId, int collegeId, List<Integer> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
        this.studentId = studentId;
        this.collegeId = collegeId;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<Integer> getCoursesId() {
        return courses;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourses(List<Integer> courses) {
        this.courses = courses;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public void addCourse(int course) {
        courses.add(course);
    }

    public void removeCourse(int course) {
        courses.removeIf(num -> num == course);
    }

    @Override
    public String toString() {
        String coursesString = "";
        for (int i = 0; i < courses.size(); i++) {
            coursesString += courses.get(i);
            if (i != courses.size() - 1) {
                coursesString += "-";
            }
        }
        Map<String, String> map = Map.of("id", String.valueOf(id), "name", name, "studentId", String.valueOf(studentId),
                "collegeId", String.valueOf(collegeId), "courses", coursesString);
        return map.toString();
    }

    @Override
    public String show() {
        return id + " " + name + " " + studentId + " " + collegeId;
    }

    public List<Course> getCourses() {
        List<Course> newCourses = new ArrayList<>();
        for (int i = 0; i < courses.size(); i++) {
            Course myCourse = new CoursesList().findOne(courses.get(i));
            if (myCourse != null)
                newCourses.add(myCourse);
        }
        return newCourses;
    }

    public int getUnitsCount() {
        int count = 0;
        for (int i = 0; i < courses.size(); i++) {
            Course myCourse = new CoursesList().findOne(courses.get(i));
            if (myCourse != null) {
                count += myCourse.getUnits();
            }
        }
        return count;
    }

    public int getPublicationsCount() {
        int count = 0;
        for (int i = 0; i < courses.size(); i++) {
            Course myCourse = new CoursesList().findOne(courses.get(i));
            if (myCourse != null) {
                if (myCourse.getType().equals("public")) {
                    count += myCourse.getUnits();
                }
            }
        }
        return count;
    }
}
