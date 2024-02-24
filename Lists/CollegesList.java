package Lists;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Models.College;
import Models.Course;

public class CollegesList extends BaseList<College> {
    private List<College> colleges = new ArrayList<>();

    public CollegesList() {
        readData();
    }

    public void readData() {
        try (FileInputStream reader = new FileInputStream(new File("p1\\DataBase\\College.txt"))) {
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                String[] groups = parts[parts.length - 1].split("-");
                String[] groups2 = parts[parts.length - 2].split("-");
                List<Integer> students = new ArrayList<>();
                List<Integer> courses = new ArrayList<>();
                for (int i = 0; i < groups.length; i++) {
                    students.add(Integer.parseInt(groups[i]));
                }
                for (int i = 0; i < groups2.length; i++) {
                    courses.add(Integer.parseInt(groups2[i]));
                }
                colleges.add(new College(Integer.parseInt(parts[0]), parts[1], courses, students));

            }
            super.setItems(colleges);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<College> getColleges() {
        return colleges;
    }

    public void addCourse(College college) {
        colleges.add(college);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("p1\\DataBase\\College.txt", true))) {
            writer.write(college.toString() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
