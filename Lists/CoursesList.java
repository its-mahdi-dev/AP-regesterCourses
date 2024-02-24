package Lists;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Models.Course;

public class CoursesList {
    private List<Course> courses = new ArrayList<>();

    public CoursesList() {
        try (FileInputStream reader = new FileInputStream(new File("p1\\DataBase\\Course.txt"))) {
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                String[] groups = parts[parts.length - 1].split("-");
                List<Integer> students = new ArrayList<>();
                for (int i = 0; i < groups.length; i++) {
                    students.add(Integer.parseInt(groups[i]));
                }
                courses.add(new Course(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), parts[5], Integer.parseInt(parts[6]),
                        students));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("p1\\DataBase\\Course.txt", true))) {
            writer.write(course.toString() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
