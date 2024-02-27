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
    private final String path = "p1\\DataBase\\Course.txt";

    public CoursesList() {
        super("p1\\DataBase\\Course.txt");
        readData();
    }

    public void readData() {
        courses = new ArrayList<>();
        try (FileInputStream reader = new FileInputStream(new File(path))) {
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Map<String, String> map = convertToStringMap(line);
                courses.add(new Course(Integer.parseInt(map.get("id")), map.get("name"),
                        Integer.parseInt(map.get("units")), Integer.parseInt(map.get("college_id")),
                        Integer.parseInt(map.get("course_code")), map.get("type"), Integer.parseInt(map.get("group")),
                        map.get("teacher"), Integer.parseInt(map.get("capacity")),
                        getIntegers(map.get("students")), getTime(map.get("times")), getTime(map.get("exam"))));
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
    }

    public void removeCourse(int id) {
        courses.removeIf(num -> num.getId() == id);
    }

}
