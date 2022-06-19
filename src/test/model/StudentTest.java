package model;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// ADD toJson() TEST

class StudentTest {
    private Student emptyStudent;
    private Student detailedStudent;
    private Course cs100;
    private Course cs200;
    private CourseList emptyCourseList;
    private ArrayList<Course> existingCourses;

    @BeforeEach
    void runBefore() {
        cs100 = new Course("cs100", 200, 200);
        cs200 = new Course("cs200", 100, 100);
        emptyCourseList = new CourseList();
        CourseList courses = new CourseList();
        courses.enroll(cs100);
        existingCourses = new ArrayList<Course>();
        existingCourses.add(cs100);
        existingCourses.add(cs200);
        emptyStudent = new Student();
        detailedStudent = new Student("sriramj", "sriramj", "abc", courses);
    }

    @Test
    void testEmptyConstructor() {
        assertNull(emptyStudent.getName());
        assertNull(emptyStudent.getUsername());
        assertNull(emptyStudent.getPassword());
        assertEquals(emptyStudent.getCourses().length(), emptyCourseList.length());
    }

    @Test
    void testDetailedConstructor() {
        assertEquals(detailedStudent.getName(), "sriramj");
        assertEquals(detailedStudent.getUsername(), "sriramj");
        assertEquals(detailedStudent.getPassword(), "abc");
        assertEquals(detailedStudent.getCourses().nthCourse(0), cs100);
        assertEquals(detailedStudent.getCourses().length(), 1);
    }

    @Test
    void testAddOneCourse() {
        assertEquals(cs100.getSlotsAvailable(), 200);
        assertTrue(emptyStudent.addCourse("cs100", existingCourses));
        assertEquals(emptyStudent.getCourses().nthCourse(0), cs100);
        assertEquals(emptyStudent.getCourses().length(), 1);
        assertEquals(cs100.getSlotsAvailable(), 199);
        assertEquals(cs100.getTotalSlots(), 200);
    }

    @Test
    void testAddOneInvalidCourse() {
        assertFalse(emptyStudent.addCourse("cs101", existingCourses));
        assertEquals(emptyStudent.getCourses().length(), 0);
    }

    @Test
    void testAddCourseAlreadyEnrolled() {
        assertEquals(cs100.getSlotsAvailable(), 200);
        assertTrue(emptyStudent.addCourse("cs100", existingCourses));
        assertEquals(emptyStudent.getCourses().nthCourse(0), cs100);
        assertEquals(emptyStudent.getCourses().length(), 1);
        assertEquals(cs100.getSlotsAvailable(), 199);

        assertFalse(emptyStudent.addCourse("cs100", existingCourses));
        assertEquals(emptyStudent.getCourses().nthCourse(0), cs100);
        assertEquals(emptyStudent.getCourses().length(), 1);
        assertEquals(cs100.getSlotsAvailable(), 199);
    }

    @Test
    void testAddFullCourse() {
        cs100.setSlotsAvailable(0);
        assertEquals(cs100.getSlotsAvailable(), 0);
        assertFalse(emptyStudent.addCourse("cs100", existingCourses));
        assertEquals(cs100.getSlotsAvailable(), 0);
    }

    @Test
    void testAddTwoCourses() {
        assertEquals(cs100.getSlotsAvailable(), 200);
        assertTrue(emptyStudent.addCourse("cs100", existingCourses));
        assertEquals(emptyStudent.getCourses().nthCourse(0), cs100);
        assertEquals(emptyStudent.getCourses().length(), 1);
        assertEquals(cs100.getSlotsAvailable(), 199);

        assertEquals(cs200.getSlotsAvailable(), 100);
        assertTrue(emptyStudent.addCourse("cs200", existingCourses));
        assertEquals(emptyStudent.getCourses().nthCourse(1), cs200);
        assertEquals(emptyStudent.getCourses().length(), 2);
        assertEquals(cs200.getSlotsAvailable(), 99);
    }

