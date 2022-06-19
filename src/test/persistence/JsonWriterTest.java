package persistence;

// CITATION: Most code modeled from JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Course;
import model.CourseList;
import model.School;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    Course geo150;
    Course astr250;
    Course cs100;
    Course math100;
    CourseList c1;
    CourseList c2;
    Student s1;
    Student s2;

    @BeforeEach
    void runBefore() {
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
        s1 = new Student("sriramj", "sriramj", "abc", c1);
        s2 = new Student("sriramj2", "sriramj2", "abc2", c2);
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/doesNotExist.json");
        try {
            School s = new School();
            JsonWriter writer = new JsonWriter("./data/\0illegalName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testWriterEmptySchool() {
        try {
            School s = new School();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySchool.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySchool.json");
            s = reader.read();
            assertEquals(s.getExistingCourses().size(), 8);
            assertEquals(s.getCurrentStudents().size(), 0);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralSchool() {
        try {
            School s = new School();
            s.getCurrentStudents().add(s1);
            s.getCurrentStudents().add(s2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSchool.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSchool.json");
            s = reader.read();
            assertEquals(s.getCurrentStudents().size(), 2);
            assertEquals(s.getExistingCourses().size(), 8);
            Student st1 = s.getCurrentStudents().get(0);
            Student st2 = s.getCurrentStudents().get(1);
            checkStudent("sriramj", "sriramj", "abc", c1, st1);
            checkStudent("sriramj2", "sriramj2", "abc2", c2, st2);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

}
