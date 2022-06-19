package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    @Test
    void testToJson() {
        Course cs100 = new Course("cs100", 200, 200);
        JSONObject json = new JSONObject();
        json.put("courseID", "cs100");
        assertEquals(json.toString(), cs100.toJson().toString());
    }

    @Test
    void testEquals() {
        Course cs100 = new Course("cs100", 200, 200);
        Course cs100v2 = new Course("cs100", 200, 200);
        Course cs200 = new Course("cs200", 100, 150);
        assertTrue(cs100.equals(cs100v2));
        assertTrue(cs100.equals(cs100));
        assertFalse(cs100.equals(cs200));
    }

}
