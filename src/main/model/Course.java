package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

// Description of a course with a course ID, total course slots and available course slots
// CITATION: Code for toJson() modified from JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class Course implements Writable {
    private String courseID;
    private Integer totalSlots;
    private Integer slotsAvailable;

    // REQUIRES: id should be of non-zero length, totalSlots and slotsAvailable should be >= 0
    // EFFECTS: creates a new Course object of specified courseID, totalSlots and slotsAvailable
    public Course(String id, Integer totalSlots, Integer slotsAvailable) {
        this.courseID = id;
        this.totalSlots = totalSlots;
        this.slotsAvailable = slotsAvailable;
    }

    // EFFECTS: Converts this object's details into a JSONObject, which is returned
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courseID", courseID);
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return Objects.equals(courseID, course.courseID)
                && Objects.equals(totalSlots, course.totalSlots)
                && Objects.equals(slotsAvailable, course.slotsAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseID, totalSlots, slotsAvailable);
    }

    public String getCourseID() {
        return courseID;
    }

    public Integer getTotalSlots() {
        return totalSlots;
    }

    public Integer getSlotsAvailable() {
        return slotsAvailable;
    }

    public void setSlotsAvailable(Integer slots) {
        this.slotsAvailable = slots;
    }
}
