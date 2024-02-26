package Lists;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Models.College;
import Models.Course;

public class CollegesList extends BaseList<College> {
    private List<College> colleges = new ArrayList<>();
    private final String path = "p1\\DataBase\\College.txt";

    public CollegesList() {
        super("p1\\DataBase\\College.txt");
        readData();
    }

    public void readData() {
        try (FileInputStream reader = new FileInputStream(new File(path))) {
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Map<String, String> map = convertToStringMap(line);
                colleges.add(new College(Integer.parseInt(map.get("id")), map.get("name"),
                        getIntegers(map.get("courses")), getIntegers(map.get("students"))));

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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(college.toString() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
