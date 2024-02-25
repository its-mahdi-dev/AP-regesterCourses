package Lists;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Models.Course;

public class CoursesList extends BaseList<Course> {
    private List<Course> courses = new ArrayList<>();

    public CoursesList() {
        readData();
    }

    public void readData() {
        courses = new ArrayList<>();
        try (FileInputStream reader = new FileInputStream(new File("p1\\DataBase\\Course.txt"))) {
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                String[] groups = parts[parts.length - 2].split("-");
                String[] times = parts[parts.length - 1].split("-");
                Map<String, Integer[]> time = new HashMap<>();
                for (String t : times) {
                    String[] newTime = t.split(":");
                    String day = newTime[0];
                    String[] hours = newTime[1].split("~");
                    time.put(
                            day, new Integer[] { Integer.parseInt(hours[0]), Integer.parseInt(hours[1]) });
                }

                List<Integer> students = new ArrayList<>();
                for (int i = 0; i < groups.length; i++) {
                    students.add(Integer.parseInt(groups[i]));
                }
                courses.add(new Course(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), parts[5], Integer.parseInt(parts[6]),
                        students, time));
            }
            super.setItems(courses);
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
            // writer.write(course.toString() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
