package model;

import exceptions.CourseNotEnrolledException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Arrays;

// Represents a student's account with a given name, username, password and registered courses.
// CITATION: Code for toJson() and coursesToJson() methods modified from JsonSerializationDemo
//           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class Student implements Writable {

    private String name;
    private String username;
    private String password;
    private CourseList courses;

    // EFFECTS: creates a new Student object enrolled in no courses
    public Student() {
        courses = new CourseList();
    }

    // REQUIRES: name, username and password should be of non-zero length
    // EFFECTS: creates a new Student object of specified name, (unique) username, password and courses
    public Student(String name, String username, String password, CourseList courses) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.courses = courses;
    }

    // REQUIRES: specified course id should exist in the school's list of existing courses
    //           id should be of non-zero length
    // MODIFIES: this, potentially a Course (from existingCourses)
    // EFFECTS: adds the specified course to the student's current courses
    public boolean addCourse(String id, ArrayList<Course> existingCourses) {
        for (Course i : existingCourses) {
            if ((i.getCourseID()).equals(id)) {
                if (this.courses.contains(i)) {
                    return false;
                } else {
                    if (i.getSlotsAvailable() == 0) {
                        return false;
                    } else {
                        this.courses.enroll(i);
                        i.setSlotsAvailable(i.getSlotsAvailable() - 1);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // REQUIRES: id should be of non-zero length
    // MODIFIES: this, potentially a Course (from existingCourses)
    // EFFECTS: drops the specified course from the student's current courses
    public boolean dropCourse(String id) {
        try {
            for (Course i : this.getCourses().getCourses()) {
                if ((i.getCourseID()).equals(id)) {
                    this.courses.unenroll(i);
                    i.setSlotsAvailable(i.getSlotsAvailable() + 1);
                    return true;
                } else {
                    continue;
                }
            }
        } catch (CourseNotEnrolledException e) {
            System.out.println("Student is not enrolled in the course!");
        }
        return false;
    }

    // REQUIRES: String user should be of non-zero length
    // EFFECTS: checks if this' (given student object's) username matches String user
    public boolean checkUsername(String user) {
        return this.username.equals(user);
    }

    // REQUIRES: String pass should be of non-zero length
    // EFFECTS: checks if this' (given student object's) password matches String pass
    public boolean checkPassword(String pass) {
        return this.password.equals(pass);
    }

    public boolean checkPassword(char[] pass) {
        char[] passwordArray = password.toCharArray();
        return Arrays.equals(pass, passwordArray);
    }

    // REQUIRES: Student must have a valid name, username, password and course list.
    // EFFECTS: Converts this object's details into a JSONObject, which is returned
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("username", username);
        json.put("password", password);
        json.put("courses", coursesToJson());
        return json;
    }

    // EFFECTS: returns courses that this student is enrolled in as a JSON array
    public JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : courses.getCourses()) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    public CourseList getCourses() {
        return this.courses;
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }


}
