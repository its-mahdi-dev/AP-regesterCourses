package views;

import java.util.List;

import Models.College;
import Models.Course;
import Models.Student;

public class AdminView {

    public void showColleges(List<College> colleges) {

        System.out.printf("%-6s%-8s\n", "id", "name");
        for (College college : colleges) {
            System.out.println(ColorsView.PURPLE + college.show() + ColorsView.RESET);
        }
        System.out.println();
        System.out.println(ColorsView.CYAN + "- type `get <college-id>` to see a college courses" + ColorsView.RESET);
    }

    public void showCourses(List<Course> courses) {
        System.out.printf("%-4s%-10s%-8s%-12s%-14s%-10s%-10s%-4s%-10s%-4s%-10s%-10s\n", "id", "name", "units",
                "capacity", "college",
                "course_code", "type", "group", "teacher", "students_count", "exam", "timesString");
        for (Course course : courses) {
            System.out.println(ColorsView.PURPLE + course.show() + ColorsView.RESET);
        }
        System.out.println();
        System.out.println(ColorsView.CYAN + "- type `0` to get colleges" + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type `remove <course-id>` to add a course" + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type `add` to add a course" + ColorsView.RESET);
        System.out.println(
                ColorsView.CYAN + "- type `addCapacity <course-id>` to add capacity of a course" + ColorsView.RESET);
        System.out.println(
                ColorsView.CYAN + "- type `addStudents <course-id>` to add students to a course" + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type `removeStudents <course-id>` to remove students from a course"
                + ColorsView.RESET);
        System.out.println(
                ColorsView.CYAN + "- type `getStudents <course-id>` to see a course srudents" + ColorsView.RESET);
    }

    public static void showMessage(String message) {
        System.out.println();
        System.out.println(ColorsView.YELLOW + message + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type 0 to get colleges" + ColorsView.RESET);
    }

    public void showErrorMessage(String message) {
        System.out.println();
        System.out.println(ColorsView.RED + message + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type 0 to get colleges" + ColorsView.RESET);
    }

    public void showSuccessMessage(String message) {
        System.out.println();
        System.out.println(ColorsView.GREEN + message + ColorsView.RESET);
        System.out.println(ColorsView.CYAN + "- type 0 to get colleges" + ColorsView.RESET);
    }

    public void showStudents(List<Student> students) {
        System.out.printf("%-10s%-10s%-8s\n",
                "studentId", "name", "college");
        for (Student student : students) {
            System.out.println(ColorsView.PURPLE + student.show() + ColorsView.RESET);
        }
        System.out.println();
        System.out.println("- type `0` to get colleges");
        System.out.println("- type `addStudents <course-id>` to add students to a course");
        System.out.println("- type `removeStudents <course-id>` to remove students from a course");
    }
}
