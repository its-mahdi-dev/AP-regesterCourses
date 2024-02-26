package views;

import java.util.List;

import Models.College;
import Models.Course;

public class StudentView {
    public void showCourses(List<Course> courses) {
        System.out.printf("%-4s%-10s%-8s%-12s%-14s%-10s%-10s%-4s%-10s%-4s%-10s%-10s\n", "id", "name", "units",
                "capacity", "college",
                "course_code", "type", "group", "teacher", "students_count", "exam", "timesString");
        for (Course course : courses) {
            System.out.println(course.show());
        }
        System.out.println();
        System.out.println("- type `1` to get your courses");
        System.out.println("- type `0` to back");
        System.out.println("- type `add <course-id>` to add a course");
    }

    public void showMyCourses(List<Course> courses) {
        System.out.printf("%-4s%-10s%-8s%-12s%-14s%-10s%-10s%-4s%-10s%-4s%-10s%-10s\n", "id", "name", "units",
                "capacity", "college",
                "course_code", "type", "group", "teacher", "students_count", "exam", "timesString");
        for (Course course : courses) {
            System.out.println(course.show());
        }
        System.out.println();
        System.out.println("- type `1` to get your courses");
        System.out.println("- type `0` to get colleges");
        System.out.println("- type `remove <course-id>` to remove a course");
    }

    public void showColleges(List<College> colleges) {
        System.out.printf("%-6s%-8s\n", "id", "name");
        for (College college : colleges) {
            System.out.println(college.show());
        }
        System.out.println();
        System.out.println("- type `1` to get your courses");
        System.out.println("- type `get <college-id>` to see a college courses");
    }

    public void showMessage(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println("- type `1` to get your courses");
        System.out.println("- type 0 to get colleges");
    }
}
