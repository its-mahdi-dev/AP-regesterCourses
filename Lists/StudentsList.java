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
import java.util.Optional;
import java.util.Scanner;

import Models.Course;
import Models.Student;

public class StudentsList extends BaseList<Student> {
    private List<Student> students = new ArrayList<>();

    public StudentsList() {
        readData();
    }

    public void readData() {
        try (FileInputStream reader = new FileInputStream(new File("p1\\DataBase\\Student.txt"))) {
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                String[] groups = parts[parts.length - 1].split("-");
                List<Integer> studentsInteger = new ArrayList<>();
                for (int i = 0; i < groups.length; i++) {
                    studentsInteger.add(Integer.parseInt(groups[i]));
                }
                students.add(new Student(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]), studentsInteger));
            }
            super.setItems(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStrudents() {
        return students;
    }

    public void addCourse(Student student, int course) {
        for(Student s : students){
            if(s.getStudentId() == student.getStudentId()){
                s.addCourse(course);
            }
        }
    }

    public void updateStudent(Student student) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("p1\\\\DataBase\\\\Student.txt", false))) {
            for (Student s : students) {
                System.out.println(s.toString());
                System.out.println("55 "+s.getCoursesId());
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
