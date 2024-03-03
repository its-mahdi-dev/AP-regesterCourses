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
            System.out.println(ColorsView.PURPLE + course.show() + ColorsView.RESET);
        }
        System.out.println();
        System.out.println(ColorsView.CYAN + "- type `1` to get your courses" + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type `0` to back" + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type `add <course-id>` to add a course" + ColorsView.RESET);
    }

    public void showMyCourses(List<Course> courses) {
        System.out.printf("%-4s%-10s%-8s%-12s%-14s%-10s%-10s%-4s%-10s%-4s%-10s%-10s\n", "id", "name", "units",
                "capacity", "college",
                "course_code", "type", "group", "teacher", "students_count", "exam", "timesString");
        for (Course course : courses) {
            System.out.println(ColorsView.PURPLE + course.show() + ColorsView.RESET);
        }
        System.out.println();
        System.out.println(ColorsView.CYAN + "- type `1` to get your courses" + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type `0` to get colleges" + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type `remove <course-id>` to remove a course" + ColorsView.RESET);
    }

    public void showColleges(List<College> colleges) {
        System.out.printf("%-6s%-8s\n", "id", "name");
        for (College college : colleges) {
            System.out.println(ColorsView.PURPLE + college.show() + ColorsView.RESET);
        }
        System.out.println();
        System.out.println(ColorsView.CYAN + "- type `1` to get your courses" + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type `get <college-id>` to see a college courses" + ColorsView.RESET);
    }

    public static void showMessage(String message) {
        System.out.println();
        System.out.println(ColorsView.YELLOW + message + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type `1` to get your courses" + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type 0 to get colleges" + ColorsView.RESET);
    }

    public void showErrorMessage(String message) {
        System.out.println();
        System.out.println(ColorsView.RED + message + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type `1` to get your courses" + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type 0 to get colleges" + ColorsView.RESET);
    }

    public void showSuccessMessage(String message) {
        System.out.println();
        System.out.println(ColorsView.GREEN + message + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type `1` to get your courses" + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type 0 to get colleges" + ColorsView.RESET);
    }
}