    @Test
    void testDropOneCourse() {
        assertEquals(cs100.getSlotsAvailable(), 200);
        assertTrue(emptyStudent.addCourse("cs100", existingCourses));
        assertEquals(emptyStudent.getCourses().nthCourse(0), cs100);
        assertEquals(emptyStudent.getCourses().length(), 1);
        assertEquals(cs100.getSlotsAvailable(), 199);

        assertTrue(emptyStudent.dropCourse("cs100"));
        assertEquals(emptyStudent.getCourses().length(), 0);
        assertEquals(cs100.getSlotsAvailable(), 200);
    }

    @Test
    void testDropInvalidCourse() {
        assertEquals(cs100.getSlotsAvailable(), 200);
        assertTrue(emptyStudent.addCourse("cs100", existingCourses));
        assertEquals(emptyStudent.getCourses().nthCourse(0), cs100);
        assertEquals(emptyStudent.getCourses().length(), 1);
        assertEquals(cs100.getSlotsAvailable(), 199);

        assertFalse(emptyStudent.dropCourse("cs101"));
        assertEquals(emptyStudent.getCourses().length(), 1);
    }

    @Test
    void testDropCourseNotEnrolled() {
        assertFalse(emptyStudent.dropCourse("cs100"));
        assertEquals(emptyStudent.getCourses().length(), 0);
        assertEquals(cs100.getSlotsAvailable(), 200);
    }

    @Test
    void testDropTwoCourses() {
        assertEquals(cs100.getSlotsAvailable(), 200);
        assertTrue(emptyStudent.addCourse("cs100", existingCourses));
        assertEquals(emptyStudent.getCourses().nthCourse(0), cs100);
        assertEquals(emptyStudent.getCourses().length(), 1);
        assertEquals(cs100.getSlotsAvailable(), 199);

        assertEquals(cs200.getSlotsAvailable(), 100);
        assertTrue(emptyStudent.addCourse("cs200", existingCourses));
        assertEquals(emptyStudent.getCourses().nthCourse(1), cs200);
        assertEquals(emptyStudent.getCourses().length(), 2);
        assertEquals(cs200.getSlotsAvailable(), 99);

        assertTrue(emptyStudent.dropCourse("cs100"));
        assertEquals(emptyStudent.getCourses().length(), 1);
        assertEquals(cs100.getSlotsAvailable(), 200);

        assertTrue(emptyStudent.dropCourse("cs200"));
        assertEquals(emptyStudent.getCourses().length(), 0);
        assertEquals(cs200.getSlotsAvailable(), 100);
    }

    @Test
    void testCoursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : detailedStudent.getCourses().getCourses()) {
            jsonArray.put(c.toJson());
        }

        assertEquals(jsonArray.toString(), detailedStudent.coursesToJson().toString());
    }

    @Test
    void testToJson() {
        JSONObject json = new JSONObject();
        json.put("name", "sriramj");
        json.put("username", "sriramj");
        json.put("password", "abc");
        json.put("courses", detailedStudent.coursesToJson());

        assertEquals(json.toString(), detailedStudent.toJson().toString());

    }

    @Test
    void testCheckValidUsername() {
        assertTrue(detailedStudent.checkUsername("sriramj"));
    }

    @Test
    void testCheckInvalidUsername() {
        assertFalse(detailedStudent.checkUsername("pizza"));
    }

    @Test
    void testCheckValidPassword() {
        assertTrue(detailedStudent.checkPassword("abc"));
    }

    @Test
    void testCheckInvalidPassword() {
        assertFalse(detailedStudent.checkPassword("pizza"));
    }

    @Test
    void testCheckValidArrayPassword() {
        char[] passArray;
        passArray = new char[]{'a', 'b', 'c'};
        assertTrue(detailedStudent.checkPassword(passArray));
    }

    @Test
    void testCheckInvalidArrayPassword() {
        char[] passArray;
        passArray = new char[]{'p', 'i', 'z', 'z', 'a'};
        assertFalse(detailedStudent.checkPassword(passArray));
    }

}
