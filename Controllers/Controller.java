package Controllers;

import java.time.LocalTime;

import Models.Student;

public class Controller {
    private Student student;
    private boolean isAdmin;

    public Controller(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Controller(Student student) {
        this.student = student;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean compareTime(LocalTime[] time1, LocalTime[] time2) {
        if ((time1[0].compareTo(time2[0]) >= 0 && time1[0].compareTo(time2[1]) < 0)
                || (time1[1].compareTo(time2[0]) > 0 && time1[1].compareTo(time2[1]) <= 0)
                || (time1[0].compareTo(time2[0]) <= 0 && time1[1].compareTo(time2[1]) >= 0)) {
            return false;
        }
        return true;
    }
}
