package Lists;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import Models.Course;
import Models.Student;

public class StudentsList extends BaseList<Student> {
    private List<Student> students = new ArrayList<>();
    private final String path = "p1\\DataBase\\Student.txt";

    public StudentsList() {
        super("p1\\DataBase\\Student.txt");
        readData();
    }

    public void readData() {
        try (FileInputStream reader = new FileInputStream(new File(path))) {
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Map<String, String> map = convertToStringMap(line);
                System.out.println(map);
                students.add(new Student(Integer.parseInt(map.get("id")), map.get("name"),
                        Integer.parseInt(map.get("studentId")), Integer.parseInt(map.get("collegeId")),
                        getIntegers(map.get("courses"))));
            }
            super.setItems(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addCourse(Student student, int course) {
        for (Student s : students) {
            if (s.getStudentId() == student.getStudentId()) {
                s.addCourse(course);
            }
            System.out.println(s.getCoursesId());
        }

        updateStudent(student);
    }

    public void removeCourse(Student student, int course) {
        for (Student s : students) {
            if (s.getStudentId() == student.getStudentId()) {
                s.removeCourse(course);
            }
        }
        updateStudent(student);
    }

    public void updateStudent(Student student) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
            for (Student s : students) {
                writer.write(s.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Student findByStudentId(int id) {

        for (Student student : students) {
            if (student.getStudentId() == id)
                return student;
        }
        return null;
    }
}
