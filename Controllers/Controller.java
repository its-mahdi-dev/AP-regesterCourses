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
}
