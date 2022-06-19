package model;

import exceptions.CourseNotEnrolledException;

import java.util.ArrayList;

// Class to represent a list of courses

public class CourseList {

    private ArrayList<Course> courses = new ArrayList<Course>();

    // MODIFIES: this
    // EFFECTS: adds course argument to the list of courses that the student is enrolled in
    public void enroll(Course c) {
        this.getCourses().add(c);
    }

    // MODIFIES: this
    // EFFECTS: removes course argument from list of courses that the student is enrolled in, if present
    public void unenroll(Course c) throws CourseNotEnrolledException {
        if (courses.contains(c)) {
            courses.remove(c);
            return;
        }
        throw new CourseNotEnrolledException();
    }

    // EFFECTS: returns true if student is enrolled in the course passed as argument, false otherwise
    public boolean contains(Course c) {
        return courses.contains(c);
    }

    // EFFECTS: returns number of courses the student is enrolled in
    public Integer length() {
        return courses.size();
    }

    // EFFECTS: returns Nth element of the list of courses that the student is enrolled in
    public Course nthCourse(Integer n) {
        return courses.get(n);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

}
