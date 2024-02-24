package Models;

import java.util.ArrayList;
import java.util.List;

import Lists.CoursesList;

public class Course extends Model {

    private int id;
    private String name;
    private int units;
    private int college_id;
    public int course_code;
    public String type;
    public int group;
    List<Integer> students;

    public Course(int id, String name, int units, int college_id, int course_code, String type, int group,
            List<Integer> students) {
        this.id = id;
        this.name = name;
        this.units = units;
        this.college_id = college_id;
        this.course_code = course_code;
        this.type = type;
        this.group = group;
        this.students = students;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getCollege_id() {
        return college_id;
    }

    public void setCollege_id(int college_id) {
        this.college_id = college_id;
    }

    public int getCourse_code() {
        return course_code;
    }

    public void setCourse_code(int course_code) {
        this.course_code = course_code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public List<Integer> getStudents() {
        return students;
    }

    public void setStudents(List<Integer> students) {
        this.students = students;
    }

    public void addStudent(int student) {
        students.add(student);
    }

    public void removeStudent(int student) {
        students.remove(student);
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
        return id + "," + name + "," + units + "," + college_id + "," + course_code + "," + type + "," + group + ","
                + studentsString;
    }

    @Override
    public String show() {
        return id + " " + name + " " + units + " " + college_id + " " + course_code + " " + type + " " + group;
    }

}
