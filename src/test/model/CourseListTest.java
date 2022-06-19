package model;

import exceptions.CourseNotEnrolledException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseListTest {
    private CourseList courseList;
    private Course cs100;
    private Course cs200;

    @BeforeEach
    void runBefore() {
        courseList = new CourseList();
        cs100 = new Course("cs100", 200, 200);
        cs200 = new Course("cs200", 100, 100);
    }

    @Test
    void testEnrollOneCourse() {
        assertEquals(courseList.length(), 0);
        courseList.enroll(cs100);
        assertEquals(courseList.length(), 1);
        assertEquals(courseList.nthCourse(0), cs100);
    }

    @Test
    void testEnrollMultipleCourses() {
        assertEquals(courseList.length(), 0);
        courseList.enroll(cs100);
        assertEquals(courseList.length(), 1);
        assertEquals(courseList.nthCourse(0), cs100);

        courseList.enroll(cs200);
        assertEquals(courseList.length(), 2);
        assertEquals(courseList.nthCourse(1), cs200);
    }

    @Test
    void testUnenrollOneCourse() {
        try {
            assertEquals(courseList.length(), 0);
            courseList.enroll(cs100);
            assertEquals(courseList.length(), 1);
            assertEquals(courseList.nthCourse(0), cs100);

            courseList.unenroll(cs100);
            assertEquals(courseList.length(), 0);
        } catch (CourseNotEnrolledException e) {
            fail("Exception shouldn't have been thrown!");
        }
    }

    @Test
    void testUnenrollMultipleCourses() {
        try {
            assertEquals(courseList.length(), 0);
            courseList.enroll(cs100);
            assertEquals(courseList.length(), 1);
            assertEquals(courseList.nthCourse(0), cs100);

            courseList.enroll(cs200);
            assertEquals(courseList.length(), 2);
            assertEquals(courseList.nthCourse(1), cs200);

            courseList.unenroll(cs100);
            assertEquals(courseList.length(), 1);

            courseList.unenroll(cs200);
            assertEquals(courseList.length(), 0);
        } catch (CourseNotEnrolledException e) {
            fail("Exception should've been thrown!");
        }
    }

    @Test
    void testUnenrollNotEnrolled() {
        try {
            assertEquals(courseList.length(), 0);
            courseList.enroll(cs100);
            assertEquals(courseList.length(), 1);
            assertEquals(courseList.nthCourse(0), cs100);

            courseList.unenroll(cs200);
            fail("Exception should've been thrown!");
        } catch (CourseNotEnrolledException e) {
            // do nothing
        }
    }

    @Test
    void testDoesNotContain() {
        assertFalse(courseList.contains(cs100));
    }

    @Test
    void testContains() {
        assertEquals(courseList.length(), 0);
        courseList.enroll(cs100);
        assertEquals(courseList.length(), 1);
        assertEquals(courseList.nthCourse(0), cs100);

        courseList.enroll(cs200);
        assertEquals(courseList.length(), 2);
        assertEquals(courseList.nthCourse(1), cs200);

        assertTrue(courseList.contains(cs100));
        assertTrue(courseList.contains(cs200));
    }

    @Test
    void testLengthEmpty() {
        assertEquals(courseList.length(), 0);
    }

    @Test
    void testLengthNonEmpty() {
        assertEquals(courseList.length(), 0);
        courseList.enroll(cs100);
        assertEquals(courseList.length(), 1);
        assertEquals(courseList.nthCourse(0), cs100);

        assertEquals(courseList.length(), 1);
    }

    @Test
    void testReturnNthCourse() {
        assertEquals(courseList.length(), 0);
        courseList.enroll(cs100);
        assertEquals(courseList.length(), 1);
        assertEquals(courseList.nthCourse(0), cs100);

        courseList.enroll(cs200);
        assertEquals(courseList.length(), 2);
        assertEquals(courseList.nthCourse(1), cs200);

        assertEquals(courseList.nthCourse(0), cs100);
        assertEquals(courseList.nthCourse(1), cs200);
    }

}
