package ui;

import model.CourseList;
import model.School;
import model.Student;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Stores useful methods relating to GUI (saving school details, adding/removing courses, etc.)

public class SchoolUI {

    private static School currentSchool;

    protected static final String JSON_STORE = "./data/school.json";
    private JsonWriter jsonWriter;
    private static JsonWriter staticJsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: initializes the school's courses and runs the application
    public SchoolUI() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        staticJsonWriter = new JsonWriter(JSON_STORE);
        loadSchool();
        LogInGUI.startup();
    }

    // MODIFIES: this, currentStudent
    // EFFECTS: Tries to student-inputted course to their list of courses. If course is successfully added, return true.
    //          Else, return false.
    public static boolean staticOnInputAdd(Student currentStudent, String courseToAdd) {
        if (currentStudent.addCourse(courseToAdd, currentSchool.getExistingCourses())) {
            staticSaveSchool();
            return true;
        } else {
            staticSaveSchool();
            return false;
        }
    }

    // MODIFIES: this, currentStudent
    // EFFECTS: If course can be dropped from current student's registered courses, drop it and return true. Else,
    //          just return false.
    public static boolean staticOnInputDrop(Student currentStudent, String courseToDrop) {
        if (currentStudent.dropCourse(courseToDrop)) {
            staticSaveSchool();
            return true;
        } else {
            staticSaveSchool();
            return false;
        }
    }

    // EFFECTS: Checks if any account details match a user-inputted set of username and password. If match exists,
    //          return true. Else, return false.
    public static Boolean checkLogin(String username, char[] password) {
        for (Student i : currentSchool.getCurrentStudents()) {
            if (i.checkUsername(username)) {
                if (i.checkPassword(password)) {
                    System.out.println("Welcome, " + i.getName() + "!");
                    LogInGUI.currentStudent = i;
                    return true;
                }
            }
        }
        return false;
    }

    // MODIFIES: currentSchool, LogInGUI.currentStudent
    // EFFECTS: Allows user to create an account with the school (assuming the username they've picked does not
    //          already exist). Saves details once account is created.
    public static Boolean addAccount(String name, String username, String password) {
        for (Student i : currentSchool.getCurrentStudents()) {
            if (i.getUsername().equals(username)) {
                return false;
            }
        }
        CourseList courses = new CourseList();
        Student newStudent = new Student(name, username, password, courses);
        currentSchool.getCurrentStudents().add(newStudent);
        staticSaveSchool();
        LogInGUI.currentStudent = newStudent;
        return true;
    }

    // EFFECTS: saves the school to file
    private void saveSchool() {
        try {
            jsonWriter.open();
            jsonWriter.write(currentSchool);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the school to file
    protected static void staticSaveSchool() {
        try {
            staticJsonWriter.open();
            staticJsonWriter.write(currentSchool);
            staticJsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: loads school from file
    public void loadSchool() {
        try {
            if (jsonReader.readFile(JSON_STORE).equals("{}")) {
                currentSchool = new School();
                saveSchool();
                return;
            }
            currentSchool = jsonReader.read();
            System.out.println("Loaded " + currentSchool.getCurrentStudents() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public static School getCurrentSchool() {
        return currentSchool;
    }
}
