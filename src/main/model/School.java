package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a school with a list of available courses and a list of its current students.
// CITATION: Code for toJson(), coursesToJson and studentsToJson() methods modified from JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class School implements Writable {

    Course cs100 = new Course("cs100", 200, 200);
    Course cs210 = new Course("cs210", 150, 150);
    Course math100 = new Course("math100", 300, 300);
    Course bio121 = new Course("bio121", 300, 300);
    Course engl100 = new Course("engl100", 280, 280);
    Course geo150 = new Course("geo150", 120, 120);
    Course astr250 = new Course("astr250", 100, 100);
    Course eco220 = new Course("eco220", 250, 250);

    ArrayList<Course> existingCourses = new ArrayList<Course>();

    ArrayList<Student> currentStudents = new ArrayList<Student>();

    // MODIFIES: this
    // EFFECTS: initializes the school's courses and constructs a new School object
    public School() {
        initializeCourses();
    }

    // MODIFIES: this
    // EFFECTS: initializes a School object's existingCourses by adding courses to it
    public void initializeCourses() {
        existingCourses.add(cs100);
        existingCourses.add(cs210);
        existingCourses.add(math100);
        existingCourses.add(bio121);
        existingCourses.add(engl100);
        existingCourses.add(geo150);
        existingCourses.add(astr250);
        existingCourses.add(eco220);
    }

    // EFFECTS: Converts this object's details into a JSONObject, which is returned
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("existingCourses", coursesToJson());
        json.put("currentStudents", studentsToJson());
        return json;
    }

    // EFFECTS: returns courses in this school as a JSON array
    public JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : existingCourses) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns students in this school as a JSON array
    public JSONArray studentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Student s : currentStudents) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }


    public ArrayList<Student> getCurrentStudents() {
        return currentStudents;
    }

    public void setCurrentStudents(ArrayList<Student> students) {
        this.currentStudents = students;
    }

    public ArrayList<Course> getExistingCourses() {
        return existingCourses;
    }
}
