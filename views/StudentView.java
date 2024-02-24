package views;

import java.util.List;

import Models.Course;

public class StudentView {
    public void showCourses(List<Course> courses) {
        System.out.println("id  name   units   college_id   course_code   type   group");
        for (Course course : courses) {
            System.out.println(course.show());
        }
        System.out.println();
        System.out.println("- type 0 to back");
        System.out.println("- type `get <course-id>` to get a course");


    }
}
