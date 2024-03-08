package Models;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class General extends Course {
    public General(int id, String name, int units, int college_id, int course_code, int group,
            String teacher, int capacity,
            List<Integer> students, Map<String, LocalTime[]> times, Map<String, LocalTime[]> exam) {
        super(id, name, units, college_id, course_code, "general", group, teacher, capacity, students, times, exam);
    }
}
