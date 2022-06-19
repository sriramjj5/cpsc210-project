package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SchoolTest {

    School testSchool;
    Course cs100;
    Course cs210;
    Course math100;
    Course bio121;
    Course engl100;
    Course geo150;
    Course astr250;
    Course eco220;
    ArrayList<Course> existingCourses;
    ArrayList<Student> currentStudents;
    Student testStudent;
    CourseList courses;


    @BeforeEach
    public void runBefore() {
        testSchool = new School();
        cs100 = new Course("cs100", 200, 200);
        cs210 = new Course("cs210", 150, 150);
        math100 = new Course("math100", 300, 300);
        bio121 = new Course("bio121", 300, 300);
        engl100 = new Course("engl100", 280, 280);
        geo150 = new Course("geo150", 120, 120);
        astr250 = new Course("astr250", 100, 100);
        eco220 = new Course("eco220", 250, 250);
        existingCourses = new ArrayList<Course>();
        existingCourses.add(cs100);
        existingCourses.add(cs210);
        existingCourses.add(math100);
        existingCourses.add(bio121);
        existingCourses.add(engl100);
        existingCourses.add(geo150);
        existingCourses.add(astr250);
        existingCourses.add(eco220);
        currentStudents = new ArrayList<Student>();
        courses = new CourseList();
        testStudent = new Student("sriramj", "sriramj", "abc", courses);
        currentStudents.add(testStudent);
        testSchool.setCurrentStudents(currentStudents);
    }

    @Test
    public void testConstructorAndInitialization() {
        assertEquals(testSchool.getExistingCourses().size(), 8);
        assertEquals(testSchool.getCurrentStudents().size(), 1);
    }

    // TEST coursestojson and studentstojson

    @Test
    public void testCoursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : existingCourses) {
            jsonArray.put(c.toJson());
        }

        assertEquals(jsonArray.toString(), testSchool.coursesToJson().toString());
    }

    @Test
    public void testStudentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Student s : currentStudents) {
            jsonArray.put(s.toJson());
        }

        assertEquals(jsonArray.toString(), testSchool.studentsToJson().toString());

    }

    @Test
    public void testToJson() {
        JSONObject json = new JSONObject();
        json.put("existingCourses", testSchool.coursesToJson());
        json.put("currentStudents", testSchool.studentsToJson());
        assertEquals(json.toString(), testSchool.toJson().toString());
    }

}
