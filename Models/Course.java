package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Lists.CollegesList;
import Lists.CoursesList;
import Lists.StudentsList;

public class Course extends Model {

    private int id;
    private String name;
    private int units;
    private int college_id;
    private int course_code;
    private String type;
    private int group;
    private String teacher;
    private int capacity;
    private Map<String, Integer[]> exam;
    private List<Integer> students;
    private Map<String, Integer[]> times;

    public Course(int id, String name, int units, int college_id, int course_code, String type, int group,
            String teacher, int capacity,
            List<Integer> students, Map<String, Integer[]> times, Map<String, Integer[]> exam) {
        this.id = id;
        this.name = name;
        this.units = units;
        this.college_id = college_id;
        this.course_code = course_code;
        this.type = type;
        this.group = group;
        this.teacher = teacher;
        this.capacity = capacity;
        this.exam = exam;
        this.students = students;
        this.times = times;
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

    public Map<String, Integer[]> getTimes() {
        return times;
    }

    public void setTimes(Map<String, Integer[]> times) {
        this.times = times;
    }

    public Map<String, Integer[]> getExam() {
        return exam;
    }
    public void setExam(Map<String, Integer[]> exam) {
        this.exam = exam;
    }
    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Integer> getStudentsId() {
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
        String timesString = "";
        for (Map.Entry<String, Integer[]> entry : times.entrySet()) {
            timesString += entry.getKey() + ":" + entry.getValue()[0] + "~" + entry.getValue()[1] + "-";
        }
        String examString = "";
        for (Map.Entry<String, Integer[]> entry : exam.entrySet()) {
            examString += entry.getKey() + ":" + entry.getValue()[0] + "~" + entry.getValue()[1];
        }

        Map<String, String> map = Map.ofEntries(
                Map.entry("id", String.valueOf(id)),
                Map.entry("name", name),
                Map.entry("units", String.valueOf(units)),
                Map.entry("college_id", String.valueOf(college_id)),
                Map.entry("course_code", String.valueOf(course_code)),
                Map.entry("type", type),
                Map.entry("group", String.valueOf(group)),
                Map.entry("teacher", teacher),
                Map.entry("capacity", String.valueOf(capacity)),
                Map.entry("students", studentsString),
                Map.entry("times", timesString),
                Map.entry("exam", examString));
        return map.toString();
    }

    @Override
    public String show() {
        String timesString = "";
        for (Map.Entry<String, Integer[]> entry : times.entrySet()) {
            timesString += entry.getKey() + ":" + entry.getValue()[0] + "~" + entry.getValue()[1] + " - ";
        }
        String examString = "";
        for (Map.Entry<String, Integer[]> entry : exam.entrySet()) {
            examString += entry.getKey() + ":" + entry.getValue()[0] + "~" + entry.getValue()[1];
        }
        String show = String.format("%-4s%-10s%-8s%-12s%-14s%-10s%-10s%-4s%-10s%-4s%-10s%-10s\n", id, name, units,
                capacity, getCollege().getName(),
                course_code, type, group, teacher, students.size(), examString, timesString);
        return show;
    }

    public List<Student> getStudents() {
        List<Student> newStudents = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            Student myStudent = new StudentsList().findOne(students.get(i));
            if (myStudent != null)
                newStudents.add(myStudent);
        }
        return newStudents;
    }

    public College getCollege() {
        College college = new CollegesList().findOne(college_id);
        return college;
    }

}
