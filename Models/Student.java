package Models;

import java.util.List;

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

    public String getName() {
        return name;
    }

    public List<Integer> getCourses() {
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

    @Override
    public String toString() {
        String coursesString = "";
        for (int i = 0; i < courses.size(); i++) {
            coursesString += courses.get(i);
            if (i != courses.size() - 1) {
                coursesString += "-";
            }
        }
        return id + "," + name + "," + studentId + "," + collegeId + "," + coursesString;
    }

    public String show() {
        return id + " " + name + " " + studentId + " " + collegeId;
    }
}
