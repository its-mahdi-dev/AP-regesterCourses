package Models;

import java.util.List;

public class College extends Model {
    private int id;
    private String name;
    private List<Integer> courses;
    private List<Integer> students;

    public College(int id, String name, List<Integer> courses, List<Integer> students) {
        this.id = id;
        this.name = name;
        this.courses = courses;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getCourses() {
        return courses;
    }

    public List<Integer> getStudents() {
        return students;
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

    public void setStudents(List<Integer> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        String studentsString = "";
        for (int i = 0; i < students.size(); i++) {
            studentsString += students.get(i);
            if (i != students.size() - 1) {
                studentsString += "-";
            }
        }
        String coursesString = "";
        for (int i = 0; i < courses.size(); i++) {
            coursesString += courses.get(i);
            if (i != courses.size() - 1) {
                coursesString += "-";
            }
        }
        return id + "," + name + "," + coursesString + "," + studentsString;
    }

    @Override
    public String show() {
        return id + "- " + name;
    }

}
