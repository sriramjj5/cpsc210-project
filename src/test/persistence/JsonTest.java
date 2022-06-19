package persistence;

import model.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// CITATION: Most code modeled from JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonTest {

    protected void checkStudent(String name, String username, String password, CourseList courses, Student st) {

        Integer n = 0;

        ArrayList<Course> studentCourses = st.getCourses().getCourses();

        assertEquals(name, st.getName());
        assertEquals(username, st.getUsername());
        assertEquals(password, st.getPassword());
        assertEquals(studentCourses.size(), courses.getCourses().size());

        for (Course c : courses.getCourses()) {
            assertEquals(studentCourses.get(n).getCourseID(), c.getCourseID());
            n += 1;
        }

    }
}
