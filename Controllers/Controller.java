package Controllers;

import Models.Student;

public class Controller {
    private Student student;

    public Controller(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean compareTime(Integer[] time1, Integer[] time2) {
        if ((time1[0] >= time2[0] && time1[0] < time2[1])
                || (time1[1] > time2[0] && time1[1] <= time2[1])
                || (time1[0] <= time2[0] && time1[1] >= time2[1])) {
            return false;
        }
        return true;
    }
}
