package persistence;

import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// CITATION: Most code modeled from JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonReaderTest extends JsonTest {

    School currentSchool;
    Course geo150;
    Course astr250;
    Course cs100;
    Course math100;
    CourseList c1;
    CourseList c2;

    @BeforeEach
    void runBefore() {
        currentSchool = new School();
        geo150 = new Course("geo150", 120, 120);
        astr250 = new Course("astr250", 100, 100);
        cs100 = new Course("cs100", 200, 200);
        math100 = new Course("math100", 300, 300);
        c1 = new CourseList();
        c1.enroll(astr250);
        c1.enroll(math100);
        c1.enroll(cs100);
        c2 = new CourseList();
        c2.enroll(astr250);
        c2.enroll(geo150);
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/doesNotExist.json");
        try {
            School s = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testReaderEmptySchool() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySchool.json");
        try {
            School s = reader.read();
            assertEquals(s.getExistingCourses().size(), 8);
            assertEquals(s.getCurrentStudents().size(), 0);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSchool() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSchool.json");
        try {
            School s = reader.read();
            assertEquals(s.getCurrentStudents().size(), 2);
            assertEquals(s.getExistingCourses().size(), 8);
            Student st1 = s.getCurrentStudents().get(0);
            Student st2 = s.getCurrentStudents().get(1);
            checkStudent("sriramj", "sriramj", "abc", c1, st1);
            checkStudent("sriramj2", "sriramj2", "abc2", c2, st2);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}