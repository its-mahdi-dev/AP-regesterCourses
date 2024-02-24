package Lists;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Models.Student;

public class StudentsList {
    private List<Student> students = new ArrayList<>();

    public StudentsList() {
        try (FileInputStream reader = new FileInputStream(new File("p1\\DataBase\\Course.txt"))) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getStrudents() {
        return students;
    }

    public void addCourse(Student student) {
        students.add(student);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("p1\\DataBase\\Student.txt", true))) {
            writer.write(student.toString() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
